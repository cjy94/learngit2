package com.chenjunyi.DynamicProgramming.子数组最大累加和问题;

/**
  乘积最大子数组
    给你一个double类型数组nums，请找出数组中乘积最大的非空连续子数组，
    并返回该子数组所对应的乘积

    乘法存在正数和负数的问题

  三种可能性：
 1) nums[i]
 2) max[i-1] * nums[1]  正数情况
 3) min[i-1] * nums[i]  负数情况，负负得正
 */
public class MaximumCumulativeProduct {

    /**
        可能性：
        (1) 自己就是最大结果 nums[i]
        (2) 当前数是一个负数， 需要和前面的最小值相乘得到最大值 min[i-1] * nums[i]
        (3) 当前数是一个正数， 需要和前面的最大值相乘得到最大值 max[i-1] * nums[i]
        三种情况取最大值

     */
    public static int max(int[] nums) {
        int n = nums.length;
        int ans = nums[0];
        int min = nums[0];
        int max = nums[0];
        for (int i =1; i < n; i++) {
            int curMin = Math.min(nums[i], Math.min(nums[i] * min, nums[i] * max));
            int curMax = Math.max(nums[i], Math.max(nums[i] * min, nums[i] * max));
            min = curMin;
            max = curMax;
            ans = Math.max(ans, max);
        }
        return ans;
    }

    public static int maxProduct(int[] nums) {
        int n = nums.length;
        int preMin = nums[0];
        int preMax = nums[0];
        int result = nums[0];
        int curMin = 0;
        int curMax = 0;
        for (int i = 1; i < n; i++) {
            // 之前的最大值*当前值，之前的最小值*当前值，当前值 取一个最大值
            curMax = Math.max(Math.max(preMax * nums[i], preMin * nums[i]), nums[i]);
            // 之前的最大值*当前值，之前的最小值*当前值，当前值 取一个最小值
            curMin = Math.min(Math.min(preMin * nums[i], preMax * nums[i]), nums[i]);
            preMin = curMin;
            preMax = curMax;
            result = Math.max(result, preMax);
        }
        return result;
    }
}
