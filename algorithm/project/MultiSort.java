package com.chenjunyi.project;

import java.util.Arrays;

/**
 *
 *  冒泡排序： 一次排序选择出最大值放在数组尾部   稳定排序
 *  选择排序： 一次排序选择出最小值放在数组前面    稳定排序
 *  插入排序：  稳定排序
 *  快速排序： 选定一个k, 一次排序将小于等于k的数值全部放在看前面，将大于k的数值全部放在k的后面， 不稳定排序
 *  归并排序： 稳定    n * (logn)
 *  堆排序： 不稳定     n * (logn)
 *  基数排序：
 *  桶排序：
 *
 */
public class MultiSort {
    public static void main(String[] args) {
        int[] arr = {-90, -78, -56, -278, 3,2,324,12,43,54,1,43,4, 90, 56,-678,-45,32,2,89,6,5};
        heap(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     *
     *  荷兰国旗问题
     *  小于num区域 l-1， 大于num区域 r+1
     *  index = l
     *   1) arr[index] == num, index++
     *   2) arr[index] < num, arr[index]和小于区域的右一个交换，index++
     *   3) arr[index] > num, arr[index]和大于区域的左一个交换，index保持不变
     *
     *
     *   partition 过程， 返回less和more索引位置，比num小的索引信息， 比num大的索引信息
     *
     */
    public static void quick(int[] arr) {
        // partition 过程

    }

    public static void merge(int[] arr) {
        int l = 0;
        int r = arr.length -1;
        mergeSort(arr, l, r);

    }

    public static void mergeSort(int[] arr, int l, int r) {
       if (l < r) {
           int mid = l + ((r-l) >>> 1);
           mergeSort(arr, l, mid);
           mergeSort(arr, mid+1, r);
           merge1(arr, l, mid ,r);
       }
    }

    public static void merge1(int[] arr, int l, int mid, int r) {
        int[] temp = new int[r-l+1];
        int i = l;
        int j = mid+1;
        int index = 0;
        while (i <= mid && j <= r) {
            if (arr[i] < arr[j]) {
                temp[index++] = arr[i++];
            } else {
                temp[index++] = arr[j++];
            }
        }
        while (i <= mid) {
            temp[index++] = arr[i++];
        }
        while (j <= r) {
            temp[index++] = arr[j++];
        }

        // 将temp数组中的元素拷贝到arr数组中
        index = 0;
        for (int x =l; x <= r; x++) {
            arr[x] = temp[index++];
        }
    }

    public static void heap(int[] arr) {
        for (int i =0; i < arr.length; i++) {
            heapInsert(arr, i);
        }

        int n = arr.length;
        while (n > 0) {
            swap(arr, 0, n-1);
            n--;
            heapify(arr, 0, n);
        }

    }

    private static void heapInsert(int[] arr, int i) {
        while (arr[(i-1) / 2] < arr[i]) {
            swap(arr, (i-1)/2, i);
            i = (i-1) / 2;
        }
    }

    private static void heapify(int[] arr, int index, int limit) {
        int left = 2 * index + 1;
        while (left < limit) {
            int largest = left + 1 < limit && arr[left] < arr[left+1] ? left+1 : left;
            largest = arr[index] < arr[largest] ? largest : index;
            if (largest == index)
                break;
            swap(arr, index, largest);
            index = largest;
            left = 2 * index + 1;
        }
    }



    public static void bubble(int[] arr) {
        for (int i = arr.length -1; i >= 0; i--) {
            for (int j = 0; j < i-1; j++) {
                if (arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                }
            }
        }
    }

    public static void select(int[] arr) {
        for (int i =0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }

    public static void insert(int[] arr) {
        for (int i =1; i < arr.length; i++) {
            int temp = arr[i];
            int index = i;
            while (index-1 >= 0 && arr[index-1] > temp) {
                arr[index] = arr[index-1];
                index--;
            }
            if(index != i)
                arr[index] = temp;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
