package com.example.springbootnacosconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @EnableDiscoveryClient 注解默认可以省略
 */
@SpringBootApplication
@EnableFeignClients
//@EnableDiscoveryClient
public class SpringbootNacosConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootNacosConsumerApplication.class, args);
    }

}
