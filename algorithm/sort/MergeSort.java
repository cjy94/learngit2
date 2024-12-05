package com.chenjunyi.sort;

import java.util.Arrays;

/**
 * 归并排序： 分-治
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];
        MergeSort sort = new MergeSort();
        System.out.println(Arrays.toString(arr));

        sort.sort(arr, 0, arr.length-1, temp);
        System.out.println(Arrays.toString(arr));

    }

    public void sort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) /2;
            //
            sort(arr, left, mid, temp);
            sort(arr, mid + 1, right, temp);
            merge(arr, left, mid , right, temp);
        }
    }

    // 最后合并阶段， 类似与华东窗口，两个指针指向left和mid,将小的值放入到temp数组中
    public void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int t = 0;
        // 将小值放入到temp数组中
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        // 将剩余元素拷贝到temp数组中
        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }

        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }

        // (三) 将temp数组中的元素开呗到arr数组中
        int tempLeft = left;
        t = 0;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;

        }



    }
}
