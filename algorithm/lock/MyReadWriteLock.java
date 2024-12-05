package com.chenjunyi.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 实现一个读写锁：
 * 1、定义一个共享变量，高位表示读锁；低位表示写锁
 * 2、获取读锁时，先判断是否有写锁，有则等待，没有则state+1
 * 3、释放读锁state--, 通知所有等待线程
 * 4、获取写锁，获取写锁时需要判断读锁和写锁是否都存在，有则等待，没有则将写锁数量加1
 */
public class MyReadWriteLock {
    private volatile int state;
    static final int SHARED_SHIFT   = 16;
    static final int MAX_COUNT      = (1 << SHARED_SHIFT) - 1;


    // 高16位表示 读锁
    int getReaderCount(int state) {
        return state >>> 16;
    }

    int getWriterCount(int state) {
        return state & MAX_COUNT;
    }
    // 获取
    void lockRead() throws InterruptedException {
        while (getWriterCount(state) > 0) {
            wait();
        }

        System.out.println(Thread.currentThread().getName()+"\t get readLock");
        // 获取到读锁，state++
        state = state + (1<<16);
    }

    void unReadLock() {
        state = state-(1<<16);
        notifyAll();

    }

    void lockWrite() throws InterruptedException {
        while (getWriterCount(state) > 0 || getReaderCount(state) > 0) {
            wait();
        }

        System.out.println(Thread.currentThread().getName()+"\t get writeLock");
        // 获取到读锁，state++
        state = state + 1;
    }

    void unWriteLock() {
        state--;
        notifyAll();
    }


}
