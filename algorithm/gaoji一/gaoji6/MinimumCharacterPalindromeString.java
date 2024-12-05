package com.chenjunyi.gaoji一.gaoji6;

/**
 * 给定一个str, 最少添加几个字符，使其是回文字符串
 */
public class MinimumCharacterPalindromeString {

    public static int[][] minChar(String str) {

        char[] s = str.toCharArray();
        int len = s.length;
        int[][] dp = new int[len][len];
        // 填写主对角线
        // 主对角线：(0,0) (1,1) (2,2) (3,3) (4,4) (5,5) ....
        // 不需要处理，默认就是0，
//        for (int i =0; i < len; i++) {
//            dp[i][i] = 0;
//        }

        // 填写次对角线
        // 次对角线：(0,1) (1,2) (2,3) (3,4) (4,5) (5,6) ...
        for (int i =0; i < len-1; i++) {
            dp[i][i+1] = s[i] == s[i+1] ? 0 : 1;   // 两个字符如果相等，已经是回文，不需要添加字符串； 否则添加一个字符整体就是回文字符串
        }

        for (int i = len-3; i >= 0; i--) {
            for (int j = i+2; j < len; j++) {
                dp[i][j] = Math.min(dp[i+1][j], dp[i][j-1]) + 1;
                if (s[i] == s[j]) {
                    dp[i][j] = Math.min( dp[i+1][j-1], dp[i][j]);
                }
            }
        }
        return dp;
    }

    public static String getPalindromeString(String str) {
        if (str == null || str.length() == 0 || str.length() == 1) {
            return str;
        }
        // 通过这张dp表恢复回文字符串
        int[][] dp = minChar(str);
        int len = str.length();
        char[] s = str.toCharArray();

        char[] ans = new char[len+dp[0][len-1]];
        int l = 0;
        int r = ans.length-1;
        int i = 0;
        int j = len-1;
        while (i <= j) {
            int dpValue = dp[i][j];
            // 如果两个字符相等，那么是来自于dp[i+1][j-1]位置的值， 来自于左下角
            if (s[i] == s[j]) {
                ans[l++] = s[i++];
                ans[r--] = s[j--];
            } else if(dpValue == dp[i+1][j]+1) {  // 来自于下侧
                ans[l++] = s[j];
                ans[r--] = s[j];
                j--;
            } else if (dpValue == dp[i][j-1]+1)  { // 来自于左侧，
                ans[l++] = s[i];
                ans[r--] = s[i];
                i++;
            }
        }
        return new String(ans);
    }

    public static void main(String[] args) {
        String str = "abcd";
       // str = "a1b2cdc21";
        String ans = getPalindromeString(str);
        System.out.println(ans);
    }

}
