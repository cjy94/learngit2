package com.chenjunyi.day02;

import java.util.Arrays;
import java.util.HashMap;

public class Code04_ArrayToStackAndQueue {
    public static void main(String[] args) {
        MyStack<String> myStack = new MyStack<>(10);
        myStack.push("adc");
        myStack.push("sed");
        myStack.push("w3");
        myStack.push("中");
        myStack.push("e3dd");
        myStack.push("gtg");
        myStack.push("yui");
        myStack.push("89");
        myStack.push("vfv");
        myStack.push("mju");
        while (myStack.isEmpty() == false) {
            System.out.println(myStack.pop());
        }

        System.out.println("=====================");

        MyQueue<String> myQueue = new MyQueue<>(10);
        myQueue.push("wer");
        myQueue.push("中");
        myQueue.push("8989");
        System.out.println(myQueue.pop());
        System.out.println(myQueue.pop());
        myQueue.push("456");
        myQueue.push("hyhyh");
        myQueue.push("jjuju");
        myQueue.push("sws");
        myQueue.push("rfvgbyb");
        myQueue.push("898iu8uiu");
        myQueue.push("hggh");
        myQueue.push("er");
        myQueue.push("a");


        
    }

    public static class MyQueue<T> {
        private Object[] array;
        private int size;
        private int limit;
        private int pushIndex;
        private int popIndex;

        public MyQueue(int len) {
            array = new Object[len];
            limit = len;
        }

        public void push(T value) {
            if (limit == size)
                throw new RuntimeException("队列已满，不能加入");
            size++;
            array[pushIndex] = value;
            pushIndex = nextIndex(pushIndex);
        }

        public Object pop() {
            if (size == 0) {
                throw new RuntimeException("队列为空");
            }
            size--;
            Object value = array[popIndex];
            popIndex = nextIndex(popIndex);
            return value;
        }

        private int nextIndex(int i) {
            return i < limit-1 ? i+1 : 0;
        }



    }

    public static class MyStack<T> {
        int index = 0;
        Object[] array;

        public MyStack(int size) {
            array = new Object[size];
        }

        public void push(T value) {
            if (index == array.length) {
                throw new RuntimeException("栈已满，不能加入");
            }
            array[index++] = value;
        }

        public Object pop() {
            if (index == 0) {
                throw new RuntimeException("栈中没有元素");
            }
            Object value = array[--index];
            return value;
        }
        public boolean isEmpty() {
            return index == 0;
        }
    }



    
}
