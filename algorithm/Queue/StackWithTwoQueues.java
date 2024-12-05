package com.chenjunyi.Queue;

import java.util.LinkedList;

/**
 * 使用两个队列来实现一个栈q1和q2
 *
 * 一个队列q1用于插入元素，一个队列q2保持空
 * 当需要弹出元素时， 将非空的队列q1弹出size-1个元素到另一个空队列q2中，q1中队尾元素用于返回，这样q1队列空
 * 下次再插入时，就往非空的队列 q2中插入元素
 */
public class StackWithTwoQueues {
    LinkedList<Integer> q1;
    LinkedList<Integer> q2;

    public StackWithTwoQueues() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    // 往不空的队列中push元素
    public void push(int value) {
        if (q1.isEmpty()) {
            q2.addLast(value);
        } else {
            q1.addLast(value);
        }
    }

    public int pop() {
        int i = 0;
        if (q1.isEmpty()) {
            int size = q2.size();
            while (i < size -1) {
                q1.addLast(q2.pollFirst());
                i++;
            }
            return q2.pollFirst();

        }  else {
            int size = q1.size();
            while (i < size -1) {
                q2.addLast(q1.pollFirst());
                i++;
            }
            return q1.pollFirst();
        }
    }

}
