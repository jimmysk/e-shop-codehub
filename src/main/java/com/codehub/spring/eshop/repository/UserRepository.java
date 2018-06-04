package com.codehub.spring.eshop.repository;

import com.codehub.spring.eshop.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Dimitris on 16/5/2018.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   User findByEmail(String email);

   @Query("SELECT u.firstName, u.lastName, COUNT (o.id) FROM User u " +
           "JOIN Order o ON u.id = o.user GROUP BY u.firstName, u.lastName")
   List<User> findUserOrdersGreaterThan(Integer minOrder);
}

