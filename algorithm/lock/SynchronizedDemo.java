package com.chenjunyi.lock;

public class SynchronizedDemo {
    public void method() {
        synchronized (this) {        // monitorEnter    monitorExit
            System.out.println("synchronized code");
        }
    }
}
