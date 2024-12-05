package com.chenjunyi.force;

/**
 * 牛牛准备参加学校组织的春游， 出发前牛牛准备往背包里装入一些零食，牛牛的背包容量为w，
 * 牛牛家里一共有N袋零食，第i 袋零食的体积为v[i]
 * 牛牛想知道在总体积步超过背包容量的情况下她一共有多少种零食方法（总体积为0，也是一种方法）
 */
public class NiuNiuBeiBao {
    public static int bags(int[] v, int w) {
        if (v == null || v.length == 0) {
            return 0;
        }
        return process(v, w, 0);

    }

    private static int process(int[] v, int bags, int index) {
        // 背包容量为0
        if (bags == 0) {
            return 1;
        }
        // 没有零食可以放
        if (index == v.length) {
            return 0;

        }
        // 有零食可放 并且 背包也有容量
        // 选择当前零食放入背包种 和 不选择当前零食放入背包种两种选择
        return process(v, bags-v[index], index+1) +
                process(v, bags, index+1);
    }

    // 0..i 位置上的数字，搞定j位置上的数字
    private static int dp(int[] v, int bags) {
        int n = v.length;
        int[][] dp = new int[n+1][bags+1];
        // 第一列都是1
        for (int i =0; i <= n; i++) {
            dp[i][0] = 1;
        }
        // 从左往右，从下往上求解
        for (int i = n-1; i >= 0; i--) {  // bags的容量
            for (int j =1; j <= bags; j++) {  // 每件零食的体积
                dp[i][j] = dp[i+1][j];
                if (j - v[i] >= 0) {
                    dp[i][j] += dp[i+1][j - v[i]];
                }
            }

        }
        return dp[0][bags];
    }




    public static void main(String[] args) {
        int[] arr = {3,2,5,2,3};
        System.out.println(bags(arr, 10));
        System.out.println(dp(arr, 10));
    }
}
