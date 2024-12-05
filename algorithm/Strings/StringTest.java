package com.chenjunyi.Strings;

import java.util.ArrayList;

/**
 *
 */
public class StringTest {
    public static void main(String[] args) {
        String str = "1010101";
        int res = print(str, 2);
        System.out.println(res);
    }
    // 打印出str的所有子串
    public static int print(String str, int k) {
        int len = str.length();
        int res = 0;
        for (int i =0; i < len; i++) {
            int[] count = new int[2];
            for (int j =i; j < len; j++) {
                count[str.charAt(j) - '0']++;
                if (count[0] > k && count[1] > k)
                    break;
                res++;
            }
        }
        return res;
    }
}
