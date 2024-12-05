package com.chenjunyi.day09_SubStringProblem;

import java.util.Arrays;
import java.util.ArrayList;

public class Code01_substring {


    // 判断str2是否是str1的字串，返回开始的位置
    // 方式1：暴力解法， str1从头到尾全部遍历一遍
    private static int method1(String str1, String str2) {
        if(str1!=null && str2!=null && str1.length()<str2.length()) return -1;
        int index2 =0;
        int n1 = str1.length();
        int n2= str2.length();
        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();
        for (int i =0;i < n1; i++) {
            if(ch1[i]==ch2[index2]) {
                index2++;
            } else {
                i=i-index2;
                index2=0;
            }
            // 如果str2字符串已经到达了尾部，说明匹配到了，则返回
            if (index2==n2-1) {
                return (i-n2+1);
            }
        }
        return -1;
    }

    private static int kmpMethod(String str1, String str2) {
        
        return -1;
    }

    /**
     * Input: a = "1010", b = "1011"
     * Output: "10101"
     */
    private static String binaryString(String a, String b) {

        int i = a.length()-1;
        int j = b.length()-1;
        StringBuilder res = new StringBuilder();
        int sum = 0;
        int carry = 0;
        while(i>=0 || j >= 0) {
            sum = carry;
            if (i >= 0) {
                sum += a.charAt(i)-'0';
                i--;
            }
            if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }
            carry = sum > 1 ? 1 : 0;
            res.append(sum % 2);
        }
        if (carry!=0) {
            res.append(carry);
        }
        return res.reverse().toString();



//        StringBuilder res = new StringBuilder();
//        int i = a.length() - 1;
//        int j = b.length() - 1;
//        int carry = 0;
//        while(i >= 0 || j >= 0){
//            int sum = carry;
//            if(i >= 0){
//                sum += a.charAt(i) - '0';
//                i--;
//            }
//            if(j >= 0){
//                sum += b.charAt(j) - '0';
//                j--;
//            }
//            carry = sum > 1 ? 1 : 0;
//            res.append(sum % 2);
//        }
//        if(carry != 0){
//            res.append(carry);
//        }
//        return res.reverse().toString();


    }


    public static boolean test1() {
        String s = String.valueOf(121);
        int s1 = 0;
        int s2 = 0;
        int s3 = 0;
        while(s2+1 <= s.length()-1 && s2+2<=s.length()-1) {
            s1++;
            s2+=2;
        }
        boolean flag = true;

        s2=0;
        s3=s.length()-1;
        while (s2<s1 && s3>s1) {
            if (s.charAt(s2)==s.charAt(s3)) {
                s1++;
                s3--;
            }  else {
                return false;
            }

        }
        return true;
    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


 public static class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode() {}
     TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }

     @Override
     public String toString() {
         return "TreeNode{" +
                 "val=" + val +
                 ", left=" + left +
                 ", right=" + right +
                 '}';
     }
 }
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode temp = head;
        while (temp != null && temp.next != null)
        {
            if (temp.next.val==temp.val)
            {
                temp.next=temp.next.next;
                continue;
            }
            temp=temp.next;
        }
        return head;
    }


    public static boolean isSame(TreeNode p, TreeNode q) {
//        ArrayList<Integer> list1  = new ArrayList<>();
//        ArrayList<Integer> list2  = new ArrayList<>();

        ArrayList<Integer> list1= preorderTraversal(p);
        ArrayList<Integer> list2  =preorderTraversal(q);
        int i =0;
        int j =0;
        while (i<list1.size() && j < list2.size()) {
            if (list1.get(i) != (list2.get(j))) {
                System.out.println(list1.get(i));
                System.out.println(list1.get(j));

                return false;
            }
            i++;j++;
        }
        if (i!=list1.size() || j !=list2.size()) return false;
        else return true;
    }

    public static void preOrder(TreeNode p, ArrayList<Integer> list) {
        if (p.left==null && p.right==null) {
            list.add(Integer.MIN_VALUE);
            return;
        }
        preOrder(p.left, list);
        list.add(p.val);
        preOrder(p.right, list);
    }

    public static ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> ans= new ArrayList<Integer>();
        preorder(root, ans);
        return ans;
    }

    public static void preorder(TreeNode root, ArrayList<Integer> ans) {
        if (root == null) {
            ans.add(500);
            return;
        }
        ans.add(root.val);
        preorder(root.left, ans);
        preorder(root.right, ans);
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(1);
      //  TreeNode n4 = new TreeNode(2);

        n1.left=n2;
        n1.right=n3;

        System.out.println(isSame(n1, n1));


    }


}
