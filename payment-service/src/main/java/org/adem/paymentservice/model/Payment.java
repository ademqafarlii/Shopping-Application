package org.adem.paymentservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.adem.paymentservice.enums.PaymentType;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cardNumber;
    private String cvv;
    private String expirationDate;
    private LocalDateTime paidAt;
    private Integer productId;

    @Enumerated
    private PaymentType paymentType;

    private String otp;
}
