package com.chenjunyi.project;

import java.util.Arrays;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  火星文计算
 *
 *  ascii 编码的顺序就是'$', '&', '*' 0-9 A-Z
 *
 *
 *
 */
public class HuoXingWenJisuan {

    public static void Sum() {

    }

    public static void main(String[] args) {
        char[] ch = {'$', '&', '*', '0', '9', 'A', 'Z'};
        Arrays.sort(ch);
        System.out.println(Arrays.toString(ch));
        ExecutorService executorService = Executors.newCachedThreadPool();
    }


    // 利用栈进行计算
    public static int compute(String s) {
        Stack<String> stack = new Stack<>();
        char[] ch = s.toCharArray();
        int cur = 0;
        for (int i =0; i < ch.length; i++) {
            char c = ch[i];
            if (c >= '0' && c <= '9') {
                cur = cur * 10 + (ch[i]-'0');
            } else if (c == '#') {
                stack.push(String.valueOf(cur));
                stack.push(String.valueOf(c));
                cur = 0;
            } else { // $
                int res = count(stack, cur);
                stack.push(String.valueOf(res));
                stack.push(String.valueOf(c));
                cur = 0;
            }

        }
        int res = cur;
        while (!stack.isEmpty()) {
            int r = count(stack, res);
            

        }
        return res;
        
    }


    public static int count(Stack<String> stack, int y) {
        String ope = stack.pop();
        int x = Integer.valueOf(stack.pop());
        if ("#".equals(ope)) {
            return  4 * x + 3 * y + 2;
        } else {
            return 2 * x + y + 3;
        }


    }
}
