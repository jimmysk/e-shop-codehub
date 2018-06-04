package com.codehub.spring.eshop.repository;

import com.codehub.spring.eshop.DTO.SalesByCategoryDto;
import com.codehub.spring.eshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;



/**
 * Created by Dimitris on 16/5/2018.
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public Collection<Product> findAllByProductCategory(Long categoryId);

    public Collection<Product> findAllByKeywordsIsContaining(String keyword);

    public Collection<Product> findByStockLessThan(BigDecimal value);

   /* @Query(value="SELECT category_desc AS Category, COUNT(order_id) AS Total_Orders from product_categories " +
            " JOIN products on product_categories.category_id = products.category_id " +
            " JOIN order_items ON products.product_id = order_items.product_id " +
            " GROUP BY product_categories.category_desc " +
            " ORDER BY Total_Orders " , nativeQuery = true)*/
    /*@Query("select Product.productDesc, ProductCategory.categoryDesc from ProductCategory join Product on ProductCategory.id = Products.id " +
            "join OrderItem on Product = OrderItem.Product " +
            "GROUP BY ProductCategory.categoryDesc,Product.productDesc")*/

    @Query(value = " SELECT product_categories.category_desc, products.product_desc, COUNT(order_id) AS total_orders FROM product_categories " +
            " JOIN products ON product_categories.category_id = products.category_id " +
            " JOIN order_items ON products.product_id = order_items.product_id " +
            " GROUP BY product_categories.category_desc, products.product_desc " +
            " ORDER BY products.product_desc, total_orders" , nativeQuery = true)

   public Collection<SalesByCategoryDto> findByTotalOrdersOrderedByProductCategory();


}
