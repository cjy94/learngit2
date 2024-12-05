package com.chenjunyi.gaoji二.gaoji1;

import java.util.Random;

/**
 * 城市和首都的距离问题
 * 给定一个路径数组paths， 表示一张图，
 * paths[i]=j， 代表城市i连向城市j，如果paths[i]=i， 表示i城市是首都，一张图里只会有一个首都且图中除首都都指向自己之外不会有环
 * 计算距离首都距离为i的城市个数
 * 例如： paths=[9, 1, 4, 9, 0, 4, 8, 9, 0, 1]
 *      result=[1, 1, 3, 2, 3, 0, 0, 0, 0, 0]
 *     result的含义是： 距离首都0的城市有1座；
 *                    距离首都1的城市有1座；
 *                    距离首都2的城市有3座；
 *                    距离首都3的城市有2座；
 *                    距离首都4的城市有3座；
 *                    距离首都5的城市有0座；
 *                    距离首都6的城市有0座；
 *                    距离首都7的城市有0座；
 *                    距离首都8的城市有0座；
 *                    距离首都9的城市有0座；
 */
public class CityCapitalProblem {

    public static void distance(int[] paths) {
        // 1、将城市图数组，转换成城市到首都的距离数组
        graphToDistanceArray(paths);
        // 2、将城市首都的距离数组， 转换成题目要求的距离统计数组
        distanceToCountsArray(paths);

    }


    // [9, 1, 4, 9, 0, 4, 8, 9, 0, 1] -> [-2, 0, -4, -2, -3, -4, -4, -2, -3, -1]
    private static void graphToDistanceArray(int[] paths) {
        int cap = 0;
        for (int start = 0; start < paths.length; start++) {
            if (paths[start] == start) {
                cap = start;
            } else if (paths[start] > -1) {
                int curI = paths[start];
                paths[start] = -1;
                int preI = start;
                while (paths[curI] != curI) {
                    if (paths[curI] > -1) {
                        int nextI = paths[curI];
                        paths[curI] = preI;
                        preI = curI;
                        curI = nextI;
                    }  else {
                        break;
                    }
                }

                int value = paths[curI] == curI ? 0 : paths[curI];
                // preI != start
                while (preI != start) {
                    int lastPreI = paths[preI];
                    paths[preI] = --value;
                    preI = lastPreI;
                }
//                while (paths[preI] != -1) {
//                    int lastPreI = paths[preI];
//                    paths[preI] = --value;
//                    curI = preI;
//                    preI = lastPreI;
//                }
               paths[preI] = --value;
            }
        }
        paths[cap] = 0;  // 首都到首都的距离是0
    }

    private static void distanceToCountsArray(int[] paths) {
        for (int start = 0; start < paths.length; start++) {
            int distans = paths[start];
            if (distans < 0) {
                paths[start] = 0;
                while (true) {
                    if (paths[-distans] > -1) {
                        paths[-distans]++;
                        break;
                    } else {
                        int nextIndex = paths[-distans];
                        paths[-distans] = 1;
                        distans = nextIndex;
                    }
                }
            }
        }
        paths[0] = 1;

    }

    public static void main(String[] args) {
        int[] paths = {9,1,4,9,0,4,8,9,0,1};
        distance(paths);
        
//        int i = 2^16;
//        i = 2^128;
        for (int i =0; i < Integer.MAX_VALUE; i++) {
            short s = (short) i;
            if (s == 0) {
                System.out.println(i);
            }
        }

      //  System.out.println((short)i);

    }
}
