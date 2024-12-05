package com.chenjunyi.practice;

import java.util.Arrays;

/**
 * 小明手中有N块积木， 并且小明知道每块积木的重量，现在小明希望将这些积木堆起来
 * 要求：
 *  1） 上面的积木重量不能小于下面的积木重量
 *  2） 上面积木的重量减去下面积木的重量不能超过X
 *  3） 没对中最下面的积木没有重量要求
 *
 *  现在小明有一个机会，除了这N块积木，还可以获得K块任意重量的积木，
 *  小明希望将积木堆在一起，同时希望积木堆的数量越少越好。
 *
 *  输入描述：
 *  n,k,x  n <= 200000 , 0<=x, k <=100000000
 *  n是积木数量， x是魔法积木的数量  k是魔法积木的总重量
 *
 *  a/b 向上取整的表达式是： a+b-1/b
 *
 *
 *  将积木排序， 从小到达遍历数组，
 *
 * */
public class JiMuProblem {

    // arr[] 积木数组，
    // x: 差值
    // k: 魔法积木的数量，每一块魔法积木变成任意重量
    public static int minSplit(int[] arr, int x, int k) {
        Arrays.sort(arr);

        int n = arr.length;
        int[] needs = new int[n];
        int size = 0;
        int splits = 0;  // 堆的数量
        for (int i =1; i < n; i++) {
            if (arr[i] - arr[i-1] > x) {
                splits++;
                needs[size++] = arr[i]-arr[i-1];
            }
        }

        if (splits == 1 || x == 0) {
            return splits;
        }
        Arrays.sort(needs, 0, size);
        // (差值-x)/x 向上取整
        // (needs[i] - x) / x 向上取整
        for (int i =0; i < needs.length; i++) {
            int counts = (needs[i] - 1) / x;
            if (k >= counts) {
                k -= counts;
                splits--;
            }   else {
                break;
            }
        }
        return splits;
    }
}
