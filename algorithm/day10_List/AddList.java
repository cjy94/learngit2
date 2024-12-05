package com.chenjunyi.day10_List;

/**
 * 给定两个链表的头节点head1和head2, 请生成代表两个正数相加值的结果链表
 * 例如：head1: 9->3->7     head2: 6->3
 * 相加后的链表是： 1->0->0->0
 *
 */
public class AddList {
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

    // 单链表的反转
    public static Node reverse(Node head) {
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

    // 多个链表相加
    // 将两个链表反转
    public static void sum(Node head1, Node head2) {
        head1 = reverse(head1);
        head2 = reverse(head2);
        int carry = 0;   // 进位信息
        int sum = 0;
        Node pre = null;
        while (head1 != null || head2 != null) {
            sum = carry;
            if (head1 != null) {
                sum += head1.value;
                head1 = head1.next;
            }
            if (head2 != null) {
                sum += head2.value;
                head2 = head2.next;
            }
            Node node = new Node(sum % 10);
            node.next = pre;
            pre = node;
            carry = sum / 10;

        }
        if (carry != 0) {
            Node node = new Node(carry);
            node.next = pre;
            pre = node;
        }
        //reverse(pre);
        Node debugHead = pre;
        while (debugHead != null) {
            System.out.println(debugHead.value);
            debugHead = debugHead.next;
        }
    }

    public static Node addList(Node head1, Node head2) {
        // 将两个链表反转，反转后相加
        head1 = reverseList(head1);
        head2 = reverseList(head2);
        // 反转后还要再将链表反转回来
        int carry = 0;
        int sum = 0;
        //Node node = null;
        Node pre = null;
        Node c1 = head1;
        Node c2 = head2;
        while (head1 != null || head2 != null) {
            sum = carry;
            if (head1 != null) {
                sum += head1.value;
                head1 = head1.next;
            }
            if (head2 != null) {
                sum += head2.value;
                head2 = head2.next;
            }
            //pre = node;
            Node node  = new Node(sum % 10);
            node.next = pre;
            pre = node;
            carry = sum / 10;

        }
        if (carry != 0) {
           // pre = node;
            Node node = new Node(carry);
            node.next = pre;
            pre = node;
        }
        head1 = reverseList(c1);
        head2 = reverseList(c2);
        return pre;
    }

    // 反转链表的操作
    private static Node reverseList(Node head) {
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

    public static void main(String[] args) {
        Node n1 = new Node(9);
        Node n2 = new Node(3);
        Node n3 = new Node(7);

        Node n4 = new Node(6);
        Node n5 = new Node(3);

        n1.next = n2;
        n2.next = n3;
        n4.next = n5;

        sum(n1, n4);

    }
}
