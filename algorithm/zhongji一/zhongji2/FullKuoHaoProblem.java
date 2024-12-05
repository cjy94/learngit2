package com.chenjunyi.zhongji一.zhongji2;

import java.util.Stack;

/**
 * 完整括号问题
 * 从左到右遍历字符， 使用count记录， (count++, )count--
 */
public class FullKuoHaoProblem {

    // 使用一个count变量记录，(和)出现的次数
    public static boolean isFull1(String str) {
        char[] s = str.toCharArray();
        int count = 0;
        for (int i =0; i < s.length; i++) {
            if (s[i] == '(') {
                count++;
            } else {
                count--;
            }
        }
        if (count != 0) {
            return false;
        }  else {
            return true;
        }

    }

    public static int isFull2(String str) {
        char[] s = str.toCharArray();
        int count = 0;
        for (int i =0; i < s.length; i++) {
            if (s[i] == '(') {
                count++;
            } else {
                count--;
            }
        }
//        if (count != 0) {
//            return false;
//        }  else {
//            return true;
//        }
        return Math.abs(count);

    }

    public static int isFull3(String str) {
        char[] s = str.toCharArray();
        int count = 0;
        int res = count;
        for (int i =0; i < s.length; i++) {
            if (s[i] == '(') {
                count++;
            } else {
                count--;
            }
            res = Math.max(count, res);
        }
//        if (count != 0) {
//            return false;
//        }  else {
//            return true;
//        }
        return res;

    }

    // 保证str全部都是括号字符，没有其他字符
    public static boolean isFull(String str) {
        char[] s = str.toCharArray();
        if (s[0] == ')') {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        stack.push(s[0]);
        int index = 1;

        while (index < s.length) {
            if (s[index] == '(') {
                stack.push(s[index]);
            }  else {   // ')' 括号， 需要栈中有元素并且栈顶元素是'('
                if (!stack.isEmpty()) {
                    Character pop = stack.pop();
                    if (pop == ')') {
                        return false;
                    }
                } else {
                    return false;
                }
            }
            index++;
        }
        if (stack.isEmpty()) {
            return true;
        }  else {
            return false;
        }

        

    }

    public static void main(String[] args) {
        String str = "()()()";
//        System.out.println(isFull(str));
//        System.out.println(isFull2(str));
        System.out.println(isFull3(str));
    }
}
