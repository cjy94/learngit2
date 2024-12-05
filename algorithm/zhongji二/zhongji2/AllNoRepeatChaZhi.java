package com.chenjunyi.zhongji二.zhongji2;

import java.util.*;

/**
 * 给定一个数组，求差值为k的去重数字对
 */
public class AllNoRepeatChaZhi {

    public static List<List<Integer>> noReapetChaZhi(int[] arr, int k) {
        Set<Integer> set = new HashSet<>();
        List<List<Integer>> list = new ArrayList<>();
        for (int n : arr) {
            set.add(n);
        }
        for (Integer cur : set) {
            if (set.contains(cur + k)) {
                list.add(Arrays.asList(cur, cur + k));
            }
        }
        return list;
    }


    public static void main(String[] args) {
        int[] arr = {3,5,2,7,0,0,4};
        System.out.println(noReapetChaZhi(arr, 2));
    }
}
