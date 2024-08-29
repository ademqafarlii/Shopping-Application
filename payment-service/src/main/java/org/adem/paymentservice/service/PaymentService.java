package org.adem.paymentservice.service;

import org.adem.paymentservice.dto.PaymentDto;
import org.adem.paymentservice.dto.PaymentPageResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentService {

    void payFromCart(PaymentDto paymentDto);
    PaymentPageResponse getAllPayments(Integer page,Integer count);
    PaymentDto getPaymentByID(Integer id);
    List<PaymentDto> getPaymentsByDate(LocalDateTime dateTime);


}
