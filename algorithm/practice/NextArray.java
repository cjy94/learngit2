package com.chenjunyi.practice;


import java.util.Arrays;

/**
 *  数组的下一个排列，即按照字典序排序
 *  比如： [1,2,3] --> [1,2,3] [1,3,2] [2,1,3] [2,3,1] [3,1,2] [3,2,1]
 *       123 -> 132
 *       132 -> 213
 *       213 -> 231
 *       231 -> 312
 *       312 -> 321
 *       321 -> 123
 *      也就是 将每一种排列结果按照从小到大进行排序，排在一个数字后面的就是他的下一个全排列
 *      123 132 213 231 213 321  可以将每一种全排列的结果放到数组中，将数组进行从小到大排序，但是题目要求的是原为排序，即空间复杂度是 O(1)
 */
public class NextArray {

    public static void main(String[] args) {
        int[] arr = {3,2,1};
        nextArray(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void nextArray(int[] arr) {
        int n = arr.length;
        int firstLess = -1;
        // 从后往前遍历arr数组，找到第一个降序的位置
        for (int i = n-2;i >= 0; i--) {
            if (arr[i] < arr[i+1]) {
                firstLess = i;
                break;
            }
        }
        // 在数组arr中找到刚刚大于arr[firstLess]的值
        if (firstLess < 0) {
            reverse(arr, 0, n-1);
        } else {
            // 在arr[firstLess...] 开始找刚刚大于arr[firstLess]的数值，
            int swapIndex = -1;
            for (int i = n-1; i > firstLess; i--) {
                if (arr[i] > arr[firstLess]) {
                    swapIndex = i;
                    break;
                }
            }
            swap(arr, firstLess, swapIndex);
            reverse(arr, firstLess+1, n-1);
        }
    }

    private static void reverse(int[] arr, int l, int r) {
        while (l < r) {
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    
}
