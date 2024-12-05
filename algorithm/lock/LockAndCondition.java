package com.chenjunyi.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockAndCondition {
                   // 条件队列
    public static void main(String[] args) throws InterruptedException {
         ReentrantLock lock = new ReentrantLock();     // 默认的构造函数是非公平锁
        Condition con = lock.newCondition();   // 和独占锁相关
        Thread t1 = new Thread(()->{
            try{
                // t1线程可以获取到锁，state=1, thread=t1
                lock.lock();
                System.out.println("t1 get lock and await...");
                // t1线程创建条件队列firstWaitter和lastWaitter同时指向t1,
                // t1释放锁资源，state=0, 并将同步队列的head.ws=0，并t2.unpark()
                // 如果t1不在同步队列中，则执行t1.park()


                // 因为t2.signal() ，故t1线程被唤醒，在之前被park的位置后继续执行
                // 如果前驱节点是head节点，那么尝试获取锁，state=1, thread=t1

                // 当t2.signal() 并且 t2.unlock() 之后，t1线程被唤醒，且可以获取到锁
                // 至此， 同步队列中没有节点，head=tail, thread=null, state=0
                //       条件队列中没有被挂起的节点
                con.await();  // t1释放锁，阻塞等待被唤醒
                System.out.println("t1 continue...");
                Thread.sleep(2000);
                System.out.println("t1 return...");
            }catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // t1释放锁资源 state=0, thread=null
                // 因为t2释放资源的时候，设置了head.ws=0,故此处直执行state=0,thread=null即可
                lock.unlock();
            }
        });

        Thread t2 = new Thread(()->{
            try{
                // 因为t1获取到了锁，t2尝试获取锁不成功；创建同步队列，并将自己添加到同步队列中，并且head.ws=-1;执行park ，t2被挂起
                // 因为t1.await()释放了锁资源，并且将t2.unpark()唤醒，故t2在执行被park的地方继续执行
                // 如自己的前驱节点是head， 那么获取锁，并将自己设置为head节点，

                // 到此， 同步队列中没有要获取锁的节点，只有个head和tail节点，thread=null, ws = 0
                //       条件队列中有一个被挂起的节点，t1.ws=-2
                lock.lock();
                System.out.println("t2 get lock and signal...");
                Thread.sleep(2000);
                // 获取条件队列中的firstWaitter节点，即t1
                // 设置t1.ws=0， 并将t1添加到同步队列中
                // 并将t1的前驱节点(即head节点)的ws=-1
                // 并执行t1.unpark() 唤醒t1线程


                // 设置 t1.ws=0， 并将t1添加到同步队列中
                // 到此， 同步队列中有一个节点t1， head.ws=-1 head.next=t1 t1.ws=0
                //       条件队列中没有节点被挂起
                con.signal();
                System.out.println("t2 return...");

            }catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // 释放锁资源，state=0,thread=null
                // 设置head.ws =0
                // 执行t2.unpark()
                lock.unlock();
            }
        });

        t1.start();
        Thread.sleep(1000);
        t2.start();

        t1.join();
        t2.join();
        System.out.println("main exit!");
    }

    public void test() {

    }



}
