package com.example.springbootelasticsearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 封装传参的实体类
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElasticSearchDocument<T> {
    private String id;
    private T data;
}
