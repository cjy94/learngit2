package com.chenjunyi.practice;

/**
 * 一维接雨水问题
 * 二维接雨水问题
 */
public class JieYuShuiProblem {

    public static int oneWater(int[] arr) {
        int n = arr.length;
        int l = 1;
        int r = n-2;
        int leftMax = arr[0];
        int rightMax = arr[n-1];
        int water = 0;
        while (l <= r) {
            if (leftMax <= rightMax) {
                water += Math.max((leftMax-arr[l]), 0);
                leftMax = Math.max(leftMax, arr[l]);
                l++;
            } else {
                water += Math.max((rightMax-arr[r]), 0);
                rightMax = Math.max(rightMax, arr[r]);
                r--;
            }
        }
        return water;
    }

}
