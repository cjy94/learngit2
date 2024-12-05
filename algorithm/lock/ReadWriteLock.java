package com.chenjunyi.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock: 会发生写饥饿的情况
 * 因为读写互斥，所以当有一个线程获取到读锁，并且读锁被长期占用时，那么就无法进行写入的操作，出现写饥饿的情况
 * jdk1.8中出现了StampLock 可以优化这个问题，
 */

public class ReadWriteLock {
    /**
     * 实现一个简单的读写缓存
     * 功能
     * 1、缓存主要提供两个功能： 读和写
     * 2、读时如果缓存中存在数据，则立即返回数据
     * 3、读时如果缓存中不存在数据，则需要从其他途径获取数据，同时写入缓存
     * 4、在写入缓存的同时，为了避免其他线程同时获取这个缓存中不存在的数据，需要阻塞其他读线程
     */

    static class MyReadWriteCache{
        HashMap<String, String> map = new HashMap<>(2);

        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock rl = lock.readLock();
        ReentrantReadWriteLock.WriteLock wl = lock.writeLock();

        // 根据ReentrantReadWriteLock源码中给出的CacheData中的例子，写出一个读写缓存
        // 获取读锁
        //rl.lock() 从缓存中拿数据，如果缓存中有数据，则读取
        // 如果缓存中没有数据，释放读锁，rl.unlock;获取写锁wl.lock() (...写入数据...)
        // rl.lock() 获取写锁，
        void operatorCache() {
            // 创建多个线程操作缓存
            for (int i = 0; i < 10; i++) {
                final String key = String.valueOf(i%2);
                final String value = String.valueOf(i);
                Thread th = new Thread(()->{
                   // 获取读锁
                    rl.lock();
                    try {
                        // 获取数据
                        String strValue = map.get(key);
                        if (strValue == null) {
                            // 释放读锁后再尝试获取写锁
                            rl.unlock();
                            // 获取写锁，写入数据
                            wl.lock();
                            try {
                                strValue = map.get(key);
                                // recheck 因为可能其他线程已经获取到写锁，并将值写入
                                if (strValue==null) {
                                    System.out.println(Thread.currentThread().getName()+"\t 写入数据: " + key);
                                    map.put(key, value);
                                    System.out.println(Thread.currentThread().getName()+"\t 写入数据完成!");
                                }
                              // 有一个操作
                                // 锁降级， 再释放写锁之前
                                rl.lock();

                            } finally {
                                // 释放写锁
                                wl.unlock();

                            }

                        } else {
                            // 如果数据存在，则直接取数据，并执行finally中的释放锁操作
                            System.out.println(Thread.currentThread().getName()+"\t 读取数据");
                            String v = map.get(key);
                            System.out.println(Thread.currentThread().getName()+"\t 读取结束: " + v);
                        }
                    } finally {
                        //释放读锁
                        rl.unlock();
                    }
                }, String.valueOf(i));
                th.start();


            }
        }
    }





 class CachedData {
   Object data;
   boolean cacheValid;
   final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

   void processCachedData() {
     rwl.readLock().lock();
     if (!cacheValid) {
       // Must release read lock before acquiring write lock
       rwl.readLock().unlock();
       rwl.writeLock().lock();
       try {
         // Recheck state because another thread might have
         // acquired write lock and changed state before we did.
         if (!cacheValid) {
           data = new Object();
           cacheValid = true;
         }
         // Downgrade by acquiring read lock before releasing write lock
         rwl.readLock().lock();
       } finally {
         rwl.writeLock().unlock(); // Unlock write, still hold read
       }
     }

     try {
     //  use(data);
     } finally {
       rwl.readLock().unlock();
     }
   }
 }



    private static class MyCache {
        volatile Map<String, Object> map = new HashMap<>();
        // 使用原始的ReentrantLock
        ReentrantLock lock = new ReentrantLock();
        // 初始化sync根据fair or noFair
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        public void write(String key, Object value) {

            //lock.lock();
             readWriteLock.writeLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t 正在写入: " + key);
                map.put(key, value);
                System.out.println(Thread.currentThread().getName() + "\t 写入完成");

            } finally {
               // lock.unlock();
                readWriteLock.writeLock().unlock();

            }

        }

