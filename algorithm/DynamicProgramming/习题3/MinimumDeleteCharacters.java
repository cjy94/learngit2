package com.chenjunyi.DynamicProgramming.习题3;

/**
  删除至少几个字符可以变成另一个字符串的子串

 给定两个字符串s1和s2，返回s1至少删除多少字符可以成为s2的子串


 dp[i][j] s1前i个字符串至少删除多少字符能变成s2前j个字符串的任意后缀串
 最后一个字符删不删进行讨论：
 1) s1[i-1] != s2[j-1]
    dp[i-1][j] + 1
 
 2)  s1[i-1] == s2[j-1]
    dp[i-1][j-1]

  返回结果是： 最后一行结果种的最小值



 暴力方法： 生成s1的所有子序列， 检查每一种子序列是否是s2的子串，最长的哪个子串就是最少删除的结果

 子序列，就是某个位置的字符要或者不要，两种情况的全排列 时间复杂度2^n

 */
public class MinimumDeleteCharacters {

    // 打印str的所有子序列
    public static void allSubseq(String str) {
        if (str != null || str.length() > 0) {
            char[] s = str.toCharArray();
            String path = "";
            process(s, 0, path);
        }
    }


    private static void process(char[] s, int index, String path) {
        if (index == s.length) {
            System.out.println("path: " + path);
            return;
        }

        // index位置的字符要
        process(s, index+1, path+s[index]);
        // index位置的字符不要
        process(s, index+1, path);
    }

    public static void main(String[] args) {
        allSubseq("abc");
    }


}
