package com.chenjunyi.gaoji一.gaoji5;

import java.util.LinkedList;

/**
 *  递归计算表达式
 *  
 */
public class Expression {
    public static int[] value(String str, int index) {
        int res[] = null;
        char[] s = str.toCharArray();
        int num = 0;
        LinkedList<String> queue = new LinkedList<>();
        // 只要没有到达表达式的末尾，或者没有遇到'('就计算取下一个字符
        while (index < s.length && s[index] != ')') {
            // 如果遇到数字
            if (Character.isDigit(s[index])) {
                num = num*10 + (s[index++]-'0');
            } else if(s[index] != '(') {
                // 如果字符不是'('也不是数字，是运算符"+、-、*、/"
                addNum(queue, num);
                queue.addLast(String.valueOf(s[index++]));
                num = 0;
            } else {
                // 遇到了'('
                res = value(str, index+1);
                num = res[0];
                index = res[1] + 1;
            }
        }
        addNum(queue, num);
        return new int[]{getNum(queue), index};
    }

    // 将num压入栈中，在压入之前如果栈顶元素是"*、/" 则需要将栈顶元素弹出，和当前元素计算后在压入栈定
    private static void addNum(LinkedList<String> queue, int num) {
        if (!queue.isEmpty()) {
            String top = queue.pollLast();
            if (top.equals("*") || top.equals("/")) {
                int cur = Integer.valueOf(queue.pollLast());
                num = top.equals("*") ? (cur * num) : (cur / num);
            } else {
                queue.addLast(top);
            }
        }
        queue.addLast(String.valueOf(num));
    }

    // 栈中的元素只有数字和"+、-" 3种类型的字符
    private static int getNum(LinkedList<String> queue) {
        int res = 0;
        boolean add = true;
        int num = 0;
        String cur = null;
        while (!queue.isEmpty()) {
            cur = queue.pollFirst();
            if (cur.equals("+")) {
                add = true;
            } else if (cur.equals("-")) {
                add = false;
            } else {
                num = Integer.valueOf(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }


    public static int getSum(String str) {
        int[] value = value(str, 0);
        return value[0];
    }

    public static void main(String[] args) {
        String str = "48*((70-65)-43)+8*1";
        int value = getSum(str);
        System.out.println(value);
    }
}
