package com.chenjunyi.project;

/**
 *  寻找山峰1
 *  就是查找一个数组中，左边和右边位置都比当前位置小的元素个数
 */
public class FoundMountain {

    public static int mountain(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            // 数组中第一个位置和最后一个位置的元素需要特殊处理
            if (i == 0) {
                if (arr[i] > arr[i + 1]) {
                    res++;
                }
            } else if (i == arr.length-1) {
                if (arr[i-1] < arr[i]) {
                    res++;
                }
            } else {
                if (arr[i-1] < arr[i] && arr[i+1] < arr[i]) {
                    res++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {0,1,4,3,1,0,0,1,2,3,1,2,1,0};
        System.out.println(mountain(arr));

    }
}
