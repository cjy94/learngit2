package com.chenjunyi.sort;

import java.util.Arrays;
import java.util.Random;

public class BubbleSort {
    public static void main(String[] args) {
        BubbleSort sort = new BubbleSort();
//        int[] array = {90,-6,-7,-89,56,43,2132,42,21,46,19,77,5};
//        System.out.println(Arrays.toString(array));
//        sort.bubble(array);
//        System.out.println(Arrays.toString(array));

        Random random = new Random();
        int size = 100000;
        int[] array1 = new int[size];
        for (int i = 0; i < size; i++) {
            array1[i] = random.nextInt();
        }
        //System.out.println(Arrays.toString(array1));
        long start = System.currentTimeMillis();
        sort.bubble(array1);
        long end = System.currentTimeMillis();
        System.out.println("10w bubble sort time: " + (end-start) + " ms");
        //System.out.println(Arrays.toString(array1));



    }

    /**
     * 通过flag变量控制， 当前一轮没有进行交换时，说明数组基本有序，没有必要进行下一轮的循环，直接退出
     * @param arr
     */
    public void bubble(int[] arr) {
        boolean swap;
        for (int i =arr.length -1; i > 0; i--) {  // 外层循环控制 比较的次数
            swap = false;
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                    swap = true;
                }
            }
            if (swap == false)
                break;
        }
    }

    /**交换数组内两个元素*/
    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
