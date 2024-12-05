package com.chenjunyi.MultiThread;

import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class Apple {
    private static volatile int num = 9;
    static ReentrantLock lock = new ReentrantLock();
    

    public static void main(String[] args) throws InterruptedException {
        // 取3个苹果
        // 取2个苹果
        Thread t1 = new Thread(()->{
            while (true) {
                lock.lock();
                try {
                    if (num >= 2) {
                        num = num -2;
                        System.out.println("猴子1吃了2个， 还剩 "+ num + "个");
                    } else {
                        break;
                    }
                } finally {
                    lock.unlock();
                }
            }
        });

        // 取2个苹果
        Thread t2 = new Thread(()->{
            while (true) {
                lock.lock();
                try {
                    if (num >= 3) {
                        num = num - 3;
                        System.out.println("猴子2吃了3个， 还剩 " + num + "个");
                    } else {
                        break;
                    }
                } finally {
                    lock.unlock();
                }
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }



    







}
