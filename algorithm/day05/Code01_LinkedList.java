package com.chenjunyi.day05;

import java.util.Arrays;
import java.util.ArrayList;

public class Code01_LinkedList {
    public static class Node {
        int value;
        Node next;

        public Node(int v) {
            this.value = v;

        }

    }

   // 链表长度奇数则，返回中点；偶数则返回上中点
    public static Node midOrUpMidNode(Node head) {
        if (head==null || head.next == null || head.next.next==null) {
            return head;
        }
        // 到达此处说明，链表中至少有2个节点
        Node fast = head.next;
        Node slow = head.next.next;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next; 
        }
        return slow;
    }
    // 链表长度奇数，则返回中点； 偶数则返回下重点
    public static Node midOrDownMidNode(Node head) {
        if (head==null || head.next == null) {
            return head;
        }
        // 到达此处说明，链表中至少有2个节点
        Node fast = head.next;
        Node slow = head.next;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // 链表长度奇数则返回中点的前一个节点，偶数则返回上中点的前一个节点
    public static Node midOrUpMidPreNode(Node head) {
        if (head==null || head.next == null || head.next.next != null) {
            return head;
        }
        // 到达此处说明，链表中至少有2个节点
        Node fast = head;
        Node slow = head.next.next;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // 链表的长度奇数则返回中点的前一个节点； 偶数则返回下中点的前一个节点
    public static Node midOrDownMidPreNode(Node head) {
        if (head==null || head.next == null) {
            return null;
        }

        if (head.next.next == null) {
            return head;
        }
        // 到达此处说明，链表中至少有2个节点
        Node fast = head.next;
        Node slow = head;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }


    public static Node right1(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> list = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
         return list.get((list.size()-1)/2);
    }

    public static Node right2(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> list = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        return list.get((list.size())/2);
    }

    public static Node right3(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        ArrayList<Node> list = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        return list.get((list.size()-3)/2);
    }

    public static Node right4(Node head) {
        if (head == null || head.next==null) {
            return null;
        }
        ArrayList<Node> list = new ArrayList<>();
        Node cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        return list.get((list.size()-2)/2);
    }
}
