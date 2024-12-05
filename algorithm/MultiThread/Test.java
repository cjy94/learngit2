package com.chenjunyi.MultiThread;


import java.util.concurrent.Semaphore;

public class Test {
    private static final Semaphore s = new Semaphore(9, false);

    static class Two implements Runnable {

        @Override
        public void run() {
            while (true){
                final boolean acquire = s.tryAcquire(2);
                if (acquire){
                    System.out.println("2号猴子拿到桃子 " + System.nanoTime());
                } else {
                    System.out.println("资源不足,2号退出: " + System.nanoTime());
                    break;
                }
            }
        }
    }

    static class Three implements Runnable {

        @Override
        public void run() {
            while (true){
                final boolean acquire = s.tryAcquire(3);
                if (acquire){
                    System.out.println("3号猴子拿到桃子: " + System.nanoTime());
                } else {
                    System.out.println("资源不足,3号退出: " + System.nanoTime());
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Two two = new Two();
        Three three = new Three();
        Thread twoThread = new Thread(two);
        Thread threeThread = new Thread(three);

        // 开始消费
        System.out.println("开始消费");
        threeThread.start();
        twoThread.start();
    }
}

