package com.chenjunyi.lock;

public class SynchronizedDemo2 {

    public synchronized void method() {              //ACC_SYNCHRONIZED, 表示该方法是一个同步方法
        System.out.println("synchronized method");
    }
}
