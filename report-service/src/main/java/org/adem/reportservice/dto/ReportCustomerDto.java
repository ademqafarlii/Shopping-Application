package org.adem.reportservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.adem.reportservice.enums.ReportType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportCustomerDto {
    private Integer reportId;
    @NotBlank(message = "Report description cannot be blank")
    private String reportDescription;
    private LocalDateTime reportedAt;
    @NotBlank(message = "Customer name cannot be blank")
    private String reportedCustomer;
    @NotBlank(message ="Report name cannot be blank" )
    private String reportName;
    private ReportType reportType;

}
