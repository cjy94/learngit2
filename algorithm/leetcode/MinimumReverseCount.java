package com.chenjunyi.leetcode;

import java.util.Arrays;

/**
 * 烙饼问题
 *
 *  简化问题就是： 将一组数据进行排序， 但不是普通的数值交换，而是要将[left,right]之间的数值全部翻转，问要将一组数组排好序，最少的翻转次数
 */
public class MinimumReverseCount {

    public static void main(String[] args) {
//        int[] arr = {1,3,2,7,4,6,5};               // [7465231]  ->  [7652314]  -> [7654132] ->  [7654321]    4*2 = 8次
//        int count = getMinReverseCount(arr);
//        System.out.println("最少的翻转次数: " + count);

        int num1 = 0xFF;
        System.out.println(num1);

        System.out.println((byte)num1);
    }

    // 贪心解法： 每次在没有处理的数值中找到最大值，将其进行2次翻转
    public static int getMinReverseCount(int[] arr) {
        int length = arr.length;
        int counter = 0;

        for (int i = 0; i < length; i++) {   // 依次找到数组中的最大值，次大值...
            int maxIndex = i;
            for (int j = i+1; j < length; j++) {
                maxIndex = arr[j] > arr[maxIndex] ? j : maxIndex;   // maxIndex保存的是数组arr[i...length]之间的最大值的索引信息
            }

            if (maxIndex != i) {
                reverse(arr, maxIndex);
                reverse(arr, i);
                counter += 2;
            }
        }
        return counter;
    }

    // 将数组arr, 从left位置开始，一直到数组的尾部，全部数据进行翻转操作
    // 例如： [1,3,2,7,4,6,5]  将[7,4,6,5]这组数据进行翻转，结果是： [5,6,4,7]
    private static void reverse(int[] arr, int left) {
        int right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }


    }

}
