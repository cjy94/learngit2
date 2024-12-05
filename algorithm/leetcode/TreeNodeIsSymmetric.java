package com.chenjunyi.leetcode;


import javax.print.DocFlavor;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class TreeNodeIsSymmetric {

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
    }


    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(3);
        TreeNode n5 = new TreeNode(4);
        TreeNode n6 = new TreeNode(3);
        TreeNode n7 = new TreeNode(4);
        n1.left=n2;
        n1.right=n3;
        n2.left=n4;
        n2.right=n5;
        n3.left=n7;
        n3.right=n6;

        System.out.println(maxPath(n1));
        System.out.println(maxPath1(n1));

    }


    public static int maxSum = Integer.MIN_VALUE;
    public static int maxPath1(TreeNode node) {
        process11(node, 0);
        return maxSum;

    }

    private static void process11(TreeNode node, int i) {
        if (node.left == null && node.right == null) {
            maxSum = Math.max(maxSum, i + node.val);
        }
        if (node.left != null) {
            process11(node.left, i + node.val);
        }

        if (node.right != null) {
            process11(node.right, i + node.val);
        }
    }


    public static int maxPath(TreeNode node) {
        return process10(node);

    }

    private static int process10(TreeNode node) {
        if (node.left == null && node.right == null) {
            return node.val;
        }
        int left = 0;
        int right = 0;
        if (node.left != null) {
            left = process10(node.left) + node.val;
        }

        if (node.right != null) {
            right = process10(node.right) + node.val;
        }
        return Math.max(left, right);
    }


    // 利用二叉树的dp套路，父节点向它的左右孩子要信息，
    public static class Info{
        boolean isSymmetric;
        int value;
        Info(boolean isSymmetric/*, int value*/) {
            this.isSymmetric = isSymmetric;
            this.value = value;

        }
    }

    public static Info process(TreeNode left, TreeNode right) {
        // 如果是叶子节点，认为是镜像的节点
        if (left==null && right== null) {
            return new Info(true);
        }
        if (left==null && right!=null) {
            return new Info(false);
        }
        if (left!=null && right==null) {
            return new Info(false);
        }
        if (left.val != right.val) {
            return new Info(false);
        }

        // 向左孩子要信息
        Info leftInfo = process(left.left, right.right);
        // 向右孩子要信息
        Info rightInfo = process(left.right, right.left);

        // 根据左右孩子的信息，汇总自己的信息
        if (leftInfo.isSymmetric==true && rightInfo.isSymmetric==true ) {
            return new Info(true);
        } else {
            return new Info(false);
        }


    }

    public static boolean isSymmetric(TreeNode root) {
        if (root==null) return true;
        return process(root.left, root.right).isSymmetric;

    }

}
