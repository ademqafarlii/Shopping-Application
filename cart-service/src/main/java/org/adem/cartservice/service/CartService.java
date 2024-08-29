package org.adem.cartservice.service;

import org.adem.cartservice.dto.CartDto;
import org.adem.cartservice.dto.CartPageResponse;

public interface CartService {
    void addProductToCartFromProductService(CartDto cartDto);

    CartPageResponse getAllProducts(Integer page,Integer count);

    CartDto getProductByProductID(Integer id);
    void deleteCartByID(Integer id);
    void deleteByProductNameStorageWithName(String name);
    public CartDto existProductInCartById(Integer id);

    CartDto getProductByName(String name);
}
