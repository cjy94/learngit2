package com.chenjunyi.project;

/**
 *  均衡字符串
 *  均衡串定义：字符串只包含两种字符，且两种字符的个数相同。
 *  给定一个均衡字符串，请给出可分割成新的均衡子串的最大个数。
 *  约定字符串中只包含大写的'X'和'Y'两种字符。
 */
public class junhengString {

    public static int count(String str) {
        char[] s = str.toCharArray();
        int countX = 0;
        int countY = 0;
        int ans = 0;
        for (char c : s) {
            if (c == 'X')
                countX++;
            else
                countY++;
            if (countX == countY) {
                ans++;
                countX = 0;
                countY = 0;
            }
        }
        return ans;
    }
}
