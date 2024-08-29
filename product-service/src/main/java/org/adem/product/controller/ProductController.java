package org.adem.product.controller;

import jakarta.validation.Valid;
import org.adem.product.dto.ProductPageResponse;
import org.adem.product.dto.ProductRequest;
import org.adem.product.dto.ProductResponse;
import org.adem.product.service.ProductService;
import org.adem.product.service.impl.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }


    @PostMapping("/create-product")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody @Valid ProductRequest productRequest){
        productService.createProduct(productRequest);
    }

    @GetMapping("/get-all-products")
    @ResponseStatus(HttpStatus.OK)
    public ProductPageResponse getAllProducts(@RequestParam Integer page, @RequestParam Integer count){
        return productService.getAllProducts(page,count);
    }

    @GetMapping("/get-product-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProductById(@PathVariable Integer id){
        return productService.getProductById(id);
    }

    @PatchMapping("/update-product-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProductByID(@RequestBody @Valid ProductRequest productRequest,@PathVariable Integer id){
        productService.updateProductById(productRequest, id);
    }

    @DeleteMapping("/delete-product-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductById(@PathVariable Integer id){
        productService.deleteProductById(id);
    }


    @GetMapping("/exist-product-by-product-id")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse existProductByProductID(@RequestParam Integer id){
        return productService.existProductByProductID(id);
    }


}
