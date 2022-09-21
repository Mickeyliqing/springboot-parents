package com.example.springbootfilterhandlerlistener.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:
 * @Date:
 * @Class:
 * @Discription:
 **/
@RestController
public class HandlerController {

    @PostMapping("/getHandler")
    public void getHandler() {
        System.out.println("getHandler :: 验证拦截器的方法");
    }
}
