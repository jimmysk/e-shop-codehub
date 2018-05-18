package com.codehub.spring.eshop.controller;

import com.codehub.spring.eshop.enums.Size;
import com.codehub.spring.eshop.exception.CartProductNotFoundException;
import com.codehub.spring.eshop.exception.EShopException;
import com.codehub.spring.eshop.exception.InvalidQuantityException;
import com.codehub.spring.eshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * Created by Dimitris on 17/5/2018.
 */

@RestController
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    @PostMapping(produces = "application/json")
    public ResponseEntity addItem(@RequestParam("product_id") Long productId,
                                  @RequestParam("quantity") BigDecimal quantity,
                                  @RequestParam("size") Size size)
            throws CartProductNotFoundException {

        cartService.addItem(1L, productId, quantity, size);
        return ResponseEntity
                .status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "/{product_id}", produces = "application/json")
    public ResponseEntity removeItem(@PathVariable("product_id") Long productId)
            throws EShopException {

        cartService.removeItem(1L, productId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping(value = "/{product_id}", produces = "application/json", params = {"quantity"})
    public ResponseEntity updateQuantity(@PathVariable("product_id") Long productId,
                                         @RequestParam(value = "quantity") BigDecimal quantity)
            throws EShopException {

        if (quantity.compareTo(BigDecimal.ZERO) != 1) {
            throw new InvalidQuantityException();
        }
        cartService.updateQuantity(1L, productId, quantity);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping(value = "/{product_id}", produces = "application/json", params = {"size"})
    public ResponseEntity updateSize(@PathVariable("product_id") Long productId,
                                     @RequestParam(value = "size") Size size)
            throws EShopException {

        cartService.updateSize(1L, productId, size);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).build();
    }

}
