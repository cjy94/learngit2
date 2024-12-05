package com.chenjunyi.Windows.习题1;

/**
 *  无重复字符的最长子串
 *  以i位置结尾情况下，能向前延伸多少
 *  前一个位置延伸到的索引位置 和 这个字符上一次出现的位置，两者中取最大值作为本次的结果
 *  dp[i] = Math.max(dp[i-1], last[ch]);
 */
public class LongestNoRepeatSubString {
}
