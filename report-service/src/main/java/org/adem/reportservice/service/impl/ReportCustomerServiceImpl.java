package org.adem.reportservice.service.impl;

import org.adem.reportservice.dto.ReportCustomerPageResponse;
import org.adem.reportservice.dto.ReportCustomerDto;
import org.adem.reportservice.dto.customer.CustomerRegistrationRequestDto;
import org.adem.reportservice.exception.CustomerNotFoundException;
import org.adem.reportservice.exception.ReportLimitExceedException;
import org.adem.reportservice.exception.ReportNotFoundException;
import org.adem.reportservice.exception.ReportTypeNotFoundException;
import org.adem.reportservice.mapper.ReportCustomerMapper;
import org.adem.reportservice.model.ReportCustomer;
import org.adem.reportservice.repository.ReportCustomerRepository;
import org.adem.reportservice.service.ReportCustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportCustomerServiceImpl implements ReportCustomerService {

    private final ReportCustomerRepository reportCustomerRepository;
    private final ReportCustomerMapper reportCustomerMapper;
    private final WebClient.Builder webClientBuilder;

    public ReportCustomerServiceImpl(ReportCustomerRepository reportCustomerRepository, ReportCustomerMapper reportCustomerMapper, WebClient.Builder webClientBuilder) {
        this.reportCustomerRepository = reportCustomerRepository;
        this.reportCustomerMapper = reportCustomerMapper;
        this.webClientBuilder = webClientBuilder;
    }


    @Override
    public String reportCustomerByName(ReportCustomerDto reportCustomerDto) {
        switch (reportCustomerDto.getReportType()) {
            case FAKE, SPAM, FRAUD, INAPPROPRIATE, VIOLENCE, HARASSMENT, PHISHING, ILLEGAL_CONTENT, MISLEADING_INFORMATION, PROFANITY:
                String username = reportCustomerDto.getReportedCustomer();
                CustomerRegistrationRequestDto customerDto = webClientBuilder.build().get()
                        .uri("http://CUSTOMER-SERVICE/v1/customer/exist-user", uriBuilder -> uriBuilder.queryParam("username", username).build())
                        .retrieve()
                        .bodyToMono(CustomerRegistrationRequestDto.class)
                        .block();

                if (customerDto == null || !username.equals(customerDto.getUsername())) {
                    throw new CustomerNotFoundException("Customer not found");
                }

                Integer countOfReport = reportCustomerRepository.countByReportedCustomer(username);

                if (countOfReport >= 3) {
                    throw new ReportLimitExceedException("Report limit exceed, you can report an account only 3 times");
                }

                reportCustomerRepository.save(reportCustomerMapper.toReportModel(reportCustomerDto));
                return "Customer reported";

            default:
                throw new ReportTypeNotFoundException("Report type not found");
        }
    }


    @Override
    public ReportCustomerDto getReportForCustomerByID(Integer id) {
        return reportCustomerRepository.findById(id)
                .stream()
                .map(reportCustomerMapper::toReportDto)
                .findFirst()
                .orElseThrow(() -> new ReportNotFoundException("Report not found"));
    }

    @Override
    public ReportCustomerPageResponse getAllReportedCustomers(Integer page, Integer count) {
        Page<ReportCustomer> reportPage = reportCustomerRepository.findAll(PageRequest.of(page, count));
        if (reportPage.isEmpty()) {
            throw new ReportNotFoundException("Report not found");
        }
        return new ReportCustomerPageResponse(
                reportPage.getContent().stream().map(reportCustomerMapper::toReportDto).collect(Collectors.toList()),
                reportPage.getTotalElements(),
                reportPage.getTotalPages(),
                reportPage.hasNext()
        );
    }

    @Override
    public void deleteReportForCustomerByID(Integer id) {
        Optional<ReportCustomer> report = reportCustomerRepository.findById(id);
        if (report.isEmpty()) {
            throw new ReportNotFoundException("Report not found");
        }
        reportCustomerRepository.deleteById(id);
    }


}
