package com.chenjunyi.thread;

/**
 * Java Memory Model： java内存模型
 *
 * 并发编成中需要解决的问题：1、线程之间如何通信； 2、线程之间如何完成同步
 * 线程间通信：1、共享变量； 2、wait/notify 消息传递
 * java内存模型是共享内存的并发模型，
 *
 * 1、哪些是共享数据： 放在堆内存、方法区中的对象，实例对象、静态变量
 *
 * CPU的处理速度和主存的处理速度不是一个量级的，为了平衡这种差距，每个CPU中都有缓存，L1,L2,L3缓存
 *
 *
 *
 *
 *
 */
public class JMM {
    //volatile: 汇编层面 是一个lock前缀指令
    // 缓存一致性协议：MESI 。 每个处理器通过嗅探总线上传播的数据来检查自己缓存的值是不是过期了
    public static volatile boolean flag = false;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            flag = true;
        }, "t1");

        Thread t2 = new Thread(() -> {
            while (!flag) {

            }
            System.out.println(Thread.currentThread().getName() + " flag: " + flag);
        }, "t2");

        t1.start();
        t2.start();
    }
}
