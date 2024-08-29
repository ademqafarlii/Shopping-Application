package org.adem.reportservice.dto;

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
public class ReportProductDto {
    private String reportName;
    private String reportDescription;
    private Integer productId;
    private LocalDateTime reportedAt;
    private ReportType reportType;

    private Integer reportID;

}
