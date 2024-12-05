package com.chenjunyi.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for singly-linked list.

 */



public class AddTwoLinkedList {
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    /**
     * 两个非空的链表。，链表中的数值时非负的，对两个链表中的数值求和
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 遍历链表l1， 放到一个字符数组中
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        // 将两个链表中的元素放入StringBuilder中
        while (cur1!=null || cur2!=null) {
            if (cur1 != null) {
                s1.append(cur1.val);
                cur1 = cur1.next;
            }
            if (cur2 != null) {
                s2.append(cur2.val);
                cur2 = cur2.next;
            }

        }
        char[] ch1 = s1.toString().toCharArray();
        char[] ch2 = s2.toString().toCharArray();
        int i1 = 0;
        int i2 = 0;
        int carry = 0;
        int sum = 0;
        StringBuilder res  = new StringBuilder();
        while (i1 < s1.length() || i2 < s2.length()) {
            sum = carry;
            if (i1 < s1.length()) {
                sum += ch1[i1++] - '0';

            }
            if (i2 < s2.length()) {
                sum += ch2[i2++] - '0';

            }
            // sum < 10    ===>  carry=0
            carry = sum >= 10 ? 1 : 0;
            res.append(sum % 10);

        }
        if (carry != 0) {
            res.append(carry);
        }

        char[] ch = res.toString().toCharArray();
        ListNode head = null;
        ListNode tail = null;
        for (int i =0;i < ch.length; i++) {
            ListNode node =  new ListNode(ch[i]-'0');
            if (head == null && tail == null) {
                head = tail =node;
            } else {
                tail.next = node;
                tail = node;
            }
        }
        return head;
    }

    /**
     * 改进版本，
     * 旧的版本时将两个链表中的val放到了StringBuilder中，改进不放入sb中，一边遍历一边相加
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        // 遍历链表l1， 放到一个字符数组中

        int carry = 0;
        int sum = 0;
      //  StringBuilder res  = new StringBuilder();
        ListNode head = null;
        ListNode tail = null;
        while (l1 != null || l2 != null) {
            sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;

            }
            if (l2 != null) {
                sum += l2.val ;
                l2 = l2.next;

            }
            // sum < 10    ===>  carry=0
            carry = sum >= 10 ? 1 : 0;
            ListNode node = new ListNode(sum%10);
            if (head== null && tail == null) {
                head = tail = node;
            } else {
                tail.next = node;
                tail = node;
            }

        }
        if (carry != 0) {
            ListNode node = new ListNode(carry);
            if (head== null && tail == null) {
                head = tail = node;
            } else {
                tail.next = node;
                tail = node;
            }
        }
        return head;
    }

    public ArrayList<ArrayList<Integer>> generate(int numRows) {

        // [[1],[1,1],[1,2,1],[1,3,3,1],[1,2,3,4,1]]
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>(numRows);
        ArrayList<Integer> first = new ArrayList();
        first.add(1);
        arrayLists.add(first);

        for (int i =1;i < numRows; i++) {
            ArrayList<Integer> temp = new ArrayList();
            temp.add(1);
            // 取出上一个list
            ArrayList<Integer> pre = arrayLists.get(i-1);
            for (int j =1; j <pre.size(); j++) {
                temp.add(pre.get(j-1)+temp.get(j));
            }
            temp.add(1);
            arrayLists.add(temp);
        }

        return arrayLists;

    }

    public int singleNumber(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }

        return xor;

    }




    public static void main(String[] args) {
//        AddTwoLinkedList test = new AddTwoLinkedList();
//       // test.generate(5);
//        int[] arr = {1,1,2,2,3,3,0,5,5,0,6};
//        System.out.println(test.singleNumber(arr));

        char[] ch = {'2',',','d','4','6','e','*'};
        for (char c : ch) {

            System.out.println(Character.isLetterOrDigit(c));
        }



    }


}
