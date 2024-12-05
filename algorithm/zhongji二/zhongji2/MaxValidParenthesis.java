package com.chenjunyi.zhongji二.zhongji2;

import java.util.Stack;

/**
 * 求最长的有效括号字串
 */
public class MaxValidParenthesis {
    public static int maxValidParenthesis (String str) {
        if (str == null || str.length() ==0) {
            return 0;
        }

        return dpWays(str);
        
    }

    private static int dpWays(String str) {
        char[] s = str.toCharArray();
        int[] dp = new int[s.length];
        int pre = 0;
        int res = 0;
        for (int i =1; i < s.length; i++) {
            if (s[i] == ')') {
                pre = i - dp[i-1] - 1;
                // 如果遇到了')'
                if (pre >= 0 && s[pre] == '(') {
                    dp[i] = dp[i-1] + 2 + (pre > 0 ? dp[pre-1] : 0);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;

    }

    private static void maxStack(Stack<Integer> stack) {
        Stack<Integer> help = new Stack<>();
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            while (!help.isEmpty() && cur > help.peek()) {
               stack.push(help.pop());
            }
            help.push(cur);
        }

        while (!help.isEmpty()) {
            stack.push(help.pop());
        }

    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(4);
        stack.push(3);
        stack.push(7);
        stack.push(5);
        stack.push(9);
        stack.push(1);
        stack.push(10);
        stack.push(-5);
        maxStack(stack);
        System.out.println(stack);
    }
}
