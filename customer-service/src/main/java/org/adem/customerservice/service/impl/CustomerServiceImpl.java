package org.adem.customerservice.service.impl;

import lombok.SneakyThrows;
import org.adem.customerservice.dto.CustomerRegistrationRequestDto;
import org.adem.customerservice.dto.auth.AuthenticationRequest;
import org.adem.customerservice.dto.auth.AuthenticationResponse;
import org.adem.customerservice.dto.auth.RegisterRequest;
import org.adem.customerservice.dto.fraud.FraudCheckResponse;
import org.adem.customerservice.enums.Role;
import org.adem.customerservice.exception.*;
import org.adem.customerservice.mapper.CustomerMapper;
import org.adem.customerservice.model.Customer;
import org.adem.customerservice.repository.CustomerRepository;
import org.adem.customerservice.security.JWTService;
import org.adem.customerservice.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final RestTemplate restTemplate;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, RestTemplate restTemplate, PasswordEncoder passwordEncoder, JWTService jwtService, AuthenticationManager authenticationManager) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.restTemplate = restTemplate;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var customer = Customer.builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        var jwtToken = jwtService.generateToken(customer);
        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD-SERVICE/v1/fraud-check/check-fraud-or-not/{customerID}",
                FraudCheckResponse.class,
                customer.getId()
        );
        if (fraudCheckResponse.getIsFraudster()) {
            throw new FraudulentCustomerException("You are fraudster");
        }

        customerRepository.save(customer);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        var user = customerRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }




    @Override
    public void updateCredentialsByUsername(CustomerRegistrationRequestDto customerRegistrationRequestDto, String username) {
        Customer existingCustomer = customerRepository.getCustomerByUsername(username)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
        updateCustomerData(existingCustomer, customerRegistrationRequestDto);
        customerRepository.save(existingCustomer);
    }

    private void updateCustomerData(Customer customer1, CustomerRegistrationRequestDto customerRegistrationRequestDto) {
        if (!customerRegistrationRequestDto.getUsername().equals(customer1.getUsername())) {
            Optional<Customer> customerWithNewUserName = customerRepository.getCustomerByUsername(customerRegistrationRequestDto.getUsername());
            if (customerWithNewUserName.isPresent()) {
                throw new UsernameHasAlreadyBeenTaken("Username has already been taken");
            }
        }
        if (!customerRegistrationRequestDto.getEmail().equals(customer1.getEmail())) {
            Optional<Customer> customerWithNewEmail = customerRepository.getCustomerByEmail(customerRegistrationRequestDto.getEmail());
            if (customerWithNewEmail.isPresent()) {
                throw new EmailHasAlreadyBeenTakenException("Email has already been taken");
            }
        }

        customer1.setFirstname(customerRegistrationRequestDto.getFirstname());
        customer1.setLastname(customerRegistrationRequestDto.getLastname());
        customer1.setUsername(customerRegistrationRequestDto.getUsername());
        customer1.setEmail(customerRegistrationRequestDto.getEmail());
        customer1.setPassword(customerRegistrationRequestDto.getPassword());
    }


    @Override
    @Transactional(readOnly = true)
    @SneakyThrows
    public CustomerRegistrationRequestDto existUser(String username) {
        return customerRepository.getCustomerByUsername(username)
                .map(customerMapper::toCustomerRegistrationRequestDto)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
    }
}
