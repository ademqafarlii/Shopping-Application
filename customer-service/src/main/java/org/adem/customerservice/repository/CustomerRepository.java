package org.adem.customerservice.repository;

import org.adem.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> getCustomerByEmail(String email);

    Optional<Customer> getCustomerByUsername(String username);

    Optional<Customer> findByEmail(String email);
}
