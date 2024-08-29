package org.adem.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.adem.paymentservice.enums.PaymentType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDto {
    private Integer productId;
    private String cardNumber;
    private String cvv;
    private String expirationDate;
    private LocalDateTime paidAt;
    private PaymentType paymentType;
    private String otp;
}
