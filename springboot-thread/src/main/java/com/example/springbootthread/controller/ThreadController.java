package com.example.springbootthread.controller;

import com.example.springbootthread.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author:
 * @Date:
 * @Class:
 * @Discription:
 **/
@RestController
public class ThreadController {

    @Autowired
    private ThreadService threadService;

    /**
     * 异步方法无返回值，在 controller
     * @return
     */
    @PostMapping("thread/01")
    public String getThread_01() {
       /* int counts = 10;
        for (int i = 0; i < counts; i++ ) {
            threadService.getThread_01("index = " + i);
        }
        return "success";*/

        // 返回的是 null
        String thread_01 = threadService.getThread_01("index = " + 1);
        return thread_01;
    }

    /**
     * 异步方法有返回值，在 controller
     * @return
     */
    @PostMapping("thread/02")
    public String getThread_02() throws ExecutionException, InterruptedException {
        CompletableFuture thread_02 = threadService.getThread_02("getThread_02");
        CompletableFuture thread_03 = threadService.getThread_03("getThread_03");
        CompletableFuture.allOf(thread_02, thread_03);
        return (String) thread_02.get() + "," + thread_03.get();
    }
}
