package com.chenjunyi.Queue;

import java.util.Stack;

/**
 * 使用两个栈来实现队列
 */
public class QueueWithTwoStacks {

    Stack<Integer> s1;    // 只入数据
    Stack<Integer> s2;

    public QueueWithTwoStacks() {
        s1 = new Stack();
        s2 = new Stack();
    }

    public boolean isEmpty() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.isEmpty();
    }

    public void enQueue(int value) {
        s1.push(value);
    }

    public int deQueue() {
        if (!s2.isEmpty()) {
            return s2.pop();
        }  else {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            return s2.pop();
        }

    }


}
