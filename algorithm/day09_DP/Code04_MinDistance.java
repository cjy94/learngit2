package com.chenjunyi.day09_DP;

/**
 * 最小编辑距离： 给定两个字符串str1和str2， 在给定3个正数ic、dc、rc， 分别代表插入、删除和更新操作
 * 返回将str1编辑成str2的最小代价
 */
public class Code04_MinDistance {
    public static int minDistanceDpWays(String str1, String str2, int ic, int dc, int rc) {
        if (str1 == null || str1.length() == 0 || str2 == null || str2.length() == 0) {
            return 0;
        }

        int row = str1.length() + 1;
        int col = str2.length() + 1;
        // dp[i][j] 代表， str1[0..i-1]编辑成str2[0..j-1]的最小代价
        int[][] dp = new int[row][col];
        // 填写第一行
        // str1="" 空字符串 编辑成str2[0..j]的代价， 从空字符串到str2只能插入
              for (int i=1; i < col; i++) {
            dp[0][i] = ic * i;
        }
        // 第一列
        for (int i =1; i < row; i++) {
            dp[i][0] = dc * i;

        }
        // 普遍位置dp[i][j]
        /**
         * str1="abc"  str2="adc"
         * 有3种方式可以将str1编辑成str2
         * 1、删除的方式: 将"a"编辑成"ad", 删除'b'  dp[i][j] = dp[i-1][j]+dc
         * 2、插入的方式： 将"ab"变价成"a"在插入'd'  dp[i][j] = dp[i][j-1]+ic
         * 3、替换的方式：
         *  3.1 str1[i] == str2[j] 那么替换的代价就是前面字符的处理代价    dp[i][j] = dp[i-1][j-1]
         *  3.2 str1[i] != str2[j] 那么替换代价就是前面字符的处理代价+rc   dp[i][j] = dp[i-1][j-1]+rc
         *
         * 以上所有方式中取最小值
         */
        for (int i =1; i < row; i++) {
            for (int j =1; j < col; j++) {
                if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                }  else {
                    dp[i][j] = dp[i-1][j-1] + rc;
                }
                dp[i][j] = Math.min(dp[i-1][j]+dc, dp[i][j]);
                dp[i][j] = Math.min(dp[i][j-1]+ic, dp[i][j]);
            }
        }

        return dp[row-1][col-1];
    }

    public static void main(String[] args) {
        System.out.println(minDistanceDpWays("abc", "adc", 5, 3, 100));
        System.out.println(minDistanceDpWays("abc", "adc", 5, 3, 2));
        System.out.println(minDistanceDpWays("abc", "abc", 5, 3, 100));
    }


}
