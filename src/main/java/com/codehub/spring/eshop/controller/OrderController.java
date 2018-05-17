package com.codehub.spring.eshop.controller;

import com.codehub.spring.eshop.domain.Order;
import com.codehub.spring.eshop.enums.OrderStatus;
import com.codehub.spring.eshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by Dimitris on 17/5/2018.
 */

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.saveOrder(order));
    }


    @PutMapping(value = "/updatestatus", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Order> updateOrderStatus(@RequestBody Integer orderId, OrderStatus orderStatus) {
        orderService.updateOrderStatus(orderId, orderStatus);
        return ResponseEntity
                .ok()
                .body(orderService.findOrderById(orderId).get());
    }


    @GetMapping(value = "/{orderId}")
    public ResponseEntity<Order> findOrderById(@PathVariable(name = "orderId") Integer orderId) {
        Optional<Order> order = orderService.findOrderById(orderId);
        return order.map(order1 -> ResponseEntity
        .ok()
        .body(order1)).orElseGet(() -> ResponseEntity
        .notFound()
        .build());
    }
}
