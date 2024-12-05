package com.chenjunyi.practice;

/**
 * 如果一个节点X， 它的左树和右树结构完全一样，那么我们认为以X为头的子树是相等树， 给定一棵二叉树的头节点head，返回head整棵树上有多少棵相等子树
 *
 * 二叉树的递归套路
 * 1) 左树num 右树num  左树结构==右树结构  (左树num + 右树num + 1)
 *
 *
 */
public class EqualSubTree {

    public class Node {
        int value;
        Node left;
        Node right;
        Node(int value) {
            this.value = value;

        }

    }

    // 判断以head为头的树，相等树的个数
    // 左树相等树的个数 + 右树相等树的个数 + (左树结构和右树结构相等 ? 1 : 0);
    // 书简复杂度O(n*logN)
    public static int sameTree(Node head) {
        if (head == null) {
            return 0;
        }
        return sameTree(head.left) + sameTree(head.right) + (same(head.left, head.right) ? 1 : 0);

    }

    public static boolean same(Node left, Node right) {
        if (left == null && right == null) {
            return true;
        }

        if (left == null && right != null || left != null && right == null) {
            return false;
        }

        return left.value == right.value && same(left.left, right.left) && same(left.right, right.right);
    }


}
