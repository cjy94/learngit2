package com.chenjunyi.practice;

import java.util.Arrays;

/**
 *  给定一个正数数组arr，代表若干人的体重
 *  再给定一个正数limit，表示所有船共同拥有的载重量
 *  每艘船最多坐两人，且不能超过载重
 *  想让所有的人同时过河，并且用最好的分配方法让船尽量少，返回最少的船数
 *
 *  左程云代码： https://github.com/algorithmzuo/coding-for-great-offer/tree/main/src/class36
 */
public class MinBoat {

    public static int maxPeople(int[] arr, int limit) {
        Arrays.sort(arr);
        int L = 0;
        for (int i =0; i < arr.length; i++) {
            if (arr[i] > limit) {
                return -1;
            }
            if (arr[i] <= limit/2) {
                L = Math.max(L, i);
            }
        }
        int R = L+1;

        return 0;
        



    }
}
