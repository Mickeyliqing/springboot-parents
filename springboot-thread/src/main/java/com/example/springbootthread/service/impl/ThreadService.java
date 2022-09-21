package com.example.springbootthread.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @Author:
 * @Date:
 * @Class:
 * @Discription:
 **/
@Service
@Slf4j
public class ThreadService {

    /**
     * 异步方法无返回值，在 controller
     * @param message
     * @return
     */
    @Async("getAsyncExecutor")
    public String getThread_01(String message) {
        log.info("传入的参数为：{}", message);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            log.error("错误信息为：{}", e.getMessage());
        }
        return message;
    }

    /**
     * 异步方法有返回值，在 controller
     * @param message
     * @return
     */
    @Async("getAsyncExecutor")
    public CompletableFuture getThread_02(String message) {
        log.info("传入的参数为：{}", message);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            log.error("错误信息为：{}", e.getMessage());
        }
        return CompletableFuture.completedFuture(message);
    }

    /**
     * 异步方法有返回值，在 controller
     * @param message
     * @return
     */
    @Async("getAsyncExecutor")
    public CompletableFuture getThread_03(String message) {
        log.info("传入的参数为：{}", message);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            log.error("错误信息为：{}", e.getMessage());
        }
        return CompletableFuture.completedFuture(message);
    }
}
