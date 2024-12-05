package com.chenjunyi.practice;

/**
 *    houses[]和hearts[]  地点数组和供暖数组
 *    所有供暖站开多大的供暖半径 ，能供暖所有的地点， 要求供暖半径尽量小
 *
 *    将houses[]和hearts[] 排序， 从小到大
 */
public class GongNuanProblem {

    public static int findRadius(int[] houses, int[] hearts) {
        int ans = 0;
        for (int i =0, j =0; i < houses.length; i++) {
            while(!best(houses, hearts, i, j)) {
                j++;
            }
            ans = Math.max(ans, Math.abs(hearts[i] - hearts[j]));
        }
        return ans;
    }

    private static boolean best(int[] houses, int[] hearts, int i, int j) {
        return j == hearts.length -1  || Math.abs(houses[i]-hearts[j]) < Math.abs(houses[i] - hearts[j+1]);
    }
}
