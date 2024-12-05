package com.chenjunyi.day02;

import java.util.PriorityQueue;
import java.util.Stack;

/**
 * 实现一个特殊的栈，再基本功能的基础上，再实现返回栈中最小元素的功能。pop() push() getMin() 操作时间复杂度O(1)
 * 可以使用线程的栈结构
 *
 * 使用两个栈，一个栈正常放入数据，另一个栈则放入当前数据和minStack栈顶元素小的元素
 */

public class Code05_MyStack {
    public static void main(String[] args) {
        MyStack<String> myStack = new MyStack<>();
       myStack.push("a");
        myStack.push("b");
        myStack.push("c");
        myStack.push("a");
        myStack.push("a");
        myStack.push("c");
        myStack.pop();
        myStack.pop();
        myStack.pop();
        System.out.println(myStack.getMin());
    }
    public static class MyStack<T>  {
        
        Stack<T> dataStack;
        Stack<T> minStack;

        public MyStack() {
            dataStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(T value) {
            Comparable<? super T> key = (Comparable<? super T>) value;
            if(minStack.isEmpty()) {
                minStack.push(value);
            } else if(key.compareTo((T) getMin()) <= 0) {
                minStack.push(value);
            } else {
                T newValue = minStack.peek();
                minStack.push(newValue);
            }
            dataStack.push(value);
        }

        private Object getMin() {
            if (minStack.isEmpty()) {
                throw new RuntimeException("栈为空");
            }
            return minStack.peek();
        }

        public T pop(){
            if (dataStack.isEmpty()) {
                throw new RuntimeException("栈为空");
            }
            minStack.pop();
            return dataStack.pop();
        }
    }
}
