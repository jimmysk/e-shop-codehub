package com.codehub.spring.eshop.service;

import com.codehub.spring.eshop.domain.Product;
import com.codehub.spring.eshop.domain.ProductCategory;

import java.util.Collection;
import java.util.Optional;

public interface ProductService {

    public ProductCategory saveCategory(ProductCategory productCategory);

    public void deleteCategoryById(Long id);

    public Optional<ProductCategory> findCategoryById(Long id);

    public Collection<ProductCategory> findAllCategories();

    public Product saveProduct(Product product);

    public void deleteProductById(Long id);

    public Optional<Product> findProductById(Long id);

    public Collection<Product> findAllProducts();

    public Collection<Product> findProductsByCategory(Long categoryId);

    public Collection<Product> findProductsByKeyword(String keyword);

}
