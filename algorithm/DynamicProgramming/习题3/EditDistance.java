package com.chenjunyi.DynamicProgramming.习题3;

/**
 *  编辑距离问题   可以用于评价两个字符串的相似程度
 *  给定了两个单词word1和wrod2， 请返回将word1转换成word2 所使用的最少代价
 *  可以对一个单词进行如下三种操作：
 *  1、插入一个字符， 代价a
 *  2、删除一个字符，代价b
 *  3、替换一个字符，代价c
 *
 *  s1[0..i-1] 前i个字符转成成s2[0...j-1]前举个字符的最小代价
 *  1、s1[0...i-1] -> s2[0..j-2] 插入一个字符，  dp[i][j-1] + a
 *  2、s1[0...i-2] -> s2[0...j-1] 删除一个字符 ，   dp[i-1][j] + b
 *  3、s1[0...i-2] -> s2[0...j-2] 替换一个字符， dp[i-1][j-1] + c
 *  4、s1[i-1] == s2[j-1] dp[i-1][j-1]  不做任何代价
 *  4种情况取最小值
 *  
 */
public class EditDistance {


}
