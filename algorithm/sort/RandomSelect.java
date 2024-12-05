package com.chenjunyi.sort;

import java.util.Random;

/*
 在不进行排序的情况下，在一个无序数组中找到第k小的数，如何实现
 利用随机选择算法
 */
public class RandomSelect {

    public static int randomSelect(int[] arr, int l, int r, int i) {
        if (l == r) {
            return arr[l];
        }

        int m = randomPartition(arr, l, r);
        int k = m - l + 1;              // 计算m的位置是在数组中第几个数
        if (i == k) {
            return arr[m];
        } else if (i < k) {  // 到左侧区域进行查找
            return randomSelect(arr, l, m - 1, i);
        } else {
            return randomSelect(arr, m + 1, r, i - k);
        }


    }

    private static int randomPartition(int[] arr, int l, int r) {
        // Math.random() 会随机产生[0,1)之间的小数
        // Math.random() *n 随机产生[0,n)之间的小数
        // ((int)Math.random()) * n 会随机产生[0,n)之间的整数
        int i = l + (int)Math.random() * (r-l+1);// 产生指定范围的随机数
        swap(arr, i, r);
        return partition(arr, l, r);
    }


    private static int partition(int[] arr, int l, int r) {
        int pivot = arr[r];
        int less = l - 1;
        while (l <= r) {
            if (arr[l] <= pivot) {
                swap(arr, ++less, l);
            }
            l++;
        }
        return less;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        //int[] arr = {2,5,3,0,2,3,0,3};   // 1 3 4 6 7 8 9
       // int[] arr = {6,4,3,1,9,8,7};
        int[] arr = {3,1,4,5,-90,87,65,67,45,67,56};
        System.out.println(randomSelect(arr, 0, arr.length - 1, 9));

    }
}