        public void read(String key) {
            // 使用传统的lock，在读取时也进行加锁， 其实在读取时，如果没有写入线程不用每次都执行顺序读操作，可以并发读
           // lock.lock();
            // 使用readWriteLock
             readWriteLock.readLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t 正在读取");
                Object result = map.get(key);
                System.out.println(Thread.currentThread().getName() + "\t 读取完成: " + result);
            } finally {
               // lock.unlock();
                readWriteLock.readLock().unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
//           MyReadWriteCache cache = new MyReadWriteCache();
//           cache.operatorCache();
//         MyCache cache = new MyCache();
//        for (int i = 0; i < 10; i++) {
//            final int temp = i;
//            new Thread(()->{
//                cache.write(String.valueOf(temp), temp);
//            }, String.valueOf(i)).start();
//
//        }
//        // 保证先写，再读
//        Thread.sleep(300);
//
//        for (int i = 0; i < 10; i++) {
//            final int temp = i;
//            new Thread(()->{
//                cache.read(String.valueOf(temp));
//            }, String.valueOf(i)).start();
//
//        }
//
//        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
//        lock.writeLock().lock();
//        lock.readLock().lock();

        ReentrantReadWriteLockCacheSystem();
        Thread.sleep(2000);
        System.out.println("==========================================");
        operatorCache();






    }


    public static void operatorCache() {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        HashMap<String, String> map = new HashMap<>(2);
        ReentrantReadWriteLock.ReadLock rl = lock.readLock();
        ReentrantReadWriteLock.WriteLock wl = lock.writeLock();
        // 创建多个线程操作缓存
        for (int i = 0; i < 10; i++) {
            final String key = String.valueOf(i % 2);
            Thread th = new Thread(()->{
                // 获取读锁
                rl.lock();
                try {
                    // 获取数据
                    String strValue = map.get(key);
                    if (strValue == null) {
                        // 释放读锁后再尝试获取写锁
                        rl.unlock();
                        // 获取写锁，写入数据
                        wl.lock();
                        try {
                            strValue = map.get(key);
                            // recheck 因为可能其他线程已经获取到写锁，并将值写入
                            if (strValue==null) {
                                strValue = key + "---- value";
                                System.out.println(Thread.currentThread().getName()+"\t put: " + key);
                                map.put(key, strValue);
                            }
                            // 有一个操作
                            // 锁降级， 再释放写锁之前
                            rl.lock();
                            System.out.println(Thread.currentThread().getName()+"\t"+"get new value: " + strValue);

                        } finally {
                            // 释放写锁
                            wl.unlock();

                        }

                    } else {
                        // 如果数据存在，则直接取数据，并执行finally中的释放锁操作
                        String v = map.get(key);
                        System.out.println(Thread.currentThread().getName()+"\t get cache: " + v);
                    }
                } finally {
                    //释放读锁
                    rl.unlock();
                }
            }, String.valueOf(i));
            th.start();


        }
    }

    public static void ReentrantReadWriteLockCacheSystem() {

        //这里为了实现简单，将缓存大小设置为4。
        Map<String, String> cacheMap = new HashMap<>(2);
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        for (int i = 0; i < 10; i++) { //同时开启20个线程访问缓存

            final String key = String.valueOf(i % 2);
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        //①读取缓存时获取读锁
                        readWriteLock.readLock().lock();
                        //获取读锁后通过key获取缓存中的值
                        String valueStr = cacheMap.get(key);
                        //缓存值不存在
                        if (valueStr == null) {
                            //③释放读锁后再尝试获取写锁
                            readWriteLock.readLock().unlock();
                            try {
                                //④获取写锁来写入不存在的key值，
                                readWriteLock.writeLock().lock();
                                valueStr = cacheMap.get(key);
                                if (valueStr == null) {
                                    valueStr = key + " --- value";
                                    cacheMap.put(key, valueStr); //写入值
                                    System.out.println(Thread.currentThread().getName() + " --------- put " + valueStr);
                                }
                                // ⑥锁降级，避免被其他写线程抢占后再次更新值，保证这一次操作的原子性
                                readWriteLock.readLock().lock();
                                System.out.println(Thread.currentThread().getName() + " --------- get new " + valueStr);
                            } finally {
                                readWriteLock.writeLock().unlock(); //⑤释放写锁
                            }

                        } else {
                            System.out.println(Thread.currentThread().getName() + " ------ get cache value");
                        }
                    } finally {
                        readWriteLock.readLock().unlock();  //②释放读锁
                    }
                }
            }, String.valueOf(i));
            thread.start();
        }
    }


}
