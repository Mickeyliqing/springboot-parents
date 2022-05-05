package com.example.springbootthread.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author:
 * @Date:
 * @Class:
 * @Discription:
 **/
@Configuration
@EnableAsync
public class ThreadConfig implements AsyncConfigurer {

    @Value("${spring.task.execution.pool.core-size}")
    private Integer coreSize;

    @Value("${spring.task.execution.pool.max-size}")
    private Integer maxSize;

    @Value("${spring.task.execution.pool.queue-capacity}")
    private Integer queueCapacity;

    @Value("${spring.task.execution.pool.keep-alive}")
    private Integer keepAlive;

    @Value("${spring.task.execution.thread-name-prefix}")
    private String threadNamePrefix;

    @Bean("getAsyncExecutor")
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        /**
         * 核心线程数：线程池创建时候初始化的线程数
         */
        executor.setCorePoolSize(coreSize);

        /**
         * 最大线程数：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
         */
        executor.setMaxPoolSize(maxSize);

        /**
         * 缓冲队列：用来缓冲执行任务的队列
         */
        executor.setQueueCapacity(queueCapacity);

        /**
         * 允许线程的空闲时间60秒：当超过了核心线程之外的线程在空闲时间到达之后会被销毁
         */
        executor.setKeepAliveSeconds(keepAlive);

        /**
         * 线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
         */
        executor.setThreadNamePrefix(threadNamePrefix);

        /**
         * 缓冲队列满了之后的拒绝策略：在新线程中执行任务，而是有调用者所在的线程来执行
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();

        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
