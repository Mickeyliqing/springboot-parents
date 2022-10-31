package com.example.springbootxxljob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringbootXxljobApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootXxljobApplication.class, args);
    }

}
