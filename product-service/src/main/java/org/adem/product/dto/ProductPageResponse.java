package org.adem.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPageResponse {
    private List<ProductResponse> productResponseList;
    private int totalPages;
    private long totalElements;
    private boolean hasNextPage;
}
