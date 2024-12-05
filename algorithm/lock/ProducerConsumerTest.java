package com.chenjunyi.lock;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerTest {

    static final int FULL = 10;
    static int count = 0;
    static Object lock = new Object();
    static ReentrantLock reentrantLock = new ReentrantLock();
    static Condition notFull = reentrantLock.newCondition();
    static Condition notEmpty = reentrantLock.newCondition();

    static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);


    // 方式1： 使用synchorized和Object.wait() notify() 方法
    private static class Producer implements Runnable {
        @Override
        public void run() {
            // 如果集合中元素数量已经达到count数，则阻塞
            for (int i =0; i < FULL; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock) {
                    while (count == FULL) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {

                        }

                    }
                    // 否则，生产数据
                    count++;
                    System.out.println(Thread.currentThread().getName()+"\t 生产数据，数量: " + i);
                    lock.notifyAll();
                }
            }

        }
    }

    private static class Consumer implements Runnable {
        @Override
        public void run() {
            for (int i =0; i < FULL; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock) {
                    while (count == 0) {
                        // 消费者没有数据可消费，阻塞
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // 有数据，进行消费
                    count--;
                    System.out.println(Thread.currentThread().getName()+"\t 消费数据： "+i);
                    lock.notifyAll();
                }
            }
        }
    }


    // 方式2： 使用ReetrantLock方式
    private static class Producer1 implements Runnable {
        @Override
        public void run() {
            // 如果集合中元素数量已经达到count数，则阻塞
            for (int i =0; i < FULL; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                reentrantLock.lock();
                try{
                    while (count == FULL) {
                        try {
                            // 生产者阻塞
                            notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {

                        }

                    }
                    // 否则，生产数据
                    count++;
                    System.out.println(Thread.currentThread().getName()+"\t 生产数据，数量: " + i);
                    //lock.notifyAll();
                    // 唤醒消费者
                    notEmpty.signal();
                } finally {
                    reentrantLock.unlock();

                }
            }

        }
    }

    private static class Consumer1 implements Runnable {
        @Override
        public void run() {
            for (int i =0; i < FULL; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                reentrantLock.lock();
                try {
                    while (count == 0) {
                        // 消费者没有数据可消费，阻塞
                        try {
                            notEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // 有数据，进行消费
                    count--;
                    System.out.println(Thread.currentThread().getName()+"\t 消费数据： "+i);
                    //lock.notifyAll();
                    // 唤醒生产者
                    notFull.signal();
                } finally {
                    reentrantLock.unlock();

                }
            }
        }
    }

    // 方式3：阻塞队列的方式
    private static class Producer2 implements Runnable {
        @Override
        public void run() {
            // 如果集合中元素数量已经达到count数，则阻塞
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    queue.put(i);
                    System.out.println(Thread.currentThread().getName()+"\t 生产数据： " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Consumer2 implements Runnable {
        @Override
        public void run() {
            for (int i =0; i < FULL; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    queue.take();
                    System.out.println(Thread.currentThread().getName()+"\t 消费数据: " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
    }

    public static int mySqrt(int x) {
        if (x ==0) return 0;
        int l = 1;
        int r = x;
        while(l<=r) {
            int mid = ((r-l)>>1) + l;
            if (mid*mid == x) return mid;
            else if(mid * mid > x) r=mid-1;
            else l=mid+1;

        }
        return r;

    }

    public static void main(String[] args) {
        System.out.println(mySqrt(2147395599));
//        for (int i =0; i< 1; i++) {
//          new Thread(new Producer2(), String.valueOf(i)).start();
//        }
//
//        for (int i =1; i< 2; i++) {
//            new Thread(new Consumer2(), String.valueOf(i)).start();
//        }

    }
}
