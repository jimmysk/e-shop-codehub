package com.codehub.spring.eshop.controller;

import com.codehub.spring.eshop.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
        log.error("CartItem Not Found", e);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity cartNotFoundException(HttpServletRequest request, HttpServletResponse response, CartNotFoundException e) {
        log.error("CartItem Product Not Found", e);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(InvalidQuantityException.class)
    public ResponseEntity invalidQuantityException(HttpServletRequest request, HttpServletResponse response, InvalidQuantityException e) {
        log.error("Quantity should be greater than zero", e);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ProductCategoryNotFoundException.class)
    public ResponseEntity invalidQuantityException(HttpServletRequest request, HttpServletResponse response, ProductCategoryNotFoundException e) {
        log.error("CartItem Product Not Found", e);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity tokenNotFoundException(HttpServletRequest request, HttpServletResponse response, TokenNotFoundException e) {
        log.error("Access Token Not Found", e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler(TokenAccessExpiredException.class)
    public ResponseEntity tokenAccessExpiredException(HttpServletRequest request, HttpServletResponse response, TokenAccessExpiredException e) {
        log.error("Access Token Expired", e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler(UserNotAuthException.class)
    public ResponseEntity userNotAuthException(HttpServletRequest request, HttpServletResponse response, UserNotAuthException e) {
        log.error("User not authorized", e);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity userNotFoundException(HttpServletRequest request, HttpServletResponse response, UserNotFoundException e) {
        log.error("User not found", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity orderNotFoundException(HttpServletRequest request, HttpServletResponse response, OrderNotFoundException e) {
        log.error("Order/orders not found", e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
