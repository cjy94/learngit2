package com.chenjunyi.practice;

/**
 * 一个数n， 能够被拆分成几个完全平方数
 * 你如： 10 -> 4 4 1 1
 *
 * n 最差由n个平方数搞出来， 1+1+1+1...
 */
public class PerfectSquares {

    // 暴力方式， 从2*2 开始枚举
    public static int squares(int n) {
        int rest = n, num = 2;
        while (num * num < n) {
            int a = n / (num * num), b = n % (num * num);
            rest = Math.min(rest, a + squares(b));
            num++;
        }
        return rest;
    }

    public static void main(String[] args) {
        System.out.println(squares(10));
    }
}
