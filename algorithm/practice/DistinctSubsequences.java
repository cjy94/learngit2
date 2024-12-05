package com.chenjunyi.practice;

import java.util.HashMap;

/**
 * 给定一个字符串str，返回str的所有子序列中有多少不同的字面值
 */
public class DistinctSubsequences {


    public static int distinct(String str) {
        char[] s = str.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        int all = 1;
        for (char v : s) {
            int newAdd = all;
            int curAll = newAdd + all - (map.containsKey(v) ? map.get(v) : 0);
            all = curAll;
            map.put(v, newAdd);
        }
        return all;
    }

}
