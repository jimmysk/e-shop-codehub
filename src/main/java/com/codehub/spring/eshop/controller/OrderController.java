package com.codehub.spring.eshop.controller;

import com.codehub.spring.eshop.domain.CartItem;
import com.codehub.spring.eshop.domain.Order;
import com.codehub.spring.eshop.domain.User;
import com.codehub.spring.eshop.enums.OrderStatus;
import com.codehub.spring.eshop.exception.EShopException;
import com.codehub.spring.eshop.service.CartService;
import com.codehub.spring.eshop.service.OrderService;
import com.codehub.spring.eshop.service.UserService;
import io.swagger.annotations.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Created by Dimitris on 17/5/2018.
 */

@RestController
@RequestMapping(value = "e-shop/order")
@Api(value = "Order", description = "Handle user's order", tags = "Order")
public class OrderController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @PostMapping(value = "create", produces = "application/json")
    @ApiOperation(value = "Create Order", notes = "Call this endpoint to create user's Order")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Order> createOrder(@RequestParam("userId") Long userId,
                                             @ApiParam(name = "Authorization", value = "Authorization",
                                                     defaultValue = "Bearer YOUR_ACCESS_TOKEN_HERE")
                                             @RequestHeader("Authorization") String accessToken) throws EShopException {

        verifyToken(accessToken);
        List<CartItem> cartItems = new ArrayList<>(cartService.findAll(userId));

        return ResponseEntity
                .ok()
                .body(orderService.checkout(cartItems));
    }



    @PutMapping(value = "updatestatus/{order_id}", produces = "application/json")
    @ApiOperation(value = "Update Order's Status", notes = "Call this endpoint to update order's status")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<Order> updateOrderStatus(@RequestParam(value = "order_id") Integer orderId,
                                                   @RequestParam(value = "order_status") OrderStatus orderStatus,
                                                   @ApiParam(name = "Authorization", value = "Authorization",
                                                           defaultValue = "Bearer YOUR_ACCESS_TOKEN_HERE")
                                                   @RequestHeader("Authorization") String accessToken)
            throws EShopException {
        isAdminOrFail(verifyToken(accessToken));
        orderService.updateOrderStatus(orderId, orderStatus);
        return ResponseEntity
                .ok()
                .body(orderService.findOrderById(orderId).get());
    }


    @GetMapping(value = "/{orderId}", produces = "application/json")
    @ApiOperation(value = "Find Order by order's id", notes = "Call this endpoint to find an order by id")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<Order> findOrderById(@PathVariable(name = "orderId") Integer orderId,
                                               @ApiParam(name = "Authorization", value = "Authorization",
                                                       defaultValue = "Bearer YOUR_ACCESS_TOKEN_HERE")
                                               @RequestHeader("Authorization") String accessToken) throws EShopException {
        isAdminOrFail(verifyToken(accessToken));
        Optional<Order> order = orderService.findOrderById(orderId);
        return order.map(order1 -> ResponseEntity
        .ok()
        .body(order1)).orElseGet(() -> ResponseEntity
        .notFound()
        .build());
    }

    @GetMapping(value = "/status/{ordStatus}", produces = "application/json")
    @ApiOperation(value = "Find Order by order's status", notes = "Call this endpoint to find order by status. Default request is Ordered")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<Collection<Order>> findByOrderStatusEquals(@PathVariable(name = "ordStatus", required = false) OrderStatus ordStatus,
                                               @ApiParam(name = "Authorization", value = "Authorization",
                                                       defaultValue = "Bearer YOUR_ACCESS_TOKEN_HERE")
                                               @RequestHeader("Authorization") String accessToken) throws EShopException {

        isAdminOrFail(verifyToken(accessToken));
        if (ordStatus == null) ordStatus = OrderStatus.ORDERED ;
        return ResponseEntity
                .ok()
                .body(orderService.findByOrderStatusEquals(ordStatus));
    }

    @GetMapping(value = "user/{userId}", produces = "application/json")
    @ApiOperation(value = "Find all orders by given user", notes = "Call this endpoint to find all orders by user")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<Collection<Order>> findOrdersByUser(@PathVariable(name = "userId") Long userId,
                                                              @ApiParam(name = "Authorization", value = "Authorization",
                                                                      defaultValue = "Bearer YOUR_ACCESS_TOKEN_HERE")
                                                              @RequestHeader("Authorization") String accessToken)
            throws EShopException {

        isAdminOrFail(verifyToken(accessToken));
        User user = userService.findById(userId).get();
        return ResponseEntity
                .ok()
                .body(orderService.findAllOrdersByUser(user));
    }
}
