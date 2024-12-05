package com.chenjunyi.Queue;


import java.util.Queue;
import java.util.Stack;

/**
 * 将队列中的元素进行逆置
 *
 * queue 1 2 3 4
 * 逆置后 4 3 2 1
 *
 * 利用栈
 */
public class QueueReverse {


    public static void reverseQueue(Queue<Integer> queue) {
        Stack<Integer> s = new Stack<>();
        while (!queue.isEmpty()) {
            s.push(queue.poll());
        }
        while (!s.isEmpty()) {
            queue.add(s.pop());
        }
    }



}
