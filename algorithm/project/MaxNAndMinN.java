package com.chenjunyi.project;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 最大n个数和最小n个数的和
 */
public class MaxNAndMinN {

    public static int sum(int[] arr, int n) {
        // 双指针对数组进行去重操作
        HashSet<Integer> set = new HashSet<>();
        for (int v : arr) {
            set.add(v);
        }
        if (set.size() < 2 * n) {
            return -1;
        } else {
            Arrays.sort(set.toArray());
            Integer[] uarr = set.toArray(new Integer[set.size()]);
            int size = uarr.length;
            return uarr[0] + uarr[1] + uarr[size-1] + uarr[size-2];
        }

    }
}
