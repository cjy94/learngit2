package com.chenjunyi.MultiThread;

import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
       // ThreadPoolExecutor e = new ThreadPoolExecutor(3, 6, 1, TimeUnit.MILLISECONDS, );
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(ThreadPoolTest::fetchPrice);
        // 如果执行成功
        // 异步任务结束时，会自动回调某个对象的方法
        cf.thenAccept((result) -> {
            System.out.println("price: " + result);

        });

        // 异步任务出错时，会自动回调某个对象的方法
        cf.exceptionally((e)->{
            e.printStackTrace();
            return null;
        });
        Thread.sleep(200);


    }

    static Double fetchPrice() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Math.random() 等概率返回[0,1) 之间的小数

        if (Math.random() < 0.3) {
            throw new RuntimeException("fetch price failed!");

        }
        // Math.random() * 20 等概率随机返回[0,20)之间的小数
        return 5 + Math.random() * 20;
    }
    
}
