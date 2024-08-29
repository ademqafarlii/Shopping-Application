package org.adem.reportservice.service.impl;

import org.adem.reportservice.dto.ReportProductDto;
import org.adem.reportservice.dto.ReportProductPageResponseDto;
import org.adem.reportservice.dto.customer.CustomerRegistrationRequestDto;
import org.adem.reportservice.dto.product.ProductRequest;
import org.adem.reportservice.exception.*;
import org.adem.reportservice.mapper.ReportProductMapper;
import org.adem.reportservice.model.ReportProduct;
import org.adem.reportservice.repository.ReportProductRepository;
import org.adem.reportservice.service.ReportProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;
import java.util.stream.Collectors;

import static org.adem.reportservice.enums.ReportType.*;

@Service
public class ReportProductServiceImpl implements ReportProductService {
    private final ReportProductRepository reportProductRepository;
    private final WebClient.Builder webClientBuilder;
    private final ReportProductMapper reportProductMapper;

    public ReportProductServiceImpl(ReportProductRepository reportProductRepository, WebClient.Builder webClientBuilder, ReportProductMapper reportProductMapper) {
        this.reportProductRepository = reportProductRepository;
        this.webClientBuilder = webClientBuilder;
        this.reportProductMapper = reportProductMapper;
    }

    @Override
    public String reportProductByID(ReportProductDto reportProductDto) {

        switch (reportProductDto.getReportType()) {
            case FAKE, SPAM, FRAUD, INAPPROPRIATE, VIOLENCE, HARASSMENT, PHISHING, ILLEGAL_CONTENT, MISLEADING_INFORMATION, PROFANITY:
                Integer id = reportProductDto.getProductId();
                ProductRequest existProduct = webClientBuilder.build().get()
                        .uri("http://PRODUCT/v1/product/exist-product-by-product-id", uriBuilder -> uriBuilder.queryParam("id", id).build())
                        .retrieve()
                        .bodyToMono(ProductRequest.class)
                        .block();

                if (existProduct == null || !id.equals(existProduct.getId())) {
                    throw new ProductNotFoundException("Product not found");
                }
                Integer countOfReport = reportProductRepository.countByProductId(id);

                if (countOfReport > 3) {
                    throw new ReportLimitExceedException("Report limit exceed, you can report a product only 3 times");
                }

                reportProductRepository.save(reportProductMapper.toReportProduct(reportProductDto));
                return "Product reported";

            default:
                throw new ReportTypeNotFoundException("Report type not found");

        }


    }

    @Override
    public ReportProductDto getReportForProductByID(Integer id) {
        return reportProductRepository.findById(id)
                .stream()
                .map(reportProductMapper::toReportProductDto)
                .findFirst()
                .orElseThrow(() -> new ReportNotFoundException("Report not found"));
    }

    @Override
    public ReportProductPageResponseDto getAllReportedProducts(Integer page, Integer count) {
        Page<ReportProduct> reportProducts = reportProductRepository.findAll(PageRequest.of(page, count));
        if (reportProducts.isEmpty()) {
            throw new ReportNotFoundException("Report not found");
        }
        return new ReportProductPageResponseDto(
                reportProducts.getContent().stream().map(reportProductMapper::toReportProductDto).collect(Collectors.toList()),
                reportProducts.getTotalElements(),
                reportProducts.getTotalPages(),
                reportProducts.hasNext()
        );
    }

    @Override
    public void deleteReportForProductByID(Integer id) {
        Optional<ReportProduct> reportProduct = reportProductRepository.findById(id);
        if (reportProduct.isEmpty()) {
            throw new ReportNotFoundException("Report not found");
        }
        reportProductRepository.deleteById(id);
    }
}
