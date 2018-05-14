package com.codehub.spring.eshop.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

/**
 * Created by Dimitris on 14/5/2018.
 */

@Entity
@Table(name = "access_tokens")
@Data
public class AccessToken {

    private int userId;

    private UUID accessToken;

    private Instant createdOn;

    private Instant expiresIn;
}
