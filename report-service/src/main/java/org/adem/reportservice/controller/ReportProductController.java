package org.adem.reportservice.controller;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import jakarta.validation.Valid;
import org.adem.reportservice.dto.ReportProductDto;
import org.adem.reportservice.dto.ReportProductPageResponseDto;
import org.adem.reportservice.service.ReportProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/v1/report/product")
public class ReportProductController {
    private final ReportProductService reportProductService;


    public ReportProductController(ReportProductService reportProductService) {
        this.reportProductService = reportProductService;
    }

    @PostMapping("/report-product-by-id")
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "product",fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "product")
    @Retry(name = "product")
    CompletableFuture<String> reportProductByID(@RequestBody @Valid ReportProductDto reportProductDto){
        return CompletableFuture.supplyAsync(()->reportProductService.reportProductByID(reportProductDto));
    }

    public CompletableFuture<String > fallbackMethod(){
        return CompletableFuture.supplyAsync(()->"Something went wrong, please report later");
    }

    @GetMapping("/get-all-reported-products")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ReportProductPageResponseDto getAllReportedProducts(@RequestParam Integer page, @RequestParam Integer count){
        return reportProductService.getAllReportedProducts(page, count);
    }

    @GetMapping("/get-report-for-product-by-id")
    @ResponseStatus(HttpStatus.ACCEPTED)
    ReportProductDto getReportForProductByID(@RequestParam Integer id){
        return reportProductService.getReportForProductByID(id);
    }

    @DeleteMapping("/delete-report-for-product-by-id")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteReportForProductByID(@RequestParam Integer id){
        reportProductService.deleteReportForProductByID(id);
    }

}
