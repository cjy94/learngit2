package com.chenjunyi.DynamicProgramming.习题4;

import java.util.ArrayList;

/**
 集团有n名员工，他们可以完成各种各样的工作创造利润
 第[i]中工作会产生profit[i]的利润，它要求group[i]名成员共同参与，
 如果员工参与了其中一项，就不能参与另一项工作，
 工作的任何至少产生minProfit利润利润的子集称为盈利计划
 并且工作的成员总数量最多为n
 有多少种计划可以选择？

 参数：  n 员工数量
        minProfit 子集的最小容量
        group[] 每个项目要求参与的员工数量
        profit[] 每个项目产生的利润

 返回方案数

 
 */
public class ProfitPlan {

    // index: 项目编号
    // r: 还有多少员工可以做项目
    // s: 利润还有s才能达到
    public int process(int[] group, int[] profit, int index, int r, int s) {
        //base case
        if (r <= 0)
            return s <= 0 ? 1 : 0;

        if (index == group.length) {  // 没有项目可做了
            return s <= 0 ? 1 : 0;
        }

        // 当前的 项目不做
        int p1 = process(group, profit, index+1, r,  s);
        // 当前的  项目做
        int p2 = 0;
        if (group[index] <= r)
            p2 = process(group, profit, index + 1,r - group[index], s - profit[index]);
        
        return p1 + p2;
    }
}
