package com.chenjunyi.MonotonicQueue.习题1;

/**
 *  最长连续子数组
 *
 *  绝对差不超过限制的最长连续子数组
 *  给你一个整数数组Nums, 和一个表示限制的整数limit
 *  请返回最长连续子数组的长度
 *  该子数组中的任意两个元素之间的绝对差必须小于或者等于limit
 *
 *  其实就是子数组内部的最大值和最小值差值的绝对值小于等于limit， 范围上的最大值和最小值达标，其他两个值之间的差值绝对值也会达标
 *
 *
 */
public class LongestContiguousSubarray {
    static int MAX = 10000;
    static int[] minQueue = new int[MAX];
    static int[] maxQueue = new int[MAX];
    static int maxL = 0, maxR = 0;
    static int minL = 0, minR = 0;
    static int[] arr;

    public static int length(int[] nums, int limit) {
        int n = nums.length;
        arr = nums;
        int ans = 0;
        for (int l = 0, r = 0; l < n; l++) {
            // [l,r) r永远是要进入的下标
            // r < n 还有数要进队列
            // ok() 计算以下nums[r]进队列能否满足(max - min <= limit)的要求
            // 有下一个位置，并且下一个位置的数ok
            while (r < n && ok(limit, nums[r])) {   //
                push(r++);
            }
            // 达标的范围是[l...r)
            ans = Math.max(ans, r-l);
            pop(l);
        }
        return ans;
    }

    private static void pop(int l) {
        if (maxL < maxR && maxQueue[maxL] == l)
            maxL++;
        if (minL < minR && minQueue[minL] == l)
            minL++;
    }


    // 最大值窗口的maxL位置的元素和num取一个最大值max
    // 最小值窗口的minL位置的元素和num取一个最小值min
    // max - min <= limit
    private static boolean ok(int limit, int num) {
        int max = maxL < maxR ? Math.max(arr[maxQueue[maxL]], num) : num;
        int min = minL < minR ? Math.min(arr[minQueue[minL]], num) : num;
        return max - min <= limit;
    }

    private static void push(int i) {
        // 大 --> 小  新进来的数要小于maxR-1的值
        while (maxL < maxR && arr[maxQueue[maxR - 1]] <= arr[i]) {
            maxR--;
        }
        maxQueue[maxR++] = i;

        // 小 ---> 大
        while (minL < minR && arr[minQueue[minR - 1]] >= arr[i]) {
            minR--;
        }
        minQueue[minR++] = i;
    }
}
