package com.chenjunyi.DynamicProgramming.习题1;

/**
 一条包含字母A-Z 的消息通过以下映射进行了编码
 A->1 B->2 ... Z->26
 要解码消息，所有数字必须基于上述映射的方法，反向映射回字母（可能有多种方法）
 例如： 11106 可以映射成AAJF | KJF

 给你一个只含数字的非空字符串s, 请计算并返回解法的方法数

 */
public class Decode {

    public int decoding(String str) {
        if (str == null || str.length() == 0)
            return 0;

        return func(str.toCharArray(), 0);
    }

    // s[index...] 开始解码，有多少种有效的解码方法
    // 字符0不能单独使用，0必须和前一位一起使用
    private int func(char[] s, int index) {
        // index到数组的最后 说明是一种解码方法，故返回1
        if (index == s.length)
            return 1;
        // 第一块的可能性，无法转、一个字符转、二个字符转
        int ans = 0;
        if (s[index] == '0') {
            ans = 0;
        } else {
            ans = func(s, index + 1);   // 一个字符进行解码的情况
            if (index +1 < s.length && (s[index] - '0') * 10 + (s[index+1] - '0') <= 26) {
                ans += func(s, index+2);                   // 2个字符进行解码的情况
            }
        }
        return ans;

    }


    /**
     进阶题目：
     
     */



}
