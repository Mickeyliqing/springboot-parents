package com.example.springbootvalidation.controller;

import com.example.springbootvalidation.model.QuartSearchCondition;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:
 * @Date:
 * @Class:
 * @Discription:
 **/
@RestController
@Slf4j
public class QuartSearchController {

    @PostMapping("/getQuartSearchValue")
    public String getQuartSearchValue(@Valid @RequestBody QuartSearchCondition quartSearchCondition) {
        log.info("传参为：{}", quartSearchCondition);
        if (ObjectUtils.isEmpty(quartSearchCondition.getValue())) {
            return "传参为空！";
        }
        return "success";
    }

    @PostMapping("/getQuartSearch")
    public List<String> getQuartSearch(@Validated @RequestBody QuartSearchCondition quartSearchCondition, BindingResult result) {
        log.info("传参为：{}", quartSearchCondition);
        List<String> list = new ArrayList<>();
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError allError : allErrors) {
                list.add(allError.getDefaultMessage());
            }
        }
        return list;
    }

}
