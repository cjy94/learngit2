package com.chenjunyi.gaoji二.gaoji1;

import java.util.Arrays;

/**
 * 小朋友分糖果问题
 */
public class CandiesProblem {

    // 原始问题
    // left < cur  糖果+1； left >= cur 糖果变成1  (当前值大于左侧/右侧的值，糖果+1, 否则糖果变成1)
    public static void candies1(int[] arr) {
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];
        left[0] = 1;
        right[arr.length-1] = 1;


        for (int i =1; i < arr.length; i++) {
            if (arr[i] > arr[i-1]) {
                left[i] = left[i-1]+1;
            }  else {
                left[i] = 1;
            }
        }

        for (int i =arr.length-2; i >= 0; i--) {
            if (arr[i] > arr[i+1]) {
                right[i] = right[i+1]+1;
            } else {
                right[i] = 1;
            }
        }

        for (int i =0; i < arr.length; i++) {
           arr[i] = Math.max(left[i], right[i]);
        }

    }

    // 进阶问题
    //  left < cur  糖果+1； left > cur 糖果变成1； left = cur  糖果维持不变
    public static void candies2(int[] arr) {
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];
        left[0] = 1;
        right[arr.length-1] = 1;


        for (int i =1; i < arr.length; i++) {
            if (arr[i] > arr[i-1]) {
                left[i] = left[i-1]+1;
            }  else if(arr[i] == arr[i-1]) {
                left[i] = left[i-1];
            } else {
                left[i] = 1;
            }
        }

        for (int i =arr.length-2; i >= 0; i--) {
            if (arr[i] > arr[i+1]) {
                right[i] = right[i+1]+1;
            } else if(arr[i] == arr[i+1]){
                right[i] = right[i+1];
            } else {
                right[i] = 1;
            }
        }

        for (int i =0; i < arr.length; i++) {
            arr[i] = Math.max(left[i], right[i]);
        }

    }

    public static void main(String[] args) {
        int[] arr = {4,3,3,2,2,4,5,5,4,3,3,2,1,4,4};
        candies1(arr);
        System.out.println(Arrays.toString(arr));

        int[] arr2 = {4,3,3,2,2,4,5,5,4,3,3,2,1,4,4};
        candies2(arr2);
        System.out.println(Arrays.toString(arr2));
    }
}
