package com.example.springbootnacosconsumer.controller;

import com.example.springbootnacosconsumer.client.IProvideService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author:
 * @Date:
 * @Class:
 * @Discription:
 **/
@RestController
public class ConsumerController {

    @Resource
    private IProvideService provideService;

    /**
     * 远程接口的方法就可以在这里使用了
     * @return
     */
    @PostMapping("/getConsumer")
    public String getConsumer() {
        return provideService.getProvide();
    }
}
