package com.chenjunyi.Stack;

import java.util.Stack;

/**
 * 如何涉及一个栈，是的getMinValue的时间复杂度O(1)
 */
public class GetMinValue {
    static Stack<Integer> stack = new Stack<>();
    static Stack<Integer> min = new Stack<>();

    public static void push(int value) {
        if (min.isEmpty()) {
            min.push(value);

        } else if(getMin() > value) {
            min.push(value);
        }  else {
            min.push(getMin());
        }
        stack.push(value);
    }

    public static int pop() {
        if (stack.isEmpty()) {
            throw new RuntimeException("stack is empty!");

        }

        min.pop();
        return stack.pop();
    }

    public static int getMin() {
        if(min.isEmpty()) {
            throw new RuntimeException("stack is empty!");

        }
        return stack.peek();
    }

    public static void push1(int value) {
        stack.push(value);
        // min栈只有当前值小于栈顶时， 才入栈
        if (min.isEmpty() || getMin() >= value) {
            min.push(value);
        }

    }

    public static int pop1() {
        if (min.isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int temp = stack.peek();
        int minTemp = min.peek();
        if (temp == minTemp) {
            min.pop();
        }
        return stack.pop();
    }


}
