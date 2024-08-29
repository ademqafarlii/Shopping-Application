package org.adem.product.service.impl;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.adem.product.dto.ProductPageResponse;
import org.adem.product.dto.ProductRequest;
import org.adem.product.dto.ProductResponse;
import org.adem.product.exception.ProductNotFoundException;
import org.adem.product.mapper.ProductMapper;
import org.adem.product.model.Product;
import org.adem.product.repository.ProductRepository;
import org.adem.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public void createProduct(ProductRequest productRequest){

        productRepository.save(productMapper.toProduct(productRequest));
        log.info("Product {} is saved",productRequest.getName());
    }

    @Override
    public ProductPageResponse getAllProducts(Integer page, Integer count) {
        Page<Product> productPage = productRepository.findAll(PageRequest.of(page,count));
        if (productPage.isEmpty()){
            throw new ProductNotFoundException("Product not found");
        }
        return new ProductPageResponse(
            productPage.getContent().stream().map(productMapper::toProductResponse).collect(Collectors.toList())
            ,productPage.getTotalPages()
            ,productPage.getTotalElements()
            ,productPage.hasNext()
        );
    }
    @Override
    public ProductResponse getProductById(Integer id){
        return productRepository.findById(id)
                .map(productMapper::toProductResponse)
                .orElseThrow(()->new ProductNotFoundException("Product not found"));
    }


    @Override
    public void updateProductById(ProductRequest productRequest, Integer id){
        Optional<Product> updateProduct = productRepository.findById(id);
        if (updateProduct.isEmpty()){
            throw new ProductNotFoundException("Product not found");
        }
        updateProduct.get().setDescription(productRequest.getDescription());
        updateProduct.get().setName(productRequest.getName());
        updateProduct.get().setPrice(productRequest.getPrice());
        productRepository.save(updateProduct.get());
        log.info("product updated {}",updateProduct.get().getId());
    }

    @Override
    public void deleteProductById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()){
            throw new ProductNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
        log.info("product deleted {}",product.get().getId());
    }




    @Override
    @Transactional(readOnly = true)
    @SneakyThrows
    public ProductResponse existProductByProductID(Integer id) {
        return productRepository.findById(id)
                .stream()
                .map(productMapper::toProductResponse)
                .findFirst()
                .orElseThrow(()->new ProductNotFoundException("Product not found"));
    }


}
