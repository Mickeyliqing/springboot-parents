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
public class ListenerController {

    @PostMapping("/getListener")
    public void getListener() {
        System.out.println("getListener :: 验证监听器的方法");
    }
}
