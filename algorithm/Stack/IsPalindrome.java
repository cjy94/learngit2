package com.chenjunyi.Stack;

import java.util.Stack;

/**
 * 给定一个由多个a字符和b字符组成的字符串，字符串中有一个特殊字符X，X位于串的正中间，如何判定该字符串是否时回文
 */
public class IsPalindrome {
    public static boolean isPalindrome(String s) {
        char[] ch = s.toCharArray();
        int l = 0;
        int r = ch.length-1;
        while (l < r && ch[l] == ch[r]) {
            l++;
            r--;

        }
        if (l < r) {
            return false;
        } else {
            return true;
        }
    }


    // 链表节点的话，利用栈判断
    public static boolean isPalindrome2(String s) {
        char[] ch = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        int i = 0;
        while (ch[i] != 'X') {
            stack.push(ch[i]) ;
            i++;
        }
        for (; i < ch.length; i++) {
            if (stack.isEmpty()) {
                return false;
            }
            if(ch[i] != stack.pop()) {
                return false;
            }
        }
        return true;
    }
}
