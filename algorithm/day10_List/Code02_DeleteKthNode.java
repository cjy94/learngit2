package com.chenjunyi.day10_List;

import java.util.TreeMap;

/**
 * 分别实现两个函数， 一个可以删除单链表中倒数第K个节点， 另一个可以删除双链表中倒数第K个节点
 */
public class Code02_DeleteKthNode {
    // 单链表
    public static class Node{
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    public static class DoubleNode{
        public int value;
        public DoubleNode next;
        public DoubleNode pre;

        public DoubleNode(int data) {
            this.value = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    ", pre=" + pre +
                    '}';
        }
    }

    // 单链表中删除第k个节点
    public static Node deleteKthNode(Node head, int k) {
        if (head == null || k < 0) {
            return head;
        }
        if (k == 0) {
            return head.next;
        }

        Node r = head;
        while (k > 0 && r != null) {
            r = r.next;
            k--;
        }
        Node l = head;
        // r节点已经走了k个位置，l节点从头开始走，当r.next==null时，l节点来到要删除节点的前一个节点上
        while (r.next != null) {
            l = l.next;
            r = r.next;
        }
        // l.next就是要删除的节点
        l.next = l.next.next;
        return head;
    }

    // 双链表中删除第k个节点 , 和单链表一样，只是处理下pre指针
    public static DoubleNode deleteKthDoubleNode(DoubleNode head, int k) {
        if (head == null || k < 0) {
            return head;
        }
        if (k == 0) {
            head = head.next;
            head.pre = null;
            return head;
        }

        DoubleNode r = head;
        while (k > 0 && r != null) {
            r = r.next;
            k--;
        }
        DoubleNode l = head;
        // r节点已经走了k个位置，l节点从头开始走，当r.next==null时，l节点来到要删除节点的前一个节点上
        while (r.next != null) {
            l = l.next;
            r = r.next;
        }
        // l.next就是要删除的节点
        DoubleNode nextNode = l.next.next;
        l.next = nextNode;
        if (nextNode !=null) {
            nextNode.pre = l;
        }
        return head;
    }
//    public class Test {
//        public static void main(String[] args)  {
//            MyClass myClass = new MyClass();
//            StringBuffer buffer = new StringBuffer("hello");
//            myClass.changeValue(buffer);
//            System.out.println(buffer.toString());
//        }
//    }

    static class  MyClass {

        void changeValue(StringBuffer buffer) {
            buffer = new StringBuffer();
            buffer.append("world");
        }
    }

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
            StringBuffer buffer = new StringBuffer("hello");
            myClass.changeValue(buffer);
            System.out.println(buffer.toString());
//        Node n1 = new Node(1);
//        Node n2 = new Node(2);
//        Node n3 = new Node(3);
//        Node n4 = new Node(4);
//        Node n5 = new Node(5);
//        Node n6 = new Node(6);
//
//        System.out.println(deleteKthNode(n1, 0));

    }



}
