package org.adem.product.service;

import org.adem.product.dto.ProductPageResponse;
import org.adem.product.dto.ProductRequest;
import org.adem.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    void createProduct(ProductRequest productRequest);

    ProductPageResponse getAllProducts(Integer page, Integer count);

    ProductResponse getProductById(Integer id);

    void updateProductById(ProductRequest productRequest, Integer id);

    void deleteProductById(Integer id);

    ProductResponse existProductByProductID(Integer id);
}
