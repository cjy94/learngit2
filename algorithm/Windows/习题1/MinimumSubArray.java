package com.chenjunyi.Windows.习题1;

/**
 *  累加和大于等于target的最短子数组
 *  计算以i位置结尾的情况下，能向左侧延伸的最近位置
 */
public class MinimumSubArray {


    public static int minSubArrayLen(int target, int[] nums) {
        int ans = Integer.MAX_VALUE;
        int n = nums.length;
        for (int l = 0, r = 0, sum = 0; r < n; r++) {
            sum += nums[r];
            while (sum - nums[l] >= target) {
                sum -= nums[l];
                l++;
            }

            if (sum >= target)
                ans = Math.min(ans, (r-l+1));
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    
}
