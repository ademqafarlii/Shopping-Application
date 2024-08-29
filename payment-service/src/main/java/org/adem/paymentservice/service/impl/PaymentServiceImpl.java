package org.adem.paymentservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.adem.paymentservice.dto.PaymentDto;
import org.adem.paymentservice.dto.PaymentPageResponse;
import org.adem.paymentservice.dto.cart.CartDto;
import org.adem.paymentservice.enums.PaymentType;
import org.adem.paymentservice.event.PaymentEvent;
import org.adem.paymentservice.exception.AlreadyPaidException;
import org.adem.paymentservice.exception.PaymentNotFoundException;
import org.adem.paymentservice.exception.ProductNotFoundException;
import org.adem.paymentservice.exception.UnsupportedPaymentTypeException;
import org.adem.paymentservice.mapper.PaymentMapper;
import org.adem.paymentservice.model.Payment;
import org.adem.paymentservice.repository.PaymentRepository;
import org.adem.paymentservice.service.PaymentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper mapper;
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentMapper mapper, WebClient.Builder webClientBuilder, KafkaTemplate<String, PaymentEvent> kafkaTemplate) {
        this.paymentRepository = paymentRepository;
        this.mapper = mapper;
        this.webClientBuilder = webClientBuilder;
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    public void payFromCart(PaymentDto paymentDto) {
        Integer id = paymentDto.getProductId();
        PaymentType paymentType = paymentDto.getPaymentType();
        Optional<Payment> byProductId = paymentRepository.findByProductId(paymentDto.getProductId());
        if (byProductId.isPresent()) {
            throw new AlreadyPaidException("You have already paid");
        }

        if (id == null) {
            throw new ProductNotFoundException("Product not found");
        }
        CartDto cartDto = webClientBuilder.build().get()
                .uri("http://CART-SERVICE/v1/cart/exist-product-in-cart-by-id", uriBuilder -> uriBuilder.queryParam("id", id).build())
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();
        if (cartDto == null || !id.equals(cartDto.getProductID())) {
            throw new ProductNotFoundException("Product not found");
        }


        switch (paymentType) {
            case DEBIT_CARD, CREDIT_CARD:

                paymentRepository.save(mapper.toPayment(paymentDto));
                log.info("paid with {}", paymentType);
                kafkaTemplate.send("notificationTopic",new PaymentEvent(paymentDto.getProductId()));
                break;
            default:
                throw new UnsupportedPaymentTypeException("Unsupported  payment type");
        }
    }

    @Override
    public PaymentPageResponse getAllPayments(Integer page, Integer count) {
        Page<Payment> paymentPage = paymentRepository.findAll(PageRequest.of(page, count));
        if (paymentPage.isEmpty()) {
            throw new PaymentNotFoundException("Payment not found");
        }
        return new PaymentPageResponse(
                paymentPage.getContent().stream().map(mapper::toPaymentDto).collect(Collectors.toList()),
                paymentPage.getTotalPages(),
                paymentPage.getTotalElements(),
                paymentPage.hasNext()
        );
    }

    @Override
    public PaymentDto getPaymentByID(Integer id) {
        return paymentRepository.findById(id)
                .stream()
                .map(mapper::toPaymentDto)
                .findFirst()
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found"));
    }

    @Override
    public List<PaymentDto> getPaymentsByDate(LocalDateTime dateTime) {
        List<PaymentDto> paymentList = paymentRepository.findByPaidAt(dateTime)
                .stream()
                .map(mapper::toPaymentDto)
                .toList();
        if (paymentList.isEmpty()) {
            throw new PaymentNotFoundException("Payment not found");
        }
        return paymentList;
    }
}
