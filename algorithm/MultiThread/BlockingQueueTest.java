package com.chenjunyi.MultiThread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 阻塞队列： BlockingQueue
 */
public class BlockingQueueTest {

    /**
     * 实现一个简单的任务队列，又producer线程不断生成任务，也有消费线程不断消费任务，
     */

    public static void main(String[] args) throws InterruptedException {
        TaskQueue q = new TaskQueue();
        //ArrayBlockingQueue<String> q = new ArrayBlockingQueue<>(5);
        // 创建3个线程从 队列中去任务
        List<Thread> consumerThreads = Stream.generate(() -> new Thread(() -> {
            for (;;) {
                try {

                    System.out.println(Thread.currentThread().getName() + " consume task: " + q.getTask());
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName()+ " consumer finish!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }

        })).limit(3).collect(Collectors.toList());
        consumerThreads.forEach(Thread::start);

        Thread producerThread = new Thread(()->{
            for (int i =0; i < 10; i++) {
                // 生产任务
                String s = "t-" + i;
                System.out.println(Thread.currentThread().getName() +  " producer task: " + s);
                q.addTask(s);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            System.out.println("all producer thread dead!");
            
        });
        producerThread.start();
        producerThread.join();   // 等待消费线程执行结束
        Thread.sleep(5000);
        System.out.println("current task queue is: " + q.queue);
        consumerThreads.forEach(Thread::interrupt); // 因为还有消费线程因为queue.isEmpty() 导致被wait(), 而生成线程又u请按不退出了，导致被阻塞的消费线程不会被notify(), 所以需要interrupt
    }


    public static class TaskQueue{
        LinkedList<String> queue = new LinkedList<>();
        public synchronized void addTask(String task) {
            queue.addLast(task);
            notifyAll();
        }

        public synchronized String getTask() throws InterruptedException {
            while (queue.isEmpty()) {
                wait();
            }

            return queue.pollFirst();

        }
    }
}
