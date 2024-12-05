package com.chenjunyi.force;

/**
 * 给定一个非负整数n, 代表二叉树的节点个数。 返回能形成多少种不同的二叉树结构
 */

public class TreeNums {

    public static int process(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2 ) {
            return 2;
        }
         int res = 0;
        // 遍历每一种左子树节点个数的情况
        for (int leftNum = 0; leftNum < n; leftNum++) {
            int leftWays = process(leftNum);
            int rightWays = process(n - leftNum -1);
            res =  leftWays * rightWays;
            
        }
        return res;
    }

    public static int dpWays(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i =1; i < n+1; i++) {
            for (int j =0; j<= i-1; j++) {
                dp[i] += dp[j] * dp[i-j-1];
            }
        }
        return dp[n];
    }

}
