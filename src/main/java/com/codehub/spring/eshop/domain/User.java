package com.codehub.spring.eshop.domain;

import com.codehub.spring.eshop.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

import static com.codehub.spring.eshop.enums.Role.CUSTOMER;

/**
 * Created by Dimitris on 14/5/2018.
 */

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Long id;

    @NotBlank(message = "Email cannot be empty")
    @Column(name = "email", unique = true, nullable = false)
    @Email(message = "Email should be valid")
    @NaturalId
    private String email;

    @NotBlank(message = "Password is necessary field")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters ")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Size(min = 2, max = 50, message = "LastName must be between 2 and 50 characters ")
    private String lastName;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "street_number", nullable = false)
    private String streetNumber;

    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(hidden = true)
    // TODO: check if can take values as administrator!
    private Role role = CUSTOMER ;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Order> orders;
}
