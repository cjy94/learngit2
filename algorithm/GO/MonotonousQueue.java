package com.chenjunyi.GO;

import java.util.Arrays;

/**
 *  单调队列
 *  维持了一个依次成为最大值的可能性
 *
 *  题目：
 *  1、滑动窗口最大值
 *  给你一个整数数组nums，有一个大小为k的滑动窗口从数组的最左侧移动到数组的最右侧
 *  你只可以看到滑动窗口内的k个数字，滑动窗口每次只向右移动一位
 *  返回滑动窗口的最大值
 *  比如： [1 3 -1 -3 5 3 6 7]  k=3
 *  输出： [3 3 5 5 6 7]
 *
 * 2、绝对差不超过限制的最长连续子数组
 *  给你一个整数数组nums，和一个表示限制的整数limit，请返回最长连续子数组的长度
 *  该子数组中任意两个元素之间的绝对差必须小于等于limit，如果不存在返回0
 *
 *  其实就是子数组的最大值-最小值，维持最大值和最小值两个窗口， 如果max-min <= limit 那么就达标
 *
 *
 * 3、接雨水的最小花盆
 *  有个水滴数组matrix[][]，每个水滴有2维数据，[0]水滴的位置 [1]水滴的高度
 *  准备一个花盆，要求滴一滴和最后一滴水落下的时间间隔>=d， 求花盆的最小长度
 *
 *  和题目2类似， minQueue和maxQueue维持这个某一个窗口的最大值和最小值，要求max-min >= d 的最小窗口
 *  
 *
 *
 */

public class MonotonousQueue {
    // 3接雨水的花盆
    public static int minLength(int[][] matrix, int d) {
        int n = matrix.length;
        Arrays.sort(matrix, 0, n, (a,b) -> a[0]-b[0]);
        int ans = Integer.MAX_VALUE;
        int[] minQueue = new int[n];
        int[] maxQueue = new int[n];
        int minH=0, minT=0, maxH=0, maxT = 0;

        for (int l =0, r =0; l < n; l++) {
            while (r < n && !satisfy(d, matrix, maxQueue, minQueue, minH, minT, maxH, maxT)) {
                push(r++, matrix[r], maxQueue, minQueue, minH, minT, maxH, maxT);
            }

            if (satisfy(d, matrix, maxQueue, minQueue, minH, minT, maxH, maxT)) {
                ans = Math.min(ans, (matrix[r-1][0] - matrix[l][0]));
            }
            pop(l, maxQueue, minQueue, minH, minT, maxH, maxT);
        }
        return ans;
    }

    private static boolean satisfy(int d, int[][] matrix, int[] maxQueue, int[] minQueue, int minH, int minT, int maxH, int maxT) {
        int max = maxH < maxT ? matrix[maxQueue[maxT]][1] : 0;
        int min = minH < minT ? matrix[minQueue[minT]][1] : 0;
        return max - min >= d;
    }

    // 2 差值不超过限制
    public static int maxDiff(int[] nums, int limit) {
        int n = nums.length;
        int[] minQueue = new int[n];
        int[] maxQueue = new int[n];
        int ans = 0;
        int minH=0, minT=0, maxH=0, maxT = 0;
        for (int l = 0, r = 0; l < n; l++) {    // [l,r)  窗口左闭右开
            // r < n  说明还有下一个数字
            while (r < n && ok(limit, nums[r], nums, maxQueue, minQueue, minH, minT, maxH, maxT)) {
                push(r++, nums, maxQueue, minQueue, minH, minT, maxH, maxT);
            }
            // 窗口是l开头的子数组能向右延伸的最大范围  [l,r)
            ans = Math.max(ans, (r-l+1));
            // 换l开头
            pop(l, maxQueue, minQueue, minH, minT, maxH, maxT);
        }
        return ans;
    }

    private static boolean ok(int limit, int num, int[] arr, int[] maxQueue, int[] minQueue, int minH, int minT, int maxH, int maxT) {
        int max = maxH < maxT ? Math.max(arr[maxQueue[maxT]], num) : num;
        int min = minH < minT ? Math.min(arr[minQueue[minT]], num) : num;
        return max - min <= limit;
    }

    // arr[index]能否进入最大值窗口和最小值窗口
    private static void push(int index, int[] arr, int[] maxQueue, int[] minQueue, int minH, int minT, int maxH, int maxT) {
        while (maxH < maxT && arr[maxQueue[maxT]] <= arr[index]) {
            maxT--;
        }
        maxQueue[maxT++] = index;

        while (minH < minT && arr[minQueue[minT]] >= arr[index]) {
            minT--;
        }
        maxQueue[minT++] = index;
    }

    // index位置是过期的下标，需要在最大值窗口和最小值窗口中将其弹出
    private static void pop(int index, int[] maxQueue, int[] minQueue, int minH, int minT, int maxH, int maxT) {
          if (maxH < maxT && maxQueue[maxT] == index) {
              maxT++;
          }
          if (minH < minT && minQueue[minT] == index) {
              minT++;
          }
    }



    // 1 滑动窗口最大值
    public static int[] maxSlidingWindow(int[] arr, int k) {
        int n = arr.length;
        int[] queue = new int[n];  // 队列中元素是从大小排序的
        int h =0, t = 0;
        // 先形成k-1大小的窗口
        for (int i =0; i < k-1; i++) {
            while (h < t && arr[i] >= arr[queue[t-1]]) {
                t--;
            }
            queue[t++] = i;
        }
        int m = n-k+1;
        int[] ans = new int[m];
        for (int l =0, r = k-1; l < m; l++, r++) {
            while (h < t && arr[r] >= arr[queue[t-1]]) {
                t--;
            }
            queue[t++] = r;
            ans[l] = arr[queue[h]];
            if (queue[h] == l) { // l马上要++， 所以队列中的h也需要++
                h++;
            }
        }
        return ans;
    }
    
    
}
