package com.chenjunyi.day02;

public class Code02_DeleteListNode {
    public static class Node {
        public int data;
        public Node next;
        Node(int data) {
            this.data = data;

        }
    }

    public static Node removeValue(Node head, int num) {
        // 先检查下要删除的节点是否是头节点，
        while (head != null) {
            if (head.data != num) {
                break;
            }
            head = head.next;
        }
        // 从这个循环中跳出后，head在第一个不需要删除的节点处
        Node cur = head;
        Node pre = head;
        while (cur != null) {
            if (cur.data == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
