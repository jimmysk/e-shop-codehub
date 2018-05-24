package com.codehub.spring.eshop.controller;

import com.codehub.spring.eshop.domain.Product;
import com.codehub.spring.eshop.exception.EShopException;
import com.codehub.spring.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Dimitris on 15/5/2018.
 */

@RestController
@RequestMapping(value = "e-shop/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws EShopException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.saveProduct(product));
    }


    @GetMapping(value = "/{productId}")
    public ResponseEntity<Product> findProductById(@PathVariable(name = "productId") Long productId) {
        Optional<Product> product = productService.findProductById(productId);
        return product.map(product1 -> ResponseEntity
                .ok()
                .body(product1)).orElseGet(() -> ResponseEntity
                .notFound()
                .build());
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Collection<Product>> findAllProducts() {
        return ResponseEntity
                .ok()
                .body(productService.findAllProducts());
    }

    @DeleteMapping(value = "/{productId}")
    public ResponseEntity deleteCategoryById(@PathVariable(name = "categoryId") Long categoryId) {
        productService.deleteCategoryById(categoryId);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping(params = "categoryId")
    public ResponseEntity<Collection<Product>> findProductsByCategory(@RequestParam(name = "categoryId") Long categoryId) {
        return ResponseEntity
                .ok()
                .body(productService.findProductsByCategory(categoryId));
    }

    @GetMapping(params = "keyword")
    public ResponseEntity<Collection<Product>> findProductsByKeyword(@RequestParam(name = "keyword") String keyword) {
        return ResponseEntity
                .ok()
                .body(productService.findProductsByKeyword(keyword));
    }


}
