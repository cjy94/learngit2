package com.chenjunyi.JVM;

/**
 *  单例模式
 */
public class Singleton {
    /**
     * instance 不使用volatile修饰时，是不正确的
     * instance = new Singleton();  这句话在执行时会分成三步；
     *         (1) malloc() 申请内存空间；
     *         (2) 调用Singleton构造函数，初始化对象；
     *         (3) 将instance执行分配的内存地址
     *
     * 因为instance是不被volatile修饰的， 所以以上三个步骤可能会被编译器或者处理器进行重排序，重排序的结果可能是：
     *         (1) malloc() 申请内存空间；
     *         (3) 将instance执行分配的内存地址；
     *         (2) 调用Singleton构造函数，初始化对象；
     *
     *  如果这时候线程A在执行完步骤(1)和(3)后，正在执行(2)还没有执行完，有一个线程B来执行getinstance()方法，
     *      在instance==null时判断到是false，那么就会直接返回instance，而这个时候的instance是一个没有执行完初始化的对象，就是不正确的
     *
     *
     *  Java对象实例化过程：
     *      (1) 分配内存空间，赋默认初值， 基本数据类型0，引用数据类型null
     *      (2) 父类属性初始化
     *      (3) 父类构造函数初始化
     *      (4) 子类属性初始化
     *      (5) 子类构造函数初始化
     *
     *
     *  参考文档：   https://juejin.cn/post/6844903772892692487
     *
     */
    private static Singleton instance;
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null)
                    instance = new Singleton();
            }
        }
        return instance;
    }
    private Singleton() {}
}
