package com.chenjunyi.gaoji一.gaoji6;

/**
 * 一个字符串种，可以切割成回文的最少切割数
 */
public class MinimumPalindromeCount {



    public static int minParts(String s) {
        if (s == null || s.length() == 0 /*|| s.length() == 1*/) {
            return 0;
        }

        // 为什么是1， 0不是可以吗？
        if (s.length() == 1) {
            return 1;
        }

        return process(s.toCharArray(), 0);
    }

    // str[i...]最少能分割成多少回文的部分，
    // 返回最少的部分数
    private static int process(char[] s, int i) {
        if (i == s.length) {
            return 0;
        }
        //尝试每一个end, 如果str[i..end]这个部分是回文， 就去尝试这个部分是作为回文的第一块
        int res = Integer.MAX_VALUE;
        boolean[][] p = isPalindrome(s);
        for (int end = i; end < s.length; end++) {
            if (p[i][end]) {
                res = Math.min(res, process(s, end+1)+1);
                
            }
        }
        return res;
        
    }

    //  dp[len+1] ???
    private static int dpWays(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chas = str.toCharArray();
        int len = chas.length;
        int[] dp = new int[len+1];
        dp[len] = 0;
        dp[len - 1] = 1;
        //尝试每一个end, 如果str[i..end]这个部分是回文， 就去尝试这个部分是作为回文的第一块

        boolean[][] p = isPalindrome(chas);
        for (int i = len-2; i>=0; i--) {
            dp[i] = chas.length - i;
            for (int j = i; j < len; j++) {
                if (p[i][j]) {
                    dp[i] = Math.min(dp[i], dp[j + 1] + 1);

                }
            }
        }
        return dp[0];

    }

    // 预处理结构
    // 字符串任何一个范围上的子串是否是回文
    // str[i..j] 部分的字符串是否是回文
    public static boolean[][] isPalindrome(char[] s) {
        int len = s.length;
        boolean dp[][] = new boolean[len][len];
        // 填写主对角线
        for (int i =0; i < len; i++) {
            dp[i][i] = true;
        }

        // 填写次对角线
        for (int i =0; i < len-1; i++) {
            dp[i][i+1] = s[i] == s[i+1] ? true : false;
        }
        // 普遍位置
        for (int i = len-3; i >= 0; i--) {
            for (int j = i+2; j < len; j++) {
                if (s[i] == s[j]) {
                    dp[i][j] = dp[i+1][j-1];
                }
            }
        }
        return dp;

    }
}
