package org.adem.customerservice.contoller;

import lombok.extern.slf4j.Slf4j;
import org.adem.customerservice.dto.CustomerRegistrationRequestDto;
import org.adem.customerservice.dto.auth.AuthenticationRequest;
import org.adem.customerservice.dto.auth.AuthenticationResponse;
import org.adem.customerservice.dto.auth.RegisterRequest;
import org.adem.customerservice.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/customer")
@Slf4j
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthenticationResponse register(@RequestBody @Valid RegisterRequest registerRequest) {
        return customerService.register(registerRequest);
    }


    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        return customerService.authenticate(authenticationRequest);
    }


    @GetMapping("/exist-user")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CustomerRegistrationRequestDto existUser(@RequestParam String username) {
        return customerService.existUser(username);
    }


    @PatchMapping("/update-customer-by-username")
    @ResponseStatus(HttpStatus.OK)
    public void updateCredentialsByUsername(
            @RequestBody @Valid CustomerRegistrationRequestDto customerRegistrationRequestDto
            , @RequestParam String username) {
        customerService.updateCredentialsByUsername(customerRegistrationRequestDto, username);
    }

}
