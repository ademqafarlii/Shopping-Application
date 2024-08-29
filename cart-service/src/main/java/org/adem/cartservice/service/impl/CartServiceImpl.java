package org.adem.cartservice.service.impl;

import org.adem.cartservice.dto.CartDto;
import org.adem.cartservice.dto.CartPageResponse;
import org.adem.cartservice.dto.product.ProductRequest;
import org.adem.cartservice.exception.NameAlreadyUsedException;
import org.adem.cartservice.exception.ProductAlreadyAddedToCartException;
import org.adem.cartservice.exception.ProductNotFoundException;
import org.adem.cartservice.mapper.CartMapper;
import org.adem.cartservice.model.Cart;
import org.adem.cartservice.repository.CartRepository;
import org.adem.cartservice.service.CartService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final WebClient.Builder webClientBuilder;

    public CartServiceImpl(CartRepository cartRepository, CartMapper cartMapper, WebClient.Builder webClientBuilder) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
        this.webClientBuilder = webClientBuilder;
    }



    @Override
    public void addProductToCartFromProductService(CartDto cartDto) {

        Optional<Cart> byProductID = cartRepository.findByProductID(cartDto.getProductID());
        if (byProductID.isPresent()){
            throw new ProductAlreadyAddedToCartException("Product already added to cart");
        }
        Integer productId = cartDto.getProductID();
        if (productId==null){
            throw new ProductNotFoundException("Product not found");
        }
        ProductRequest productRequest = webClientBuilder.build().get()
                .uri("http://PRODUCT/v1/product/exist-product-by-product-id",uriBuilder -> uriBuilder.queryParam("id",productId).build())
                .retrieve()
                .bodyToMono(ProductRequest.class)
                .block();
        if (productRequest==null || !productId.equals(productRequest.getId())){
            throw new ProductNotFoundException("Product not found");
        }
        cartRepository.save(cartMapper.toCart(cartDto));
    }

    @Override
    public CartPageResponse getAllProducts(Integer page, Integer count) {
        Page<Cart> cartPage = cartRepository.findAll(PageRequest.of(page, count));
        if (cartPage.isEmpty()) {
            throw new ProductNotFoundException("Product not found in cart");
        }
        return new CartPageResponse(
                cartPage.getContent().stream().map(cartMapper::toCartDto).collect(Collectors.toList()),
                cartPage.getTotalElements(),
                cartPage.getTotalPages(),
                cartPage.hasNext()
        );
    }

    @Override
    public CartDto getProductByProductID(Integer id) {
        return cartRepository.findByProductID(id)
                .stream()
                .map(cartMapper::toCartDto)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }
    @Override
    public CartDto getProductByName(String name) {
        return cartRepository.findBySaveWithThisName(name)
                .stream()
                .map(cartMapper::toCartDto)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    }


    @Override
    public void deleteCartByID(Integer id) {
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isEmpty()) {
            throw new ProductNotFoundException("Product not found");
        }
        cartRepository.deleteById(id);
    }

    @Override
    public void deleteByProductNameStorageWithName(String name) {
        Optional<Cart> cart = cartRepository.findBySaveWithThisName(name);
        if (cart.isEmpty()) {
            throw new ProductNotFoundException("Cart not found");
        }
        cartRepository.delete(cart.get());
    }

    @Override
    @Transactional(readOnly = true)
    public CartDto existProductInCartById(Integer id){
        return cartRepository.findByProductID(id)
                .stream()
                .map(cartMapper::toCartDto)
                .findFirst()
                .orElseThrow(()->new ProductNotFoundException("Product not found"));
    }




}
