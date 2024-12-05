package com.chenjunyi.gaoji一.gaoji5;

/**
 * 求两个字符串的公共子序列
 */
public class CommonSubSequence {

    public static String commonSubSequence(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int[][] dp = new int[s1.length][s2.length];
        dp[0][0] = s1[0] == s2[0] ? 1 : 0;
        // 第一行
        for (int col = 1; col < s2.length; col++) {

            dp[0][col] = s1[0] == s2[col] ? dp[0][col-1] +1 : dp[0][col];
        }

        for (int row = 1; row < s1.length; row++) {
            dp[row][0] = s2[0] == s1[row] ? dp[row-1][0]+1 : dp[row-1][0];
        }

        // 普遍位置
        for (int row = 1; row < s1.length; row++) {
            for (int col = 1; col < s2.length; col++) {
                if (s1[row] == s2[col]) {
                    dp[row][col] = dp[row-1][col-1] +1;
                }
                dp[row][col] = Math.max(dp[row][col], Math.max(dp[row-1][col], dp[row][col-1]));
            }
        }

        int commonLen = dp[s1.length-1][s2.length-1];
        // 通过commonlen和dp表组成公共子序列的结果
        return null;

    }
}
