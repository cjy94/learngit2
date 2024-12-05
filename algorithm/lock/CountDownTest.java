package com.chenjunyi.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;


/**
 * CountDownLaunch：让一些线程阻塞直到另一些线程完成一系列操作后才被唤醒
 * 减法
 */

public class CountDownTest {
    private static void closeDoor() throws InterruptedException {
        CountDownLatch count = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 上完自习，离开教室");
                count.countDown();

            }, String.valueOf(i)).start();

        }

        // 主线程需要等待子线程全部完成，再执行
        // 调用线程会被阻塞，main线程会被阻塞
        count.await();
        System.out.println(Thread.currentThread().getName()+"\t 班长锁教室");

    }

    public static void main(String[] args) throws InterruptedException {
        closeDoor();



    }
}
