package com.chenjunyi.GO;

/**
 *   2、输入一个整数数字列，可以是整数或者负数，寻找连续若干个数相加的最大值，
 *
 *   思路： 以每一个位置为结尾情况下能向前延伸多远可以获取到最大累加和，比较每一个位置的可以获取的最大累加的最大值进行返回
 *   dp[0] = arr[0] 0位置的数值最大累加和只能是自己
 *   dp[i] (i>0): 可能的情况是自己不动arr[i]; 或者自己和前面获取的最大累加和相加dp[i-1]+arr[i]
 *   这两种情况取最大值 dp[i] = Math.max(dp[i]+arr[i], arr[i])
 */
public class aliyun2 {
    public static void main(String[] args) {
        int[] arr = {8,-1,2,-1,3,-4};
        System.out.println(maxSum(arr));
    }

    public static int maxSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int len = arr.length;
        int[] dp = new int[len];
        dp[0] = arr[0];
        for (int i =1; i < len; i++) {
            dp[i] = Math.max(dp[i-1] + arr[i], arr[i]);
        }
        int res = 0;
        for (int i =0; i < len; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
