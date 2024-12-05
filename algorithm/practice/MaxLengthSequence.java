package com.chenjunyi.practice;

import java.util.Stack;

/**
 *  给定一个字符串str，和一个正数k
 *  返回长度为k的所有子序列中，字典序最大的子序列
 *
 *  子序列可以不连续
 *
 *  比如：str="gfedcba"  k=4
 *  单调栈：栈底到栈顶的字符要求是从大到小的 ,
 *  1) index会遍历到str的结尾位置，栈中数据的数量大于等于k; 比如： gfedcba k=4
 *  2) 栈中数据-1 + index后面所有字符数量不足k个
 */
public class MaxLengthSequence {

    public static String maxLength(String s, int k) {
        if (k <= 0 || s.length() <= k) {
            return "";
        }
        char[] arr = s.toCharArray();
        int n = arr.length;
        char[] stack = new char[arr.length];
        int size = 0;
        for (int i =0; i < arr.length; i++) {
            // 如果栈中有元素， 并且栈顶元素的字典序小于arr[i] 并且 栈中数据大小加上(n-index)的数量 > k； 则将栈中的元素弹出，
            // 为了满足栈中的元素从栈底到栈顶是从大到小的
            while (size > 0 && stack[size] < arr[i] && size + n - i > k) {
                size--;
            }
            // 情况2)
            if (size + n - i == k) {
                return String.valueOf(stack, 0, size) + s.substring(i);
            }
            stack[size++] = arr[i];
        }
        // 情况1)
        return String.valueOf(stack, 0, k);
    }
}
