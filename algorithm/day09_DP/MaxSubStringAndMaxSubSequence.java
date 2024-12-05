package com.chenjunyi.day09_DP;

/**
 * str1 = "abc2345ji"; str2 = "abchji2345";
 * 求两个字符串的最大公共子序列和最大公共字串问题
 * 区别： 子序列不要求字符连续； 字串要求字符是连续的
 * 比如： 最长公共子序列： "abc2345"
 *       最长公共字串： "2345"
 */
public class MaxSubStringAndMaxSubSequence {

    /**
     * 计算dp表时间复杂度是（m*n）, 空间复杂度是(m*n)
     */
    public static String maxCommonSubString(String str1, String str2) {
        if (str1 == null || str1.length() == 0 || str2== null || str2.length() == 0) {
            return "";
        }

        int[][] dp = getMaxCommonLength(str1, str2);
        // 找到dp表中的最大值以及最大值在str1/str2中的索引位置
        int n = str1.length();
        int m = str2.length();
        int max = 0;
        int index = 0;
        for (int i =0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dp[i][j] > max) {
                    max = dp[i][j];
                    index = i; // 当公共字串的长度最大时，最后一个字符在str1字符串中的index
                }
            }
        }
        return str1.substring(index-max +1, index+1);

    }

    // 求两个字符串的最长公共字串
    // dp[i][j] 表示必须以str1[i]和str2[j]字符结尾的字符串的公共字串有多长
    // 两种情况1、str1[i] == str2[j]     dp[i][j] = dp[i-1][j-1]+1
    //       2、str1[i] != str2[j]    dp[i][j] = 0
    private static int[][] getMaxCommonLength(String str1, String str2) {

        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[n][m];
        // 第一行的值， 表示str1[0]的字符和str2[0..m]的字符串可以组成的最长公共字串
        for (int i =0; i < n; i++) {
            dp[0][i] =   str1.charAt(0) == str2.charAt(i) ? 1 : 0;
        }

        for (int i =0; i < m; i++) {
            dp[i][0] =   str2.charAt(0) == str1.charAt(i) ? 1 : 0;
        }
        // 普遍位置
        for (int i =1; i < n; i++) {
            for (int j =1; j < m; j++) {
                dp[i][j] = str1.charAt(i) == str2.charAt(j) ? dp[i-1][j-1]+1 : 0;
            }
        }
        return dp;
    }

    // 计算两个字符串的最长公共子序列问题
    public static String maxCommonSubSequence(String str1, String str2) {
        if(str1==null || str1.length() == 0 || str2 == null || str2.length() == 0) {
            return "";
        }
        int[][] dp = getMaxCommonSequenceLength(str1, str2);
        int n = str1.length()-1;
        int m = str2.length()-1;
        // dp[n][m]即右下角的值就是str1[0..n]结尾和str2[0..m]结尾的最长公共子序列的长度
        char[] res = new char[dp[n][m]];
        int index = res.length-1;
        while (index >= 0) {
            if (n > 0 && dp[n-1][m] == dp[n][m]) {
                n--;
            } else if (m > 0 && dp[n][m-1] == dp[n][m]) {
                m--;
            } else {
                // 从左上角来的
                res[index] = str1.charAt(n);
                n--;
                m--;
                index--;
            }

        }
        return String.valueOf(res);

    }

    // 计算两个字符串的最长公共子序列问题
    // dp[i][j]的含义是： 以str[i]结尾的字符串和以str[j]结尾的字符串的最长公共子序列

    private static int[][] getMaxCommonSequenceLength(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[n][m];
        // 填第一行的值，str1[0]的字符和str2[0..index]字符串种的最长公共子序列
        dp[0][0] = str1.charAt(0) == str2.charAt(0)   ? 1 : 0;
        for (int i =1; i < m; i++) {
            dp[0][i] = Math.max(dp[0][i-1],str1.charAt(0) == str2.charAt(i) ? 1 : 0);

        }
        // 第一列
        for (int i =1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0],str1.charAt(i) == str2.charAt(0) ? 1 : 0);
        }

        // 普遍位置
        for (int i =1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1]+1);
                }
            }
        }
        return dp;
    }


    public static void main(String[] args) {
        System.out.println(maxCommonSubSequence("1A2C3D4B56", "B1D23CA456A"));
    }
}
