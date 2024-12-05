package com.chenjunyi.MultiThread;

import java.util.concurrent.*;

/**
 * 线程池死锁模拟
 */
public class ThreadPoolDeadLock {
    // 创建一个核心线程和最大线程数是5，队列大小未20的线程池
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(5,5,30L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(20));

    private static ThreadPoolExecutor sonExecutor = new ThreadPoolExecutor(5,5,30L, TimeUnit.SECONDS,new ArrayBlockingQueue<>(20));

    public static void main(String[] args) throws InterruptedException {
        for (int i =0; i < 10; i++) {
            // 完线程池中放了10个异步任务， 线程池的coreSize是5， 索引只有5个线程会执行任务，剩下的5个任务会被放到ArrayBlockingQueue队列中
            // ThreadPoolDeadLock.work() 中又会往线程池中放入一个子任务，也就是10个父任务，没有父任务又有一个子任务，一共20个任务
            CompletableFuture.supplyAsync(ThreadPoolDeadLock::work, executor);
            System.out.printf("executor i=%s queue=%s", i, executor.getQueue().size());
            System.out.println();
        }

        while (executor.getQueue().size() > 0) {
            System.out.printf("checkInterval executor queue size: %s" , executor.getQueue().size());
            System.out.printf(", activeCount: %s ", executor.getActiveCount());
            System.out.printf(", completedTaskCount: %s" , executor.getCompletedTaskCount());
            System.out.printf(", taskCount: %s", executor.getTaskCount());
            System.out.println();
            // 每个1s钟检查一次线程池钟的任务情况
            Thread.sleep(1000);
        }

        System.out.println("main======");
        executor.shutdown();
        sonExecutor.shutdown();
    }
    
    // 模拟一个异步调用
    // 将任务放到线程池中
    public static int work() {
        try {
            System.out.println(String.format("work %s thread begin, queue=%s", Thread.currentThread().getName(), executor.getQueue().size()));


           // Integer result = CompletableFuture.supplyAsync(ThreadPoolDeadLock::workInnerTask, executor).get();
            // 解决线程池死锁，子任务使用其他线程，不和父任务共用一个线程池
            Integer result = CompletableFuture.supplyAsync(ThreadPoolDeadLock::workInnerTask, sonExecutor).get();

            System.out.println(String.format("work %s thread done!", Thread.currentThread().getName()));

            return result;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static int workInnerTask() {
        System.out.println(String.format("workInnerTask %s thread begin, queue=%s", Thread.currentThread().getName(), sonExecutor.getQueue().size()));
        try {
            Thread.sleep(2000);
            System.out.println(String.format("workInnerTask %s thread done!", Thread.currentThread().getName()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ThreadLocalRandom.current().nextInt();
    }
}
