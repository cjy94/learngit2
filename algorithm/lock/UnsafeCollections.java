package com.chenjunyi.lock;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class UnsafeCollections {
   // static List<String> list = new CopyOnWriteArrayList(new ArrayList<>(10));

    /**
     * ArrayList 是线程不安全的集合
     */
    private static void unsafeArrayList() {
        //ArrayList<String> list = new ArrayList<>();
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 200; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

    }

    private static void unsafeHashSet() {
        Set<String> set = new HashSet<>();
        //CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
        /**
         * HashSet 不安全的set集合  当在打印的时候，会获取modCount变脸，但是add操作会修改该变量，导致出现 ConcurrentModificationException 异常
         * 
         */
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }

    }

    public static void main(String[] args) {
        //unsafeArrayList();
        unsafeHashSet();
        /**
         * 1 故障现象：
         *  java.util.ConcurrentModificationException
         *
         * 2、导致原因
         *  ArrayList 是线程不安全的集合
         *
         * 3、解决方案
         *  3.1 可以使用vector(1.0 jdk) 加了锁， 但是并发性下降
         *  ArrayList(1.2 jdk) 牺牲了安全性，并发性提高
         *
         *  3.2 Collections.synchronizedList(list);
         *          辅助工具类，将不安全的list集合，封装成安全的集合
         *  3.3 CopyOnWriteArrayList 写时复制
         * 4、优化建议
         */

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
    }
}
