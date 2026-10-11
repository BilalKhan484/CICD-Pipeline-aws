package com.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.restapi")
@EntityScan(basePackages = "com.restapi.model")
@RestController
public class ApiSpringbootApplication {

    @GetMapping("/hello")
    public String hello(){
        return "springboot rest-api";
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiSpringbootApplication.class, args);
    }
}