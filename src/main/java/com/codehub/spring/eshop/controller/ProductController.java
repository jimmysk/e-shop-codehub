package com.codehub.spring.eshop.controller;

import com.codehub.spring.eshop.domain.Product;
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
@RequestMapping(value = "e-shop/product")
@Api(value = "Product", description = "Handle products", tags = "Product")
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;


    @PostMapping(consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Create product", notes = "Call this endpoint to create a new product")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> createProduct(@RequestBody Product product,
                                                 @ApiParam(name = "Authorization", value = "Authorization",
                                                         defaultValue = "Bearer YOUR_ACCESS_TOKEN_HERE")
                                                 @RequestHeader("Authorization") String accessToken) throws EShopException {
        isAdminOrFail(verifyToken(accessToken));
        return ResponseEntity
                .ok()
                .body(productService.saveProduct(product));
    }


    @GetMapping(value = "/{productId}", produces = "application/json")
    @ApiOperation(value = "Find product by product id", notes = "Call this endpoint to find a product by id")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<Product> findProductById(@PathVariable(name = "productId") Long productId,
                                                   @ApiParam(name = "Authorization", value = "Authorization",
                                                           defaultValue = "Bearer YOUR_ACCESS_TOKEN_HERE")
                                                   @RequestHeader("Authorization") String accessToken) throws EShopException {
        isAdminOrFail(verifyToken(accessToken));
        Optional<Product> product = productService.findProductById(productId);
        return product.map(product1 -> ResponseEntity
                .ok()
                .body(product1)).orElseGet(() -> ResponseEntity
                .notFound()
                .build());
    }

    @GetMapping(value = "/all", produces = "application/json")
    @ApiOperation(value = "Find all products", notes = "Call this endpoint to find all products")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<Collection<Product>> findAllProducts() {
        return ResponseEntity
                .ok()
                .body(productService.findAllProducts());
    }

    @DeleteMapping(value = "/{productId}", produces = "application/json")
    @ApiOperation(value = "Remove Product", notes = "Call this endpoint to remove a product")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity deleteProductById(@PathVariable(name = "productId") Long productId,
                                            @ApiParam(name = "Authorization", value = "Authorization",
                                                    defaultValue = "Bearer YOUR_ACCESS_TOKEN_HERE")
                                            @RequestHeader("Authorization") String accessToken) throws EShopException {
        isAdminOrFail(verifyToken(accessToken));
        productService.deleteProductById(productId);
        return ResponseEntity
                .noContent()
                .build();
    }

    @GetMapping(params = "categoryId", produces = "application/json")
    @ApiOperation(value = "Find Product by Category", notes = "Call this endpoint to find a product by category id")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<Collection<Product>> findProductsByCategory(@RequestParam(name = "categoryId") Long categoryId) {
        return ResponseEntity
                .ok()
                .body(productService.findProductsByCategory(categoryId));
    }

    @GetMapping(params = "keyword", produces = "application/json")
    @ApiOperation(value = "Find Product by Keyword", notes = "Call this endpoint to find a product by Keyword")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<Collection<Product>> findProductsByKeyword(@RequestParam(name = "keyword") String keyword) {
        return ResponseEntity
                .ok()
                .body(productService.findProductsByKeyword(keyword));
    }
}
