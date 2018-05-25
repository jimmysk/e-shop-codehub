package com.codehub.spring.eshop.domain;

import com.codehub.spring.eshop.enums.MetricUnit;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Dimitris on 14/5/2018.
 */

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @Column(name = "product_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ProductCategory productCategory;

    @Column(name = "product_desc")
    private String productDesc;

    @Column(name = "other_product_info")
    private String otherProductInfo;

    @Column(name = "keywords")
    private String keywords;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "metric_unit")
    @Enumerated(EnumType.STRING)
    private MetricUnit metricUnit;

    @Column(name = "stock")
    private BigDecimal stock;

    @Column(name = "stock_level")
    private BigDecimal stockLevel;

    @Column(name = "tax")
    private BigDecimal tax;
}
