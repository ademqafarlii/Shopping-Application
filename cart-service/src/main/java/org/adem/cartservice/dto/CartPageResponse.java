package org.adem.cartservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartPageResponse {
    List<CartDto> followedCustomersList;
    long totalElements;
    int totalPages;
    boolean hasNextPage;
}
