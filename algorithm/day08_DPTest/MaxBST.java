package com.chenjunyi.day08_DPTest;

import com.chenjunyi.day09_SubStringProblem.Code01_substring;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class MaxBST {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static class Info{
        public TreeNode maxBSTHead;
        public boolean isBST;
        public int max;
        public int min;
        public int maxBSTSize;
        public Info(TreeNode head, boolean isBST, int max, int min, int maxBSTSize) {
            this.maxBSTHead = head;
            this.isBST = isBST;
            this.min = min;  // 整棵树的最小值
            this.max= max;   // 整棵树的最大值
            this.maxBSTSize = maxBSTSize;
        }
    }

    public static Info process(TreeNode x) {
        // base case x是空树
        if (x == null) {
            return new Info(null, true, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }
        // base case x是叶子节点
        if (x.left == null && x.right == null) {
            return null;
        }


        // 向左树要信息
        Info left = process(x.left);
        // 向右树要信息
        Info right = process(x.right);

        // 组装自己的信息

        int max = x.val;
        int min = x.val;

        if (left != null) {
            max = Math.max(max, left.max);
            min = Math.min(min, left.min);
        }

        if (right != null) {
            max = Math.max(max, right.max);
            min = Math.min(min, right.min);
        }

        int maxBSTSize = 0;
        boolean isBST = false;
        TreeNode maxBSTHead = null;

        // 1） 最大搜索二叉树是左子树
        if (left != null) {
            maxBSTSize = left.maxBSTSize;
            maxBSTHead = left.maxBSTHead;
        }
        // 2） 最大搜索二叉树是右子树
        if (right != null && right.maxBSTSize > maxBSTSize) {
            maxBSTSize = right.maxBSTSize;
            maxBSTHead = right.maxBSTHead;
        }

        // 3）整棵树都是搜索二叉树
        // 左树得是bst, 右树得是bst， 并且当前节点大于左树的最大值， 当前节点的值小于右树的最小值
        if (
                (left == null || left.isBST) &&
                (right == null || right.isBST)

        ) {
            if (
                    (left == null || x.val > left.max) &&
                    (right == null || x.val < right.min)

            ) {
                // 整棵树都是bst
                isBST = true;
                maxBSTHead = x;
                int leftSize = left == null ? 0 : left.maxBSTSize;
                int rightSize = right == null ? 0 : right.maxBSTSize;

                maxBSTSize = leftSize + 1 + rightSize;
            }

        }
        return new Info(maxBSTHead, isBST, max, min, maxBSTSize);

    }

    public static int maxValue(int[] arr) {
        if (arr== null || arr.length == 0) {
            return 0;
        }
        int cur = arr[0];
        int max = Integer.MIN_VALUE;
        for (int i =1; i < arr.length; i++) {
            max = Math.max(max, cur);
            cur += arr[i];
            cur = cur < 0 ? 0 : cur;

        }
        return max;

        
    }



    public static void main(String[] args) {
        int[] arr = {1,1,-1,-10,11, 4, -6, 9, 20, -10, -2};
        System.out.println(maxValue(arr));
    }


}

