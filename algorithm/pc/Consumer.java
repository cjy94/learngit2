package com.chenjunyi.pc;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

    private BlockingQueue<Integer> queue;
    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;

    }

    @Override
    public void run() {
        while (true){
            try {
               // Thread.sleep(100);
                if(queue.size() == 0) System.out.println("=============the queue is empty,the consumer thread is waiting................");
                Integer item = queue.take();
                System.out.println("consumer:" + Thread.currentThread().getName() + " consume:" + item+";the size of the queue:" + queue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        

    }
}
