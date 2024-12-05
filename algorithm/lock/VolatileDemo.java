package com.chenjunyi.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * volatile 是jvm 虚拟机提供的轻量级的同步机制
 */
public class VolatileDemo {
    static class MyData {
        volatile int number = 0;
        AtomicInteger atomicInteger = new AtomicInteger();
        /*synchronized*/ void addPlusPlus() {
            number++;
        }

        void atomicAdd() {
            atomicInteger.incrementAndGet();
        }

    }


    private static volatile  boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
             //atomicTest();
        AtomicReference<String> ato = new AtomicReference<>("");
        //ato.compareAndExchange("as", "bc");
        ato.compareAndSet("as", "bc");
        System.out.println(ato.get());

    }

    // volatile 不保证原子性的，解决方式： 使用synchorinzed或者atomic
    private static void atomicTest() throws InterruptedException {
        MyData data = new MyData();
        // 20个线程  每个线程执行1000次的++操作
        for (int i =0; i < 20; i++) {
            new Thread(() -> {
                for (int j =0; j < 1000; j++) {
                    data.addPlusPlus();
                    data.atomicAdd();
                }

            }).start();
        }
        //Thread.sleep(5000);

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println("main number: " + data.number + ", atomic: " + data.atomicInteger);

    }

    private static void visibilityTest() throws InterruptedException {
        Thread t1 = new Thread(()->{
            flag = false;
            System.out.println("t1已经修改了变量flag=false!");


        });

        Thread t2 = new Thread(()->{
            while (flag) {

            }
        });

        t2.start();
        Thread.sleep(100);
        t1.start();
        t1.join();
        t2.join();

        System.out.println("==");

    }
}
