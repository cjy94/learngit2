package com.chenjunyi.leetcode;

import com.chenjunyi.day02.Code01_ReverseList;
import java.util.List;
import java.util.ArrayList;

public class ReverseLinkedList {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public ListNode reverseList(ListNode head) {

        ListNode pre = null;
        ListNode next = null;

        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;

        }
        return pre;



    }

    public boolean isPalindrome(ListNode head) {
        
        // 遍历一遍链表记录个数
        List<Integer> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        int l = 0;
         int r = list.size()-1;
         while (l <= r) {
             if (list.get(l) == list.get(r)) {
                 l++;
                 r--;
             } else {
                 return false;
             }
         }
         return true;

        //



    }

    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 0 ; i <n; i++) {
            // 只是3的倍数，
            if ((i+1) % 3 == 0 && (i+1) % 15 != 0) {
                list.add("Fizz");
            } else if((i+1) % 5 == 0 && (i+1) % 15 != 0) {
                list.add("Buzz");
            } else if((i+1) % 15 == 0) {
                list.add("FizzBuzz");
            } else {
                list.add("" + (i+1));
            }
        }
        return list;

    }

    public int firstUniqChar(String s) {
        // 取出字符串中没有重复字符的索引位置， 没有-1
        int ans = Integer.MAX_VALUE;
        for (int i = 'a'; i<= 'z'; i++) {
            int index = s.indexOf(i);
            if (index != -1 && index == s.lastIndexOf(i)) {
                ans = Math.min(ans, index);
            }
        }
        return ans;

    }

    public static void main(String[] args) {
        ReverseLinkedList test = new ReverseLinkedList();
        test.firstUniqChar("loveleetcode");
        System.out.println(test.fizzBuzz(15));
        ReverseLinkedList.ListNode n1 = test.new ListNode(1);
        ReverseLinkedList.ListNode n2 = test.new ListNode(2);
        ReverseLinkedList.ListNode n3 = test.new ListNode(3);
        ReverseLinkedList.ListNode n4 = test.new ListNode(4);
        ReverseLinkedList.ListNode n5 = test.new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        test.reverseList(n1);
    }
}
