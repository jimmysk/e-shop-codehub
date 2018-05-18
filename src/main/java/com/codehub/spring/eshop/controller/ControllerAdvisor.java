package com.codehub.spring.eshop.controller;

import com.codehub.spring.eshop.exception.CartNotFoundException;
import com.codehub.spring.eshop.exception.CartProductNotFoundException;
import com.codehub.spring.eshop.exception.InvalidQuantityException;
import com.codehub.spring.eshop.exception.ProductCategoryNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Slf4j
public class ControllerAdvisor {

    @ExceptionHandler(CartProductNotFoundException.class)
    public ResponseEntity cartProductNotFoundException(HttpServletRequest request, HttpServletResponse response, CartProductNotFoundException e) {
        log.error("Cart Not Found", e);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity cartNotFoundException(HttpServletRequest request, HttpServletResponse response, CartNotFoundException e) {
        log.error("Cart Product Not Found", e);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(InvalidQuantityException.class)
    public ResponseEntity invalidQuantityException(HttpServletRequest request, HttpServletResponse response, InvalidQuantityException e) {
        log.error("Quantity should be greater than zero", e);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ProductCategoryNotFoundException.class)
    public ResponseEntity invalidQuantityException(HttpServletRequest request, HttpServletResponse response, ProductCategoryNotFoundException e) {
        log.error("Cart Product Not Found", e);
        return ResponseEntity.notFound().build();
    }
}
