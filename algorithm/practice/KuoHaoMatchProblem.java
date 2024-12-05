package com.chenjunyi.practice;

import java.util.Stack;

/**
 * 括号匹配问题
 * 利用栈'{'、'['、'(' 压栈； 否则弹出
 * 最后栈是空的， 返回true; 否则返回false
 *
 */
public class KuoHaoMatchProblem {

    // 给定一个字符串，该字符串是由'[]''{}''()'组成的，判断该字符串中的括号是否匹配
    public static boolean isValid(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        if (str.length() == 1) {
            return false;
        }
        char[] s = str.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i =0; i < s.length; i++) {
            char ch = s[i];
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.add(ch == '(' ? ')' : (ch == '{' ? '}' : ']'));
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char first = stack.pop();
                if (first != ch) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
       
    }
}
