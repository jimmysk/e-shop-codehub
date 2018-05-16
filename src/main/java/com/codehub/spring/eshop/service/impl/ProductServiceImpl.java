package com.codehub.spring.eshop.service.impl;

import com.codehub.spring.eshop.domain.Product;
import com.codehub.spring.eshop.domain.ProductCategory;
import com.codehub.spring.eshop.repository.ProductCategoryRepository;
import com.codehub.spring.eshop.repository.ProductRepository;
import com.codehub.spring.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory saveCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    @Override
    public void deleteCategoryById(Long id) {
        productCategoryRepository.deleteById(id);
    }

    @Override
    public Optional<ProductCategory> findCategoryById(Long id) {
        return productCategoryRepository.findById(id);
    }

    @Override
    public Collection<ProductCategory> findAllCategories() {
        return productCategoryRepository.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Collection<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Collection<Product> findProductsByCategory(Long categoryId) {
        return productRepository.findAllByProductCategory(categoryId);
    }

    @Override
    public Collection<Product> findProductsByKeyword(String keyword) {
        return productRepository.findAllByKeywordsIsContaining(keyword);
    }
}
