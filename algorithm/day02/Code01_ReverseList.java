package com.chenjunyi.day02;

public class Code01_ReverseList {
    // 单链表
    public static class Node {
        public int data;
        public Node next;
        Node(int data) {
            this.data = data;

        }
    }

    // 双向链表
    public static class DoubleNode {
        public int data;
        public DoubleNode next;
        public DoubleNode pre;
        
        DoubleNode(int data) {
            this.data = data;

        }
    }

    // 单链表的反转
    public static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        
        return pre;
    }

    public static DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.pre = next;
            pre = head;
            head = next;
        }
        return pre;
        
    }

    


}
