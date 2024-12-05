package com.chenjunyi.StackAndQueue;

import java.util.Stack;

public class ReverseStack {

    // 逆序一个栈， 1 2 3 4 5 ==> 5 4 3 2 1

    // 该方法是返回并移除栈底元素
    public static int getAndRemoveLastElement(Stack<Integer> stack) {
        // 获取到栈顶元素
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }
    }

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = getAndRemoveLastElement(stack);
        // 此时 栈中还剩4个元素， 栈底的元素被移除，剩余其他4个元素
        reverse(stack);
        // 调用stack.push() 时， 栈已经空了，将栈顶元素压入栈底
        stack.push(i);
    }


    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        getAndRemoveLastElement(stack);
    }
}
