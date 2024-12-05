package com.chenjunyi.day09_DP;

/**
 * 给定一个字符串，全部由数字组成，返回可能组成字母字符串的可能情况有多少种？
 */
public class Code05_NumricStringTransformString {

    // 暴力递归的方式
    private static int method1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process1(str, 0);
    }

    private static int process1(String str, int i) {
        if (i == str.length() ) {
            return 1;
        }
        // 如果以'0'开头
        if (str.charAt(i) == '0') {
            return 0;
        }
        // 以1开头的情况
        if (str.charAt(i) == '1') {
            int res = process1(str, i+1);
            if (i + 1 < str.length()) {
                res += process1(str, i+2);
            }
            return res;
        }
        // 以2开头
        if (str.charAt(i) == '2') {
            int res = process1(str, i+1);
            if (i + 1 < str.length() && (str.charAt(i+1) - '0' >= 0 && str.charAt(i+1) - '0' <= 6)) {

                res += process1(str, i+2);
            }
            return res;
        }
        // 以3 及其他数字组成的情况
        return process1(str, i+1);
    }

    private static int dp1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int n = str.length();
        int[] dp = new int[n+1];
        dp[n] = 1;
        for (int i = n-1; i>= 0; i--) {
            if (str.charAt(i) == '0') {
                dp[i] = 0;
            } else if (str.charAt(i) == '1') {
                dp[i] = dp[i+1];
                if (i+1 < n ) {
                    dp[i] += dp[i+2];
                }
            }  else if (str.charAt(i) == '2') {
                dp[i] = dp[i+1];
                if (i+1 < n && str.charAt(i+1)-'0' >= 0 && str.charAt(i+1)-'0' <= 6) {
                    dp[i] += dp[i+2];
                }
            } else {
                dp[i] = dp[i+1];
            }

        }
        return dp[0];

    }

    



    public static void main(String[] args) {
        System.out.println(dp1("1111"));
    }
}
