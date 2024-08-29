package org.adem.reportservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.adem.reportservice.model.ReportProduct;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportProductPageResponseDto {
    List<ReportProductDto> reportList;
    long totalElements;
    int totalPages;
    boolean hasNextPage;
}
