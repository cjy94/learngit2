package com.chenjunyi.day10_List;

/**
 * 给定一个链表的头节点head, 以及两个整数from和to， 在单链表上将第from个节点和第to个节点进行反转
 */
public class Code04_ReversePartNode {

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

    public static Node reversePart(Node head, int from, int to) {
        if (head == null) {
            return head;
        }

        // 1、遍历链表找到from的前一个节点和to的后一个节点
        int len = 0;
        Node node1 = head;
        Node fPre = null;
        Node fPos = null;
        while (node1 != null) {
            len++;
            fPre = len == from-1 ? node1 : fPre;
            fPos = len == to + 1 ? node1 : fPos;
            node1 = node1.next;
        }

        // 检查from to的正确性
        if (from > to || from < 1 || to >len) {
            return head;
        }

        // fPre有可能是null, 如果from的头节点那么fPre就是null
        node1 = fPre == null ? head : fPre.next;
        Node node2 = node1.next;
        node1.next = fPos;
        Node next = null;
        while (node2 != fPos) {
            next = node2.next;
            node2.next = node1;
            node1 = node2;
            node2 = next;
        }
        // node1就是反转后链表的头节点
        if (fPre != null) {
            fPre.next = node1;
            return head;
        }
        return node1;



    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        System.out.println(reversePart(n1, 2, 5));

    }

}
