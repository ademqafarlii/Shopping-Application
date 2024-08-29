package org.adem.paymentservice.controller;

import jakarta.validation.Valid;
import org.adem.paymentservice.dto.PaymentDto;
import org.adem.paymentservice.dto.PaymentPageResponse;
import org.adem.paymentservice.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/payment")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @PostMapping("/pay-from-cart")
    @ResponseStatus(HttpStatus.CREATED)
    void payFromCart(@RequestBody @Valid PaymentDto paymentDto) {
        paymentService.payFromCart(paymentDto);
    }

    @GetMapping("/get-all-payments")
    @ResponseStatus(HttpStatus.OK)
    PaymentPageResponse getAllPayments(@RequestParam Integer page, @RequestParam Integer count) {
        return paymentService.getAllPayments(page, count);
    }

    @GetMapping("/get-payment-by-id")
    @ResponseStatus(HttpStatus.OK)
    PaymentDto getPaymentByID(@RequestParam Integer id) {
        return paymentService.getPaymentByID(id);
    }

    @GetMapping("/get-payments-by-date")
    @ResponseStatus(HttpStatus.OK)
    List<PaymentDto> getPaymentsByDate(@RequestParam LocalDateTime dateTime) {
        return paymentService.getPaymentsByDate(dateTime);
    }
}
