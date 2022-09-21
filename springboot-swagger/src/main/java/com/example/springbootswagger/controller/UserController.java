package com.example.springbootswagger.controller;

import com.example.springbootswagger.model.UserParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 定义请求方法
 */
@Api(value = "UserController")
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/add")
    @ApiOperation("新增方法")
    @ApiImplicitParam(name = "userParam", type = "Body", dataTypeClass = UserParam.class, required = true)
    public Boolean add(@RequestBody UserParam userParam) {
        return true;
    }

    @PostMapping("/list")
    @ApiOperation("查询方法")
    public List<UserParam> getList() {
        List<UserParam> list = new ArrayList<>();
        UserParam userParam = new UserParam(1, "ka", "男");
        list.add(userParam);
        return list;
    }
}
