package com.chenjunyi.project;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 *  小朋友来自哪些小区问题
 *  和鹅厂文化衫是一个问题
 *  a/b 向上取整 --> (a+b-1)/b
 */
public class xiaopengyoulaizinaxiexiaoqu {

    public static int count(int[] arr) {
        Arrays.sort(arr);  // 从小到达排序
        int pre = arr[0];
        int count = 1;
        int ans = 0;
        for (int i =1; i < arr.length; i++) {
            if (arr[i] == pre) {
                count++;

            } else {
                ans += (count+pre+1-1)/(pre+1) * (pre +1);
                pre = arr[i];
                count = 1;
            }
        }
        return ans + (count+pre+1-1)/(pre+1) * (pre +1);
    }

}
