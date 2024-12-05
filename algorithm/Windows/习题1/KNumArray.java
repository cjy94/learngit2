package com.chenjunyi.Windows.习题1;

/**
 *  k个不同整数的子数组
 *  给定一个正整数数组nums 和 一个整数k, 返回nums中[好子数组]的数目
 *  如果nums的某个子数组中不同整数的个数恰好为k，
 *  则称nums的这个连续、不一定不同的子数组为[好子数组]
 *  例如： [1,2,3,1,2]中有3个不同的整数： 1,2以及3
 *  子数组是数组的连续部分
 */
public class KNumArray {

    public static void main(String[] args) {
        System.out.println(Long.toBinaryString(~0L));
        System.out.println(Long.toBinaryString((~0L << 63)));
        System.out.println((~0L << 63) >>> 63);

        print(~0L);
        print((~0L << 63));
        print((~0L << 63) >>> 63);
    }

    public static void print(long num) {
        StringBuilder str = new StringBuilder();
        for (int i =63; i >= 0; i--) {
            str.append((num & (1L << i)) == 0 ? "0" : "1");
        }
        System.out.println(str.toString());
    }



}
