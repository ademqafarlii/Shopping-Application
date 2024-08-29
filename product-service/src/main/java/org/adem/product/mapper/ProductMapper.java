package org.adem.product.mapper;

import org.adem.product.dto.ProductRequest;
import org.adem.product.dto.ProductResponse;
import org.adem.product.exception.ProductNotFoundException;
import org.adem.product.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(ProductRequest productRequest){
        if (productRequest==null){
            throw new ProductNotFoundException("Product not found");
        }

        return Product.builder()
                .price(productRequest.getPrice())
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .build();
    }

    public ProductResponse toProductResponse(Product product){
        if (product==null){
            throw new ProductNotFoundException("Product not found");
        }
        return ProductResponse.builder()
                .id(product.getId())
                .price(product.getPrice())
                .description(product.getDescription())
                .name(product.getName())
                .build();
    }
}
