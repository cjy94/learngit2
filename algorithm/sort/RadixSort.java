package com.chenjunyi.sort;

import java.util.Random;

/**
 * 基数排序， 又称桐子法（bin sort）
 *  通过键值的各个位的值，将要排序的元素分配至某些“桶”中，达到排序的作用， 稳定排序
 *  基数排序是效率高的稳定排序
 *  将正数按照位数切割成不同的数字，然后按照每个位数分别比较
 */
public class RadixSort {
    public static void main(String[] args) {
        for (int i =0; i < 10; i++) {
            int pivot = new Random().nextInt(5) + 0;
            System.out.println(pivot);
        }

    }

    public void sort(int[] arr) {

    }
}
