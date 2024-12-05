package com.chenjunyi.zhongji一.zhongji1;

public class ChiCaoProblem {

    public static String winner(int n) {
        if (n < 5) {
            return (n == 0 || n==2) ? "后手" : "先手";
        }
        int base = 1;
        while (base <= n) {
            if (winner(n-base).equals("后手")) {
                return "先手";
            }
            if (n / 4 < base) {
                break;
            }
            base *= 4;
        }
        return "后手";
    }
}
