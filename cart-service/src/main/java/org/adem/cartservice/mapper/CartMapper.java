package org.adem.cartservice.mapper;

import org.adem.cartservice.dto.CartDto;
import org.adem.cartservice.exception.ProductNotFoundException;
import org.adem.cartservice.model.Cart;
import org.springframework.stereotype.Service;

@Service
public class CartMapper {
    public Cart toCart(CartDto cartDto){
        if (cartDto == null){
            throw new ProductNotFoundException("Cart not found");
        }
        return Cart.builder()
                .saveWithThisName(cartDto.getSaveWithThisName())
                .productID(cartDto.getProductID())
                .build();
    }
    public CartDto toCartDto(Cart cart){
        if (cart==null){
            throw new ProductNotFoundException("Cart not found");
        }
        return CartDto.builder()
                .productID(cart.getProductID())
                .saveWithThisName(cart.getSaveWithThisName())
                .build();
    }
}
