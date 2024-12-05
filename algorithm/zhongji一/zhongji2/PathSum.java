package com.chenjunyi.zhongji一.zhongji2;

/**
 * 二叉树的路径和
 */
public class PathSum {
    public static class Node{
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    public static int maxSum = Integer.MIN_VALUE;

    public static int maxPath(Node head) {
        process(head, 0);
        return maxSum;
    }

    private static void process(Node head, int i) {
        if (head.left == null && head.right == null) {
            maxSum += i + head.value;
        }

        if (head.left != null) {
            process(head.left, i + head.value);
        }

        if (head.right != null) {
            process(head.right, i + head.value);
        }
    }

    private static int process2(Node head) {
        if (head.left == null && head.right == null) {
            return head.value;
        }
        int next = Integer.MIN_VALUE;
        if (head.left != null) {
            next = process2(head.left);
        }

        if (head.right != null) {
            next = Math.max(next, process2(head.right));
        }
        return next + head.value;
    }
}
