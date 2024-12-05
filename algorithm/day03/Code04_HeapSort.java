package com.chenjunyi.day03;

import java.util.Arrays;
import java.util.Random;

/**
 * 额外的空间复杂度： O(1), 时间复杂度：O(nlogN)
 * priorityQueue, 系统中的队列，内部使用的小根堆
 */
public class Code04_HeapSort {

    public static void main(String[] args) {

//        int[] arr = {9,12,34,90,-34,2,1,89};
//        heapSort(arr);
//        System.out.println(Arrays.toString(arr));
        if (cmp()) {
            System.out.println("nice!");
        } else {
            System.out.println("Oops!");
        }
    }

    public static boolean cmp() {
        int size = 100000;
        int[] arr1 = new int[size];
        int[] arr2 = new int[size];
        Random r = new Random();
        for(int i =0; i<size; i++) {
            int v = r.nextInt();
            arr1[i] = v;
            arr2[i] = v;
        }
        long start = System.currentTimeMillis();
        heapSort(arr1);
        long end = System.currentTimeMillis();
        System.out.println((end-start)+" ms");
        start = System.currentTimeMillis();
        Arrays.sort(arr2);
        end = System.currentTimeMillis();
        System.out.println((end-start) + " ms");
        return Arrays.equals(arr1,arr2);
    }

    public static void heapSort(int[] arr) {
        // 将无序数组， 调整为一个大根堆
        for (int i =0; i < arr.length; i++) {
            heapInsert(arr, i);
        }

//        for (int i =arr.length-1; i>=0; i--) {
//            heapify(arr, i, arr.length);
//        }

        int heapSize = arr.length;
        // 将大元素，放到数组的尾部
        swap(arr, 0, --heapSize);
        while (heapSize>0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }

        
    }

    private static void heapify(int[] arr, int i, int heapSize) {
        int temp = arr[i];
        for (int k = 2*i+1; k < heapSize; k = 2*k+1) {
            if (k+1 < heapSize && arr[k] < arr[k+1]) {
                k++;
            }
            if (temp < arr[k]) {
                arr[i] = arr[k];
                i = k;

            } else {
                break;
            }
        }
        arr[i] = temp;
    }

    private static void heapInsert(int[] arr, int i) {
        while (arr[i] > arr[(i-1)/2]) {
            swap(arr, i, (i-1)/2);
            i = (i-1)/2;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
