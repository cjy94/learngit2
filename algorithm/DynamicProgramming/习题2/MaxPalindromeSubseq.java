package com.chenjunyi.DynamicProgramming.习题2;

/**
 最长回文子序列问题
  给定一个字符串，找出其中最长的回文子序列，并返回子序列长度
  将str逆序形成str2,  即找到str和str2两个字符串最长的公共子序列问题
 str:        ab12cd34ef3k21tr
 str的逆序串: tr12k3fe43dc21ba
 原始串和逆序串的最长公共子序列就是原始串的最长回文子序列
 */
public class MaxPalindromeSubseq {


    // s的最长回文子序列
    public int longestPalindromeSubseq1(String s) {
        char[] str = s.toCharArray();
        return f(str, 0, str.length-1);
    }

    // 动态规划求解
    // 字符串s[l...r]之间的最长回文子序列
    private int f(char[] s, int l, int r) {
        // s[]只有一个字符
        if (l == r)
            return 1;

        // s[]有两个字符
        if (l == r+1)
            return s[l] == s[r] ? 2 : 1;

        // s[]有更多字符的情况
        if (s[l] == s[r]) {
            return 2 + f(s, l+1, r-1);
        } else {
            return Math.max(f(s, l+1, r), f(s, l, r-1));
        }
    }


    // 严格位置依赖的dp
    // s[l...r] 因为 l和r都是在[0..n]之间的，故dp是个正方形表，又因为l<=r 总是成立的，索引正方形的下半部分是无效区域，只需要填满正方形的右上部分
    public int longestPalindromeSubseq2(String s) {
        char[] str = s.toCharArray();
        int len = str.length;
        int[][] dp = new int[len][len];

        // 对角线
        for (int i =0; i < len; i++)
            dp[i][i] = 1;

        // 右上对角线
        for (int i =0; i < len-1; i++)
            dp[i][i+1] = str[i] == str[i+1] ? 2 : 1;

        // 从下往上, 从左往右填dp
        for (int l = len - 2; l >= 0; l--) {
            for (int r = l + 2; r < len; l++) {
                if (str[l] == str[r])
                    dp[l][r] = 2 + dp[l+1][r-1];
                else
                    dp[l][r] = Math.max(dp[l+1][r], dp[l][r-1]);
            }
        }
        return dp[0][len-1];
    }
}
