package com.chenjunyi.practice;

/**
 * 给定一个长度len，表示一共有几位
 * 所有字符都是小写(a~z)， 可以生成长度为1、长度为2、长度为3... 长度为len的所有字符
 * 如果把所有字符串根据字典序排序， 每个字符串都有所在额位置
 * 给定一个字符串str，给定一个len， 请返回str是总排序中的第几个
 * 比如：str=cdb, len=7
 * 第一位c:
 * 以a开头，剩下长度(0~6)的所有可能性
 * +
 * 以b开头，剩下长度(0~6)的所有可能性
 * +
 * c 自己1个
 *
 * 第二位d:
 * 以ca开头，剩下长度(0~5)的所有可能性
 * +
 * 以cb开头，剩下长度为(0~5)的所有可能性
 * +
 * 以cc开头， 剩下长度为(0~5)的所有可能性
 * +
 * cd 自己1个
 *
 * 第三位b:
 * 以cda开头，剩下长度为(0~4)的所有可能性
 * +
 * cdb自己
 *
 *
 *
 */
public class StringKth {

    public static int kth(String str, int len) {
        if (str == null || str.length() == 0 || str.length() > len) {
            return -1;
        }
        char[] s = str.toCharArray();
        int ans = 0;
        for (int i =0, rest = len-1; i < s.length; i++, rest--) {
            ans += (s[i] - 'a') * f(rest) + 1;
        }
        return ans;
    }

    // 给定一个长度，返回0~n长度的字符串的所有可能结果
    public static int NLengthCounts(int n) {
        int base = 26;
        int ans = 0;
        for (int i =0; i <n; i++) {
            ans += Math.pow(base, i);
        }
        return ans;
    }

    public static int f(int n) {
        int ans = 1;
        for (int i =1, base=26; i < n; i++, base *= 26) {
            ans += base;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(NLengthCounts(6));
        System.out.println(f(6));
    }
}
