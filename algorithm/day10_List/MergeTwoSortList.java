package com.chenjunyi.day10_List;

/**
 * 给定两个有序的单链表的头节点head1和head2， 请合并两个有序链表，合并后的链表依然有序， 并返回合并后链表的头节点
 */
public class MergeTwoSortList {
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

    public static Node mergeList(Node head1, Node head2) {
        Node head = null;
        Node tail = null;
        Node next1 = head1;
        Node next2 = head2;
        while (head1 != null && head2 != null) {

            if (head1.value <= head2.value) {
                next1 = head1.next;
                head1.next = null;
                if (tail == null && head == null) {
                    head = tail = head1;
                }  else {
                    tail.next = head1;
                    tail = head1;
                }
                head1 = next1;
            } else {
                next2 = head2.next;
                head2.next = null;
                if (tail == null && head == null) {
                    head = tail = head2;
                }  else {
                    tail.next = head2;
                    tail = head2;
                }
                head2 = next2;
            }

        }

        while (head1 != null) {
            next1 = head1.next;
            head1.next = null;
            if (tail == null && head == null) {
                head = tail = head1;
            }  else {
                tail.next = head1;
                tail = head1;
            }

            head1 = next1;
        }
        while (head2 != null) {
            next2 = head2.next;
            head2.next = null;
            if (tail == null && head == null) {
                head = tail = head2;
            }  else {
                tail.next = head2;
                tail = head2;
            }

            head2 = next2;
        }
        return head;
    }


    public static Node mergeList1(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return head1 != null ? head1 : head2;
        }

        Node head = head1.value < head2.value ? head1 : head2;
        Node cur1 = head == head1 ? head1 : head2;  // 指向头节点较小的链表
        Node cur2 = head == head1 ? head2 : head1; // 指向头节点较大的链表
        Node pre = null;
        Node next = null;
        while (cur1 != null && cur2 != null) {
            // pre第一次赋值一定在第一个if里面
            if (cur1.value <= cur2.value) {
                pre = cur1;
                cur1 = cur1.next;
            } else {
                next = cur2.next;
                pre.next = cur2;
                cur2.next = cur1;
                pre = cur2;
                cur2 = next;
            }
        }
        pre.next = cur1 == null ? cur2 : cur1;
        return head;

    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(3);
        Node n3 = new Node(4);
        Node n4 = new Node(5);
        Node n5 = new Node(6);
        Node n6 = new Node(7);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;


        Node n11 = new Node(4);
        Node n12 = new Node(6);
        Node n13 = new Node(7);
        Node n14 = new Node(8);
        Node n15 = new Node(9);
        n11.next = n12;
        n12.next = n13;
        n13.next = n14;
        n14.next = n15;

        System.out.println(mergeList1(n1, n11));
    }
}
