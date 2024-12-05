package com.chenjunyi.gaoji一.gaoji5;

/**
 * 求两个字符串的公共字串问题
 */
public class CommonSubString {

    public static String subString(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int[][] dp = new int[s1.length][s2.length];
        // 填写第一行
        for (int col = 0; col < s2.length; col++) {
            dp[0][col] = s1[0] == s2[col] ? 1 : 0;
        }

        // 填写第一列
        for (int row = 0; row < s1.length; row++) {
            dp[0][row] = s2[0] == s1[row] ? 1 : 0;
        }

        int max = 0;
        int rowAns = 0;
        int colAns  =0;

        // 填写普通位置
        for (int row = 1; row < s1.length; row++) {
            for (int col = 1; col < s2.length; col++) {
                if (s1[row] == s2[col]) {
                    dp[row][col] = dp[row-1][col-1] + 1;
                    if (dp[row][col] > max) {
                        max = dp[row][col];
                        rowAns = row;
                        colAns = col;
                    }
                }
            }
        }
//        System.out.println(str1.substring(rowAns-max+1, rowAns+1));
//        System.out.println(str2.substring(colAns-max+1, colAns+1));

        return str2.substring(colAns-max+1, colAns+1);
    }

    public static void main(String[] args) {
        String str1 = "aabcaac";
        String str2 = "aaabccab";
        String s = subString(str1, str2);
        System.out.println(s);
    }
}
