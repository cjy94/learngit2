package com.chenjunyi.DynamicProgramming.子数组最大累加和问题;

/**
 *  打家劫舍问题
 *  沿街有一排连续的房子，小偷不能偷连续房子内的现金
 *  小偷的 偷窃能力 定义为他在窃取过程中能从房间中窃取的 最大金额
 *  给你一个整数数组nums表示每个房间内的现金金额
 *  第i房间中房有nums[i]的现金
 *  另外给一个整数k, 表示小偷需要窃取至少k间房屋
 *  返回小偷需要的最小窃取能力值
 *
 *
 * 能力是有范围的： 房屋最小金额 ~ 房屋最大金额
 * 对能力进行二分，每次二分的选择一个能力，如果这个能力值能满足偷够k间不相邻的房屋，则记录该能力值，能往左侧看能否在找到更小的能力值满足； 否则向右侧查找
 *
 * 
 */
public class DaJiaJieShe {

    public int houses(int[] nums, int k) {
        int n= nums.length;
        int l = nums[0];
        int r = nums[0];
        for (int i = 1; i < n; i++) {
            l = Math.min(l, nums[i]);
            r = Math.max(r, nums[i]);
        }
        int ans = 0;
        while (l < r) {
            int m = ((r-l) >> 1) + l;
            if (best(nums, n, m) >= k) {
                ans = m;
                r = m-1;
            } else {
                l = m+1;
            }
        }
        return ans;
    }

    // nums[]: 每个房屋的现金金额数
    // n: nums数组的长度
    // ability: 小偷的偷窃能力
    // 要求，在nums[]数组中，不能偷连续的房子，并且偷窃的房子金额要小于等于ability， 在这样的要求下，最多偷窃的房子数量
    // 返回最多偷窃的房子数量
    private int best(int[] nums, int n, int ability) {
        if (n == 1)
            return nums[0] <= ability ? 1 : 0;

        if (n == 2)
            return (nums[0] <= ability || nums[1] <= ability) ? 1 : 0;

        int prepre = nums[0] <= ability ? 1 : 0;
        int pre = (nums[0] <= ability || nums[1] <= ability) ? 1 : 0;
        int cur = 0;
        for (int i =2; i < n; i++) {
            cur = Math.max(pre, nums[i] <= ability ? (1 + prepre) : prepre);
            prepre = pre;
             pre = cur;
        }
        return pre;
    }

}
