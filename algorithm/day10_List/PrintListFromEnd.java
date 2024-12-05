package com.chenjunyi.day10_List;

/**
 * 从尾部开始打印链表
 */
public class PrintListFromEnd {
    public static class Node{
        int value;
        Node next;
        public Node(int value) {
            this.value = value;

        }

    }

    // 使用递归的方式遍历, 时间复杂度是n， 空间复杂度是n
    public static void printListFromEnd(Node head1) {
        if (head1 == null) {
            return;
        }
        printListFromEnd(head1.next);
        System.out.println(head1.value);
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        printListFromEnd(n1);
    }
}
