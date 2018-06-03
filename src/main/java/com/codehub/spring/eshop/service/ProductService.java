package com.codehub.spring.eshop.service;


import com.codehub.spring.eshop.DTO.ProdReports;
import com.codehub.spring.eshop.DTO.SalesByCategoryDto;
import com.codehub.spring.eshop.domain.Product;
import com.codehub.spring.eshop.domain.ProductCategory;
import com.codehub.spring.eshop.exception.EShopException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    public ProductCategory saveCategory(ProductCategory productCategory);

    public void deleteCategoryById(Long id);

    public Optional<ProductCategory> findCategoryById(Long id);

    public Collection<ProductCategory> findAllCategories();

    public Product saveProduct(Product product) throws EShopException;

    public void deleteProductById(Long id);

    public Optional<Product> findProductById(Long id);

    public Collection<Product> findAllProducts();

    public Collection<Product> findProductsByCategory(Long categoryId);

    public Collection<Product> findProductsByKeyword(String keyword);

    public List<ProdReports> findTopSellingProducts();

    public Collection<Product> findByStockLessThan(BigDecimal value);

    public Collection<SalesByCategoryDto> findByTotalOrdersOrderedByProductCategory();

}
