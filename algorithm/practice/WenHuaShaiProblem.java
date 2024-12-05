package com.chenjunyi.practice;

import java.util.Arrays;

/**
 * 鹅厂文化衫问题
 *  a/b 向上取整    ---->   (a+b-1)/b
 */
public class WenHuaShaiProblem {

    public static int minPeople(int[] arr) {
        Arrays.sort(arr);
        int count = 1;
        int value = arr[0];
        int ans = 0;
        for (int i =1; i < arr.length; i++) {
            if (arr[i] != value) {
                ans += ((count + value) / (value + 1)) * (value + 1);
                value = arr[i];
                count = 1;
            } else {
                count++;
            }
        }
        return ans + ((count + value) / (value + 1)) * (value + 1);
    }

    
}
