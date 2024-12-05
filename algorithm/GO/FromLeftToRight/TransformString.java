package com.chenjunyi.GO.FromLeftToRight;

import java.util.Arrays;

/**
 *
 *  从左往右的尝试模型：
 *
 *
 *  字符串转化
 *  规定1和A对应，2和B对应，3和C对应
 *  给定一个只有数字的字符组成的字符串str，返回有多少种转化结果
 *
 */

public class TransformString {
    public static void main(String[] args) {
        String s = "11106";
        System.out.println(transform(s));
        System.out.println(dpWays(s));
    }

    public static int transform(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int len = s.length();
        int[] dp = new int[len];
        Arrays.fill(dp, -1);
        return process1(s.toCharArray(), 0, dp);
    }

    private static int process(char[] str, int index) {
        // 如果来到了字符串的末尾， 那么就找到了一种转化方法
        if (index == str.length) {
            return 1;
        }
        int ans = 0;
        if (str[index] == '0') {
            ans = 0;
        } else {   // cur是 '1-9'的字符，自己转化
            ans = process(str, index+1);
            // 10-19 20-26 两个进行转化
            if (index+1 < str.length && ((str[index]-'0') * 10 + (str[index+1]-'0')) <= 26) {
                ans += process(str, index+2);
            }
        }
        return ans;
    }

    // 记忆化搜索的方法
    private static int process1(char[] str, int index, int[] dp) {
        // 如果来到了字符串的末尾， 那么就找到了一种转化方法
        if (index == str.length) {
            return 1;
        }
        if (dp[index] != -1)
            return dp[index];

        int ans;
        if (str[index] == '0') {
            ans = 0;
        } else {   // cur是 '1-9'的字符，自己转化
            ans = process1(str, index+1, dp);
            // 10-19 20-26 两个进行转化
            if (index+1 < str.length && ((str[index]-'0') * 10 + (str[index+1]-'0')) <= 26) {
                ans += process1(str, index+2, dp);
            }
        }
        dp[index] = ans;
        return dp[index];
    }

    // 动态规划方式
    private static int dpWays(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int len = str.length;
        int[] dp = new int[len+1];
        dp[len] = 1;
        for (int index = len-1; index >= 0; index--) {
            if (str[index] == '0') {
                dp[index] = 0;
            } else {   // cur是 '1-9'的字符，自己转化
                dp[index] = dp[index+1];
                // 10-19 20-26 两个进行转化
                if (index+1 < str.length && ((str[index]-'0') * 10 + (str[index+1]-'0')) <= 26) {
                    dp[index] += dp[index+2];
                }
            }
        }
        return dp[0];
    }

}
