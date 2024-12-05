package com.chenjunyi.DynamicProgramming.习题1;

/**
 *  不同子序列
 *  给定一个字符串s，计算s的不同非空子序列的个数
 *
 * abc: a ab abc b bc c
 * 字符串种没有重复字符的时候：子序列个数是2^n
 * 但是如果字符串中存在重复字符，就需要另外讨论
 *
 * 补充：同余原理
 */
public class DifferentSubsequences {

    // 计算字符串s的不同非空子序列个数，s中可能包含重复字符
    public static int distinctSubseqII(String s) {
        char[] str = s.toCharArray();
        int all = 1, newAdd = 0;
        int[] chars = new int[26];   // 存储每个字符串上次结尾情况的不同非空子序列个数
        for (char ch : str) {
            newAdd = all - chars[ch-'a'];
            all += newAdd;
            chars[ch-'a'] += newAdd;
        }
        return all -1;
    }

    public static void main(String[] args) {
        System.out.println(distinctSubseqII("aba"));
    }

}
