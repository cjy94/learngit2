package com.chenjunyi.Heap;

import java.util.Arrays;

/**
 *  堆排序
 */
public class HeapSort1 {

    public static void heapSort(int[] arr) {
        // 先将数组弄成大顶堆
        //再每次将0和heapSize的数值交换，每次将最大值放到数组后面，再调整成大顶堆，再将0和--heapSize位置的数组交换，循环往复，直到heapSize==0

        int heapSize = arr.length;
//        for (int i = 0; i < heapSize; i++) {
//            heapInsert(arr, i);
//        }   // arr是一个大顶堆   时间复杂度o(n)

        for (int i = heapSize-1; i >= 0; i--) {
            heapify(arr, 0, i);
        }

        while (heapSize > 0) {
            swap(arr, 0, heapSize-1);
            heapSize--;
            heapify(arr, 0, heapSize);
        }
    }

    private static void heapify(int[] arr, int index, int limit) {
        int left = index * 2 + 1;
        while (left < limit) {
            int largest =  left + 1 < limit && arr[left] < arr[left+1] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index)
                break;
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    // 大顶堆，没插入一个元素向上调整，即和父亲节点比较数值，如果比父亲节点大就交换，否则不变
    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index-1) / 2);
            index = (index - 1) / 2;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {4,-1,9,34,22,567,867,-90,86,13,4345};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));

    }

}
