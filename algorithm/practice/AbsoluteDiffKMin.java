package com.chenjunyi.practice;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 数字对的差值绝对值
 * {5,3,1,4}
 * (5,3) (5,1) (5,4) (3,1) (3,4) (1,4)
 * 2  4  1  2  1  3
 * 所有数字对差值绝对值排序后的第k个是：
 *
 *
 * 1 3 4 5 所有差值绝对值在0--4之间
 *
 * 思路： 二分
 * 1、遍历一遍数组，取出数组中的最大值和最小值，计算一个差值max, 数组的最小差值是min=0
 * 在[min, max]之间找到第k小的差值
 *
 *
 * 数组中插值绝对值<=x的数量有多少
 * [1,3,6,9,13,17,19,25]   使用双指针
 *  L R
 */
public class AbsoluteDiffKMin {

    public static int absoluteDiff(int[] arr, int k) {
        int n = arr.length;
        Arrays.sort(arr);
        int max = arr[n-1] - arr[0];
        int min = 0;
        int ans = -1;
        while (min <= max) {
            int mid = min + ((max-min) >> 1);
            if (diff(arr,mid) < k) {
                ans = mid;
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return ans + 1;
    }

    // 数组arr中， 差值绝对值小于等x的数字对有多少
    // arr是有序的
    public static int diff(int[] arr, int x) {
        int ans = 0;
        // 外层控制l ， 内层控制r
        for (int l =0, r = 1; l < arr.length; r = Math.max(r, ++l)) {
            while (r < arr.length && Math.abs(arr[l] - arr[r]) <= x) {
                r++;
            }
            ans += (r - l - 1);
        }
        return ans;


    }

    public static void main(String[] args) {
        int[] arr = {1,3,5,7,9};
        System.out.println(diff(arr,2));
    }
}
