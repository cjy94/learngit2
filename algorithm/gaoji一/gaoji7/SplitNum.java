package com.chenjunyi.gaoji一.gaoji7;

public class SplitNum {

    public static int ways1(int num) {
        return process(1, num);
    }

    // pre 裂开的前一部分
    // rest 还剩多少值，需要去裂开，要求裂开出来的第一部分，不要比pre小
    // 返回裂开的方法数
    public static int process(int pre, int rest) {
        // base case
        if (rest == 0) {
            return 1;  // 之前的裂开方案是有效的， 构成了1种方法
        }
        if (pre > rest) {
            return 0;
        }

        int ways = 0;
        for (int i = pre; i <= rest; i++) {   // i: rest第一个裂开的部分，   
            ways += process(i, rest - i);
        }
        return ways;
    }

    // 暴力递归 到动态规划的版本
    public static int dpWays1(int num) {
        if (num < 1) {
            return 0;
        }
        int[][] dp = new int[num+1][num+1];
        // 第一行的数据是无效的
        // 第一列都是1
        for (int rest = 1; rest <= num; rest++) {
            dp[rest][0] = 1;
        }
        // 填写对角线
        for (int rest = 1; rest <= num; rest++) {
            dp[rest][rest] = 1;
        }

        // 普遍位置
        for (int pre = num-1; pre> 0 ;pre--) {
            for (int rest =pre; rest <= num; rest++) {
                // 斜率优化 , for循环存在枚举行为
                dp[pre][rest] = dp[pre][rest-pre]+ dp[pre + 1][rest];
            }
        }
        return dp[1][num];
    }


    public static int dpWays(int num) {
        if (num < 1) {
            return 0;
        }
        int[][] dp = new int[num+1][num+1];
        for (int pre = 1; pre < dp.length; pre++) {
            dp[pre][0] = 1;
        }
        for (int pre = num; pre > 0; pre--) {
            for (int rest = pre; rest <= num; rest++) {

                // 有枚举行为
                for (int i = pre; i <= rest; i++) {
                    dp[pre][rest] += dp[i] [rest - i];
                }
            }
        }
        return dp[1][num];

    }

    public static void main(String[] args) {
        System.out.println(ways1( 8));
        System.out.println(dpWays( 8));

    }
}
