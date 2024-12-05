package com.chenjunyi.gaoji一.gaoji5;

public class MaxPath_TreeDp {
    private static class TreeNode{
        int value;
        TreeNode left;
        TreeNode right;
        TreeNode(int value) {
            this.value = value;
        }
    }

    private static class Info{
        int maxPath;
        int headMaxPath;
        public Info(int maxPath, int headMaxPath) {
            this.maxPath = maxPath;
            this.headMaxPath = headMaxPath;
        }
    }

    private static Info process(TreeNode x) {
        if (x == null) {
            return null;
        }
        Info left = process(x.left);
        Info right = process(x.right);


        int p1 = Integer.MIN_VALUE;
        // 第一种情况, 和x无关
        if (left != null) {
            p1 = Math.max(left.maxPath, p1);
        }
        if (right != null) {
            p1 = Math.max(right.maxPath, p1);
        }

        // 第2种情况, 和x有关
        // 2.1 x自己
        int p2 = x.value;
        // 2.2 左孩子开始的最大距离 ， 只往左走
        int p3 = left != null ? left.headMaxPath + x.value : Integer.MIN_VALUE;

        // 2.3 右孩子开始的最大距离， 只往右走
        int p4 = right != null ? right.headMaxPath + x.value : Integer.MIN_VALUE;

        // 如果可能往上走的话，那么存在p5的情况
//        int p5 = left != null && right != null ? x.value + left.headMaxPath + right.headMaxPath : Integer.MIN_VALUE;

        int maxPath = Math.max(Math.max(p1, p2), Math.max(p3, p4));
        int headMaxPath = Math.max(p2, Math.max(p3, p4));

        return new Info(maxPath, headMaxPath);
        
    }


}
