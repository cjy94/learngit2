package com.chenjunyi.pc;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;

    }

    @Override
    public void run() {
        Random r = new Random();
        while (true){
            try {
                //Thread.sleep(100);
                if(queue.size() == 10) System.out.println("================the queue is full,the producer thread is waiting..................");
                int i = r.nextInt(100);
                queue.put(i);
                System.out.println("producer:" + Thread.currentThread().getName() + " produce:" + i+"; the size of the queue:" + queue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
