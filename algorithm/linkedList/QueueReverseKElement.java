package com.chenjunyi.linkedList;

import com.chenjunyi.Joseph.JosephProblem;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 给定=一个整数k和一个整数队列，如何把队列中前k个元素逆置
 * 如 [10,20,30,40,50,60,70,80,90] k =4
 * 输出[40,30,20,10,50,60,70,80,90]
 *
 *
 * s [10,20,30,40]     queue [50,60,70,80,90]
 * queue.addLast(s.pop())
 *
 * queue[50,60,70,80,90,40,30,20,10]
 *
 * while(index < size-k) {
 *     queue.addLast(queue.pollFirst());
 * }
 *
 */
public class QueueReverseKElement {

    public static void reverseQueue(LinkedList<Integer> queue, int k) {
        if (queue == null || k >= queue.size()) {
            return;
        }
        Stack<Integer> s = new Stack<>();
        int index = 0;
        while (index < k) {
            s.push(queue.pollFirst());
            index++;

        }
        while (!s.isEmpty()) {
            queue.addLast(s.pop());
        }

        index = 0;
        while (index < queue.size()-k) {
            queue.addLast(queue.pollFirst());
            index++;
        }

    }
}
