package com.chenjunyi.practice;

import java.util.Arrays;

/**
 * 给定一个正数数组arr， 长度n, 正数x和y
 * 目标是让arr整体的累加和<=0
 * 可以对数组种的数num 执行以下两种操作中的一种， 且每个数最多执行一次操作：
 * 1) 可以选择让num变成0， 承担x的代价
 * 2) 可以选择让num变成-num， 承担Y的代价
 * 返回达到目的的最小代价
 *
 * 
 *
 */
public class XYProblem {

    // 动态规划方式
    // 类似背包： 一个位置执行3中操作，要么不操作，要么x操作，要么y操作
    public static int minOpStep1(int[] arr, int x, int y) {
        int sum = 0;
        for (int value : arr) {
            sum += value;
        }

        return process(arr, x, y, 0, sum);
    }


    // 对 arr[index...] index以及index后面的数字进行x或者y的操作，使其total<=0的最小代价
    public static int process(int[] arr, int x, int y, int index, int total) {
        // 如果arr数组的累加和total <= 0， 不需要左任何操作，返回0
        if (total <= 0) {
            return 0;
        }
        //如果 total >0 但是已经到了数组末尾位置
        if (index == arr.length) {
            return Integer.MAX_VALUE;
        }

        // 不做任何操作的代价
        int p1 = process(arr, x, y, index+1, total);
        // p2 做x操作的代价
        int p2 = Integer.MAX_VALUE;
        int p2Next = process(arr, x, y, index+1, total - arr[index]);
        if (p2Next != Integer.MAX_VALUE) {
            p1 = x + p2Next;
        }

        // 做y操作的代价
        int p3 = Integer.MAX_VALUE;
        int p3Next = process(arr, x, y, index+1, total - 2 * arr[index]);
        if (p3Next != Integer.MAX_VALUE) {
            p3 = y + p3Next;
        }
        return Math.min(p1, Math.min(p2, p3));
    }

    // 贪心的方案
    // 将数组进行从大到小排序
    public static int minOpStep2(int[] arr, int x, int y) {
        Arrays.sort(arr);
        for (int l =0, r = arr.length-2; l <= r ;l++, r--) {
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
        }
        // 全部做y操作
        if (x >= y) {
            int sum = 0;
            for (int value : arr) {
                sum += value;

            }
            int cost = 0;
            for (int i = 0; i < arr.length && sum > 0; i++) {
                sum -= (arr[i] << 1);
                cost += y;
            }
            return cost;
        } else {  // 可能x操作， 可能y操作
            // 求一个后缀累加和
            int n = arr.length;
            int prefixSum[] = new int[n];
            prefixSum[n-1] = arr[n-1];
            for (int i = n-2 ; i >=0 ; i--) {
                prefixSum[i] += (prefixSum[i+1] + arr[i]);
            }
            // 执行0个Y操作的代价
            int left = mostLeft(arr, 0, 0);
            int cost = left * x;
            // 1个y操作， 2个y操作， 3个y操作枚举出所有的代价取最小代价
            for (int i = 0; i < prefixSum.length-1; i++) {
                // 0..0执行y操作； 0..1执行y操作； 0..2执行y操作
                left =  mostLeft(arr, i+1, prefixSum[i]);
                cost = Math.min(cost, (y * (i + 1) + x * (left - i - 1)));
            }
            return cost;
        }

    }

    // arr[l...]从l位置到数组的末尾， 查找小于等于value的最左位置
    public static int mostLeft(int[] arr, int l, int value) {
        int r = arr.length;
        int ans = arr.length;
        while (l <= r) {
            int mid = l + ((r-l) >> 1);
            if (arr[mid] <= value) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }


}

