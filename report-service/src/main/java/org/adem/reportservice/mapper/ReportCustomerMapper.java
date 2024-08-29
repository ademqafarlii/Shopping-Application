package org.adem.reportservice.mapper;

import org.adem.reportservice.dto.ReportCustomerDto;
import org.adem.reportservice.exception.ReportNotFoundException;
import org.adem.reportservice.model.ReportCustomer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReportCustomerMapper {

    public ReportCustomer toReportModel(ReportCustomerDto reportCustomerDto){
        if (reportCustomerDto ==null){
            throw new ReportNotFoundException("Report not found");
        }
        return ReportCustomer.builder()
                .reportedAt(LocalDateTime.now())
                .reportName(reportCustomerDto.getReportName())
                .reportDescription(reportCustomerDto.getReportDescription())
                .reportedCustomer(reportCustomerDto.getReportedCustomer())
                .reportType(reportCustomerDto.getReportType())
                .build();
    }

    public ReportCustomerDto toReportDto(ReportCustomer reportCustomer){
        if (reportCustomer ==null){
            throw new ReportNotFoundException("Report not found");
        }
        return ReportCustomerDto.builder()
                .reportId(reportCustomer.getId())
                .reportedAt(reportCustomer.getReportedAt())
                .reportName(reportCustomer.getReportName())
                .reportType(reportCustomer.getReportType())
                .reportDescription(reportCustomer.getReportDescription())
                .reportedCustomer(reportCustomer.getReportedCustomer())
                .build();
    }


}
