package com.chenjunyi.practice;

/**
 * 给定两个字符串str1和str2， 在str1中寻找一个最短子串，能包含str2的所有字符
 * 字符顺序无所谓，str1这个最短子串也可以包含多余字符，
 * 返回这个最短包含子串
 *
 * 欠账模型、窗口问题
 */
public class MinSubString {

    public static int minLength(String s1, String s2) {
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        int[] map = new int[256];
        for (int i =0; i < ch2.length; i++) {
            map[ch2[i]]++;
        }

        int l =0;
        int r = 0;
        int ans = Integer.MAX_VALUE;
        int all = ch2.length;
        while (r < ch1.length) {
            map[ch2[r]]--;
            if(map[ch1[r]] > 0) {
                all--;
            }

            if (all == 0) {
                while (map[ch1[l]] < 0) {
                    map[ch1[l++]]++;
                }
                ans = Math.min(ans, (r-l+1));
                map[ch1[l++]]++;
                all++;
            }
            r++;
            
        }
        return ans;
    }

    /**
     *  一个字符串，每种字符保留一个，要求字典序最小的结果
     *  不可以改变字符串str的相对次序
     */

    public static String minAscii(String str) {
         if (str == null || str.length() == 0) {
             return str;
         }
        char[] ch = str.toCharArray();
        int[] map = new int[256];
        for (int i=0;i < ch.length; i++) {
            map[ch[i]]++;
        }

        int minASCIndex= 0;
        for (int i =0;i < ch.length; i++) {
            minASCIndex = ch[minASCIndex] > ch[i] ? i : minASCIndex;
            if(--map[ch[i]] == 0) {
                break;
            }
        }
        return String.valueOf(ch[minASCIndex]) + minAscii(str.substring(minASCIndex+1).replaceAll(String.valueOf(minASCIndex), ""));
    }
}
