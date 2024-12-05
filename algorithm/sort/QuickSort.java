package com.chenjunyi.sort;

import java.util.ArrayList;
import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {-78,56,56,34,2,890,87644,3465,54,45,32,35,-78,-89,56,423,2,34,2,34};
        QuickSort sort = new QuickSort();
//        sort.quick(arr, 0, arr.length-1);
//        System.out.println(Arrays.toString(arr));

        int size = 1000000;
        int[] array = new int[size];
        for (int i =0; i < size; i++) {
            array[i] = (int)(Math.random() * size);
        }
        long start = System.currentTimeMillis();
        sort.quick(array, 0, size-1);
        long end = System.currentTimeMillis();
       // System.out.println(Arrays.toString(array));
        System.out.println("took: " +(end-start)+" ms");


    }

    public void quick(int[] arr, int left, int right) {
        int pivot = arr[(left + right) / 2];
        int l = left;
        int r = right;
        int temp =0;

        while (l < r) {
            while (arr[l] < pivot) {
                l++;
            }
            while (arr[r] > pivot) {
                r--;
            }

            // 说明 pivot 左边的值都比pivot小； pivot右边的值都比pivot大
            if (l >= r) {   
                break;
            }
            // 否则 交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            if (arr[l] == pivot) {
             //   l++;
                r--;    // (???)    因为arr[l]处的值就是刚刚arr[r]的值， 故需要将r向前移动
            }

            if (arr[r] == pivot) {
             //   r--;
                l++;     //(???)
            }
        }
        // 如果l==r, 必须l++, r--
        if (l == r) {
            l++;
            r--;

        }

        // 向左递归
        if (left < r) {
            quick(arr, left, r);
        }


        // 向右递归
        if (right > l) {
            quick(arr, l, right);
        }


    }
}
