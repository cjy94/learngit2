package com.chenjunyi.DynamicProgramming.习题1;

/*
在接下来的一年时间里，你要旅行的日子将以一个名为days的数组给出，
每一项是一个1到365的整数，
火车票有三种销售方式：
一张 为期1天 的通行证 售价为costs[0]元
一张 为期7天 的通行证 售价为costs[1]元
一张 为期30天 的通行证 售价为costs[2]元
通行证允许数天无限制的旅行

例如，如果我们在第2天获得一张为期7天的通行证，那么我们可以连着旅行7天(第2~8天)
返回 尼希昂要完成在给定的列表days中列出的每一天的旅行所需要的最小花费

使用动态规划求解

 */
public class MinimumCostForTravel {

    // days和costs都是固定的
    // i 是已经选择了
    static int[] durations = {};
    static int minCost(int[] days, int[] costs, int i) {
        // 已经没有旅行天数了， 不需要再花费
        if (i == days.length)
            return 0;

        //枚举每一种火车票的最低花费情况
        int ans = Integer.MAX_VALUE;
        for (int k = 0, j = i; k < costs.length; k++) {
            while (j < days.length && days[i] + durations[k] > days[j]) {
                j++;
            }
            ans = Math.min(ans, costs[k] + minCost(days, costs, j));
        }
        return ans;
    }

    // 记忆化缓存法
    static int minCost2(int[] days, int[] costs, int[] dp, int i) {
        // 已经没有旅行天数了， 不需要再花费
        if (i == days.length)
            return 0;
        if (dp[i] != Integer.MAX_VALUE)
            return dp[i];
        //枚举每一种火车票的最低花费情况
        int ans = Integer.MAX_VALUE;
        for (int k = 0, j = i; k < costs.length; k++) {
            while (j < days.length && days[i] + durations[k] > days[j]) {
                j++;
            }
            ans = Math.min(ans, costs[k] + minCost(days, costs, j));
        }
        dp[i] = ans;
        return ans;
    }



    public static void main(String[] args) {
     
    }

}
