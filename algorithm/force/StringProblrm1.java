package com.chenjunyi.force;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

/**
 * 字符串相关问题
 * 1、打印一个字符出的全部子序列
 *  "abc" -> 'a' 'b' 'c' 'ac' 'bc' 'abc'
 */
public class StringProblrm1 {

    public static ArrayList<String> printAllSequence(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        String sub = "";
        ArrayList<String> ans = new ArrayList<String>();
        process(str.toCharArray(), 0, sub, ans);
        return ans;
    }

    private static void process(char[] str, int index, String sub, ArrayList<String> list) {
        if (index == str.length) {
            list.add(sub);
            return;
        }
        String no = sub;
        process(str, index+1, no, list);
        String yes = sub+str[index];
        process(str, index+1, yes, list);
    }

    public static Set<String> printAllSequenceNoRepeat(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        String sub = "";
        HashSet<String> set = new HashSet<>();
        processNoRepeat(str.toCharArray(), 0, sub, set);
        return set;
    }

    private static void processNoRepeat(char[] str, int index, String sub, HashSet<String> set) {
        if (index == str.length ) {
            set.add(sub);
            return;
        }
        String no = sub;
        processNoRepeat(str, index+1, no, set);
        String yes = sub+str[index];
        processNoRepeat(str, index+1, yes, set);
    }

    /**
     *
     *  问题： 给定一个字符串， 求s种有多少字面量不同的子序列
     * "aba"
     * count数组中记录以每个字符结尾情况下的子序列个数
     *
     * 1、第一个字符a, 子序列有： {""} {"a"}                    count[a]=1, all =2
     * 2、第二个字符b, 子序列有： {""} {"a"} {"b"} {"ab"}       count[b]=2, all =4
     * 3、第三个字符a, 子序列有： {""} {"a"} {"b"} {"ab"}    {"a"}{"aa"}{"ba"}{"aba"}   count[a]=all-count['a']=4-1=3, all =4+3
     *  其中{"a"} 和 之前的{"a"}是重复的，
     *
     *
     */

    public static int distinctSubseq4(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int[] count = new int[26];
        int all = 0;     // 初始值为1， 代表""也是一种答案
        for (Character x : str) {
            // 不考虑重复字符的情况
//            int add = all + 1;
//            all += add;
            // 考虑有重复字符的出现 count[]记录以每一个字符X结尾的子序列个数
            int add = all + 1 - count[x-'a'];
            all += add;
            count[x-'a'] += add;
        }
        return all + 1; // 1 指一个空字符串""；

        // “aaaa”
        // a: add: 1 all : 1  count[0]=1;
        // aa: add: 1  all:2  count[0]=2;
    }

    // 字符串的全排列
    public static void permutation(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        String sub = "";
        processPermutation(str.toCharArray(), 0);
    }

    private static void processPermutation(char[] str, int i) {
        if (i==str.length) {
            System.out.println(Arrays.toString(str));
            return;
        }
        for (int j = i; j < str.length; j++) {
            swap(str, i, j);
            processPermutation(str, i+1);
            swap(str, i, j);   // 恢复现场

        }
    }

    private static void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

    public static void main(String[] args) {
        String s = "111";
        ArrayList<String> strings1 = printAllSequence("aaaa");  // 一个字符串全部子序列的个数是2^len
        System.out.println(strings1.size());
        System.out.println(strings1);
        System.out.println("================");
        Set<String> strings = printAllSequenceNoRepeat("aaaa");
        System.out.println(strings.size());
        System.out.println(distinctSubseq4("aaaa"));
    }

}
