package com.chenjunyi.DynamicProgramming.子数组最大累加和问题;

/**
 * 子数组最大累加和问题
 *
 *  一个整数数组nums，找出一个具有最大累加和的非空子数组，返回其最大累加和
 *
 *  以i结尾情况下的累加和是多少，取所有i结尾情况下的累加和最大值
 *
 *  dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
 *
 *
 * 附加问题
 * 子数组中找到拥有最大累加和的子数组，并返回如下三个信息
 * 1) 最大累加和的子数组的开头left
 * 2) 最大累加和的子数组的结尾right
 * 3) 最大子数组的累加和sum
 *
 */
public class MaximumSumSubArray {

    public int maxSubArray1(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int result = dp[0];
        for (int i=1; i < n; i++) {
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            result = Math.max(dp[i], result);
        }
        return result;
    }

    // 附加题，返回最大累加和值和数组的left,right信息
    public static int[] maxSubArray2(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = 0;
        int sum = Integer.MIN_VALUE;

        int l = 0;
        int r = 0;
        int pre = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (pre >= 0) {
                r = i;
                pre += nums[i];
            } else {
                l = r = i;
                pre = nums[i];
            }
            if (pre > sum) {
                sum = pre;
                left = l;
                right = r;
            }
        }
        return new int[] {left, right, sum};
    }

    public static void main(String[] args) {
        int[] arr = {5,-2,3,-9,4,-1,3,1,-9,4};
        int[] ints = maxSubArray2(arr);
        System.out.println(ints[0] +" "+ ints[1] +" " +ints[2]);

    }
}
