package com.chenjunyi.thread;

/**
 *  join: 线程间协作的一种方式
 *  线程A实例执行threadB.join() 表示线程A等到线程B执行结束，再继续执行
 *
 *  以下代码演绎了 线程接力执行
 *  每个线程都会等待前一个线程结束才会继续执行
 *
 *
 *  sleep() 和 wait()
 *  sleep: 不会释放锁
 *  wait方法必须再同步函数或者同步代码块中执行， 必须获得对象锁， wait会释放占有的对象锁，使得线程进入等待池中，等待下一次获取资源。wait必须等待notify/notifyAll唤醒
 *  sleep方法可以在任何位置调用， sleep只会让出CPU不会释放锁， sleep在达到休眠时间后获得CPU时间片就会执行，
 *
 *
 *  yield: 调用该方法的线程让出CPU，让出的时间片只会分配给当前线程相同优先级的线程
 */
public class JoinTest {

    public static void main(String[] args) {
        Thread preThread = Thread.currentThread();
        for (int i =1; i <=10; i++) {
            JoinThread curThread = new JoinThread(preThread);
            curThread.start();
            preThread = curThread;
        }
    }

    static class JoinThread extends Thread {
        private Thread thread;
         JoinThread(Thread thread) {
             this.thread = thread;
         }

        @Override
        public void run() {
            try {
                thread.join();          //thread-0 线程调用main线程join() 表示thread-0线程等待main线程执行结束，再继续执行
                                        //thread-1 线程调用thread-0线程join() 表示thread-1线程等待thread-0线程执行结束，再继续执行
                                       // thread-2 线程调用thread-1线程join() 表示thread-2线程等待thread-1线程执行结束，再继续执行 ...
                System.out.println(thread.getName()+" terminated.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
