package org.adem.cartservice.controller;

import jakarta.validation.Valid;
import org.adem.cartservice.dto.CartDto;
import org.adem.cartservice.dto.CartPageResponse;
import org.adem.cartservice.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @PostMapping("/add-to-cart")
    @ResponseStatus(HttpStatus.CREATED)
    void addProductToCartFromProductService(@RequestBody CartDto cartDto){
        cartService.addProductToCartFromProductService(cartDto);
    }

    @GetMapping("/get-all-products")
    @ResponseStatus(HttpStatus.OK)
    CartPageResponse getAllProducts(@RequestParam Integer page, @RequestParam Integer count){
        return cartService.getAllProducts(page, count);
    }

    @GetMapping("/get-product-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    CartDto getProductByProductID(@PathVariable Integer id){
        return cartService.getProductByProductID(id);
    }

    @GetMapping("/get-product-by-name/{name}")
    @ResponseStatus(HttpStatus.OK)
    CartDto getProductByName(@PathVariable String name){
        return cartService.getProductByName(name);
    }

    @DeleteMapping("/delete-product-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteCartByID(@PathVariable Integer id){
        cartService.deleteCartByID(id);
    }

    @DeleteMapping("/delete-product-by-product-name/{name}")
    @ResponseStatus(HttpStatus.OK)
    void deleteByProductNameStorageWithName(@PathVariable String name){
        cartService.deleteByProductNameStorageWithName(name);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/exist-product-in-cart-by-id")
    public CartDto existProductInCartById(@RequestParam Integer id){
        return cartService.existProductInCartById(id);
    }
}
