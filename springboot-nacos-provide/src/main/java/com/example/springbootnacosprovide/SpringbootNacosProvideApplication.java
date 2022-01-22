package com.example.springbootnacosprovide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @EnableDiscoveryClient 注解默认可以省略
 */
@SpringBootApplication
//@EnableDiscoveryClient
public class SpringbootNacosProvideApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootNacosProvideApplication.class, args);
    }

}
