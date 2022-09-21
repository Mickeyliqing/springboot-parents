package com.example.springbootelasticsearch.exception;

/**
 * 定义错误的实体类
 **/
public class BaseException extends RuntimeException {

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
