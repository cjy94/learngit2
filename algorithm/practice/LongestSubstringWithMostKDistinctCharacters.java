package com.chenjunyi.practice;

/**
 * 给定一个字符串str，在给一个长度k
 * 求字符串str中所有包含k个字符的子串中，返回长度最长的子串长度
 *
 * 窗口问题
 */
public class LongestSubstringWithMostKDistinctCharacters {

    public static int lengthOfLongestSubstringWithMostKDistinct(String s, int k) {
        char[] str = s.toCharArray();
        int n = str.length;
        int ans = 0;
        int R = 0;
        int[] counts = new int[256];
        int diff = 0;
        for (int i =0; i < n; i++) {
            while (R < n && (diff < k || (diff == k && counts[str[R]] > 0))) {
                diff += counts[str[R]] == 0 ? 1 : 0;
                counts[str[R]]++;
                R++;
            }
            ans = Math.max(ans, (R-i));
            diff -= counts[str[i]] == 1 ? 1 : 0;
            counts[str[i]]--;
        }
        return ans;
    }
}
