package com.chenjunyi.GO.OneRowOneColumn;

/**
 *
 *  两个字符串的最长公共子序列问题
 *  str1= "ab1cd2ef345gh"  str2="opq123rs4tx5yz"
 *  最长公共子序列： "12345"
 *
 *  dp[i][j]: str1[0...i]前缀字符串和str2[0...j]前缀字符串的最长公共子序列是多少?
 *
 *  dp[0][0] = str1[0] == str2[0] ? 1 : 0;
 *  第一行的dp[0][i] = Math.max(dp[0][i-1], str1[0] == str2[i] ? 1 : 0);
 *  第一列的dp[i][0] = Math.max(dp[i-1][0], str1[i] == str2[0] ? 1 : 0)
 *
 *  str1[i]==str2[j]   dp[i][j] = dp[i-1][j-1]+1
 *  str[i] != str2[j]  dp[i-1][j-1]
 *
 *  1、dp[i-1][j]
 *  2、dp[i][j-1]
 *  3、dp[i-1][j-1] +  (str1[i]==str2[j] ? 1 : 0);
 *
 *  以上3中情况取最大
 */
public class TwoStringCommonSubSequence {

    public static int largestCommonSubSequence(char[] s1, char[] s2) {
        int[][] dp = new int[s1.length][s2.length];
        dp[0][0] = s1[0] == s2[0] ? 1 : 0;
        for (int i =1; i < s1.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], s1[i] == s2[0] ? 1 : 0);
        }

        for (int j = 1; j < s2.length; j++) {
            dp[0][j] = Math.max(dp[0][j-1], s1[0] == s2[j] ? 1 : 0);
        }

        for (int i =1; i < s1.length; i++) {
            for (int j =1; j < s2.length; j++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                if (s1[i] == s2[j])
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1]+1);
            }
        }
        return dp[s1.length-1][s2.length-1];
    }


    // 寻找业务找限制
}
