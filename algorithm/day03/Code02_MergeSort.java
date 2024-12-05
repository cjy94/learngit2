package com.chenjunyi.day03;

import java.util.Arrays;

public class Code02_MergeSort {
    public static void main(String[] args) {
        int[] arr = {1,3,4,2,5};
        int va = sort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
        System.out.println(va);


    }

    public static int sort(int[] arr, int l, int r) {
        if (l < r) {
            int mid = l + ((r-l) >> 1);
            return sort(arr, l, mid)
                    + sort(arr,mid+1, r)
                    + merge1(arr, l, mid, r);
        }
        return 0;
    }

    /**
     *  归并排序，求小和
     *  对一个数值，左边有多少个元素比他小，
     *  规则：左边元素小会产生小和，左边元素放入temp[]中，l++, 并且计算小和的数量 arr[l]*(r-r')
     */
    private static int merge1(int[] arr, int l, int mid, int r) {
        int left =l, right=mid+1, index = 0;
        int[] temp = new int[arr.length];
        int res = 0;
        while (left <= mid && right <= r) {
            if (arr[left] < arr[right]) {
                temp[index] = arr[left];
                res += arr[left] * (r-right+1);
                left++;
                index++;
            } else {
                temp[index] = arr[right];
                right++;
                index++;
            }
        }
        while (left<= mid) {
            temp[index++] = arr[left++];
        }
        while (right <= r) {
            temp[index++] = arr[right++];
        }

        index = 0;
        for (int x = l; x <= r; x++) {
            arr[x] = temp[index];
            index++;
        }
        return res;

    }


    /**
     *  归并排序，将左侧的有序数组，和右侧的有序数组进行比较大小的重新写入新的数组中
     */
    private static void merge(int[] arr, int l, int mid, int r) {
        int[] temp = new int[arr.length];
        int index = 0;
        int left = l, rigth= mid+1;
        while (left<=mid && rigth <= r) {
            if (arr[left] < arr[rigth]) {
                temp[index++] = arr[left++];
            } else {
                temp[index++] = arr[rigth++];
            }
        }
        while (left<= mid) {
            temp[index++] = arr[left++];
        }
        while (rigth <= r) {
            temp[index++] = arr[rigth++];
        }

        index = 0;
        for (int x = l; x <= r; x++) {
            arr[x] = temp[index];
            index++;
        }


    }


}
