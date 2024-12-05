package com.chenjunyi.project;

/**
 * 最小字符串：
 *  给定一个字符串 s，最多只能进行一次变换，返回变换后能得到的最小字符串（按照字典序进行比较）。
 *  变换规则：交换字符串中任意两个不同位置的字符。
 *
 *  枚举每一个索引位置，index确定后，[index+1...n]之间的字符，找到比index小的，进行交换，因为只允许交换一次，每一个索引位置找到比当前字符小的就结束本次循环
 *
 */
public class MinmumString {
    public static void main(String[] args) {
        String str = "acbdef";
        String temp = minString(str);
        System.out.println(temp);
    }

    public static String minString(String s) {
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            int tempIndex = i;
            char temp = str[tempIndex];
            for (int j = i; j < str.length; j++) {
                if (str[j] < temp) {
                    tempIndex = j;
                    temp = str[tempIndex];
                }
            }
            if (tempIndex != i) {
                str[tempIndex] = str[i];
                str[i] = temp;
                break;
            }
        }
        return String.valueOf(str);
    }
    private static void swap(char[] ch, int i, int j) {
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
    }
}
