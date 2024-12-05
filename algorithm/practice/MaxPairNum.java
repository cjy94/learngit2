package com.chenjunyi.practice;

import java.util.Arrays;

/**
 *  给定一个数组arr，代表每个人的能力值。再给定一个非负数k
 *  如果两个人能力差值正好为k，那么可以凑在一起比赛
 *  一局比赛只有两个人，
 *  返回最多可以同时有多少场比赛
 *
 *
 *  数组中两个数的差值正好是k的数组对最多有多少对?
 *
 *  暴力方法： 将数组中所有的全排列弄出来，再全排列中计算两个数的差值是否是k，以及等于k的数值对有多少对
 *
 *  贪心算法： 先对数组进行排序，窗口
 *
 */
public class MaxPairNum {

    // 暴力方法： 将数组的全排列， 再每一个组合中计算出现差值为k的数组对个数，再所有全排列中取最多的排列
    public static int maxPairNum1(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k < 0) {
            return 0;
        }
        return process(arr, 0, k);
    }


    public static int process(int[] arr, int index, int k) {
        int ans = 0;
        if (index == arr.length) {
            for (int i =1; i < arr.length; i+=2) {
                if (arr[i] - arr[i-1] == k) {
                    ans++;
                }
            }
        }  else {
           for (int j = index; j < arr.length; j++) {
                swap(arr, index, j);
                ans = Math.max(ans, process(arr, index+1, k));
                swap(arr, index, j);
           }
        }
        return ans;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    // 贪心算法
    public static int maxPairNum2(int[] arr, int k) {
        Arrays.sort(arr);
        int L = 0;
        int R = 0;
        int n = arr.length;
        int ans = 0;
        boolean[] used = new boolean[n];
        while (L < n && R < n) {
            if (used[L]) {
                L++;
            } else if (L >= R) {
                R++;
            } else {
                int diff = arr[R] - arr[L];
                if (diff == k) {
                   ans++;
                   used[R] = true;
                   R++;
                   L++;
                } else if (diff < k) {
                    R++;
                } else {
                    L++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {3,1,2,4};
        maxPairNum1(arr, 2);
    }
}
