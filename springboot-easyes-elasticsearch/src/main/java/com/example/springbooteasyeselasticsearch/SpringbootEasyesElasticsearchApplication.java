package com.example.springbooteasyeselasticsearch;

import cn.easyes.starter.register.EsMapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EsMapperScan("com.example.springbooteasyeselasticsearch.dao")
public class SpringbootEasyesElasticsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootEasyesElasticsearchApplication.class, args);
    }

}
