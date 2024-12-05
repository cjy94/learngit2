package com.chenjunyi.gaoji一.gaoji6;

/**
 * 最长回文子序列问题
 *   给定一个字符串str，求最长的回文子序列
 *
 *   "1abc232hu1"  最长回文子序列是 "12321"
 *
 */
public class LongestPalindromeSubSequence {


    /**
     *  范围内的尝试模型
     *
     */
    public static int maxPalindromeSubSequence(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        if (str.length() == 1) {
            return 1;
        }
        char[] s = str.toCharArray();

        int n = str.length();
        int[][] dp = new int[n][n];
        // 例如： str="1 a b c 2 3 2 a 1"
        //            0 1 2 3 4 5 6 7 8
        // 正方形区域， 下半部分是没用的
        // 填写主对角线
        // 主对角线： (0,0) (1,1) (2,2) (3,3) (4,4) (5,5) (6,6) (7,7) (8,8)
        for (int i =0; i < n; i++) {
            dp[i][i] = 1;
        }
        // 填写次对角线
        // 次对角线： (0,1) (1,2) (2,3) (3,4) (4,5) (5,6) (6,7) (7,8)
        for (int i=0; i < n-1; i++) {    // 两个字符比较是否是回文子序列，相等则是2，不等则是1
            dp[i][i+1] = s[i] == s[i+1] ? 2 : 1;
        }

        // 从下往上填写dp表
        for (int i = n-3; i>= 0; i--) {
            for (int j = i+2; j < n; j++) {
                if (s[i] == s[j]) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                }
                dp[i][j] = Math.max(Math.max(dp[i+1][j], dp[i][j-1]), Math.max(dp[i][j], dp[i+1][j-1]));
            }
        }

        return dp[0][n-1];

    }

    public static void main(String[] args) {
        String str = "1abc232a1";
        str = "112ab3cd2e1";
        int i = maxPalindromeSubSequence(str);
        System.out.println(i);
    }
}
