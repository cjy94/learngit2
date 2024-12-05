package com.chenjunyi.practice;

import java.util.Stack;

/**
 * 无效括号串， 变成有效括号串
 * i:检查的位置   j:可能删除的位置
 */
public class KuoHaoProblem {


    // 括号匹配问题 , 使用栈
    //  可以将栈用数组替换掉
    public static boolean match(String str) {
        int n = str.length();
        char[] stack = new char[n];
        int size = 0;
        char[] arr = str.toCharArray();
        for (int i =0; i < arr.length; i++) {
            char ch = arr[i];
            if (ch == '(' || ch == '[' || ch == '{') {
                 stack[size++] = (ch == '(' ? ')' : (ch == '[' ? ']' : '}'));

            } else {
                if (size == 0) {
                    return false;
                }
                char last = stack[--size];
                if (last != ch)
                    return false;
            }
        }
        return size == 0;
    }
}
