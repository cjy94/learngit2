package com.chenjunyi.practice;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 一个无序数组长度为n，所有数字都一样，并且值在[0...n-1] 范围上
 * 返回让这个无序数组变有序的最小交换次数
 *
 * {2,0,5,3,1,4}  -> 数组变有序的最小交换次数
 *
 * 数字和索引下标的关系是： i = arr[i]
 *
 */
public class MinSwapTimes {

    // 数据离散化 {45,3,98} -> {1,0,2}
    public static void change(int[] arr) {
        int[] copy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(copy);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i =0; i < copy.length; i++) {
            map.put(copy[i], i);
        }

        // 修改arr
        for (int i =0; i < arr.length; i++) {
            arr[i] = map.get(arr[i]);
        }

    }

    public static int minSwapTimes(int[] arr) {
        int count = 0;
        for (int i =0; i < arr.length; i++) {
            while (arr[i] != i) {
                swap(arr, i, arr[i]);
                count++;
            }
        }
        return count;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 暴力求最小交换次数
    // 长度为N的数组，在n-1次交换后， 数组一定有序
    public static int minSwapTimes2(int[] arr, int times) {
        boolean sorted = true;
        for (int i =0; i < arr.length-1; i++) {
            if (arr[i] > arr[i+1]) {
                sorted = false;
                break;
            }
        }
        if (sorted) {
            return times;
        }
        if (times >= arr.length-1) {
            return Integer.MAX_VALUE;
        }

        int ans = Integer.MAX_VALUE;
        for (int i =0; i < arr.length; i++) {
            for (int j =i+1; j < arr.length; j++) {
                swap(arr, i, j);
                ans = Math.min(ans, minSwapTimes2(arr, times+1));
                swap(arr, j, i);
            }
        }
        return ans;
    }
}
