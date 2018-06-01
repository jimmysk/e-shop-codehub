package com.codehub.spring.eshop.repository;

import com.codehub.spring.eshop.DTO.ProdReports;
import com.codehub.spring.eshop.domain.Product;
import com.codehub.spring.eshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Dimitris on 16/5/2018.
 */

@Repository
public interface ReportRepository extends JpaRepository<ProdReports, Long> {

    @Query(value = "select Prd.product_desc, Prd.product_id, " +
            "       (select  sum(It.quantity) as total from PUBLIC.order_items as It where It.product_id = Prd.product_id ) " +
            "from PUBLIC.products as Prd " +
            "group by Prd.product_id order by total DESC " +
            "limit 10", nativeQuery = true)
    List<ProdReports> findTopSellingProducts();
    }


