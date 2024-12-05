package com.chenjunyi.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 先将待排序的序列构建成一个大顶堆， 最大值放在堆顶，并将其与末尾元素进行交换
 */
public class HeapSort {
    public static void main(String[] args) {
        Random r = new Random();
        int[] arr1 = new int[200];
        for (int i =0; i < 200; i++) {
            arr1[i] = r.nextInt();
        }
        int[] arr2 = arr1;

        HeapSort test = new HeapSort();
        test.sort(arr1);
        Arrays.sort(arr2);
        System.out.println(Arrays.equals(arr1, arr2));
    }

    /**
     * 数组中，父节点和左右孩子节点的关系是：i 父节点， (2i+1)左孩子节点 (2i+2)右孩子节点
     * 从最后一个非叶子节点调整
     * @param arr
     */

    public void sort(int[] arr) {
        // 1、构建初始的大顶堆

        for (int i = arr.length/2 -1; i>=0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        // 2、交换堆顶元素与末尾元素，并调整堆位大顶堆
        for (int i= arr.length-1; i > 0; i--) {
            //2.1、交换堆顶元素与末尾元素
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            // 2.2 调整堆为大顶堆
            adjustHeap(arr, 0, i);


        }




         
    }

    /**
     * 将数组构建成大顶堆
     * @param arr  待调整数组
     * @param i    非叶子节点在数组中的索引
     * @param len  对多少个元素进行调整
     */
    private void adjustHeap(int[] arr, int i, int len) {
        int temp = arr[i];
        // i 是要进行调整节点的索引位置
        for (int k = 2*i+1; k < len; k =2*k+1) {
            // 如果有右孩子，并且右孩子的值比左孩子大，那么就将k赋值给右孩子
            if (k+1 < len && arr[k] < arr[k+1]) {
                k++;
            }

            // 如果孩子节点比父节点的值大，那么就交换父节点和孩子节点
            if (temp < arr[k]) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }
}
