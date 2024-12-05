package com.chenjunyi.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *  CyclicBarrier： 他要做的事情是，让一组线程达到一盒屏障时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活
 *  线程进入屏障通过await()
 *  加法
 */

public class CyclicBarrierTest {



    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(6, ()->{
            System.out.println(Thread.currentThread().getName()+"\t 班长锁教室！");

        });
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 上完自习，离开教室");
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }, String.valueOf(i)).start();

        }

    }
}
