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
public class FilterController {

    @PostMapping("/getFilter")
    public void getFilter() {
        System.out.println("getFilter :: 验证执行器的方法");
    }
}
