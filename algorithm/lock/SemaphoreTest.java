package com.chenjunyi.lock;

import java.util.concurrent.Semaphore;

/**
 *  信号量主要用于两个目的，一个是用于多个共享资源的互斥使用， 另一个用于并发线程数的控制
 *
 *  抢车位
 */

public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t 抢到车位");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName()+"\t 离开车位======");
                }


            }).start();

        }

        
    }
}
