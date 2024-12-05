package com.chenjunyi.day10_List;

import java.util.Stack;

/**
 * 给定一个单向链表，链表的节点编号i[1...n]， 其中n为链表中元素的个数，编写一个函数从表头开始找到最后一个满足i%k==0条件的元素，k是一个整数常量
 * 例如，n=19, k =3, 那么最后一个编号是18
 */

public class ModularNodes {

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

    public static Node modularNode(Node head, int k) {
        Node cur = head;
        Node modular = null;
        Node next = null;
        int i =1;
        while (cur != null) {
            next = cur.next;
            if (i%k == 0) {
                modular = cur;
            }
            i++;
            cur = next;
        }
        return modular;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        n1.next=n2;
        n2.next=n3;
        n3.next=n4;
        n4.next=n5;
        n5.next = n6;
        n6.next=n7;
        n7.next=n8;
        System.out.println(modularNode(n1, 3));
    }
}
