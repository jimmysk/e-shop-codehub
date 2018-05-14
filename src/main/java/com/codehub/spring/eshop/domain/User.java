package com.codehub.spring.eshop.domain;

import com.codehub.spring.eshop.enums.Role;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Dimitris on 14/5/2018.
 */

@Entity
@Table(name = "users")
@Data
public class User {

    private int id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String street;

    private String streetNumber;

    private String postalCode;

    private String city;

    private String country;

    private Role role;
}
