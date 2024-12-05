package com.chenjunyi.sort;

import javax.management.StandardEmitterMBean;
import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8,9,1,7,2,3,5,4,6,0};
        ShellSort sort = new ShellSort();
       // sort.shell(arr);
        sort.shell2(arr);
//        System.out.println(Arrays.toString(arr));

        int size = 1000000;
        int[] array = new int[size];
        for (int i =0; i < size; i++) {
            array[i] = (int)(Math.random() * size);
        }
        long start = System.currentTimeMillis();
        sort.shell2(array);
        long end = System.currentTimeMillis();
        // System.out.println(Arrays.toString(array));
        System.out.println("took: " + (end-start) + " ms");


    }

    // 对插入排序的一种改进  (交换法)
    public void shell(int[] arr) {
        // 第一轮循环
        for (int gap = arr.length/2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        int temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;

                    }
                }
            }
        }
        
    }


    // 移动法
    public void shell2(int[] arr) {
        int len = arr.length;
        for (int gap  = len / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < len; i++) {
                int value = arr[i];   // 记录本次插入的值
                int position = i - gap;     // 记录要插入的位置
                while (position >= 0 && arr[position] > value) {
                    arr[position + gap] = arr[position];      // 将大值往后移
                    position -= gap;

                }
                arr[position + gap] = value;    // 插入要插入的位置
            }
        }
    }
}
