package com.chenjunyi.day09_DP;

/**
 * 机器人问题
 *
 */
public class Code01_RobbitProblem {

    /**
     *  机器人从[1...N] 范围中的S位置开始走，走K步到达E的方法有哪些？
     *  当到达N[0]时只能往N[1]走，当到达N[N-1]时只能往N[N-2]走
     * @param N   一些位置信息
     * @param S    规定开始的起始位置
     * @param E   规定最后到达的终止位置
     * @param K   规定走的步数限制
     * @return
     */
    private static int process1(int N, int S, int E, int K) {
        return f1(N, S, E, K);
    }

    // 暴力尝试的方式
    private static int f1(int N, int E, int S, int k) {
        if (k==0) {
            return S==E ? 1 : 0;
        }
        // 如果到达1位置，只能往2位置走
        if (S==1){
            f1(N,E, 2,k-1);
        }
        // 如果到达最后位置，只能往前一个位置走
        if (S==N) {
            f1(N,E,N-1,k-1);
        }
        // 正常一个中间位置的话，右往左走和往右走2种
        return f1(N,E,S-1,k-1) + f1(N,E,S+1,k-1);

    }


    private static int process2(int N, int S, int E, int K) {
        // 其中E和K是可变参数，所以建立一个二维表
        int[][] dp = new int[N+1][K+1];
        for (int i =0; i<=N; i++) {
            for (int j=0; j <=K; j++) {
                dp[i][j] = -1;
            }
        }

        return f2(N, S, E, K, dp);
    }
    // 添加缓存的方式-----记忆搜索
    private static int f2(int N, int E, int S, int K, int[][] dp) {
        // 先查表
        if (dp[S][K] != -1) {
            return dp[S][K];
        }
        if (K==0) {
            dp[S][K] =  S==E ? 1 : 0;
            return dp[S][K];
        }
        // 如果到达1位置，只能往2位置走
        if (S==1){
            dp[S][K] = f2(N,E, 2,K-1, dp);
           return dp[S][K];
        }
        // 如果到达最后位置，只能往前一个位置走
        if (S==N) {
            dp[S][K] = f2(N,E,N-1,K-1, dp);
            return dp[S][K];
        }
        // 正常一个中间位置的话，右往左走和往右走2种
        dp[S][K] = f2(N,E,S-1,K-1, dp) + f2(N,E,S+1,K-1, dp);
        return dp[S][K];
    }
}
