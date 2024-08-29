package org.adem.customerservice.mapper;

import org.adem.customerservice.dto.CustomerRegistrationRequestDto;
import org.adem.customerservice.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomerModel(CustomerRegistrationRequestDto customerRegistrationRequestDto){
        return Customer.builder()
                .email(customerRegistrationRequestDto.getEmail())
                .firstname(customerRegistrationRequestDto.getFirstname())
                .lastname(customerRegistrationRequestDto.getLastname())
                .username(customerRegistrationRequestDto.getUsername())
                .password(customerRegistrationRequestDto.getPassword())
                .build();
    }

    public CustomerRegistrationRequestDto toCustomerRegistrationRequestDto(Customer customer){
        return CustomerRegistrationRequestDto.builder()
                .email(customer.getEmail())
                .firstname(customer.getFirstname())
                .lastname(customer.getLastname())
                .username(customer.getUsername())
                .password(customer.getPassword())
                .build();
    }
}
