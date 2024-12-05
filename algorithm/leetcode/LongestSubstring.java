package com.chenjunyi.leetcode;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * 找出一个字符串中的无重复字符的最长字串
 */
public class LongestSubstring {

    //方法一： 滑动窗口 + hashSet 求解
    /*
    1、r  先走，字符全部放入set集合中， l后走，控制相同字符就删除set集合

     */
    public int lengthOfLongestSubstring(String s) {
        char[] ch = s.toCharArray();
        int len = ch.length;
        int l = 0;
        int r =0;
        int maxLength = 0;
        HashSet<Character> set = new HashSet<>();
        for (; r < len; r++) {
            if (!set.contains(ch[r])) {
                set.add(ch[r]);
                maxLength = Math.max(maxLength, r-l+1);
            } else {
                // 出现了相等字符，需要进行删除操作
                while (ch[l] != ch[r]) {
                    set.remove(ch[l]);
                    l++;
                }
                if (ch[l] == ch[r]) {
                    set.remove(ch[l]);
                    l++;
                }
               set.add(ch[r]);
            }
        }
        return maxLength;
    }



    public int lengthOfLongestSubstring1(String s) {
        char[] ch = s.toCharArray();
        int l =0;
        int r = 0;
        int maxLength = 0;
        HashSet<Character> set = new HashSet<>();
        while (r < ch.length) {
            // set中不包含重复字符时，加入到set集合中， 并将r++, 计算无重复字符串的长度
            if (!set.contains(ch[r])) {
                set.add(ch[r]);
                r++;
                maxLength = Math.max(r-l, maxLength);
            } else {

                // 如果有重复时，将相同字符串前面的所有字符都要弹出，因为要连续的字符串
                while (ch[l] != ch[r]) {
                    set.remove(ch[l]);
                    l++;
                }

                if (ch[l] == ch[r]) {
                    set.remove(ch[l]);
                    l++;
                }
                set.add(ch[r]);
                r++;

            }

        }
        return maxLength;
    }
    public static void main(String[] args) {
//        LongestSubstring test = new LongestSubstring();
//        System.out.println(test.lengthOfLongestSubstring1("bbbbbb"));
//        System.out.println(test.lengthOfLongestSubstring1("abcabcbb"));

        System.out.println(Integer.parseInt("-7878"));
    }
}
