package com.chenjunyi.zhongji二.zhongji6;

/**
 * 最长递增路径问题
 */
public class LongestIncPath {

    public static int maxPath(int[][] martix) {
        int ans = Integer.MIN_VALUE;
        // 认为是递归函数的缓存
        int[][] dp = new int[martix.length][martix[0].length];

        for (int i =0; i < martix.length; i++) {
            for (int j =0; j < martix[0].length; j++) {
                ans = Math.max(ans, process(martix, i, j, dp));
            }
        }
        return ans;
    }

    // 从matrix[row][col]出发， 能够得到的最长递增路径，长度返回
    // 终止条件就是4个if条件都不命中，
    private static int process(int[][] martix, int row, int col, int[][] dp) {

        if (dp[row][col] != 0) {
            return dp[row][col];
        }
        // 尝试走上
        int nextUp = 0;
        if (row >= 1 && martix[row][col] < martix[row-1][col]) {
            nextUp = process(martix, row-1, col, dp);
        }
        // 尝试走下
        int nextDown = 0;
        if (row+1 < martix.length && martix[row][col] < martix[row+1][col]) {
            nextDown = process(martix, row+1, col, dp);
        }
        // 尝试走左
        int nextLeft = 0;
        if (col-1 >= 0 && martix[row][col] < martix[row][col-1]) {
            nextLeft = process(martix, row, col-1, dp);
        }
        // 尝试走右
        int nextRight = 0;
        if (col+1 < martix[0].length && martix[row][col] < martix[row][col+1]) {
            nextRight = process(martix, row, col+1, dp);
        }
        int ans = Math.max(Math.max(nextUp, nextDown), Math.max(nextLeft, nextRight)) + 1;
        dp[row][col] = ans;
        return ans;
    }


}
