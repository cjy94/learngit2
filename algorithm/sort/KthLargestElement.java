package com.chenjunyi.sort;

import java.util.Random;

/**
 * 利用快排
 * 第n大的元素， 应该防止在len-n的位置上, 而快排的核心思想就是依次排序后，比pivot小的元素都放置在了左边，比pivot大的元素都放置在了右边
 * 可以利用pivot和(len-n)的位置信息，确定继续对那一部分的数据进行递归排序
 */
public class KthLargestElement {

    // 在一个无序数组中找到第k大的数
    public static int findKth(int[] arr, int k) {
        if (k > arr.length) return -1;
        return quick(arr, k);

    }

    private static int quick(int[] arr, int k) {
        System.out.println(arr.length - k);
        return process(arr, 0, arr.length-1, arr.length-k);
    }

    private static int process(int[] arr, int l, int r, int k) {
        if (l == r) return arr[l];
        // [l, r]范围上随机等概率取一个pivot
        swap(arr, l + (int)Math.random() * (r-l + 1), r);
        int m = partition(arr, l, r);
        if (m == k) {
            return arr[k];
        } else if(m < k) {
            return process(arr, m + 1, r, k);
        } else {
            return process(arr, l, m-1, k);
        }

    }

    private static int partition(int[] arr, int l, int r) {
        int pivot = arr[r];
        int less = l -1;
        while (l <= r) {
            if (arr[l] <= pivot) {
                swap(arr, ++less, l);
            }
            l++;

        }
        return less;
    }

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {3,1,4,5,-90,87,65,67,45,67,56};
       // System.out.println(quick(arr, 2));
        System.out.println(findKth(arr, 2));

       
    }
}
