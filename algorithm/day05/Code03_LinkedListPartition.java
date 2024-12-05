package com.chenjunyi.day05;

import java.util.ArrayList;


/**
 * 将单链表划分成左边小，中间等， 右边大的结构
 */
public class Code03_LinkedListPartition {

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(4);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(2);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(2);
        n1.next=n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        ListNode head = partition(n1, 3);
        System.out.println(head.val);
    }




    public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    /**
     * 将链表分成2个区域，比x小的在左侧，大于等于x的放在右侧
     * 思路： 用两个链表，一个链表用于记录小的节点； 一个链表用于记录大的节点，最后将小链表的next指针指向大的链表，即可
     * @param head
     * @param x
     * @return
     */
    public static ListNode partition(ListNode head, int x) {
        ListNode sH = null;
        ListNode sT = null;
        ListNode bH = null;
        ListNode bT = null;
        ListNode next = null;
        while (head != null) {
             next = head.next;
             head.next = null;
            if(head.val < x) {
                if (sH == null) {
                    sH = sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else {
                if (bH == null) {
                    bH = bT = head;
                } else {
                    bT.next = head;
                    bT = head;
                }
            }
            head = next;
        }
        // 将两个链表连起来
        if (sT != null) {
            sT.next = bH;

        }

        return sH != null ? sH : bH;
    }






}
