package com.example.springbootthread.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyRunnable implements Runnable{
    private static final Lock lock = new ReentrantLock();
    @Override
    public void run() {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                System.out.println("线程名：" + Thread.currentThread().getName() + "输出的结果：" + i);
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        Thread Thread1 = new Thread(myRunnable);
        Thread Thread2 = new Thread(myRunnable);
        Thread Thread3 = new Thread(myRunnable);
        Thread1.start();
        Thread2.start();
        Thread3.start();
    }
}
