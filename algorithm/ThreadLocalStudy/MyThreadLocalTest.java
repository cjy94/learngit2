package com.chenjunyi.ThreadLocalStudy;

import java.lang.reflect.Field;

public class MyThreadLocalTest {

    private static ThreadLocal<String> local = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
//        Thread t = new Thread(()->test("abc",false));
//        t.start();
//        t.join();
//        System.out.println("--gc后--");
//        Thread t2 = new Thread(() -> test("def", true));
//        t2.start();
//        t2.join();

        // main线程
        local.set("main thread");
        System.out.println(Thread.currentThread().getName() + " 线程字面量是： " + local.get());
        // t1线程

        Thread t1 = new Thread(()->{
            local.set("t1 thread");
            System.out.println(Thread.currentThread().getName()+" 线程字面量是： "+local.get());


        }, "t1");
        
        t1.start();
        t1.join();
        System.out.println(Thread.currentThread().getName() + " 线程 最后的字面量是： " + local.get());












    }
    


    private static void test(String s, boolean isGC) {
        try {
            new ThreadLocal<>().set(s);
            if (isGC) {
                System.gc();
            }
            Thread t = Thread.currentThread();
            Class<? extends Thread> clz = t.getClass();
            Field field = clz.getDeclaredField("threadLocals"); // ThreadLocal.ThreadLocalMap
            field.setAccessible(true);
            Object threadLocalMap = field.get(t); // 调用ThreadLocal.ThreadLocalMap.get() 方法
            Class<?> tlmClass = threadLocalMap.getClass();
            Field tableField = tlmClass.getDeclaredField("table");
            tableField.setAccessible(true);
            Object[] arr = (Object[]) tableField.get(threadLocalMap);
            for (Object o : arr) {
                if (o != null) {
                    Class<?> entryClass = o.getClass();
                    Field valueField = entryClass.getDeclaredField("value");
                    Field referenceField = entryClass.getSuperclass().getSuperclass().getDeclaredField("referent");
                    valueField.setAccessible(true);
                    referenceField.setAccessible(true);
                    System.out.println(String.format("弱引用key:%s,值:%s", referenceField.get(o), valueField.get(o)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
        

}
