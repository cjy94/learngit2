package com.chenjunyi.zhongji一.zhongji2;

import java.util.Stack;

/**
 * 将栈中的元素进行排序
 */
public class StackSort {
    public static void sort(Stack<Integer> stack) {
        Stack<Integer> temp = new Stack<>();
        while (!stack.isEmpty()) {
            Integer value = stack.pop();
            while (!temp.isEmpty() && temp.peek() < value) {
                stack.push(temp.pop());
            }
            temp.push(value);
        }
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.add(7);
        stack.add(5);
        stack.add(3);
        stack.add(6);
        stack.add(2);
        System.out.println(stack);
        sort(stack);

        System.out.println(stack);
    }
}
