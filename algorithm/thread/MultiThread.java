package com.chenjunyi.thread;

/**
 *  计算机体系结构、操作系统、编译程序都做了贡献
 *  cpu增加了缓存，以协调与内存的速度差异    // 导致可见性问题
 *  操作系统增加了进程、线程，以分时复用cpu, 均衡cpu与i/o设备的速度差异    // 导致原子性问题
 *  编译器进行指令重排，     // 导致有序性问题
 *
 *
 *  并发出现不安全的本质： 原子性、有序性、可见性其中一个或者几个原则被打破导致线程不安全
 *  并发三要素： 原子性、可见性、有序性
 *  可见性：一个线程对共享变量进行修改，另一个线程能够立刻看到  volatile
 *  原子性： 分时复用引起，cpu1执行一条指令后，就切换到cpu2执行另一条指令， i++
 *  有序性：再执行程序时，为了提高性能，编译器和处理器会进行指令重排序
 *      对于编译器，JMM编译器重排序规则禁止特定类型的编译器重排序； 对于处理器，JMM的处理器重排序规则要求java在生成指令序列时，插入特定的内存屏障指令，通过内存屏障禁止特定类型的处理器重排序
 *
 *  java解决并发问题： JMM(java 内存模型)
 *
 *  happens-before
 *  1、程序顺序规则：
 *  2、监视器规则：对监视器的解锁，happens-before随后对这个监视器的加锁
 *  3、volatile规则： 对volatile变量的写操作，happens-before于任意后续对这个变量的读
 *  4、传递性规则： A happens before B, B happens before C,那么 A happens-before C
 *
 *
 *  as if serial: 不管怎么重排序，单线程程序执行结果不能被改变
 *
 *  java内存模型规范了JVM如何提供按照需要来禁用缓存和编译优化的办法， 方法包括： volatile、final、synchronized、Happens-before规则
 *
 */

public class MultiThread {
}
