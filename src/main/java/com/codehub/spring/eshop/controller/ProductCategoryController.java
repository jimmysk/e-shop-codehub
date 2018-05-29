package com.codehub.spring.eshop.controller;

import com.codehub.spring.eshop.domain.ProductCategory;
import com.codehub.spring.eshop.exception.EShopException;
import com.codehub.spring.eshop.service.ProductService;
import io.swagger.annotations.*;
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
@Api(value = "Product Category", description = "Handle product categories", tags = "Product Category")
public class ProductCategoryController extends BaseController {

    @Autowired
    private ProductService productService;


    @PostMapping(produces = "application/json")
    @ApiOperation(value = "Create Product Category", notes = "Call this endpoint to create Product Category")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductCategory> createProductCategory(@RequestBody ProductCategory productCategory,
                                                                 @ApiParam(name = "Authorization", value = "Authorization",
                                                                         defaultValue = "Bearer YOUR_ACCESS_TOKEN_HERE")
                                                                 @RequestHeader("Authorization") String accessToken) throws EShopException{
        isAdminOrFail(verifyToken(accessToken));
        if (productCategory.getId() != null) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        } else {
            return ResponseEntity
                    .ok()
                    .body(productService.saveCategory(productCategory));
        }
    }

    @PutMapping(produces = "application/json")
    @ApiOperation(value = "Update Product Category", notes = "Call this endpoint to update Product Category")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<ProductCategory> updateProductCategory(@RequestBody ProductCategory productCategory,
                                                                 @ApiParam(name = "Authorization", value = "Authorization",
                                                                         defaultValue = "Bearer YOUR_ACCESS_TOKEN_HERE")
                                                                 @RequestHeader("Authorization") String accessToken) throws EShopException {
        isAdminOrFail(verifyToken(accessToken));
        if (productCategory.getId() == null) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
        } else {
            return ResponseEntity
                    .ok()
                    .body(productService.saveCategory(productCategory));
        }
    }


    @GetMapping(value = "/{categoryId}", produces = "application/json")
    @ApiOperation(value = "Find product category", notes = "Call this endpoint to find product category by id")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<ProductCategory> findProductCategoryById(@PathVariable(name = "categoryId") Long categoryId,
                                                                   @ApiParam(name = "Authorization", value = "Authorization",
                                                                           defaultValue = "Bearer YOUR_ACCESS_TOKEN_HERE")
                                                                   @RequestHeader("Authorization") String accessToken) throws EShopException {
        isAdminOrFail(verifyToken(accessToken));
        Optional<ProductCategory> productCategory = productService.findCategoryById(categoryId);
        return productCategory.map(productCategory1 -> ResponseEntity
                .ok()
                .body(productCategory1)).orElseGet(() -> ResponseEntity
                .notFound()
                .build());
    }

    @GetMapping(value = "all", produces = "application/json")
    @ApiOperation(value = "Find all categories", notes = "Call this endpoint to find all categories")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<Collection<ProductCategory>> findAllCategories() {
        return ResponseEntity
                .ok()
                .body(productService.findAllCategories());
    }

    @DeleteMapping(value = "/{categoryId}", produces = "application/json")
    @ApiOperation(value = "Delete product category", notes = "Call this endpoint to delete product category by id")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity deleteCategoryById(@PathVariable(name = "categoryId") Long categoryId,
                                             @ApiParam(name = "Authorization", value = "Authorization",
                                                     defaultValue = "Bearer YOUR_ACCESS_TOKEN_HERE")
                                             @RequestHeader("Authorization") String accessToken) throws EShopException {
        isAdminOrFail(verifyToken(accessToken));
        productService.deleteCategoryById(categoryId);
        return ResponseEntity
                .noContent()
                .build();
    }


}
