package com.codehub.spring.eshop.repository;

import com.codehub.spring.eshop.domain.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created by Dimitris on 16/5/2018.
 */

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, Long> {

    AccessToken findByAccessToken(UUID accessToken);

    @Transactional
    void deleteAccessTokenByAccessToken(UUID accessToken);

}
