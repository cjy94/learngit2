package com.chenjunyi.practice;

import java.util.LinkedList;

/**
 * str="48*(70-65)-43+8*1"
 */
public class CountString {
    public static void main(String[] args) {

    }

    public static int getValue(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        return g(s.toCharArray(), 0)[0];

    }

    public static int[] g(char[] arr, int i) {
        int cur = 0;
        LinkedList<String> queue = new LinkedList<>();
        while (i < arr.length && arr[i] != ')') {
            if (arr[i] >= '0' && arr[i] <= '9') {  // 遇到数字
                cur = cur *10 + (arr[i++]-'0');
            } else if(arr[i] != '(') {   // 遇到加减乘除运算符
                addNum(queue, cur);   // 把当前数字加到容器中
                queue.addLast(String.valueOf(arr[i++]));    // 把当前符号也加到容器中
                cur = 0;
            } else { // 遇到左括号(
                int[] ans = g(arr, i+1);
                cur = ans[0];
                i = ans[1] + i;
            }
        }
        addNum(queue, cur);
        return new int[] {getNum(queue), i};

    }

    private static void addNum(LinkedList<String> queue, int num) {
        if (!queue.isEmpty()) {
            String first = queue.pollLast();
            if (first.equals("+") || first.equals("-")) {
                queue.addLast(first);
            } else {
                int num1 = Integer.valueOf(queue.pollLast());
                num = first.equals("*") ? (num1 * num) : (num1 / num);
            }
        }
        queue.addLast(String.valueOf(num));

    }

    // 容器中的运算符只有'+'、'-'两种
    private static int getNum(LinkedList<String> queue) {
        boolean add = true;
        int num = 0;
        int ans = 0;
        String cur = null;
        while (!queue.isEmpty()) {
            cur = queue.pollLast();
            if (cur.equals("+")) {
                add = true;
            }  else if(cur.equals("-")) {
                add = false;
            } else {
               num = Integer.valueOf(cur);
               ans += add ? num : (-num);
            }
        }
        return ans;
    }
}
