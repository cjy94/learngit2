package com.chenjunyi.DynamicProgramming.背包问题;

/**
 *  01 背包(模板)
 *  给定一个整数t，表示背包的容量
 *  有m个货物，每个货物可以选择1次
 *  每个货物都有自己的体积costs[i]和价值values[i]
 *  返回在不超过总容量的情况下，怎么挑选货物达到最大价值
 *
 *
 *  dp[i][j]: 前i个货物中自由选择，在总容量不超过j的条件下，能获得的最大价值是多少
 *
 *  dp[i][j] = Math.max(dp[i-1][j], dp[i -1][j-costs[i]] + values[i]);
 *
 *
 */

public class Backpack {


    // t: 背包总重量
    public static int maxValue(int[] costs, int[] values, int t) {
        int m = costs.length;   // 货物数量
        int[][] dp = new int[m+1][t+1]; // 表示前m个货物自由选择，在总重量不超过t的情况下的最大价值
        /* 第一行的数值
            dp[0][t]: 在没有货物可选择的情况下，总重量不超过0...t的最大价值是0，默认值就是0，可以不进行填写
        */

        for (int i = 1; i <= m; i++) {
            for (int j = 0; j <= t; j++) {
                dp[i][j] = dp[i-1][j];
                if (j - costs[i] >= 0)
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-costs[i]] + values[i]);
            }
        }

        return dp[m][t];
    }


    // 空间压缩的dp
    public static int maxValue1(int[] costs, int[] values, int t) {
        int n = costs.length;
        int[] dp = new int[t+1];
        // 第0行: dp[0,0,0,0...0]
        for (int i = 1; i <= n; i++) {  // 遍历每一个货物
            for (int j = t; j >= costs[i]; j--) {     // 每一个货物在任何一种重量下的最大价值, 原始dp表中dp[i][j]依赖dp[i-1][j]，所以从后往前填写
                dp[j] = Math.max(dp[j], dp[j-costs[i]] + values[i]);
            }
        }
        return dp[t];

    }
}
