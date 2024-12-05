package com.chenjunyi.StackAndQueue;

import java.util.Stack;

/**
 * 使用栈结构，实现pop,peek,add，getMin(返回栈中最小元素), 要求时间复杂度O(1)
 */
public class GetMinWithStack {
    private Stack<Integer> stackData = new Stack<>();
    private Stack<Integer> stackMin = new Stack<>();

    public void add(Integer data) {
        stackData.push(data);
        if (stackMin.isEmpty()) {
            stackMin.push(data);
        } else {
            int value = stackMin.peek();
            if (value > data) {
                stackMin.push(data);
            } else {
                stackMin.push(value);
            }
        }
    }

    public Integer pop() {
        if(stackData.isEmpty()) {
            throw new IllegalArgumentException("stack is empty!!");
        }
        int value = stackData.pop();
        stackMin.pop();
        return value;
    }

    public Integer peek() {
        return stackData.peek();
    }

    public Integer getMin() {
        return stackMin.peek();
    }


    public boolean isEmpty() {
        return stackData.isEmpty();
    }


    public static void main(String[] args) {
        GetMinWithStack stack = new GetMinWithStack();
        stack.add(11);
        stack.add(12);
        stack.add(13);
        stack.add(10);
        stack.add(11);
        stack.add(9);
        System.out.println(stack.getMin());
        System.out.println(stack.pop());
        System.out.println(stack.getMin());
    }


}
