package com.chenjunyi.JVM;



/**
 *  死锁相关
 *  避免死锁：
 *  1、避免一个线程同时获得多个锁
 *  2、避免一个线程在锁内部占有多个资源，尽量保证每个锁只占用一个资源
 *
 *
 *  同步 & 异步  都是形容一次方法的调用
 *  同步：调用一开始，调用者必须等待被调用的方法结束后，调用者后面的代码才能执行。
 *  异步：调用者不用管被调用方法是否完成，都会继续执行后续的代码，当被调用的代码执行完成后，会通知调用者
 *  
 *  
 */
public class DeadLock {

    private static String a = "A";
    private static String b = "B";

    public static void main(String[] args) {
        deadLock();

    }

    private static void deadLock() {
        Thread A = new Thread(()->{
            synchronized (a) {
                System.out.println("get a");
                try {
                    Thread.sleep(3000);
                    synchronized (b) {
                        System.out.println("get b");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }

        }, "a");

        Thread B = new Thread(()->{
            synchronized (b) {
                System.out.println("get b");
                try {
                    Thread.sleep(3000);
                    synchronized (a) {
                        System.out.println("get b");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "b");

        A.start();
        B.start();

    }

}
