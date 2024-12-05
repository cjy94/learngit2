package com.chenjunyi.DynamicProgramming.子数组最大累加和问题;

/**
 * 环形数组的子数组最大累加和
 *
 * 答案会分成两种情况
 * 1) 数组是连续的:
 * 2) 数组是被隔断的： 答案长度是all-(数组的最小连续累加和数组的长度)
 *
 *
 * 附加：
 * 环形数组中不能选择相邻元素的最大累加和
 *  1) 0位置的数字不选择，可选择的范围是[1..n]
 *  2) 0位置的数字选择， 可选择的范围是[0..n-1]
 *  两种情况选择最大值
 *
 * 
 */

public class CircularArrayMaximumSum {

    // 环形数组的最大累加和问题
    public int maxSum1(int[] nums) {
        int n = nums.length;
        int all = 0;
        int maxPre = 0;
        int minPre = 0;
        int maxResult = 0;
        int minResult = 0;
        for (int i =0; i < n; i++) {
            all += nums[i];
            maxPre = Math.max(nums[i], (nums[i] + maxPre));
            maxResult = Math.max(maxResult ,maxPre);
            minPre = Math.min(nums[i], (nums[i]+minPre));
            minResult = Math.min(minPre, minResult);
        }
        return all == minResult ? maxResult : Math.max(maxResult, (all-minResult));
    }

    // 环形数组的最大累加和问题 附加题
    // 不能选择相邻数字
    public int maxSum2(int[] nums) {
        int n = nums.length;

        return Math.max(best(nums, 0, n-2), best(nums, 2, n-1) + nums[0]);
    }

    // 在nums[left...right]范围上选择不相邻数字的最大累加和
    private int best(int[] nums, int l, int r) {
        if (l > r)
            return 0;

        if (l == r) {
            return nums[l];
        }

        if (l+1 == r) {
            return Math.max(nums[l], nums[r]);
        }

        int prepre = nums[l];
        int pre = Math.max(nums[l], nums[l+1]);
        int cur = Math.max(pre, prepre);
        for (int i =l+2; i <= r; i++) {
            cur = Math.max(Math.max(prepre + nums[i], nums[i]), pre);
            prepre = pre;
            pre = cur;
        }
        return pre;

    }

}
