package com.chenjunyi.zhongji二.zhongji8;

/**
 * 给定两个字符串S和T，返回S子序列等于T的   不同子序列个数有多少个？
 */
public class DistinctSubSequence {

    public static int numDistinct1(String S, String T) {
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        return process(s, t, s.length, t.length);
    }

    // s[0..i]之间形成的子序列的字面值 == t[0..j]的字面值的  子序列个数有多少？
    private static int process(char[] s, char[] t, int i, int j) {
        if (j==0) {
            return 1;
        }
        if (i == 0) {
            return 0;
        }
        int res = process(s,t,i-1,j);
        if (s[i-1] == t[j-1]) {
            res += process(s,t,i-1,j-1);
        }
        return res;
    }


    public static int numDistinct2(String S, String T) {
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        int[][] dp = new int[s.length+1][t.length+1];
        for (int j = 0; j <= t.length; j++) {
            dp[0][j] = 0;
        }
        for (int i =0; i <= s.length; i++) {
            dp[i][0] = 1;
        }

        // dp[i][j]含义：
        // s取长度为i的前缀字符串S[0...i-1], 其中有多少子序列， 字面值
        // ==
        // t取长度为j的前缀字符串T[0...j-1]
        for (int i =1; i<= s.length; i++) {
            for (int j =1; j<= t.length; j++) {
                dp[i][j] = dp[i-1][j] + (s[i-1] == t[j-1] ? dp[i-1][j-1] : 0);
            }
        }
        return dp[s.length][t.length];
    }

}
