package com.codehub.spring.eshop.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

/**
 * Created by Dimitris on 14/5/2018.
 */

@Entity
@Table(name = "access_tokens")
@Data
@Builder
public class AccessToken {

    @Id
    @Column(name = "access_token_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "access_token", nullable = false)
    private UUID accessToken;

    @Column(name = "created_on", nullable = false)
    private Instant createdOn;

    @Column(name = "expires_in", nullable = false)
    private Instant expiresIn;

}
