package com.chenjunyi.Queue;

/**
 * 用链表实现队列
 *
 */
public class LinkListQueue {
    public static class Node{
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    Node head;
    Node tail;
    public LinkListQueue() {

    }

    //
    public boolean isEmpty() {
        return head == null;
    }

    public void enQueue(int value) {
        Node node = new Node(value);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }

    }

    public int deQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }

        Node tmp = head;
        head = head.next;
        tmp.next = null;
        return tmp.value;
    }
}
