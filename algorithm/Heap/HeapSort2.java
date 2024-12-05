package com.chenjunyi.Heap;

import java.util.Arrays;

/**
 *  堆排序
 *  先将数组构建成一个大顶堆
 *  再将堆顶元素和最后一个元素交换，这样就将最大元素排在了数组的结尾位置，再在数组[0...n-2]的位置构建成一个大顶堆， 在将堆顶元素和n-2位置的元素交换
 *      ，将次大元素排在数组倒数第二的位置... 依次排好
 */
public class HeapSort2 {

    public static void main(String[] args) {
        int[] arr = {45,87,645,45,35,34,-978,745,35,33,-3444,-656,767,32,4,35,465};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr) {
        // 将数组构建成一个大顶堆
        int n = arr.length;
        for (int i =0; i < n; i++) {
            heapInsert(arr, i);
        }

        while (n > 0)  {
            swap(arr, 0, n-1);
            n--;
            heapify(arr, 0, n);
        }
    }

    private static void heapify(int[] arr, int index, int limit) {
        int left = 2 * index + 1;
        while (left < limit) {
            int largest = left + 1 < limit && arr[left] < arr[left+1] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index)
                break;
            swap(arr, largest, index);
            index = largest;
            left = 2 * index + 1;
        }
    }

    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) { // 和父节点的数值比较，比父亲大就交换
            swap(arr, index, (index - 1)/2);
            index = (index - 1) / 2;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }
}
