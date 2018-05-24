package com.codehub.spring.eshop.controller;

import com.codehub.spring.eshop.domain.User;
import com.codehub.spring.eshop.enums.Size;
import com.codehub.spring.eshop.exception.EShopException;
import com.codehub.spring.eshop.exception.InvalidQuantityException;
import com.codehub.spring.eshop.service.CartService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * Created by Dimitris on 17/5/2018.
 */

@RestController
@RequestMapping(value = "e-shop/cart")
public class CartController extends BaseController {

    @Autowired
    private CartService cartService;


    @PostMapping(produces = "application/json")
    @ApiOperation("Add Item to Cart")
    public ResponseEntity addItem(@RequestParam("product_id") Long productId,
                                  @RequestParam("quantity") BigDecimal quantity,
                                  @RequestParam("size") Size size,
                                  @RequestHeader("Authorization") String accessToken)
            throws EShopException {
        User user = verifyToken(accessToken);
        cartService.addItem(user.getId(), productId, quantity, size);
        return ResponseEntity
                .status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "/{product_id}", produces = "application/json")
    @ApiOperation("Remove Item")
    public ResponseEntity removeItem(@PathVariable("product_id") Long productId,
                                     @RequestHeader("Authorization") String accessToken)
            throws EShopException {
        User user = verifyToken(accessToken);
        cartService.removeItem(user.getId(), productId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping(value = "/{product_id}/quantity/{quantity}", produces = "application/json", params = {"quantity"})
    @ApiOperation("Update Quantity")
    public ResponseEntity updateQuantity(@PathVariable("product_id") Long productId,
                                         @PathVariable(value = "quantity") BigDecimal quantity,
                                         @RequestHeader("Authorization") String accessToken)
            throws EShopException {
        User user = verifyToken(accessToken);
        if (quantity.compareTo(BigDecimal.ZERO) != 1) {
            throw new InvalidQuantityException();
        }
        cartService.updateQuantity(user.getId(), productId, quantity);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping(value = "/{product_id}/size/{size}", produces = "application/json", params = {"size"})
    @ApiOperation("Update Size")
    public ResponseEntity updateSize(@PathVariable("product_id") Long productId,
                                     @PathVariable(value = "size") Size size,
                                     @RequestHeader("Authorization") String accessToken)
            throws EShopException {
        User user = verifyToken(accessToken);
        cartService.updateSize(user.getId(), productId, size);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).build();
    }

}
