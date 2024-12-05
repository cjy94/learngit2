package com.chenjunyi.Strings;

import java.util.HashMap;
import java.util.Map;

/**
 *  无重复字符的最长子串
 *  子串是连续的
 *
 *  以i位置 结尾情况下，能向前延伸多少
 *  preIndex: 这个字符上次出现的位置
 *  dp[i-1]
 *  取最大值
 *
 */
public class LargestSubStringNoDuplicate {
    public static void main(String[] args) {
        String s = "abcabcbb";
        int i = lengthOfLongestSubstring1(s);
    }

    public static int lengthOfLongestSubstring1(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] c = s.toCharArray();
        int l = c.length;
        int[] dp = new int[l];
        dp[0] = 1;  // 长度
        map.put(c[0], 0);
        for (int i = 1; i < l; i++) {
            char ch = c[i];
            if (!map.containsKey(ch)) {  // 当前字符没出现过
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = Math.min(dp[i-1]+1, i- map.get(ch));
            }
            map.put(ch, i);
        }
        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        // 特判
        if (len < 2) {
            return len;
        }

        // dp[i] 表示以 s[i] 结尾的最长不重复子串的长度
        // 因为自己肯定是不重复子串，所以初始值设置为 1
        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            dp[i] = 1;
        }
        Map<Character, Integer> map = new HashMap<>();
        map.put(s.charAt(0), 0);
        for (int i = 1; i < len; i++) {
            Character c = s.charAt(i);
            if (map.containsKey(c)) {
                if (dp[i - 1] >= i - map.get(c)) {
                    dp[i] = i - map.get(c);
                } else {
                    dp[i] = dp[i - 1] + 1;
                }
            } else {
                dp[i] = dp[i - 1] + 1;

            }
            // 设置字符与索引键值对
            map.put(c, i);
        }
        // 最后拉通看一遍最大值
        int res = dp[0];
        for (int i = 1; i < len; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;

    }
}
