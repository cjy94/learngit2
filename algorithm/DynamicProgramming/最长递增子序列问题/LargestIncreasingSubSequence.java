package com.chenjunyi.DynamicProgramming.最长递增子序列问题;

/**
 *
 *   最长递增子序列问题
 *   给定一个整数数组nums，找到其中(1)最长严格递增子序列长度  [i-1] < [i]，(2) 最长不下降子序列长度  [i-1] <= [i]
 *
 *   dp[i]: 以i位置做结尾的最长递增子序列
 *     
 *      1) 如果nums[i] <= nums[i-1]， 那么就需要在[0...i-1]范围上找到严格小于nums[i]的值，那个基础上加1
 *      2) 如果 nums[i] > nums[i-1]的时候，dp[i-1] + 1;
 *
 *
 *      前面的数字中比我小的离我最近的数在什么位置，要进行记录
 *      
 *
 */
public class LargestIncreasingSubSequence {

    // 找到nums上的最长递增子序列
    public static int largest(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int[] dp = new int[n];

        /**
         * 以0位置结尾， 0-0范围上形成的最长子序列长度
         * 以1位置结尾， 0-1范围上形成的最长子序列长度
         *  ...
         * 以i位置结尾，0-i范围上形成的最长子序列长度
         *
         */
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j =0; j <i ; j++) {  // 时间复杂度O(n^2)
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // 使用ends数组，做优化的求解最长递增子序列的方法
    // ends[i] 表示目前所有长度为i+1的递增子序列的最小结尾是多少
    // 时间复杂度 O(n * log n)
    public static int largest1(int[] nums) {
        int n = nums.length;
        int[] ends = new int[n];
        int length = 0;
        for (int i =0, find = 0; i < n; i++) {
            find = find(ends, length, nums[i]);
            if (find == -1)
                ends[length++] = nums[i];
            else
                ends[find] = nums[i];
        }
        return length;
    }

    // 在ends[0...length]范围上， 查找大于num的最左的位置
    private static int find(int[] ends, int length, int num) {
        int l = 0; int r = length - 1;
        int ans = -1;   // 不存在 返回-1， 就是ends数组要扩充有效区
        while (l <= r) {
            int mid = (r-l)/2 + l;
            if (ends[mid] >= num) {
                ans = mid;
                r = mid-1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }


}
