package com.chenjunyi.DynamicProgramming.习题3;

/**
 *  交错组成字符串
 *  给定三个字符串s1,s2,s3, 请帮忙验证s3是否能由s1和s2交错组成
 *
 *
 *  dp[i][j]: s1[0...i-1]和s2[0...j-1]能否交错组成出s3[0...i+j-1]
 *
 *  1、s1[i-1] == s3[i+j-1]  dp[i-1][j]
 *  2、s2[j-1] == s3[i+j-1]  dp[i][j-1]
 *
 *  以上2种情况有一个为true就是true,
 *  最后返回dp[i][j]的值
 *  
 */

public class StaggeredComposition {
    
}
