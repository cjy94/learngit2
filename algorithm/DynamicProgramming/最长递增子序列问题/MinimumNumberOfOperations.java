package com.chenjunyi.DynamicProgramming.最长递增子序列问题;

/**
 *  使数组K递增的最少操作数
 *  给你一个下标从0开始包含n个正整数的数组arr，和一个正整数k
 *  如果对于每个满足k <= i <= n-1的下标i
 *  都有arr[i-k] <= arr[i], 那么称arr是k递增的
 *  每一次操作中，可以选择一个下标i并将arr[i]改成任意正整数
 *  请返回对于给定的k，使数组变成K递增的最少操作数
 *
 *
 *   例如： [5 2 4 7 2 4 6 2]   k=3
 *   1、0开始：[5 7 6]
 *   2、1开始：[2 2 2]
 *   3、2开始：[4 4 ]
 *
 *   划分好每一组的数据，再这一组中求出最长递增子序列的长度，用length-最长递增子序列的长度 就是最少操作数
 *
 *
 *
 */
public class MinimumNumberOfOperations {

    public static int kIncreasing(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        int[] arr = new int[n];
        for (int i =0; i <n; i++) {
            int len = 0;

            for (int j = i; j < n; j += k) {
                arr[len++] = nums[j];
            }
            ans += (len - lengthOfNoDecreasing(arr, len));
        }
        return ans;
    }

    // 再数组arr[0...len]范围上找到一组 最长递增子序列
    private static int lengthOfNoDecreasing(int[] arr, int len) {
        int[] ends = new int[len];
        int length = 0;
        for (int i =0, find = 0; i < len; i++) {
            find = find(ends, length, arr[i]);
            if (find == -1)
                ends[length++] = arr[i];
            else
                ends[find] = arr[i];
        }
        return length;

    }

    private static int find(int[] ends, int length, int value) {
        int l = 0; int r = length - 1;
        int ans = -1;
        while (l <= r) {
            int mid = (r-l) / 2 + l;
            if (ends[mid] > value) {  // 最长不下降
                ans = mid;
                r = mid - 1;
            } else {
                l = mid +1;
            }
        }
        return ans;
    }
}
