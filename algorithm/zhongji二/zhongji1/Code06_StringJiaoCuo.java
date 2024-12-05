package com.chenjunyi.zhongji二.zhongji1;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.Arrays;

/**
 * 给定三个字符串str1, str2, aim， 如果aim包含且仅包含来自str1和str2的所有字符串，而且在aim种属于str1的字符之间保持原来在str1的顺序，
 *   属于str2的字符之间保持原来在str2的顺序，那么称aim是str1和str2的交错组成，实现一个函数，判断aim是否是str1和str2交错组成
 *
 *  举例：str1="AB", str2="12",那么"AB12","A1B2","1A2B"和"1AB2"等都是str1和str2的交错组成
 *
 *  1、从左往右尝试模型
 *  2、范围尝试模型
 *  3、x做行，y做列的尝试模型
 *  4、
 */

public class Code06_StringJiaoCuo {
    private static boolean process(String str1, String str2, String aim) {
        // aim的长度应该是str1长度和str2长度的和
        char[] str1_ch = str1.toCharArray();
        int str1_len = str1_ch.length;
        char[] str2_ch = str2.toCharArray();
        int str2_len = str2_ch.length;
        char[] aim_ch = aim.toCharArray();
        int aim_len = aim_ch.length;

        if (aim_len < str1_len + str2_len) return false;
        boolean result = false;
        boolean[][] dp = new boolean[str1_len+1][str2_len+1];
        dp[0][0] = true;
        // 处理第一行
        for (int i =1; i <= str2_len; i++) {
            dp[0][i] = aim_ch[i] == str2_ch[i] ? true : false;
        }

        // 处理第一列
        for (int i=1; i <= str1_len; i++) {
            dp[i][0] = aim_ch[i] == str1_ch[i] ? true : false;
        }

        // dp表中的其他情况
        // (1)
        for(int i = 1; i <= str1_len; i++) {
            for (int j =1; j <= str2_len; j++) {
                // 第一种
                boolean res1 = false;
                boolean res2 = false;
                // 如果aim[i+j-1] 的最后一个字符来自str1中的最后一个字符，那么aim最后一个字符之前的所有字符应该是str1最后一个字符之前的所有和str2的全部
                if (aim_ch[i+j-1] == str1_ch[i] && dp[i-1][j] == true) {
                    res1= true;
                }
                //  如果aim[i+j-1] 的最后一个字符来自str2中的最后一个字符，那么aim最后一个字符之前的所有字符应该是str2最后一个字符之前的所有和str1的全部
                if (aim_ch[i+j-1] == str2_ch[j] && dp[i][j-1]==true) {
                    res2 = true;
                }

                if (res1==false && res2==false) {
                    dp[i][j] = false;
                } else {
                    dp[i][j] = true;
                }
            }
        }
        return dp[str1_len][str2_len];
    }



    // str1 和 str2字符交错组成，能否组成aim
    public static boolean f(String str1, String str2, String aim) {
        if (aim.length() != (str1.length() + str2.length())) {
            return false;
        }
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int n = s1.length;
        int m = s2.length;
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0] = true;
        // 第一行
        for (int i =1; i <= m; i++) {
            dp[0][i] = aim.charAt(i-1) == s2[i-1] ? true : false;
        }

        // 第一列
        for (int i =1; i <= n; i++) {
            dp[i][0] = aim.charAt(i-1) == s1[i-1] ? true : false;
        }

        for (int i =1; i <= n; i++) {
            for (int j=1; j <= m; j++) {
                if (s1[i-1] == aim.charAt(i+j-1) && dp[i-1][j] == true) {
                    dp[i][j] = true;
                }

                if (s2[j-1] == aim.charAt(i+j-1) && dp[i][j-1] == true) {
                    dp[i][j] = true;
                }

            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        String s1 = "aaabcc";
        String s2 = "aabdb";
        String aim = "aabaadabccb";
        System.out.println(f(s1, s2, aim));


//        String s1 = "AB";
//        String s2 = "12";
//        String aim = "AB12";
//        System.out.println(f(s1, s2, aim));

    }
}
