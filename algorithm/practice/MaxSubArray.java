package com.chenjunyi.practice;

/**
 * 子数组的最大累加和问题
 * 子数组，子串： 以某个位置结尾的情况下的答案是多少
 * [-3，4，-5，2，-1，6，-7]
 * [-3, 4, -1, 2, 1, 7, 0]
 * 可能性：1) 不向前扩充； 2) 向左扩充
 *
 * [-2,3,-2,4,-2,-1,2]
 * [-2,3,1,5,3,2,4]
 */
public class MaxSubArray {

    public static int process(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int pre = arr[0]; //
        int max = pre;
        for (int i =1; i < arr.length; i++) {
            pre = Math.max(arr[i] + pre, arr[i]);
            max = Math.max(max, pre);
        }
        return max;
    }

}
