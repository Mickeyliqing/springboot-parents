package com.example.springbootsecret.config;

import com.example.springbootsecret.filter.RequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 注册过滤
 */
@Configuration
public class SignatureConfig {

    @Bean
    public RequestFilter requestCachingFilter() {
        return new RequestFilter();
    }

    @Bean
    public FilterRegistrationBean<?> requestCachingFilterRegistration(RequestFilter requestCachingFilter) {
        FilterRegistrationBean<?> bean = new FilterRegistrationBean<>(requestCachingFilter);
        bean.setOrder(1);
        return bean;
    }
}
