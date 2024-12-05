package com.chenjunyi.day10_List;

import com.chenjunyi.day09_SubStringProblem.Code01_substring;

/**
 * 链表高频题
 *
 * 1、返回两个无环链表相交的第一个节点
 * 2、每K个节点一组反转链表
 * 3、复制带随机指针的链表
 * 4、判断链表是否是回文结构
 * 5、返回链表的第一个入环节点
 * 6、链表排序，要求时间复杂度o(n * logN) 空间复杂度o(1)
 *
 *  
 */
public class LinkedListTest {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }



    private static ListNode start = null;
    private static ListNode end = null;
    // 链表排序
    public static ListNode sortList(ListNode head) {
        // 获取链表的长度
        int n =0;
        ListNode cur = head;
        while (cur != null) {
            n++;
            cur = cur.next;
        }

        ListNode l1, r1, l2, r2, next, lastTermEnd;
        for (int step = 1;  step < n; step <<= 1) {
            l1 = head;
            r1 = findEnd(l1, step);
            l2 = r1.next;
            r2 = findEnd(l2, step);
            next = r2.next;
            r1.next = null;
            r2.next = null;
            merge(l1,r1,l2,r2);
            head = start;
            lastTermEnd = end;
            while (next != null) {
                l1 = next;
                r1 = findEnd(l1, step);
                l2 = r1.next;
                if (l2 == null) {
                    lastTermEnd.next = l1;
                    break;
                }
                r2 = findEnd(l2, step);
                next = r2.next;
                r1.next = null;
                r2.next = null;
                merge(l1,r1,l2,r2);
                lastTermEnd.next = start;
                lastTermEnd = end;
            }

        }
        return head;

    }

    // l1 ... r1 ->null
    // l2 ... r2 ->null
    // 将两个链表合并
    private static void merge(ListNode l1, ListNode r1, ListNode l2, ListNode r2) {
        ListNode pre;
        if (l1.val < l2.val) {
            start = l1;
            pre = l1;
            l1 = l1.next;
        } else {
            start = l2;
            pre = l2;
            l2 = l2.next;
        }

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                pre.next = l1;
                pre = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                pre = l2;
                l2 = l2.next;
            }
        }

        if (l1 != null) {
            pre.next = l1;
            end = r1;
        } else {
            pre.next = l2;
            end = r2;
        }
    }

    private static ListNode findEnd(ListNode start, int step) {
        while (start.next != null && --step != 0) {
            start = start.next;
        }
        return start;

    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(4);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(1);
        ListNode n4 = new ListNode(3);
        ListNode n5 = new ListNode(6);
        ListNode n6 = new ListNode(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        sortList(n1);
    }


}
