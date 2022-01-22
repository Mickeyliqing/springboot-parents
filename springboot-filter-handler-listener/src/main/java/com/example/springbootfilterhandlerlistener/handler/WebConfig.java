package com.example.springbootfilterhandlerlistener.handler;

import com.example.springbootfilterhandlerlistener.handler.SpringbootHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author:
 * @Date:
 * @Class:
 * @Discription:
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SpringbootHandler()).addPathPatterns("/*").excludePathPatterns("/getHandler");
    }
}
