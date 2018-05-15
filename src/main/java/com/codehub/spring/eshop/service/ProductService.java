package com.codehub.spring.eshop.service;

import com.codehub.spring.eshop.domain.Product;
import com.codehub.spring.eshop.domain.ProductCategory;

import java.util.Collection;

public interface ProductService {

    public ProductCategory saveCategory(ProductCategory productCategory);

    public void deleteCategoryById(int id);

    public void findCategoryById(int id);

    public Collection<ProductCategory> findAllCategories();

    public Product saveProduct(Product product);

    public void deleteProductById(int id);

    public Product findProductById(int id);

    public Collection<Product> findAllProducts();

    public Collection<Product> findProductsByCategory(int categoryId);

    public Collection<Product> findProductsByKeyword(String keyword);

}
