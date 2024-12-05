package com.chenjunyi.day10_List;

/**
 * 给定一个单链表头节点head, 链表长度为N，如果n为偶数，那么前n/2个节点算作左半区，后n/2节点算作右半区
 * 如果n为奇数，那么前n/2个节点算作左半区，后n/2+1个节点算作右半区。 右半区从左到右依次记作L1->L2->L3->...
 * 右半区从左到右依次记作R1->R2->R3->R4->...， 将单链表调整成L1->R1->L2->R2->...的形式
 *
 *
 * 1、找到链表的mid位置，左半区与右半区分离成两个链表mid.next = null;
 * 分别基座left和right
 * 2、将两个链表按照土木要求合并起来
 *
 */
public class ReLocateTwoList {

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


    public static void relocate(Node head) {
        Node mid = head;
        Node right = head.next;
        while (right.next != null && right.next.next != null) {
            mid = mid.next;
            right = right.next.next;
        }
        right = mid.next;
        mid.next = null;
        mergeLR(head, right);

    }

    private static void mergeLR(Node left, Node right) {
        Node next =  null;
        while (left.next != null) {
            next = right.next;
            right.next = left.next;
            left.next = right;
            left = right.next;
            right = next;
        }
        left.next = right;
    }
}
