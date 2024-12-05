package com.chenjunyi.Median;

import java.util.Arrays;

/**
 * 算法导论： 同时找出最大值和最小值的方法
 * 题目： 给定一个大小为 n的数组，无序，找到其中的最大值和最小值，要求元素之间的比较尽可能少
 *
 * 1、如果将arr[0]定义为min和max的初始值，那么从arr[1..n]的每一个元素，都要和min和max进行一次比较，最终确定处min和max的值， 这样的比较比较次数是；2n
 * 2、事实上，只需要至多3(n/2)次比较就可以同时找到最小值和最大值， 比2n的常数时间要小
 */
public class MaxAndMin {
    // 方式1  2n的比较次数
    public static int[] findMinMax1(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        for (int i =1; i< arr.length; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }

        return new int[]{min, max};
    }

    // 方式2， 最多3(n/2)次比较次数
    public static int[] findMinMax2(int[] arr) {
        // 选择出最大值和最小值的初值
        int n = arr.length;
        int min;
        int max;
        int first;
        if (n%2 == 0) {  // 如果数组长度是偶数
            if (arr[0] < arr[1]) {
                min = arr[0];
                max = arr[1];
            } else {
                min = arr[1];
                max = arr[0];
            }
            first = 2;
        } else {          // 数组长度是奇数
            min = arr[0];
            max = arr[0];
            first = 1;
        }

        for (; first < n-1; first += 2) {
            // 余下的元素， 两两互相比较，较小者在和min比较，较大者在和max比较
            if (arr[first] < arr[first+1]) {
                if(arr[first]<min)
                    min = arr[first];
                if(arr[first+1]>max)
                    max = arr[first+1];

            } else {
                if(arr[first]>max)
                    max = arr[first];
                if(arr[first+1]<min)
                    min = arr[first+1];

            }
        }
        return new int[] {min, max};
    }

    private static int[] produceData(int size, int maxValue) {
        int[] array = new int[size];
        for (int i =0; i < size; i++) {
            // Math.random() 是[0,1)范围内的小数
            //  Math.random() * n [0,n) 范围内的小数
            // （int）Math.random() * n [0,n) 范围内的正数
            array[i] = (int)(Math.random() * maxValue);
        }
        return array;
    }

    public static void main(String[] args) {
//        int[] arr = {-1, -10, 3,4,23,30, 0,23,23,45,98,98};
//        System.out.println(Arrays.toString(findMinMax2(arr)));
        for (int i =0; i < 10; i++) {
            int[] arr = produceData(1000000, 5000);
            int[] arr1 = new int[arr.length];
            System.arraycopy(arr, 0, arr1, 0, arr.length);
            long start = System.currentTimeMillis();
            int[] a1 = findMinMax1(arr);
            long end = System.currentTimeMillis();
            System.out.println("find1 took: " + (end-start) + " ms");
            start = System.currentTimeMillis();
            int[] a2 = findMinMax2(arr1);
            end = System.currentTimeMillis();
            System.out.println("find2 took: " + (end-start) + " ms");

            if (!Arrays.equals(a1, a2)) {
                System.out.println("Oops!!!");
            }
        }
    }




}
