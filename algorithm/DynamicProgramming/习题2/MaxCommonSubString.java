package com.chenjunyi.DynamicProgramming.习题2;

/**
 * 最长公共子串问题
 * 给定2个字符串str1和str2, 计算处str1和str2的最长公共子串
 *
 * str1: ab12c3e
 * str2: tg1v23b
 * str1和str2的最长公共子串是："123"
 * 情况：
 * 1、str1和str2都不包含最后一个字符时，出现了最长公共子串
 *    str1: ab12c3e    str2: tg1v23b
 *    str1[0...i-1]
 *    str2[0...j-1]
 *
 * 2、str1包含最后一个字符，str2不包含最后一个字符
 *    str1: ab12ce3    str2: tg1v23b
 *    str1[0...i]
 *    str2[0...j-1]
 *
 * 3、str1不包含最后一个字符，str2包含最后一个字符
 *    str1: ab12c3e   str2: tg1v2b3
 *    str1[0...i-1]
 *    str2[0...j]
 *
 * 4、str1和str2最后一个字符相同的情况
 *      str1: abc12e3   str2: bh1g23
 *      str1[0...i-1] + 1
 *      str2[0...j-1] + 1
 *
 *
 */
public class MaxCommonSubString {


    // 可以改成空间压缩，将二维dp改成一维dp
    public int maxCommonSubstring(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int n = str1.length;
        int m = str2.length;
        int[][] dp = new int[n+1][m+1];

        // 从左往右，从上往下的填满dp表 , i和j代表str1和str2的长度
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (str1[i-1] == str2[j-1])
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[n][m];
    }
}
