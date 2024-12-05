package com.chenjunyi.leetcode;

import java.util.List;
import java.util.ArrayList;
public class ListRemoveNthNode {



    public static class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }


 // 使用集合的方法。 占用额外的空间
    public static ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode back = head;
        ArrayList<Integer> list = new ArrayList<>();
        while (back != null) {
            list.add(back.val);
            back = back.next;
        }

        list.remove(list.size()-n);
        if (list.size() == 0) return head.next;
        ListNode cur = new ListNode(list.get(0));
        ListNode tail = cur;
        for (int i =1; i < list.size();i++) {
            ListNode tmp = new ListNode(list.get(i));
            tail.next = tmp;
            tail = tmp;
        }
        return cur;

    }

    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        // 简单的方式是遍历一遍链表将节点都放到集合中， 将指定的元素删除后，在组合成链表返回，但是占用了额外的内存空间
        ListNode s = head;
        ListNode f = head;
        for (int i =0; i < n; i++) {
            f = f.next;
        }
        if (f == null) return head.next;
        while (f.next!=null) {
            s = s.next;
            f= f.next;
        }
        s.next = s.next.next;
        return head;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
//        ListNode n2 = new ListNode(2);
//        ListNode n3 = new ListNode(3);
//        ListNode n4 = new ListNode(4);
//        ListNode n5 = new ListNode(5);
//        n1.next = n2;
//        n2.next = n3;
//        n3.next = n4;
//        n4.next = n5;
       //System.out.println(removeNthFromEnd2(n1, 1));
        System.out.println(removeNthFromEnd1(n1, 1));
        System.out.println(7/-3);


    }
}
