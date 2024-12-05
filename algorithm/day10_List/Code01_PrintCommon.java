package com.chenjunyi.day10_List;

/**
 * 给定两个有序链表的头指针head1和head2， 打印两个俩表的公共部分
 */
public class Code01_PrintCommon {

    public static class Node{
        public int value;
        public Node next;
        public Node(int data) {
            this.value = data;
        }
    }

    public static void print(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return;
        }

        Node n1 = head1;
        Node n2 = head2;
        while (n1 != null && n2 != null) {
            if (n1.value < n2.value) {

                n2 = n2.next;
            } else if(n2.value < n1.value) {

                n1 = n1.next;
            } else {
                System.out.println(n1.value);
                n1 = n1.next;
                n2 = n2.next;
            }
        }
    }
}
