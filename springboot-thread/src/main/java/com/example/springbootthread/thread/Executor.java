package com.example.springbootthread.thread;

import java.util.concurrent.*;

public class Executor implements Callable {
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            System.out.println("线程名：" + Thread.currentThread().getName() + "输出的结果：" + i);
            sum++;
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Executor executor = new Executor();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future submit = executorService.submit(executor);
        Integer sum = (Integer) submit.get();
        System.out.println(sum);
    }
}
