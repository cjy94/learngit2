package com.chenjunyi.Stack;

import java.util.Stack;

/**
 * 利用栈检测，一组表达式中，括号是否合法
 * 比如： ()(()(()))() 合法的；  ()(()()() 不合法的
 *
 */
public class ParenthesisMatching {

    // 时间复杂度O(n), 空间复杂度O(n)
    public static boolean islegal(String s) {
        // 遇到左括号就压入栈，遇到右括号就将栈顶的(弹出，
        char[] ch = s.toCharArray();
        if (ch[0] == ')') {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        stack.push(ch[0]);
        for (int i =1; i < ch.length; i++) {
            char c = ch[i];
            if (c == '(') {
                stack.push(c);
            }else {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(islegal("()(()()()"));
    }
}
