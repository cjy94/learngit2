package com.chenjunyi.zhongji二.zhongji4;

public class GuaiShouProblem {


    public static void main(String[] args) {
        int[] hp = {3,9,5,15};
        int[] money = {1,4,1,5};

        long l1 = func1(hp, money);
        long l = func2(hp, money);
        System.out.println(l1);
        System.out.println(l);
    }

    // 方法一： 行是怪兽编号， 列是武力值
    public static long func1(int[] d, int[] m) {

        return process(d, m, 0, 0);
    }

    // i: 当前怪兽的编号
    // cur: 当前所具有的能力
    // 该函数的含义是： 来到第i号怪兽， 当前的武力值是cur， 想要顺利通过i号后面所有的怪兽，所需的最少的钱是多少
    private static long process(int[] d, int[] m, int i, int cur) {
        if (i == d.length) {
            return 0;
        }

        if (cur < d[i]) {
            return m[i] + process(d, m, i+1, cur+d[i]);
        } else {
            return Math.min(
                    m[i] + process(d, m, i+1, cur+d[i]),
                    process(d, m, i+1, cur)
            );
        }
    }

    // 方法二： dp[i][j]， 根据数据状况， 如果怪兽的武力值很大，但是花费的钱较小，那么就用钱做列
    // dp[i][j]: 表示通过[0..i]个怪兽， 严格花m[j]元的前提下，所能拥有的最大能力值是多少
    // 如果严格花money钱 无法顺利通过[0..i]号怪兽， 那么dp[i]][j]是-1
    public static long func2(int[] d, int[] m) {
        // 获取m中的最大值
        int sum = m[0];
        for (int i =1; i < m.length; i++) {
            sum += m[i];
        }
        int n = m.length;

        // dp[i][j]含义：
        // 能经过0~i号怪兽，且花钱为j（严格花j钱）时的武力值最大时多少
        // 如果dp[i][j]==-1， 表示经过0~i的怪兽，花钱为j是无法通过的，
        int[][] dp = new int[n][sum+1];
        // 默认值修改成-1
        for (int i =0; i < n; i++) {
            for (int j = 0; j <= sum; j++) {
                dp[i][j] = -1;
            }
        }

        // 填写第一行
//        for (int i =1; i <= sum; i++) {
//            dp[0][i] = i >= m[0] ? d[0] : -1;
//        }
        dp[0][m[0]] = d[0];

        // 填写普通位置
        for (int i =1; i < n; i++) {
            for (int j =1; j<= sum; j++) {
                // 可能情况1： 不为当前怪兽花钱
                if (dp[i-1][j] >= d[i]) {
                    dp[i][j] = dp[i-1][j];
                }
                // 可能情况2： 为当前怪兽花钱，但是是严格花j钱
                if (j-m[i] >= 0 && dp[i-1][j-m[i]] != -1) {
                    // 两种情况中选择最大的
                    dp[i][j] = Math.max(dp[i][j], d[i]+dp[i-1][j-m[i]]);
                }
            }
        }
        int res = 0;
        // dp最后一行中第一个不是-1的各自所对应的列数据就是最少的钱
        for (int i =0; i<= sum; i++) {
            if (dp[n-1][i] != -1) {
                res = i;
                break;
            }
        }
        return res;

    }



}
