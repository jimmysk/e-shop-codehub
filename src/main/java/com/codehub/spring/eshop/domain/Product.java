package com.codehub.spring.eshop.domain;

import com.codehub.spring.eshop.enums.MetricUnit;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by Dimitris on 14/5/2018.
 */

@Entity
@Table(name = "products")
@Data
public class Product {

    private int id;

    private int categoryId;

    private String productDesc;

    private String otherProductInfo;

    private String keywords;

    private BigDecimal price;

    private MetricUnit metricUnit;

    private BigDecimal stock;

    private BigDecimal stockLevel;

    private BigDecimal tax;
}
