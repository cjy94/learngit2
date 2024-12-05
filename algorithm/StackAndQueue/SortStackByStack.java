package com.chenjunyi.StackAndQueue;

import java.util.Stack;

/**
 * 用一个栈结构，实现将一个栈中的数据进行排序，要求排序后从栈顶到栈底从小到大的顺序
 */
public class SortStackByStack {
    public static void sortStackByStackAsc(Stack<Integer> stack) {
        Stack<Integer> help = new Stack<>();
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            while (!help.isEmpty() && help.peek() < cur) {
                stack.push(help.pop());
            }
            help.push(cur);
        }

        while (!help.isEmpty()) {
            stack.push(help.pop());
        }

    }

    public static void sortStackByStackDesc(Stack<Integer> stack) {
        Stack<Integer> help = new Stack<>();
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            while (!help.isEmpty() && help.peek() > cur) {
                stack.push(help.pop());
            }
            help.push(cur);
        }

        while (!help.isEmpty()) {
            stack.push(help.pop());
        }

    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.add(7);
        stack.add(4);
        stack.add(5);
        stack.add(3);
        stack.add(6);
        sortStackByStackDesc(stack);

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
