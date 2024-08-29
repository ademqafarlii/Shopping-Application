package org.adem.reportservice.mapper;

import org.adem.reportservice.dto.ReportProductDto;
import org.adem.reportservice.exception.ReportNotFoundException;
import org.adem.reportservice.model.ReportProduct;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReportProductMapper {

    public ReportProduct toReportProduct(ReportProductDto reportProductDto){
        if (reportProductDto==null){
            throw new ReportNotFoundException("Report not found");
        }
        return ReportProduct.builder()
                .reportedAt(LocalDateTime.now())
                .productId(reportProductDto.getProductId())
                .reportName(reportProductDto.getReportName())
                .reportDescription(reportProductDto.getReportDescription())
                .reportType(reportProductDto.getReportType())
                .build();
    }

    public ReportProductDto toReportProductDto(ReportProduct reportProduct){
        if (reportProduct==null){
            throw new ReportNotFoundException("Report not found");
        }
        return ReportProductDto.builder()
                .productId(reportProduct.getProductId())
                .reportDescription(reportProduct.getReportDescription())
                .reportedAt(reportProduct.getReportedAt())
                .reportName(reportProduct.getReportName())
                .reportType(reportProduct.getReportType())
                .reportID(reportProduct.getId())
                .build();
    }
}
