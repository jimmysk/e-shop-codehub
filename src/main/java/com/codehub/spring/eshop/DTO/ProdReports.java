package com.codehub.spring.eshop.DTO;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapKey;
@Entity
@Data
public class ProdReports {

    private String product_desc;
    @Id
    private Integer product_id;

    private Integer total;

}