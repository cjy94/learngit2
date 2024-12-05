package com.chenjunyi.practice;

/**
 *
 *   dda'jia'jie'she * 一个数组中，在不能取相邻数的情况下，子序列的最大累加和问题
 *
 * 背包问题?  dp[i]
 * 1、只包含i自己   arr[i]
 * 2、不包含i  dp[i-1]
 * 3、i+ dp[i-2]
 *
 * 3种情况选最大
 */
public class DaJiaJieSheProblem {

    public static int maxSum(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);
        for (int i = 2; i < n; i++) {
            // 1、只选择当前位置的值最为子序列的最大值
            // 2、不选择i位置的值， 选择dp[i-1]的结果最为子序的最大值
            // 3、选择i位置的值，同时也选择dp[i-2]的结果作为子序列的最大值
            dp[i] = Math.max(arr[i], Math.max(arr[i]+dp[i-2], dp[i-1]));
        }
        return dp[n-1];
    }
}
