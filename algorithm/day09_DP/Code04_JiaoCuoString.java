package com.chenjunyi.day09_DP;

import java.math.BigDecimal;

/**
 * 给定3个字符串，str1、str2、aim, 判断aim是否是由str1和str2交错组成出来的
 */
public class Code04_JiaoCuoString {

    public static boolean JiaoCuoString(String str1, String str2, String aim) {
        if (str1 == null || str1.length() == 0 ||
            str2 == null || str2.length() == 0 ||
            aim == null || aim.length() == 0 || aim.length() != str1.length()+str2.length()) {
            return false;
        }

        int row = str1.length()+1;
        int col = str2.length()+1;
        // dp[i][j] 表示，str1[0..i-1]和str2[0..j-1] 能否交错组成aim，
        boolean[][] dp = new boolean[row][col];
        // 第一行， 表示str1="" 和str2[0..i] 能否组成aim[0..i-1]
        dp[0][0] = true;
        for (int i =1; i < col; i++) {
            dp[0][i] = str2.charAt(i-1) == aim.charAt(i-1) ? true : false;
        }
        // 第一列，表示str1[0,,i]和str2="" 能否组成aim[0..i-1]
        for (int i = 1; i < row; i++) {
            dp[i][0] = str1.charAt(i-1) == aim.charAt(i-1) ? true : false;
        }

        // 普遍位置
        // dp[i][j] 表示str1[0..i-1]和str2[0..j-1] 能否组成aim[0..i+j-1]
        for (int i =1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                // 两种情况
                // 1、如果str1[0..i-2]和str2[0..j-1]已经组成了aim[0..i+j-2], 那么str1[i-1]==aim[i+j-1] 就可以组成
                // 2、如果str1[0..i-1]和str2[0..j-2]已经组成了aim[0..i+j-2], 那么str2[i-1]==aim[i+j-1] 就可以组成
                if (str1.charAt(i-1) == aim.charAt(i+j-1) && dp[i-1][j] ||
                    str2.charAt(j-1) == aim.charAt(i+j-1) && dp[i][j-1]) {
                    dp[i][j] = true;
                }

            }

        }
        // 返回str1[0..i-1]和str2[0..j-1]  能否组成aim[0..i+j-1]
        return dp[row-1][col-1];


    }



    public static boolean JiaoCuoString1(String str1, String str2, String aim) {
        if (str1 != null && str2 != null && aim != null && aim.length() == str1.length() + str2.length()) {
            char[] s1 = str1.toCharArray();
            char[] s2 = str2.toCharArray();
            int n1 = s1.length;
            int n2 = s2.length;
            boolean[][] dp = new boolean[n1 + 1][n2 + 1];
            // dp[i][j] 表示str1前缀为i 和 str2前缀为j的情况下能否则组合出aim[i+j-1]的前缀字符串
            // 填写第一列
            dp[0][0] = true;
            for (int i =1; i <= n1; i++) {
                dp[i][0] = s1[i] == aim.charAt(i) ? true : false;

            }
            // 第一行
            for (int i =1; i <= n2; i++){
                dp[0][i] = s2[i] == aim.charAt(i) ? true : false;
            }

            // 普遍位置，
            // 两种情况，要么aim[i+j-1]来自于s1[i-1]； 要么aim[i+j-1]来自于s2[j-1]
            for (int i =2; i <= n1; i++) {
                for (int j =2; i <= n2; j++) {
                    if (s1[i - 1] == aim.charAt(i+j-1) && dp[i-1][j] == true ||
                        s2[j-1] == aim.charAt(i+j-1) && dp[i][j-1] == true) {
                        dp[i][j] = true;
                    }
                }
            }

        }
        return false;

    }

    public static void main(String[] args) {
       // System.out.println(JiaoCuoString("ab", "12", "a12b"));

        int l1 = Integer.MIN_VALUE;
        System.out.println(l1 / -1);
    }
}
