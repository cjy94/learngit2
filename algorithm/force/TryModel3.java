package com.chenjunyi.force;
import java.util.List;
import java.util.ArrayList;

/**
 * 多样本位置全对应的尝试模型： 一个样本做行， 一个样本做列
 *
 *
 * 两个字符串的最长公共子序列问题
 *
 * "abc1cd2ef345gh" "opq123rs4t5"  最长公共子序列"12345"
 *
 *
 *
 */
public class TryModel3 {
    // 列出一个字符串的子序列
    public static ArrayList<String> printAllSequence(String str) {
        if (str == null || str.length() == 0) {
            return new ArrayList<String>();
        }
        ArrayList<String> list = new ArrayList<>();
        String sub = "";
        process(str,list, sub, 0);
        return list;
    }

    private static void process(String str, ArrayList<String> list, String sub, int i) {
        if (i == str.length()) {
            list.add(sub);
            return;
        }


        // 选择当前字符作为子序列的一部分
        process(str, list, sub+str.charAt(i), i+1);
        // 不选择当前字符作为子序列的一部分
        process(str, list, sub, i+1);
    }


    public static int maxCommonSequence(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2==null || s2.length()==0) {
            return 0;
        }
        int n = s1.length();
        int m = s2.length();
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[][] dp = new int[n][m];
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        // 填第1行
        for (int i =1; i<n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], str1[0]==str2[i] ? 1 : 0);
        }

        // 填第1行
        for (int i =1; i<m; i++) {
            dp[0][i] = Math.max(dp[0][i-1], str1[i]==str2[0] ? 1 : 0);
        }


        // 填写其余行
        /**
         * 1、最长子序列，不以str1[i]和str2[j]结尾， dp[i-1][j-1]
         * 2、最长子序列，以str1[i]结尾，不以str2[j]结尾，dp[i][j-1]
         * 3、最长子序列，不以str1[i]结尾，以str2[j]结尾， dp[i-1][j]
         * 4、最长子序列，以str[i]和str[j]结尾， 前提str1[i]==str2[j] dp[i-1][j-1]+1
         *
         * 以上情况取最大值
         */
        for (int i =1; i < n; i++) {
            for (int j =1; j< m; j++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                if(str1[i] == str2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1]+1);
                }
            }
        }
        
        return dp[n][m];
    }

    public static void main(String[] args) {
        System.out.println(printAllSequence("abcd"));
        ArrayList<String> l1 = printAllSequence("a1b2");
        ArrayList<String> l2 = printAllSequence("a12");

    }
}
