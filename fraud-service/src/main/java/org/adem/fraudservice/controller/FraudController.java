package org.adem.fraudservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.adem.fraudservice.dto.FraudCheckResponse;
import org.adem.fraudservice.service.FraudCheckService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/fraud-check")
@Slf4j
public class FraudController {
    private final FraudCheckService fraudCheckService;

    public FraudController(FraudCheckService fraudCheckService) {
        this.fraudCheckService = fraudCheckService;
    }

    @GetMapping("/check-fraud-or-not/{customerID}")
    public FraudCheckResponse isFraudster(@PathVariable Integer customerID){
        boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerID);
        log.info("Fraud check request for customer {}",customerID);
        return new FraudCheckResponse(isFraudulentCustomer);
    }
}
