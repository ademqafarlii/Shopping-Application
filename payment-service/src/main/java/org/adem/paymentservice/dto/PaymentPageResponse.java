package org.adem.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PaymentPageResponse {
    private List<PaymentDto> paymentList;
    private Integer totalPages;
    private long totalElements;
    private Boolean hasNext;
}
