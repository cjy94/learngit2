package com.chenjunyi.practice;

/**
 * 最长递增子序列问题
 */

public class LargestSubSequence {
    public static void main(String[] args) {
        int n = 64;
        boolean[] f = new boolean[64];
        int count = 64 / 2;
        for (int i =3; i * i < n; i+= 2) {
            if (f[i]) {
                continue;
            }
            for (int j = i*i; j < n; j+= 2*i) {
                if (!f[j]) {
                    --count;
                    f[j] = true;
                }
            }
        }
        System.out.println(count);
    }

}
