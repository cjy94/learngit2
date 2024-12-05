package com.chenjunyi.day05;

/**
 * 找到两个链表的入环节点
 * 快慢指针，
 */
public class Code05_LinkedListHuan {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /**
     * 给一个链表，如果有环则返回第一个入环的节点； 否则返回null
     * 1、f 快指针依次走2不； 2、慢指针，依次走1步
     * 当快慢指针相遇，说明该链表存在环
     * 2、找到第一个入环节点，将f节点返回到head的位置，f走一步， s也走一部， 当f和s相遇的节点就是第一个入环节点
     * @param head
     * @return
     */
    public ListNode getHuanNode(ListNode head) {
         if (head == null || head.next == null || head.next.next == null) return null;
         ListNode s = head.next;
         ListNode f = head.next.next;
         while (s != f ) {    // 快慢指针相遇则出现了环
             if ((f.next == null || f.next.next == null)){
                 return null;
             }
             s = s.next;
             f = f.next.next;

         }
         f = head;
         while (s!=f) {
             s = s.next;
             f = f.next;
         }
         return s;
    }

    /**
     * node1和bode2是无环链表，如果两个无环链表相交则返回第一个相交节点，否则返回null;
     * @param node1
     * @param node2
     * @return
     */
    public ListNode noLoop(ListNode node1, ListNode node2) {
        ListNode cur1 = node1;

        ListNode cur2 = node1;
        int n = 0;
        while (cur1.next != null) {
            cur1 = cur1.next;
            n++;
        }

        while (cur2.next != null) {
            cur2 = cur2.next;
            n--;
        }
        // 不相交的情况
        if (cur1 != cur2) return null;
        // 相交的情况
        // cur1 代表长链表的头节点； cur2代表短链表的头节点
        if (n > 0) {
            cur1 = node1;
        } else {
            cur2 = node2;
        }
        n = Math.abs(n);

        while (n!=0) {
            cur1 = cur1.next;
            n--;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;



    }

     
}
