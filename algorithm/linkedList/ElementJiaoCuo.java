package com.chenjunyi.linkedList;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 给定一个整数队列，如何把队列中前半部分和后半部分的元素相互交叉，完成队列中元素的重新排列，
 * 例如 [11,12,13,14,15,16,17,18,19,20] 那么前半部扽和后半部分的子序列就是[11,12,13,14,15]和[16,17,18,19,20]
 * 重排列的结果就是：[11,16,12,17,13,18,14,19,15,20]
 */
public class ElementJiaoCuo {
    public static void intersectQueue(LinkedList<Integer> queue) {
        Stack<Integer> s = new Stack<>();
        int index = 0;
        while (index < (queue.size() >> 1)) {
            s.push(queue.pollFirst());
        }
        // queue[16,17,18,19,20]  s[11,12,13,14,15]



        while (!s.isEmpty()) {
            queue.addLast(s.pop());
        }
        // queue [16,17,18,19,20,15,14,13,12,11]



        index = 0;
        while (index < queue.size() / 2) {
            queue.addLast(queue.pollFirst());
        }
        //  queue[15,14,13,12,11,16,17,18,19,20]



        index = 0;
        while (index < queue.size() / 2) {
            s.push(queue.pollLast());
        }
        // s[15,14,13,12,11] queue [16,17,18,19,20]



        while (!s.isEmpty()) {
            queue.addLast(s.pop());             // 11,12,13,14,15
            queue.addLast(queue.pollFirst());   // 16,17,18,19,20

        }

    }
}
