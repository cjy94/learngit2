package com.chenjunyi.linkedList;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 给定一个整数栈，如何检查栈中每对相邻数字是否连续。每对数字的值可以是递增或者递减的。 如果栈中元素的个数
 * 是奇数的，那么组队时忽略栈顶元素。例如，假设栈中元素[4,5,-2,-3,11,10,5,6,20]， 那么算法应该输出真，
 * 因为每对二元组(4,5) (-2,-3) (11,10) (5,6) 都是连续的数字
 */
public class CheckStackPairWiseOrder {

    // 时间复杂度O(n)
    public static boolean check(Stack<Integer> s) {
        LinkedList<Integer> queue = new LinkedList<>();
        while (!s.isEmpty()) {
            queue.addLast(s.pop());
        }
        while (!queue.isEmpty()) {
            s.push(queue.pollFirst());
        }
        boolean res = true;

        while (!s.isEmpty()) {
            int first = s.pop();
            queue.addLast(first);
            if (!s.isEmpty()) {
                int second = s.pop();
                queue.addLast(second);
                if (Math.abs(first - second) != 1) {
                    res = false;
                }

            }
        }

        // 当操作完成后，栈中还保存这原始的数据
        while (!queue.isEmpty()) {
            s.push(queue.pollFirst());
        }
        return res;
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(4);
        s.push(5);
        s.push(-2);
        s.push(-3);
        s.push(11);
        s.push(10);
        s.push(5);
        s.push(6);
        s.push(20);
        System.out.println(check(s));
    }
}
