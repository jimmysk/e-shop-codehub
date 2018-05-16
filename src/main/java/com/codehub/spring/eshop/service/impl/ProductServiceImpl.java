package com.codehub.spring.eshop.service.impl;

import com.codehub.spring.eshop.domain.Product;
import com.codehub.spring.eshop.domain.ProductCategory;
import com.codehub.spring.eshop.repository.ProductCategoryRepository;
import com.codehub.spring.eshop.repository.ProductRepository;
import com.codehub.spring.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory saveCategory(ProductCategory productCategory) {
        return null;
    }

    @Override
    public void deleteCategoryById(int id) {

    }

    @Override
    public void findCategoryById(int id) {

    }

    @Override
    public Collection<ProductCategory> findAllCategories() {
        return null;
    }

    @Override
    public Product saveProduct(Product product) {
        return null;
    }

    @Override
    public void deleteProductById(int id) {

    }

    @Override
    public Product findProductById(int id) {
        return null;
    }

    @Override
    public Collection<Product> findAllProducts() {
        return null;
    }

    @Override
    public Collection<Product> findProductsByCategory(int categoryId) {
        return null;
    }

    @Override
    public Collection<Product> findProductsByKeyword(String keyword) {
        return null;
    }
}
