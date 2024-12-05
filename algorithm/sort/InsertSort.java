package com.chenjunyi.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 时间复杂度：根据数据状况不同，时间复杂度不同
 * 最差情况是o(n^2), 最好情况是o(n)
 */

public class InsertSort {
    public static void main(String[] args) {
        InsertSort sort = new InsertSort();
        int[] array = {90,-6,-7,-89,56,43,2132,42,21,46,19,77,5};
        System.out.println(Arrays.toString(array));
        sort.insert(array);
        System.out.println(Arrays.toString(array));


        Random random = new Random();
        int size = 100000;
        int[] array1 = new int[size];
        for (int i = 0; i < size; i++) {
            array1[i] = random.nextInt();
        }
        long start = System.currentTimeMillis();
        sort.insert(array1);
        long end = System.currentTimeMillis();
       // System.out.println(Arrays.toString(array1));
        System.out.println("insert sort time: " + (end-start) + " ms");

    }

    public void insert(int[] arr) {
        for (int i =1; i < arr.length; i++) {
            int value = arr[i];   // 记录本次插入的值
            int position = i;     // 记录要插入的位置
            while (position > 0 && arr[position-1] > value) {
                arr[position] = arr[position-1];      // 将大值往后移
                position--;

            }
            arr[position] = value;    // 插入要插入的位置
        }
    }
}
