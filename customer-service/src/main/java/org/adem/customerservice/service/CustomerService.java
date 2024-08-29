package org.adem.customerservice.service;

import org.adem.customerservice.dto.CustomerRegistrationRequestDto;
import org.adem.customerservice.dto.auth.AuthenticationRequest;
import org.adem.customerservice.dto.auth.AuthenticationResponse;
import org.adem.customerservice.dto.auth.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface CustomerService {

    AuthenticationResponse register(RegisterRequest registerRequest);

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

    CustomerRegistrationRequestDto existUser(String username);

    void updateCredentialsByUsername(
            CustomerRegistrationRequestDto customerRegistrationRequestDto, String username);
}
