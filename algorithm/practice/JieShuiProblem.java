package com.chenjunyi.practice;

/**
 *
 *  [3,4,8,7,2,6]
 *  
 *
 */
public class JieShuiProblem {
    public static void main(String[] args) {

    }

    public static int process(int[] arr) {
        int l = 0;
        int r = arr.length-1;
        int res = 0;
        while (l < r) {
            res = Math.max(Math.max(arr[l], arr[r]) * (r-l), res);
            if (arr[l] < arr[r]) {
                l++;
            } else {
                r--;
            }
        }
        return res;
    }
}
