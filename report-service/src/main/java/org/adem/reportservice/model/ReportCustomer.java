package org.adem.reportservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.adem.reportservice.enums.ReportType;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReportCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String reportName;
    private String reportDescription;
    private LocalDateTime reportedAt;
    private String reportedCustomer;
    private ReportType reportType;

}
