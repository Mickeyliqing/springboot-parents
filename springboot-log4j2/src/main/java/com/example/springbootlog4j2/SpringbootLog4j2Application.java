package com.example.springbootlog4j2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootLog4j2Application {

    public static void main(String[] args) {
        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        SpringApplication.run(SpringbootLog4j2Application.class, args);
    }

}
