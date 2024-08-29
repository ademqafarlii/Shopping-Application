package org.adem.reportservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import jakarta.validation.Valid;
import org.adem.reportservice.dto.ReportCustomerPageResponse;
import org.adem.reportservice.dto.ReportCustomerDto;
import org.adem.reportservice.service.ReportCustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/v1/report/customer")
public class ReportCustomerController {
    private final ReportCustomerService reportCustomerService;

    public ReportCustomerController(ReportCustomerService reportCustomerService) {
        this.reportCustomerService = reportCustomerService;
    }

    @Retry(name = "customer")
    @TimeLimiter(name = "customer")
    @CircuitBreaker(name = "customer",fallbackMethod = "fallbackMethod")
    @PostMapping("/report-customer-by-customer-name")
    @ResponseStatus(HttpStatus.CREATED)
    CompletableFuture<String> reportCustomer(@RequestBody @Valid ReportCustomerDto reportCustomerDto) {
       return CompletableFuture.supplyAsync(()->reportCustomerService.reportCustomerByName(reportCustomerDto));
    }

    public CompletableFuture<String> fallbackMethod(ReportCustomerDto reportCustomerDto){
        return CompletableFuture.supplyAsync(()->"Something went wrong, please report after");
    }

    @GetMapping("/get-report-for-customer-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    ReportCustomerDto getReportForCustomerByID(@PathVariable Integer id) {
        return reportCustomerService.getReportForCustomerByID(id);
    }

    @GetMapping("/get-all-reported-customers")
    @ResponseStatus(HttpStatus.OK)
    ReportCustomerPageResponse getAllReportedCustomers(@RequestParam Integer page, @RequestParam Integer count) {
        return reportCustomerService.getAllReportedCustomers(page,count);
    }

    @DeleteMapping("/delete-report-for-customer-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteReportForCustomerByID(@PathVariable Integer id) {
        reportCustomerService.deleteReportForCustomerByID(id);
    }

}
