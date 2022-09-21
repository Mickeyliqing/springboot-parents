package com.example.springbootthread;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.springbootthread.dao")
public class SpringbootThreadApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootThreadApplication.class, args);
    }

}
