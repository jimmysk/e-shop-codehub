package com.codehub.spring.eshop.controller;

import com.codehub.spring.eshop.domain.ProductCategory;
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
@RequestMapping(value = "e-shop/product/category")
public class ProductCategoryController {

    @Autowired
    private ProductService productService;


    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ProductCategory> createProductCategory(@RequestBody ProductCategory productCategory) {
        if (productCategory.getId() != null) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        } else {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(productService.saveCategory(productCategory));
        }
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ProductCategory> updateProductCategory(@RequestBody ProductCategory productCategory) {
        if (productCategory.getId() == null) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        } else {
            return ResponseEntity
                    .ok()
                    .body(productService.saveCategory(productCategory));
        }
    }


    @GetMapping(value = "/{categoryId}")
    public ResponseEntity<ProductCategory> findProductCategoryById(@PathVariable(name = "categoryId") Long categoryId) {
        Optional<ProductCategory> productCategory = productService.findCategoryById(categoryId);
        return productCategory.map(productCategory1 -> ResponseEntity
                .ok()
                .body(productCategory1)).orElseGet(() -> ResponseEntity
                .notFound()
                .build());
    }

    @GetMapping(value = "all")
    public ResponseEntity<Collection<ProductCategory>> findAllCategories() {
        return ResponseEntity
                .ok()
                .body(productService.findAllCategories());
    }

    @DeleteMapping(value = "/{categoryId}")
    public ResponseEntity deleteCategoryById(@PathVariable(name = "categoryId") Long categoryId) {
        productService.deleteCategoryById(categoryId);
        return ResponseEntity
                .noContent()
                .build();
    }


}
