package com.example.springbootnacosconsumer.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @FeignClient(name = "${spring.provide.url}")
 * 调用远程接口地址，通过配置项配置
 * 这个地址，是项目 springboot-nacos-provide 注册在 nacos 服务的名称，也是项目 springboot-nacos-provide 在配置项中 spring-application-name 的配置项名称
 *
 **/
@FeignClient(name = "${spring.provide.url}")
@Component
public interface IProvideService {

    @PostMapping("/getProvide")
    String getProvide();
}
