package com.chenjunyi.StackAndQueue;

import java.util.Stack;

public class TwoStackToQueue {
    Stack<Integer> pushStack = new Stack<>();
    Stack<Integer> popStack = new Stack<>();

    public void push(Integer data) {
        pushStack.push(data);
        dao();

    }

    public Integer pop() {
        int value = popStack.pop();
        dao();
        return value;
    }

    public void dao() {
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
    }

    public boolean isEmpty() {
        return popStack.isEmpty();
    }

    public static void main(String[] args) {
        TwoStackToQueue queue = new TwoStackToQueue();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);

        while (!queue.isEmpty()) {
            System.out.println(queue.pop());
        }



    }
}
