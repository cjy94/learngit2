package com.chenjunyi.zhongji二.zhongji2;

import java.util.Arrays;

/**
 * 最小不可组成和问题
 * 非常经典的背包问题
 * 
 */
public class MinUncomposibleSum {

    public static int minSum(int[] arr) {
        int sum = arr[0];
        int min = arr[0];

        for (int i =1; i < arr.length; i++) {
            sum += arr[i];
            min = Math.min(min, arr[i]);

        }
        int n = arr.length;

        boolean[][] dp = new boolean[n][sum+1];
        // 默认第一列都是false, 第一列dp[i][0], 表示arr[0..i]位置上的元素自由组合，能否组合出0，
        // 第一行
        dp[0][arr[0]] = true;
        for (int i =1; i < n; i++) {
            for (int j =1; j < sum; j++) {
                if (arr[i] == j) {
                    dp[i][j] = true;
                }
                if (dp[i-1][j] || (j-arr[i] >= 0 && dp[i-1][j-arr[i]])) {
                    dp[i][j] = true;
                }
            }
        }

        for (int i =min; i <= sum; i++) {
            if (dp[n-1][i] == false) {
                return i;
            }
        }
        return sum+1;
    }


    // 数组中存在1的情况， 可以更快的找到最小不可组成和
    public static int minSum2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int range = 1;
        Arrays.sort(arr);
        for (int i =1; i != arr.length; i++) {
            if (arr[i] > range + 1) {
                return range + 1;
            } else {
                range += arr[i];
            }
        }
        return range+1;
    }

    public static void main(String[] args) {
        int[] arr = {3,2,5};
        System.out.println(minSum(arr));
    }
    
}
