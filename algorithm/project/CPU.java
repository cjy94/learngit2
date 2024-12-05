package com.chenjunyi.project;

import java.util.HashSet;

/**
 *  CPU 算力问题
 *  将A和B数组进行排序，
 *  计算sumA 和sumB
 *  要交换的差值是(sumA-sumB)/2
 *  从A开始遍历，差值 - A[i]=B[i] 就返回结果
 *
 */
public class CPU {

    public static void compute(int[] a, int[] b) {
        int sumA = 0, sumB = 0;
        HashSet<Integer> arrA = new HashSet<>();
        HashSet<Integer> arrB = new HashSet<>();
        for (int v : a) {
            sumA += v;
            arrA.add(v);
        }
        for (int v : b) {
            sumB += v;
            arrB.add(v);
        }

        int diff = (sumA - sumB) / 2;
        int resA = 0;
        int resB = 0;
        for (Integer v : arrA) {
            if (arrB.contains(v - diff)) {
                resA = v;
                resB = v - diff;
            }
        }

        System.out.println(resA + " " + resB);
    }
}
