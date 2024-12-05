package com.chenjunyi.day02;

import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 使用两个栈实现队列
 */
public class Code07_StackToQueue {
    public static void main(String[] args) {
        if (true) {
            MyQueue<Integer> myQueue = new MyQueue<>();
            myQueue.offer(1);
            myQueue.offer(2);
            myQueue.offer(3);
            myQueue.offer(4);
            System.out.println(myQueue.poll());
            System.out.println(myQueue.poll());
            myQueue.offer(5);
            System.out.println(myQueue.poll());
            System.out.println(myQueue.poll());
            System.out.println(myQueue.poll());
            myQueue.offer(6);
            System.out.println(myQueue.poll());
            System.out.println(myQueue.poll());
            System.out.println(myQueue.poll());
        }
        
        System.out.println("=================");
        if(true) {
            LinkedBlockingQueue<Integer> q = new LinkedBlockingQueue<>();
            q.add(1);
            q.add(2);
            q.add(3);
            q.add(4);
            System.out.println(q.poll());
            System.out.println(q.poll());
            q.add(5);
            System.out.println(q.poll());
            System.out.println(q.poll());
            System.out.println(q.poll());
            q.add(6);
            System.out.println(q.poll());
            System.out.println(q.poll());
            System.out.println(q.poll());
        }
    }

    public static class MyQueue<T> {
        Stack<T> dataStack;
        Stack<T> helpStack;

        public MyQueue() {
            dataStack = new Stack<>();
            helpStack = new Stack<>();
        }

        public void offer(T value) {
            dataStack.push(value);
        }

        public T poll(){
            if (dataStack.isEmpty() && helpStack.isEmpty()) {
                return null;
            } else {
                if (helpStack.isEmpty()) {  // 很重要： help为空才能导数据
                    while (!dataStack.isEmpty()) {
                        helpStack.push(dataStack.pop());
                    }
                }
            }
            return helpStack.pop();
        }
    }
}
