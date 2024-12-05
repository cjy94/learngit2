package com.chenjunyi.pc;

import java.util.concurrent.ArrayBlockingQueue;

public class Test {
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
        Thread p1 = new Thread(new Producer(queue));
        Thread p2 = new Thread(new Producer(queue));
//        Thread p3 = new Thread(new Producer(queue));
//        Thread p4 = new Thread(new Producer(queue));
//        Thread p5 = new Thread(new Producer(queue));

        p1.start();
        p2.start();
//        p3.start();
//        p4.start();
//        p5.start();

        Thread c1 = new Thread(new Consumer(queue));
        Thread c2 = new Thread(new Consumer(queue));
//        Thread c3 = new Thread(new Consumer(queue));
//        Thread c4 = new Thread(new Consumer(queue));
//        Thread c5 = new Thread(new Consumer(queue));
//        Thread c6 = new Thread(new Consumer(queue));
//        Thread c7 = new Thread(new Consumer(queue));
        c1.start();
        c2.start();
//        c3.start();
//        c4.start();
//        c5.start();
//        c6.start();
//        c7.start();

        try {
            p1.join();
            p2.join();
//            p3.join();
//            p4.join();
//            p5.join();

            c1.join();
            c2.join();
//            c3.join();
//            c4.join();
//            c5.join();
//            c6.join();
//            c7.join();

        }   catch (Exception e) {
            e.printStackTrace();
        }


    }

}
