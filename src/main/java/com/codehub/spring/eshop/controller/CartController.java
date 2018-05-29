package com.codehub.spring.eshop.controller;

import com.codehub.spring.eshop.domain.CartItem;
import com.codehub.spring.eshop.domain.User;
import com.codehub.spring.eshop.enums.Size;
import com.codehub.spring.eshop.exception.EShopException;
import com.codehub.spring.eshop.exception.InvalidQuantityException;
import com.codehub.spring.eshop.service.CartService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by Dimitris on 17/5/2018.
 */

@RestController
@RequestMapping(value = "e-shop/cart")
@Api(value = "Cart", description = "Handle a user's cart", tags = "Cart")
public class CartController extends BaseController {

    @Autowired
    private CartService cartService;


    @PostMapping(produces = "application/json")
    @ApiOperation(value = "Add Item to Cart", notes = "Call this endpoint to add product to a user's cart")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public void addItem(@RequestParam("product_id") Long productId,
                        @RequestParam("quantity") BigDecimal quantity,
                        @RequestParam("size") Size size,
                        @ApiParam(name = "Authorization", value = "Authorization",
                                defaultValue = "Bearer YOUR_ACCESS_TOKEN_HERE")
                        @RequestHeader("Authorization") String accessToken)
            throws EShopException {
        User user = verifyToken(accessToken);
        cartService.addItem(user.getId(), productId, quantity, size);
    }

    @DeleteMapping(value = "/{product_id}", produces = "application/json")
    @ApiOperation(value = "Remove Item", notes = "Call this endpoint to remove an item from a user's cart")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeItem(@PathVariable("product_id") Long productId,
                           @ApiParam(name = "Authorization", value = "Authorization",
                                   defaultValue = "Bearer YOUR_ACCESS_TOKEN_HERE")
                           @RequestHeader("Authorization") String accessToken)
            throws EShopException {
        User user = verifyToken(accessToken);
        cartService.removeItem(user.getId(), productId);
    }

    @PutMapping(value = "/{product_id}/quantity", produces = "application/json", params = {"quantity"})
    @ApiOperation(value = "Update Quantity", notes = "Call this endpoint to update the quantity of an item in a user's cart")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateQuantity(@PathVariable("product_id") Long productId,
                               @RequestParam(value = "quantity") BigDecimal quantity,
                               @ApiParam(name = "Authorization", value = "Authorization",
                                       defaultValue = "Bearer YOUR_ACCESS_TOKEN_HERE")
                               @RequestHeader("Authorization") String accessToken)
            throws EShopException {
        User user = verifyToken(accessToken);
        if (quantity.compareTo(BigDecimal.ZERO) != 1) {
            throw new InvalidQuantityException();
        }
        cartService.updateQuantity(user.getId(), productId, quantity);
    }

    @PutMapping(value = "/{product_id}/size", produces = "application/json", params = {"size"})
    @ApiOperation(value = "Update Size", notes = "Call this endpoint to update the size of an item in a user's cart")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateSize(@PathVariable("product_id") Long productId,
                           @RequestParam(value = "size") Size size,
                           @ApiParam(name = "Authorization", value = "Authorization",
                                   defaultValue = "Bearer YOUR_ACCESS_TOKEN_HERE")
                           @RequestHeader("Authorization") String accessToken)
            throws EShopException {
        User user = verifyToken(accessToken);
        cartService.updateSize(user.getId(), productId, size);
    }

    @DeleteMapping(value = "/", produces = "application/json")
    @ApiOperation(value = "Drop Cart", notes = "Call this endpoint to remove all items from a user's cart")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCart(@ApiParam(name = "Authorization", value = "Authorization",
            defaultValue = "Bearer YOUR_ACCESS_TOKEN_HERE")
                           @RequestHeader("Authorization") String accessToken)
            throws EShopException {
        User user = verifyToken(accessToken);
        cartService.dropCart(user.getId());
    }


    @GetMapping(value = "user", produces = "application/json")
    @ApiOperation(value = "Find content of Cart", notes = "Call this endpoint to retrieve the contents of the shopping cart for a given account")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<Collection<CartItem>> findCartContentByUser(@ApiParam(name = "Authorization", value = "Authorization",
                                                                            defaultValue = "Bearer YOUR_ACCESS_TOKEN_HERE")
                                                                          @RequestHeader("Authorization") String accessToken)
            throws EShopException {
        User user = verifyToken(accessToken);
        return ResponseEntity
                .ok()
                .body(cartService.findAll(user.getId()));

    }


    @GetMapping(value = "user/{userId}", produces = "application/json")
    @ApiOperation(value = "Find content of Cart by User Id", notes = "Call this endpoint to retrieve the contents of the shopping cart by user id")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<Collection<CartItem>> findCartContentByUserId(@PathVariable(name = "userId") Long userId,
                                                                        @ApiParam(name = "Authorization", value = "Authorization",
                                                                                defaultValue = "Bearer YOUR_ACCESS_TOKEN_HERE")
                                                                        @RequestHeader("Authorization") String accessToken)
            throws EShopException {

        isAdminOrFail(verifyToken(accessToken));
        return ResponseEntity
                .ok()
                .body(cartService.findAll(userId));
    }
}
