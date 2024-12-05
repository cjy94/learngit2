package com.chenjunyi.practice;

/**
 * 在两条独立的水平线上按给定的顺序写下nums1 和 nums2的整数，
 * 现在，可以控制一些连续两个数字nums[i]和nums[j]的直线，这些直线需要同时满足：nums[i]==nums[j]，且直线不与任何其他连线相交
 *
 * 比如：
 * 1 4 2
 * 1 2 4
 *
 * 2条： 1 -> 1  和 4 -> 4   或者
 *      1 -> 1  和 2 -> 2
 *
 *  思路：两个字符串的最长公共子序列
 */
public class MaxUncrossedLines {

    public static int maxUncrossedLines(int[] nums1, int[] nums2) {
        int N = nums1.length;
        int M = nums2.length;
        int[][] dp = new int[N][M];
        dp[0][0] = nums1[0] == nums2[0] ? 1 : 0;
        // 第一行
        for (int j =1; j < M; j++) {
            dp[0][j] = nums1[0] == nums2[j] ? 1 : dp[0][j-1];
        }

        for (int i =1; i < M; i++) {
            dp[i][0] = nums1[i] == nums2[0] ? 1 : dp[i-1][0];
        }

        for (int i =1; i < N; i++) {
            for (int j =1; j < M; j++) {
                int p1 = dp[i-1][j];
                int p2 = dp[i][j-1];
                int p3 = nums1[i] == nums2[j] ? (dp[i-1][j-1]+1) : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[N-1][M-1];
    }
}
