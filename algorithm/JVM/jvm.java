package com.chenjunyi.JVM;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *  -XlogGC
 *
 *  thread local allocation buffer(TLAB)
 *
 *   通过java代码获取JVM参数
 *
 *   ManagementFactory: 监视和管理java虚拟机和java运行时中的其他组件，
 *   Runtime类可以获取一些内存、CPU核数等相关的数据
 *
 *   分区算法： G1 垃圾收集
 *
 *   垃圾回收的相关概念：
 *   安全点
 *   安全区域
 *   System.gc(): 发生的是full gc， 提醒jvm的垃圾回收器执行gc,但是不确定是否马上执行
 *      = Rumtime.getRuntime().gc()
 *   底层调用Runtime.getRuntime().gc()
 *
 *
 *
 *
 * 垃圾收集器： 经典的7种
 * Serial GC：串行垃圾收集器，最基本，最悠久的垃圾收集器  client模式下的新生代默认收集器
 *      采用复制算法， 串行回收
 *
 *      优势： 简单而高效，限定在单个CPU的环境来说，专心做垃圾收集获得最高的单线程的收集效率
 *      新生代用Serial GC, 老年代使用Serial Old GC
 *      -XX:+UseSerialGC 表明新生代使用Serial GC，对应的老年代使用Serial Old GC
 *
 * ParNew GC: 并行垃圾回收器，Parallel并行， new新生代
 *      新生代采用复制算法， 在Server模式下的默认垃圾收集器
 *      -XX:+UseParNewGC 表示年轻代使用并行垃圾收集器，不影响老年代   jdk9及以后的版本会移除
 *      -XX:ParallelGCThreads 限制线程数量
 *      新生代 ParNewGC, 老年代 CMS
 *
 * Parallel GC: 吞吐量优先垃圾回收器 回收新生代，采用复制算法
 *      和ParNew GC收集器不同，目标是达到一个可控制的吞吐量，也被称为吞吐量优先的垃圾回收器
 *      老年代是Parallel Old GC(采用标记-压缩算法， 也是并行回收), 用来替换Serial Old GC
 *      自适应调节策略：
 *      高吞吐量：适合后台运行，和用户交互不太多的场景
 *      JDK8 默认使用的
 *      -XX:MaxGCPauseMillis
 *      -XX:GCTimeRatio
 *
 * CMS: Concurrent Mark Sweep (低延迟垃圾收集器)  第一款真正意义上的并发垃圾收集器
 *      主要关心的点：尽可能在垃圾收集期间缩短用户线程的停顿时间
 *      采用标记-清除算法，空间碎片化，分配内存采用空闲链表的方式
 *      与新生代的垃圾收集Serial GC、ParNew GC配合使用
 *      后备方案： Serial Old GC
 *
 *    工作原理：
 *      1、初始标记：标记出GC Roots能直接关联的对象， 执行速度非常快    stw
 *      2、并发标记：从GC Roots直接关联的对象开始遍历整个对象图  时间较长    和用户线程并发执行
 *      3、重新标记：修正并发标记期间，用户线程运行而导致的一部分对象变动， 比并发标记时间短 stw
 *      4、并发清理：和用户线程并发执行
 *      为什么不采用标记-压缩算法呢？ 并发清理过程是和用户线程并发执行的，为了保证用户线程的正确执行，所以不能采用标记-压缩算法
 *
 *    弊端：
 *      1、内存碎片化：
 *      2、CMS对cpu资源非常敏感：
 *      3、无法处理浮动垃圾：并发标记环节是和用户线程并发执行的，可能之前初始标记时不是垃圾，但是此阶段变成了垃圾，但是这些垃圾在本次垃圾收集阶段无法被清除掉
 *
 * 总结：
 *   最小化内存和并行开销：Serial GC
 *   最大化应用程序额吞吐量：ParNew GC
 *   最小化GC的终端和停顿时间：CMS
 *
 * G1 GC： 区域化分代式收集  全功能的垃圾收集器
 *  在延迟可控的情况下尽可能高的吞吐量
 *  并行回收器， 把堆内存分割成多个region， 使用不同的region表示Eden,S0,S1,Old
 *  优先回收价值比较大的region，维护空闲的region链表
 *  主要针对配备多核CPU以及大容量内存的机器
 *  jdk9 默认垃圾回收器  -XX:+UseG1GC
 *  -XX:+UseG1GC
 *
 *  特点：
 *     1、并行与并发： 并行发生stw
 *     2、分代收集：
 *     3、空间整合：region之间是复制算法，从整体上看又是标记-压缩算法，两种算法都可以避免内存碎片化
 *     4、可预测的停顿时间模型： 能够让使用者明确指定在一个长度为m毫秒的时间片段内，消耗在垃圾收集上的时间不得超过n毫秒
 *
 *  缺点：
 *      1、资源使用较高
 *      2、小内存上表现不佳，大内存上发挥其优势
 *   G1 GC可以采用应用程序的线程承担后台运行的GC工作
 *
 *  回收过程：
 *   1、年轻代
 *   2、老年代并发标记过程
 *   3、混合回收
 *
 * 年轻代：Serial GC 、ParNew GC、Parallel GC(jdk8默认)
 * 老年代: Serial OldGC、CMS、 Parallel Old GC
 *  G1GC： 区域化分代式收集   在延迟可控的情况下尽可能高的吞吐量
 *  CMS: 尽可能在垃圾收集期间，缩短用户线程的停顿时间
 *  Parallel GC: 吞吐量优先的垃圾收集器，适合后台运行，和用户交互不太多的场景
 *
 */
public class jvm {

    /**
     *
     */
    public static void main(String[] args) throws ClassNotFoundException {
//        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
//        MemoryUsage usage = memoryMXBean.getHeapMemoryUsage();
//        System.out.println("INIT HEAP: " + usage.getInit() / 1024 / 1024 + "M");
//        System.out.println("MAX HEAP: " + usage.getMax() / 1024 / 1024 + "M");
//        System.out.println("USE HEAP: " + usage.getUsed() / 1024 / 1024 + "M");
//        System.out.println("\nFull Information:");
//        System.out.println("Heap Memory Usage: " + memoryMXBean.getHeapMemoryUsage());
//        System.out.println("Non-Heap Memory Usage: " + memoryMXBean.getNonHeapMemoryUsage());
        String s = "jijsiajisjais哈哈jisjiajsiajijodlkcoldk哈哈jidjisjisjdsi哈哈";
        int a = s.indexOf("哈哈");//*第一个出现的索引位置
//        while (a != -1) {
//            System.out.print(a + "\t");
//            a = s.indexOf("哈哈", a + 1);//*从这个索引往后开始第一个出现的位置
//        }

        jvm J =new jvm();

        // 获取Class 方式
        Class<?> aClass = Class.forName("com.chenjunyi.JVM.jvm"); //1  只有这种是反射
        Class clazz1 = jvm.class;   //2
        Class clazz2 = J.getClass();  // 3
        
        System.out.println(aClass == J.getClass());

        


    }

    /**
     *  1、每一个任务加一个try-catch
     *  2、Thread 类中右一个UncaughtExceptionHandler(Thread, Throwable)
     */
    public static void executeTaskWithException(){
        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.execute(()->{
            int i = 10/0;
        });
        
        executor.submit(()->{    // 如果不调用get方法， 则不会抛出异常
            int i = 10/0;
            System.out.println("submit方法执行...");
            return "执行";
        });

        executor.shutdown();
    }
}
