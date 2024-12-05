package com.chenjunyi.zhongji一.zhongji6;

import java.util.TreeMap;

/**
 * 牛牛准备参加学校组织的春游，出发前牛牛准备往背包中装入一些零食，牛牛的背包容量为w, 
 * 牛牛家里一共有n 袋零食，第i袋零食的体积为v[i]
 * 牛牛向知道再体积不超过背包容龄的情况下，它一共有多少种零食方法
 */
public class BeiBao {
    // v为每袋零食的体积， w为背包的重量
    public int method(int[] v, int w) {
        if (w < 0 || v.length <= 0) {
            return 0;
        }

        int[][] dp = new int[v.length][w+1];
        // 第一行第一列的值 填好
        for (int i =0;i < v.length; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0;i <= w; i++) {
            dp[0][i] = v[0] == dp[0][i] ? 1 : 0;
        }

        // 普通位置
        // 使用当前位置的零食，dp[i][j]
        // dp[i][j]: 自由使用0-i位置的零食，严格等于j重量的方法数
        for (int i=1; i <dp.length; i++) {
            for (int j=1; j< dp[0].length; j++) {
                int p1 = dp[i-1][j];
                int p2 = 0;
                if(j-v[i] >= 0) {
                    p2 = dp[i-1][j-v[i]];
                }
                dp[i][j] =p1 + p2;
            }

        }
        return 0;
    }

    public static void main(String[] args) {
//        System.out.println(Integer.MIN_VALUE % 10);
//        System.out.println(Integer.MIN_VALUE / 10);

       
    }

}
