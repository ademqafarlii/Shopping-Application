package org.adem.fraudservice.service.impl;

import org.adem.fraudservice.model.FraudCheckHistory;
import org.adem.fraudservice.repository.FraudCheckHistoryRepository;
import org.adem.fraudservice.service.FraudCheckService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FraudCheckServiceImpl implements FraudCheckService {
    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public FraudCheckServiceImpl(FraudCheckHistoryRepository fraudCheckHistoryRepository) {
        this.fraudCheckHistoryRepository = fraudCheckHistoryRepository;
    }

    @Override
    public boolean isFraudulentCustomer(Integer customerID) {
        fraudCheckHistoryRepository.save(
                FraudCheckHistory.builder()
                        .customerID(customerID)
                        .createdAt(LocalDateTime.now())
                        .isFraudster(false)
                        .build()
        );
        return false;
    }
}
