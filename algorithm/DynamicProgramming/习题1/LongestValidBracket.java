package com.chenjunyi.DynamicProgramming.习题1;

/**
 *
 最长有效括号串

 枚举以每一个位置结尾的情况下，往前推多远能形成有效括号串，
 在每一种情况下取最大值就是整体的最长有效括号串
 
 转移方程：
 dp[i] = dp[i-1] + 2 + (dp[p-1] | 0)
 */

public class LongestValidBracket {

     
}
