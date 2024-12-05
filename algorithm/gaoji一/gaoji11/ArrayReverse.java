package com.chenjunyi.gaoji一.gaoji11;

import java.util.Arrays;

/**
 * 完美洗牌问题
 *  给定一个长度为偶数的数组arr， 长度记为2*N。前N个为左部分，后N个为右部分。
 * arr就可以表示为{L1,L2...，Ln,R1,R2,...,Rn}，请将数组调整成{R1,L1,R2,L2,...,Rn,Ln}
 *
 *

 *
 *
 * 1、其中使用了一个算法原型，就是不使用额外的空间，实现数组中元素逆序 {abcd甲乙}  -> {甲乙abcd}
 * 1.1  先将abcd 逆序 dcba
 * 1.2  再将甲乙 逆序 乙甲
 * 1.3  整体逆序 甲乙abcd
 *
 *
 * 
 *
 */
public class ArrayReverse {


    // 一个数组，前n个元素和后(len-n)个元素实现逆序
    public static void rotate(Object[] arr, int left, int m, int right) {
        reverse(arr, left, m-1);
        reverse(arr, m, right);
        reverse(arr, left, right);

    }
    public static void reverse(Object[] arr, int l, int r) {
        while (l < r) {
            swap(arr, l++, r--);
        }
    }


    private static void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        String[] arr = {"a", "b", "c", "d", "甲", "乙"};
        System.out.println(Arrays.toString(arr));
        rotate(arr, 0, 4, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
}
