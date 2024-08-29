package org.adem.paymentservice.repository;

import org.adem.paymentservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {

    List<Payment> findByPaidAt(LocalDateTime localDateTime);

    Optional<Payment> findByProductId(Integer id);
}
