package com.chenjunyi.leetcode;

import java.util.concurrent.FutureTask;

public class AddTwoNumber {

  public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 保存进位
        int carry = 0;
        int sum = 0;
        ListNode head = null;
        ListNode tail = null;
        // 从头遍历链表，执行两个数的相加操作，用sum保存， carry 保存进位的信息，用于sum下次使用
        while (l1 != null || l2 != null) {
            sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            if (head == null && tail == null) {
                head = tail = new ListNode(sum % 10);

            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum/10;
        }
        if (carry != 0) {
            tail.next = new ListNode(carry);
            tail = tail.next;

        }
        return head;
    }

    public static void main(String[] args) {

        AddTwoNumber test = new AddTwoNumber();
        AddTwoNumber.ListNode n1 = test.new ListNode(9);
        AddTwoNumber.ListNode n2 = test.new ListNode(9);
        AddTwoNumber.ListNode n3 = test.new ListNode(9);
        AddTwoNumber.ListNode n4 = test.new ListNode(9);
        AddTwoNumber.ListNode n5 = test.new ListNode(9);
        AddTwoNumber.ListNode n6 = test.new ListNode(9);
        AddTwoNumber.ListNode n7 = test.new ListNode(9);

        n1.next=n2;
        n2.next=n3;
        n3.next=n4;
        n4.next=n5;
        n5.next = n6;
        n6.next=n7;

        AddTwoNumber.ListNode n8 = test.new ListNode(9);
        AddTwoNumber.ListNode n9 = test.new ListNode(9);
        AddTwoNumber.ListNode n10 = test.new ListNode(9);
        AddTwoNumber.ListNode n11 = test.new ListNode(9);
        n8.next=n9;
        n9.next=n10;
        n10.next=n11;



        test.addTwoNumbers(n1, n8);


    }
}
