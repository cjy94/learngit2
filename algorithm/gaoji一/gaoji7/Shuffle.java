package com.chenjunyi.gaoji一.gaoji7;

/**
 * 完美洗牌问题
 * 
 */
public class Shuffle {

    // 将数组划定区域的左右两侧逆序
    public static void rotate(int[] arr, int l, int m , int r) {
        reverse(arr, l, m-1);
        reverse(arr, m, r);
        reverse(arr, l, r);

    }

    private static void reverse(int[] arr, int l, int r) {
        while (l < r) {
            swap(arr, l++, r--);
        }                    
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
