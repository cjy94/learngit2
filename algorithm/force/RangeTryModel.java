package com.chenjunyi.force;

import java.util.ArrayList;

/**
 * 范围上的尝试模型
 *
 * 问题：
 * 假设有拍成一行的N个位置， 记为1-N
 * 开始是机器人在其中M的位置上
 * 如果机器人来到1的位置，那么下一步只能往右走2的位置
 * 如果机器人来到N位置，那么下一步只能往左走来到N-1位置
 * 如果机器人来到中间位置，那么下一步可以往左走也可以往右走
 * 规定机器人必须走K步，最终能来到p位置的 的方法有多少种
 * 给定4个参数n,m,k,p 返回方法数
 */
public class RangeTryModel {
    public static int way1(int n, int m, int p, int k) {
        return process1(n,m,p,k);
    }

    // 机器人 从m位置，经过k步 最终走到p位置的方法数
    //  在还有rest步可走的情况下，从m->p 位置的方法
    private static int process1(int n, int m, int p, int rest) {
        // 没有步数可以走了
        if (rest == 0) {
            return m==p ? 1 : 0;
        }
        // 还有步可以走

        // 如果来到了1位置，那么只有一种选择
        if (m==1) {
            return process1(n,m+1,p,rest-1);

        }
        // 如果来到了最后的位置，也只有一种选择
        if (m==n) {
            return process1(n,m-1, p, rest-1);
        }

        // 在中间位置有两种选择
        return process1(n,m-1, p, rest-1) + process1(n, m+1, p, rest-1);

    }


    // 将所有cur 和 rest的结果放到缓存中
    //
    public static int way2(int n, int m, int p, int k) {
        int[][] dp = new int[n+1][k+1];
        // 先设置这张表
        for (int i =0; i <= n; i++) {
            for (int j = 0; j<= k; j++) {
               dp[i][j] = -1;
            }
        }

        return process2(n,m,p,k, dp);
    }

    // 每一次暴力递归的过程中， 都加上缓存
    // 记忆化搜索， 也是粗糙的动态规划， 
    private static int process2(int n, int m, int p, int rest, int[][] dp) {
        if (dp[m][rest] != -1) {
            return dp[m][rest];
        }
        if (rest == 0) {
            dp[m][rest] = m==p ? 1 : 0;
            return dp[m][rest];
        }
        if (m==1) {
            dp[m][rest] = process2(n,m+1,p,rest-1, dp);
            return dp[m][rest];

        }
        if (m==n) {
            dp[m][rest] = process2(n,m-1, p, rest-1, dp);
            return dp[m][rest];
        }
        dp[m][rest] = process2(n,m-1, p, rest-1, dp) + process2(n, m+1, p, rest-1, dp);
        return dp[m][rest];

    }



    

    public static void main(String[] args) {
        System.out.println(way1(7, 2, 3, 5));
        System.out.println(way2(7, 2, 3, 5));

    }
}
