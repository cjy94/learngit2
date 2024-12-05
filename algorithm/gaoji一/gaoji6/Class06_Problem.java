package com.chenjunyi.gaoji一.gaoji6;

/**
 * 以下问题都是使用范围内的尝试模型
 *
 */
public class Class06_Problem {

    /**
     *  给定一个字符串str, 求最长的回文子序列长度
     *  dp[i][j]表示str[i..j]范围上的最长回文子序列
     *  1、以i开头，以j结尾        str[i]==str[j]成立， dp[i+1][j-1]+2
     *  2、不以i开头，以j结尾                          dp[i+1][j]
     *  3、以i开头，不以j结尾                          dp[i][j-1]
     *  4、不以i开头，不以j结尾                        dp[i+1][j-1]
     *
     *  以上情况取最大值
     *
     */
    public static int getLongestPalindromeSebSequence(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        if (str.length() == 1) {
            return 1;
        }

        char[] s = str.toCharArray();
        int n = s.length;
        int[][] dp = new int[n][n];
        // 主对角线
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        // 次对角线   (0,1) (1,2) (2,3) (3,4) (4,5)...
        for (int i =0; i < n-1; i++) {
            dp[i][i+1] = s[i] == s[i+1] ? 2 : 1;
        }

        // 普遍位置
        for (int i = n-3;i >=0; i--) {
            for (int j = i+2; j < n; j++) {
                if (s[i] == s[j]) {
                    dp[i][j] = dp[i+1][j-1]+2;
                }
                dp[i][j] = Math.max(Math.max(dp[i+1][j], dp[i][j-1]),Math.max(dp[i+1][j-1], dp[i][j]));
            }
        }
        return dp[0][n-1];
    }


    /**
     *  给定一个字符串str, 如果可以在str的任意位置添加字符， 请返回在添加字符最少的情况下，让str整体都是回文的一种结果
     *
     *  dp[i][j]: 表示str[i..j]范围字符串，至少添加多少字符，使str[i..j]成为回文字符串
     *  情况
     *  (1) str[i]==str[j]成立， 搞定str[(i+1)..(j-1)]使其成为回文
     *  (2) 搞定str[i+1...j]形成回文, str[i]添加到字符串的尾部
     *  (3) 搞定str[i...j-1]形成回文, str[j]添加到字符串的首部
     *  以上3种情况取最小值
     *
     */

    public static int[][] minCharPalindromeString(char[] s) {
        int n = s.length;
        int[][] dp = new int[n][n];
        // 填写主对角线
//        for (int i =0; i < n; i++) {
//            dp[i][i] = 0;
//        }

        // 填写次对角线
        for (int i =0; i < n-1; i++) {
            dp[i][i+1] = s[i] == s[i+1] ? 0 : 1;
        }

        // 填写普遍位置
        for (int i = n-3; i >=0 ; i--) {
            for (int j = i+2; j < n; j++) {
                dp[i][j] = Math.min(dp[i+1][j], dp[i][j-1]) + 1;
                if (s[i] == s[j]) {
                    dp[i][j] = Math.min(dp[i+1][j-1], dp[i][j]);
                }
            }
        }
        return dp;


    }


    public static String getPalindromeString(String str) {
        if (str == null || str.length() == 0 || str.length() == 1) {
            return str;
        }

        // 获取最少字符的dp数组
        char[] s = str.toCharArray();
        int[][] dp = minCharPalindromeString(s);
        char[] res = new char[s.length + dp[0][s.length-1]];
        // str的两个指针
        int i =0;
        int j = s.length-1;
        // res的两个指针
        int l = 0;
        int r = res.length-1;
        while (i <= j) {
            int dpValue = dp[i][j];
            if (s[i] == s[j]) {  // 来自于左下角
                res[l++] = s[i++];
                res[r--] = s[j--];
            } else if (dpValue == dp[i][j-1]+1) {  // 来自于左侧
                res[l++] = s[j];
                res[r--] = s[j];
                j--;

            } else if (dpValue == dp[i+1][j]+1) {
                res[l++] = s[i];
                res[r--] = s[i];
                i++;

            }
        }
        return new String(res);
    }

    /**
     *  给定一个字符串str, 返回把str全部切成回文子串的最小分割数
     *  例如 str="aba"  f(str, i): 表示从i位置往后所有字符串最少能够切成的回文个数
     *  a+f(1)  可以        res= f(1)+1
     *  ab+f(2) 不可以
     *  aba+f(3) 可以       res = f(3)+1
     *
     *  res取最小值
     *
     *
     */

    public static int minSplit(String str) {
        if (str == null || str.length() == 0 ) {
            return 0;
        }
        if(str.length() == 1) {
            return 1;

        }
        boolean[][] dp = isPalindrome(str.toCharArray());
        return process(str.toCharArray(), 0, dp);
    }

    private static int process(char[] s, int i, boolean[][] dp) {
        if (i == s.length) {
            return 0;
        }

        int ways = Integer.MAX_VALUE;
        // 枚举所有可能的情况
        // p(s,0) -> 如果str[0,0]是回文字符串， 那么 调用p(s,1);
        // p(s,1) -> 如果str[1,2]
        for (int end = i; end< s.length; end++) {
            if (dp[i][end]) {   // 如果是回文
                ways = Math.min(process(s, end+1, dp)+1, ways);
            }
        }
        return ways;

    }


    /**
     *  字符串中 每一个子串是否是回文
     *  dp[i][j]表示s[i..j]范围上的字符串是否是回文
     *  情况
     *  (1) s[i] == s[j]     dp[i][j] == dp[i+1][j-1]
     *  (2) s[i] != s[j]   dp[i][j] = false;
     *
     *  范围的尝试模型
     *
     */
    public static boolean[][] isPalindrome(char[] s) {
        int n = s.length;
        boolean[][] dp = new boolean[n][n];
        // 主对角线
        for (int i =0; i < n; i++) {
            dp[i][i] = true;
        }
        // 次对角线
        for (int i=0; i < n-1; i++) {
            dp[i][i+1] = s[i] == s[i+1] ? true : false;
        }

        // 普遍位置
        for (int i = n-3; i >= 0; i--) {
            for (int j =i+2; j < n; j++) {
                if (s[i] == s[j]) {
                    dp[i][j] = dp[i+1][j-1];
                }
            }
        }
        return dp;

    }

    public static void main(String[] args) {
//        String s = "a1bb1c";
//        System.out.println(getLongestPalindromeSebSequence(s));
//        String s = "abcd";
//        System.out.println(getPalindromeString(s));
        String s = "aba";
        System.out.println(minSplit(s));


    }
}
