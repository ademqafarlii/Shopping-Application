package org.adem.cartservice.dto.product;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    private Integer id;
    @NotBlank(message = "Product name cannot be blank")
    private String name;
    private String description;
    @NotNull(message = "Product price cannot be null")
    private BigDecimal price;
}
