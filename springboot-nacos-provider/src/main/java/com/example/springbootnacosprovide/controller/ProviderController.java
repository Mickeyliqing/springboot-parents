package com.example.springbootnacosprovide.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:
 * @Date:
 * @Class:
 * @Discription:
 **/
@RestController
public class ProviderController {

    /**
     * 定义一个方法，对外提供
     * @return
     */
    @PostMapping("/getProvide")
    public String getProvide() {
        return "success";
    }
}
