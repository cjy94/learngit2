package com.chenjunyi.Stack;

import com.chenjunyi.leetcode.ReverseLinkedList;

import java.util.Stack;

/**
 * 给定一个栈，如何只使用栈操作（push和pop）逆置栈中的元素
 */
public class ReverseStackValue {

    public void reverse(Stack stack) {
        // 首先利用递归操作，将所有元素全部弹出，直至栈空
        if (stack.isEmpty()) return;
        Object temp = stack.pop();
        reverse(stack);
        // 再将栈顶元素压入空战
        insertAtBottom(stack, temp);
    }

    public void insertAtBottom(Stack stack, Object data) {
        if(stack.isEmpty()) {
            stack.push(data);
            return;
        }
        Object temp = stack.pop();
        insertAtBottom(stack, data);
        stack.push(temp);

    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack.peek());
        System.out.println(stack.toString());
        ReverseStackValue test = new ReverseStackValue();
        test.reverse(stack);
        System.out.println(stack);
    }
}
