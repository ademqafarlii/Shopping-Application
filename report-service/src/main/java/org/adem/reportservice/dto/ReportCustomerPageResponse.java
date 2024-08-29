package org.adem.reportservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ReportCustomerPageResponse {
    List<ReportCustomerDto> reportList;
    long totalElements;
    int totalPages;
    boolean hasNextPage;
}
