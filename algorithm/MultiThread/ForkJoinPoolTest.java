package com.chenjunyi.MultiThread;

import java.util.concurrent.*;

/**
 * ForkJoinPool 线程池
 */
public class ForkJoinPoolTest {
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(5,5,30L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(20));

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Future<Integer> future =   executor.submit(()->{
//            int sum = 0;
//            for (int i =0; i <= 10000; i++) {
//                sum += i;
//            }
//            return sum;
//        });
//
//        Integer res = future.get();
//        executor.shutdown();
//        System.out.println(res);

        // 提交一个大任务，会将大人物拆分成很多个小任务，通过10个线程去执行
        ForkJoinPool executor = new ForkJoinPool(10);

        // 大任务
        SumTask task = new SumTask(1,10000);

        // 拆分任务不是主线程执行，
        ForkJoinTask<Long> future = executor.submit(task);
        if (future.isCompletedAbnormally()) {
            System.out.println(future.getException());
        }

        System.out.println("result: " + future.get());
        executor.shutdown();

    }
}
