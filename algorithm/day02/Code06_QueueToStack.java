package com.chenjunyi.day02;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Code06_QueueToStack {
    public static void main(String[] args) {
        MyStack<Integer> my = new MyStack<>();
        my.push(12);
        my.push(13);
        my.push(14);
        my.push(15);
        System.out.println(my.pop());     // 15
        my.push(16);
        my.push(17);
        System.out.println(my.pop());          // 17
    }

    public static class MyStack<T> {
        private Queue<T> dataQueue;
        private Queue<T> helpQueue;
        public MyStack() {
            dataQueue = new LinkedBlockingQueue<>();
            helpQueue = new LinkedBlockingQueue<>();
        }

        // 先将元素添加到helpQueue队列中，如果dataQueue中有元素，那么将data中的元素放到help 队列的尾部
        public void push(T value) {
            helpQueue.offer(value);
            while (!dataQueue.isEmpty()) {
                helpQueue.offer(dataQueue.poll());
            }
            Queue<T> temp = dataQueue;
            dataQueue = helpQueue;
            helpQueue = temp;

        }

        public T pop() {
            return dataQueue.poll();
        }

    }
}
