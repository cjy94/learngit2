package com.chenjunyi.DynamicProgramming.子数组最大累加和问题;

/**
 *  不能选相邻元素的最大累加和问题
 *  给定一个数组，可以随意选择数字，但是不能选择相邻的数字，返回能得到的最大累加和
 *
 *  nums[i] 选择 dp[i] = dp[i-2] + nums[i];
 *  nums[i]不选择dp[i] = dp[i-1];
 *  只要nums[i]  dp[i] = nums[i];
 *
 *  三种情况选择最大作为dp[i]的值
 */
public class MaxSumWithoutAdjacent {

    public int maxSum(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        int result = Math.max(dp[0], dp[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(Math.max(dp[i-2] + nums[i], nums[i]), dp[i-1]);
            result = Math.max(result, dp[i]);
        }
        return  result;
    }

    public int maxSum1(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        if (nums.length == 2)
            return Math.max(nums[0], nums[1]);
        
        int n = nums.length;
        int prepre = nums[0];
        int pre = Math.max(nums[0], nums[1]);
        int cur = Math.max(pre, prepre);
        for (int i = 2; i < n; i++) {
            cur = Math.max(Math.max(prepre + nums[i], nums[i]), pre);
            prepre = pre;
            pre = cur;
        }
        return pre;
    }
}
