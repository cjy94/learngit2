package com.chenjunyi.JVM;

/**
 *
 *  垃圾标记算法：
 *  垃圾清除算法：
 *  垃圾回收器：
 *
 *  什么是垃圾： 垃圾是指在运行程序中没有任何指针指向的对象
 *      对象包括： 基本数据类型(标量)    引用数据类型(聚合量)
 *                基本数据类型不涉及GC
 *
 *  内存泄漏：对象本身不用了，但是GC又不能回收    对象没有正确释放
 *  内存溢出：创建了过多的对象，占用内存，导致超出设置的最大内存
 *
 *  早期垃圾收集器： 手动执行new和delete \  malloc和free
 *
 *  GC主要区域： 堆和方法区
 *  堆： 频繁收集Young区，较少收集Old区，基本不动方法区域(方法区、元空间)
 *
 *  垃圾回收相关算法：
 *  1、标记阶段： 引用计数算法、可达性分析算法(根搜索算法、追踪性垃圾收集)
 *     判断对象存活的方式
 *     如果使用可达性分析算法来判断内存是否可回收，那么分析工作必须在一个能保障一致性的快照中进行，所以需要stop the world
 *     object.finalize()， finalizer线程调用 GC线程调用， 如果没有发生GC，那么这个方法就不会被调用
 *
 *     由于finalize()方法的存在，虚拟机中的对象一般处于三种可能的状态，
 *          1、可触及的，
 *          2、可复活的 (在finalize()中复活)，
 *          3、不可触及的 (finalize()被调用，并且没有复活，那么会进入到不可触及状态)
 *
 *
 *
 *  2、清除阶段： 标记-清除算法、复制算法、标记压缩算法
 *
 *
 *
 *  GC Roots:哪些结构可以是GCRoots 虚拟机栈中引用的对象、方法区中的静态属性引用的对象、方法区中常量引用的对象、 系统类加载器
 *
 *   生成dump文件： /home/es/software/jdk1.8.0.181/bin/jmap  -dump:format=b,file=/data01/es/38482.dump  38482
 *
 *     /tmp/.java_pid$pid文件被删掉后会出现该问题
 *
 *      gdb -p 247630
 *      gcore /data01/jvm.core
 *      /home/es/software/jdk1.8.0_181/bin/jmap -dump:format=b,file=247630_2.hprof /home/es/software/jdk1.8.0_181/bin/java ./jvm.core
 *
 *
 *  方法区：
 *  Although the method area is logically part of the heap, simple implementations may choose not to either garbage collect or compact it.
 *  This specification does not mandate the location of the method area or the policies used to manage compiled code.
 *
 *
 *  评估GC的性能指标
 *   ***吞吐量：运行用户代码的时间占总运行时间的比例 （总运行时间=程序运行时间 + 内存回收的时间）  (0...1)之间的数值 越大越好， 越接近1越好
 *  垃圾收集的开销： 垃圾收集占用的时间与总运行时间的比例                                                       越小越好， 越接近0越好
 *   ***暂停时间：垃圾回收的时间，
 *  收集频率： 垃圾收集行为发生的频率
 *   ***内存占用： Java堆区所占用的内存大小
 *  快速： 一个对象从诞生到被回收所经历的时间
 *
 *  重点关注： (1) 吞吐量 和  (2) 暂停时间
 *
 *  现在的标准： 在最大吞吐量优先的情况下，降低暂停时间。 在可控的暂停时间内，提升吞吐量
 *
 *  java常见的垃圾回收器有哪些？
 *  1、Serial GC  单核CPU场景    ParallelNew GC
 *  2、CMS: Concurrent Mark Sweep    并发垃圾回收器， 低延迟
 *
 *  经典的垃圾回收器
 *  串行回收器： Serial 、Serial Old
 *  并行回收器   ParNew、Parallel Scavenge、Parallel Old
 *  并发回收器   CMS G1
 *
 *  新生代收集器：Serial  ParNew  Parallel Scavenge
 *  老年代收集器： Serial Old   Parallel Old   CMS
 *  整堆回收： G1
 *
 *
 *
 *
 *  java -XX:+PrintFlagsFinal
 *  jmap: 打印堆信息， 整个进程
 *  jstack: 打印JVM中线程快照
 *
 *
 */

public class GCRelated {
    public static void main(String[] args) {
        String str = "[";
        str += "abc";
        str += "gts";
        System.out.println(str);
    }

}
