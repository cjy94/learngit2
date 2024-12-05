package com.chenjunyi.practice;

import java.util.HashMap;

/**
 * 把一个01字符串切成多个部分，要求每一个部分的0和1比例一样，同时要求尽可能多的划分
 * 比如： 0101010
 * 01 01 01 01 一种切法， 0和1比例为1:1
 * 0101 0101 一种切法，0和1比例1：1
 * 两种切法都符合要求，但是尽可能多的划分，部分数为1
 * 给定一个01字符串str, 假设长度为N，要求返回一个长度为N的数组ans
 * 输入： str = "010100001"
 * 输出: ans = [1,1,1,2,1,2,1,1,3]
 */
public class Ratio01Split {

    public static int[] split(String str) {
        if (str == "" || str.length() == 0) {
            return new int[0];
        }
        char[] arr = str.toCharArray();
        int n = arr.length;
        // 表示： 分子是k， 分母是i的有j个； 分母是x的有y个...
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
        int one = 0;
        int zero = 0;
        int[] ans = new int[n];
        for (int i =0; i < n; i++) {
            if (arr[i] == '1') {
                one++;
            } else {
                zero++;
            }
            if (zero == 0 || one == 0) {
                ans[i] = i+1;
            } else {
                int maxGcd = gcd(zero, one);
                int a = zero / maxGcd;
                int b = one / maxGcd;
                if (!map.containsKey(a)) {
                    map.put(a, new HashMap<>());
                }

                if (!map.get(a).containsKey(b)) {
                    map.get(a).put(b, 1);
                }  else {
                    map.get(a).put(b, map.get(a).get(b) + 1);
                }
                ans[i] = map.get(a).get(b);
            }
        }
        return ans;
    }

    // 辗转相除法 计算两个数的最大公约数
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }


    // for test
    public static void main(String[] args) {
        

    }

}
