package org.adem.paymentservice.mapper;

import org.adem.paymentservice.dto.PaymentDto;
import org.adem.paymentservice.exception.PaymentNotFoundException;
import org.adem.paymentservice.model.Payment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentMapper {

    public Payment toPayment(PaymentDto paymentDto){
        if (paymentDto==null){
            throw new PaymentNotFoundException("Payment not found");
        }
        return Payment.builder()
                .cvv(paymentDto.getCvv())
                .productId(paymentDto.getProductId())
                .paymentType(paymentDto.getPaymentType())
                .otp(paymentDto.getOtp())
                .cardNumber(paymentDto.getCardNumber())
                .expirationDate(paymentDto.getExpirationDate())
                .paidAt(LocalDateTime.now())
                .build();
    }

    public PaymentDto toPaymentDto(Payment payment){
        if (payment==null){
            throw new PaymentNotFoundException("Payment not found");
        }

        return PaymentDto.builder()
                .productId(payment.getProductId())
                .cvv(payment.getCvv())
                .paymentType(payment.getPaymentType())
                .otp(payment.getOtp())
                .cardNumber(payment.getCardNumber())
                .expirationDate(payment.getExpirationDate())
                .paidAt(payment.getPaidAt())
                .build();
    }
}
