package com.chenjunyi.practice;

import java.util.Arrays;
import java.util.HashMap;

/**
 *  把一个01字符串切成多个部分，要求每一部分的0和1比例一样， 同时要求尽可能多的划分
 *  比如： 01010101
 *    01 01 01 01 这是一种切法， 0和1比例为 1:1
 *    0101 0101 也是一种切法， 0和1比例为 1:1
 *    两种切法都符合要求，那么尽可能多的划分为第一种方法， 部分数是4
 *    比如： 00001111
 *    只有一种切法是全体 00001111， 部分数是1
 *    给定一个01字符串str, 假设长度为N, 要求返回一个长度为N的数组ans
 *    输入str="010100001"
 *    ans=[1,1,1,2,1,2,1,1,3]
 */
public class Ratio0Split {

    public static void main(String[] args) {
//        int[] arr = {0,1,0,1,0,0,0,0,1};
//        int[] res = split(arr);
//        System.out.println(Arrays.toString(res));

        System.out.println(1%3);
    }

    public static int[] split(int[] arr) {
        // key： 分子， value: 分母：个数
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
        int zero = 0;  // 0的个数
        int one = 0;   // 1的个数
        int[] ans = new int[arr.length];

        for (int i =0; i < arr.length; i++) {
            if (arr[i] == 0) {
                zero++;
            } else {
                one++;
            }

            // 如果arr[0...i-1]之间的数全是0或者全是1， 那么分割数量就是i+1
            // 00000  切割结果： 0 0 0 0 0 5个
            if (one==0 || zero == 0) {
                ans[i] = i+1;
            }  else {  // arr[0...i-1] 有0有1的情况
                int gcd = gcd(zero, one);
                int a = zero / gcd;
                int b = one / gcd;
                if (!map.containsKey(a)) {
                    map.put(a, new HashMap<>());
                }

                if (!map.get(a).containsKey(b)) {
                    map.get(a).put(b,1);
                } else {
                    map.get(a).put(b, map.get(a).get(b)+1);
                }
                ans[i] = map.get(a).get(b);
            }
        }
        return ans;


    }

    // 辗转相除法求， 两个数的最大公约数
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);

    }
}
