package com.codehub.spring.eshop.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

/**
 * Created by Dimitris on 14/5/2018.
 */

@Entity
@Table(name = "access_tokens")
@Data
public class AccessToken {

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id")
    private Long userId;

    @Column(name = "access_token", nullable = false)
    private UUID accessToken;

    @Column(name = "created_on", nullable = false)
    private Instant createdOn;

    @Column(name = "expires_in", nullable = false)
    private Instant expiresIn;
}
