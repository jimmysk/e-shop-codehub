package com.codehub.spring.eshop;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class EShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(EShopApplication.class, args);
	}


    @Autowired
    DataSource dataSource;


    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        final Flyway flyway = new Flyway();
        flyway.setDataSource(this.dataSource);
        return flyway;
    }
}
