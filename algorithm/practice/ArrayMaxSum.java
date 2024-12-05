package com.chenjunyi.practice;

/**
 * 数组最大累加和问题
 */
public class ArrayMaxSum {

    public static int maxSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int pre = arr[0];    // 上一步的最大累加和
        int ans = arr[0];
        for (int i =1; i < arr.length; i++) {
            pre = Math.max(arr[i] + pre, arr[i]);
            ans = Math.max(pre, ans);
        }
        return ans;
    }
}
