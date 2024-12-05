package com.chenjunyi.Code_PriorityQueue;

import java.util.Arrays;

/**
 * 堆排序
 * 构建一个小顶堆实现，升序排序
 */
public class HeapSort {

    public static void heapSort(int[] arr) {
        int n = arr.length;
        // 从第一个非叶子节点开始调整成小顶堆
        for (int i = (n-1)/2; i >=0; i--) {
            adjustHeap(arr, i, n);
        }

        // 交换堆顶和尾部元素
        for (int i =n-1; i >= 0; i--) {
            swap(arr, 0,i);
            adjustHeap(arr, 0, i);

        }

    }



    private static void adjustHeap(int[] arr, int i, int n) {
        int data = arr[i];
        for (int l = i*2+1; l < n; l=l*2+1) {
            if (l+1 < n && arr[l] > arr[l+1]) {
                l = l+1;
            }
            // l是子节点，i是父节点
            // 如果父节点大于孩子节点
            // 则
            if (data > arr[l]) {
                arr[i] = arr[l];
                i = l;
            } else {
                break;
            }
        }
        arr[i] = data;
    }


    public static void heapSort1(int[] arr) {
        for (int i =0; i < arr.length; i++) {
            heapInsert(i, arr);
        }

        int size = arr.length;
        swap(arr, 0, --size);
        while (size > 0) {
            adjustHeap(arr, 0, size);
            swap(arr, 0, --size);
        }

    }

    private static void heapInsert(int i, int[] arr) {
        // i是要插入节点的位置，需要和父节点比对
        while (arr[i] < arr[(i-1)/2]) {
            swap(arr,i, (i-1)/2);
            i = (i-1)/2;
        }
    }


    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }

    public static void main(String[] args) {
        int[] arr = {2,3,53,7,4,3,1,-90,87,65};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
        int[] arr1 = {2,3,53,7,4,3,1,-90,87,65};
        heapSort1(arr1);
        System.out.println(Arrays.toString(arr1));
    }

}
