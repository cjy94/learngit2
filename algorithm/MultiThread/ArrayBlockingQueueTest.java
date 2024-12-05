package com.chenjunyi.MultiThread;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 使用阻塞队列实现 生产者消费者模型
 */
public class ArrayBlockingQueueTest {


    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        // 消费数据
        List<Thread> consumerThreads = Stream.generate(() -> new Thread(() -> {
            while (true) {
                try {
                    String task = queue.take();
                    System.out.println(Thread.currentThread().getName() + " consumer task: " + task);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        })).limit(3).collect(Collectors.toList());
        consumerThreads.forEach(Thread::start);

        // 生产数据
        Thread producer = new Thread(()->{
           for (int i =0; i < 10; i++) {
               if (queue.offer("t-"+i)) {    // 生产数据成功
                   System.out.println(Thread.currentThread().getName() + " success offer task: t-" + i);
                   System.out.println(Thread.currentThread().getName() + " current taskQueue = " + queue);
               } else {            // 生产数据失败
                   System.out.println(Thread.currentThread().getName() + " fail to offer task: " + i);
                   System.out.println(Thread.currentThread().getName() + " current taskQueue = " + queue);
                   
               }
               try {
                   Thread.sleep(10);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });
        producer.start();
        producer.join();
        Thread.sleep(2000);
        consumerThreads.forEach(Thread::interrupt);
    }
}
