package com.chenjunyi.day05;


import java.util.HashMap;

public class Code04_RandLinkedListCopy {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode rand;
        ListNode(int x) { val = x; }
    }

    public static ListNode copyListWithRand(ListNode head) {
        if (head == null) return head;
        ListNode cur = head;
        HashMap<ListNode, ListNode> map = new HashMap<>();
        while (cur != null) {
            map.put(cur, new ListNode(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }

    public static ListNode copyListWithRand2(ListNode head) {
         if (head == null) return  head;
         ListNode cur = head;
         ListNode next = null;
         // 1->2
         // 1->1'->2
         while (cur != null) {
             next = cur.next;
             cur.next = new ListNode(cur.val);
             cur.next.next = next;
             cur = next;
         }
         cur = head;
         ListNode curCopy;
         while (cur != null) {
             next = cur.next.next;
             curCopy = cur.next;
             curCopy.rand = cur.rand == null ? null : cur.rand.next;
             cur = next;
         }

         // 将两个链表独立出来
        cur = head;
        ListNode res = head.next;
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            curCopy.next = next == null ? null : next.next;
            cur = next;
        }
        return res;
    }

    
}
