package com.example.springbootnacosprovide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @EnableDiscoveryClient 注解默认可以省略
 */
@SpringBootApplication
//@EnableDiscoveryClient
public class SpringbootNacosProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootNacosProviderApplication.class, args);
    }

}
