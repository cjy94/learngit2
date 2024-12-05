package com.chenjunyi.DynamicProgramming.习题3;

/**
 不同子序列问题
 给定两个字符串s和t, 统计在s的所有子序列中有多少个子序列严格等于t

 暴力解法： 枚举出s的所有子序列放到集合中，在集合中判断有多少个t
 动态规划： 删除/保留s中的字符，使其字符串等于t


 转移方程： dp[i][j] = dp[i-1][j] + (s[i-1] == t[j-1] ? dp[i-1][j-1] : 0); 
 */
public class DifferentSubseq {
}
