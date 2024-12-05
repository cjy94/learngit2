package com.chenjunyi.practice;

/**
 * 可进行的操作有：保留字符、删除字符、添加字符、替换字符
 *
 * ab1c -> abc
 *
 * dp[i][j]：将str1前i个字符编辑成str2前j个字符的代价
 * str1[0...i-1]    str2[0...j-1]
 *
 * 1) dp[i-1][j] + delete
 * 2) dp[i][j-1] + add
 * 3) str1[i-1] == str2[j-1]  dp[i-1][j-1]
 * 4) str1[i-1] != str2[j-1] dp[i-1][j-1] + replace
 *
 * int N = str1.length;
 * int M = str2.length;
 * int[][] dp = new int[N][M];
 *
 * for (int i =1; i < N; i++) {
 *     dp[i][0] = ic * i;
 * }
 *
 * for (int j =1; j < M; j++) {
 *     dp[0][j] = dc * i;
 * }
 *
 * for (int i =1; i < N; i++) {
 *     for (int j =1; j < M; j++) {
 *          dp[i][j] = str1[i-1] == str2[j-1] ?  dp[i-1][j-1] : (dp[i-1][j-1] + rc);
 *          dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + dc);
 *          dp[i][j] = Math.min(dp[i][j], dp[i][j-1] + ac);
 *     }
 * }
 *
 *
 */

public class EditDistance {
    public static void main(String[] args) {

    }



}
