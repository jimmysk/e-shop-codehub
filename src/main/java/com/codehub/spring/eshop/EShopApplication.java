package com.codehub.spring.eshop;

import com.codehub.spring.eshop.request.interceptor.LoggingInterceptor;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.sql.DataSource;

@SpringBootApplication
public class EShopApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(EShopApplication.class, args);
	}

    @Autowired
    DataSource dataSource;

    @Bean
    public HandlerInterceptorAdapter loggingInterceptor() {
        return new LoggingInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(loggingInterceptor());
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway() {
        final Flyway flyway = new Flyway();
        flyway.setDataSource(this.dataSource);
        return flyway;
    }
}
