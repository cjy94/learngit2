package com.chenjunyi.day02;

/**
 * 求数组中的最大值，使用递归
 */
public class Code08_GetMax {
    public static void main(String[] args) {
        int[] array = {90,56,77,43,12,34,56,354,67,4576,89,89,90,87,-90,2343444};
        int m = max(array, 0, array.length-1);
        System.out.println(m);


    }

    public static int max(int[] arr, int l, int r) {
        if (l == r) return arr[r];
        int mid = ((r-l) >> 1) +l;
        int left = max(arr, l, mid);
        int right = max(arr, mid+1, r);
        return Math.max(left, right);
    }


    public static int oneMinIndex(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int n = arr.length;
        // 只有一个数时
        if (n == 1) {
            return 0;
        }

        if(n == 2) {
            return arr[0] < arr[1] ? 0 : 1;
        }

        if (arr[n-1] < arr[n-2]) {
            return n-1;
        }

        // 不止2个数
        int l =0;
        int r = n-1;
        while (l < r-1) {
            int mid = l + ((r-l)>>1);
            if (arr[mid] < arr[mid-1] && arr[mid] < arr[mid+1]) {
                return mid;
            }
            if (arr[mid] > arr[mid-1]) {
                r = mid-1;
            }

            if (arr[mid] > arr[mid+1]) {
                l = mid+1;
            }
        }
        return arr[l] < arr[r] ? l : r;
    }
}
