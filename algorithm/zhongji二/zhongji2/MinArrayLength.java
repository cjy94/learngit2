package com.chenjunyi.zhongji二.zhongji2;

/**
 * 给定一个无序数组arr，如果只能对一个子数组进行排序，但是想让数组整体都有序，求需要排序的最短数组长度
 */
public class MinArrayLength {


    public static int minLength(int[] arr) {

        int maxIndex = 0;
        int minIndex = arr.length-1;
        int max = arr[maxIndex];
        int min = arr[minIndex];

        // 从左往右遍历，找右边界
        for (int i =1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if (arr[i] < max) {
                maxIndex = i;
            }
        }

        for (int j =minIndex-1; j >= 0; j--) {
            min = Math.min(min, arr[j]);
            if (arr[j] > min) {
                minIndex = j;
            }
        }

        return maxIndex-minIndex+1;

    }

    public static void main(String[] args) {
        int[] arr = {1,5,3,4,2,6,7};
        System.out.println(minLength(arr));
    }
}
