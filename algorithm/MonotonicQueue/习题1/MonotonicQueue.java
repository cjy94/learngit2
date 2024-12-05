package com.chenjunyi.MonotonicQueue.习题1;

/**
 * 单调队列的实现
 *
 * 滑动窗口: 窗口是左闭右开的 [)
 * 窗口内维持这某个区域的最小值或者最小值
 *
 *  一个整数数组nums, 有一个大小为k的滑动窗口从数组的最左侧移动到数组的最右侧
 *  你只可以看到在滑动窗口内的k个数字，
 *  返回滑动窗口中的最大值
 *
 *  先维持一个k-1大小的窗口
 *
 */
public class MonotonicQueue {
    final static int MAX = 10000;

    static int[] queue = new int[MAX];

    static int h, t;  // 窗口的范围[h...t-1]     滑动窗口是 [)

    //
    public static int[] queue(int[] nums, int k) {
        int n = nums.length;
        h = t = 0;
        // 先形成长度为k-1的窗口, 窗口内的数据是从大到小的
        for (int i =0; i < k-1; i++) {
            // 窗口内的数据从左往右是从大到小的
            while (h < t && nums[queue[t-1]] <= nums[i]) {
                t--;
            }
            queue[t++] = i;
        }

        int m = n - k +1;
        int[] ans = new int[m];
        for (int l =0, r = k-1; l < m; l++, r++) {
            while (h < t && nums[queue[t-1]] <= nums[r]) {
                t--;
            }
            queue[t++] = r;  // 右侧进窗口
            // 窗口已经形成， 收集答案
            ans[l] = nums[queue[h]];
            // l位置的数出去
            if (queue[h] == l)
                h++;      
        }

        return ans;



    }
}
