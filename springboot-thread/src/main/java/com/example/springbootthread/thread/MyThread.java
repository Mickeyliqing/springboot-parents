package com.example.springbootthread.thread;

public class MyThread extends Thread{

    @Override
    public void run() {
        synchronized (MyThread.class) {
            for (int i = 0; i < 10; i++) {
                System.out.println("线程名：" + Thread.currentThread().getName() + "输出的结果：" + i);
            }
        }
    }

    public static void main(String[] args) {
        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();
        MyThread myThread3 = new MyThread();
        myThread1.start();
        myThread2.start();
        myThread3.start();
    }
}
