package com.chenjunyi.Stack;

import java.util.Stack;

/**
 * 实现一个函数，对栈中的元素进行升序排列
 */
public class StackSort {

    public static Stack sort(Stack<Integer> stack) {
        Stack<Integer> nStack = new Stack();
        while (!stack.isEmpty()) {
            int temp = stack.pop();
            while (!nStack.isEmpty() && nStack.peek() > temp) {
                stack.push(nStack.pop());
            }
            nStack.push(temp);
        }
        return nStack;
    }


    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(3);
        s.push(6);
        s.push(5);
        s.push(2);
        s.push(7);
        s.push(6);
        s.push(9);
        Stack s2 = sort(s);
        while (!s2.isEmpty()) {
            System.out.println(s2.pop());
        }
    }
}
