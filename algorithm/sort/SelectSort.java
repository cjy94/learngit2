package com.chenjunyi.sort;

import java.util.Arrays;
import java.util.Random;

public class SelectSort {
    public static void main(String[] args) {
        SelectSort sort = new SelectSort();
        BubbleSort sort1 = new BubbleSort();
        int[] array = {90,-6,-7,-89,56,43,2132,42,21,46,19,77,5};
        System.out.println(Arrays.toString(array));
        sort.select(array);
        System.out.println(Arrays.toString(array));

        Random random = new Random();
        int size = 100000;
        int[] array1 = new int[size];
       // int[] array2 = new int[size];
        for (int i = 0; i < size; i++) {
            int value = random.nextInt();
            array1[i] = value;
          //  array2[i] = value;
        }

       // System.out.println(Arrays.toString(array1));
        long start = System.currentTimeMillis();
        sort.select(array1);
       // sort1.bubble(array2);
        long end = System.currentTimeMillis();
        System.out.println("select time: " + (end-start) + " ms");
        //System.out.println(Arrays.equals(array1, array2));


        //System.out.println(Arrays.toString(array1));

    }

    public void select(int[] arr) {
        for (int i =0; i < arr.length; i++) {
            int minIndex = i;
            for (int j =i+1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j])
                    minIndex = j;         // 不立即交换位置，而是一趟比较之后，确定好最小值的位置，最后交换
            }
            if(minIndex != i)
                swap(arr, i, minIndex);

        }

    }

    /**交换数组内两个元素*/
    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
