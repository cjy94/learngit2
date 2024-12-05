package com.chenjunyi.lock;

import java.util.concurrent.atomic.AtomicInteger;

public class MySpinLock {
    private AtomicInteger state = new AtomicInteger(0);



    public int getState() {
        return state.get();
    }

    public void lock() {
        final Thread current = Thread.currentThread();
        while (!state.compareAndSet(0, 1)) {

        }

    }

    public void unLock() {
        final Thread current = Thread.currentThread();
        state.compareAndSet(1, 0);
    }

    public static void main(String[] args) throws InterruptedException {
        MySpinLock lock = new MySpinLock();
        Thread t1 = new Thread(()->{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"\t"+" get lock success!");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unLock();
            }

        }, "AAA");

        Thread t2 = new Thread(()->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"\t"+" get lock success!");
//                Thread.sleep(5000);
            }  finally {
                lock.unLock();
            }

        }, "BBB");

        // AAA 线程先启动， 获取到锁
        t1.start();
        // main  线程休眠1s钟，保证AAA线程持有一会锁，
        Thread.sleep(1000);

        // 启动BBB线程也获取锁，但是AAA线程在持有着锁，BBB线程获取不到锁， 自旋获取
        t2.start();

        


    }

    

}
