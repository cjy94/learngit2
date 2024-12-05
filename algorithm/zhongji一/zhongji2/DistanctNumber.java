package com.chenjunyi.zhongji一.zhongji2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

/**
 * 去重数字对
 */
public class DistanctNumber {

    public static List<List<Integer>> distanct(int[] arr, int k) {
        List<List<Integer>> res = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        for (int i =0; i < arr.length; i++) {
            set.add(arr[i]);
        }

        for (Integer cur : set) {
            if (set.contains(k+cur)) {
                res.add(Arrays.asList(cur, cur+k));
            }
        }
        return res;
    }

    public static void main(String[] args) {

    }

}
