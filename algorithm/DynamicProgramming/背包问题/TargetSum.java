package com.chenjunyi.DynamicProgramming.背包问题;

/**
 *  目标和
 *  给你一个非负数组nums和一个目标target
 *  像数组中的每个整数前加'+'或者'-'，然后串联起所有的整数，可以构造一个表达式
 *  例如nums=[2,1]，可以在2之前添加'+'，在1之前添加'-'
 *  然后串联起来得到表达式"+2-1"
 *  返回可以通过上述构造方式，运算结果等于target的表达式个数
 *
 *
 *  平移解决sum是负数的问题
 *
 */
public class TargetSum {

    public static int target(int[] nums, int target, int index, int sum) {
        if (index == nums.length)
            return sum == target ? 1 : 0;

        return target(nums, target, index+1, sum - nums[index]) + target(nums, target,index +1, sum + nums[index]);
    }

    /*
        最优解解决该问题
        1、nums中的数字无论是正数还是负数， 不影响最终结果，因为要在每个数字前面添加'+'/'-'号
        2、nums中的所有数(非负数)累加起来 < target，没有任何表达式累加起来可以等于target，直接返回0
        3、nums中的数不管怎么变化，最终的奇偶性不会变，如果nums数组的累加和和target的奇偶性不一样，直接返回0
        4、nums数组中取出一部分数字添加'-' 累加和sumA; 一部分数字添加'+'累加和sumB
            sumA - sumB = target    两边同时加sumA+sumB
            sumA - sumB + sumA + sumB = target + sumA + sumB
            2 * sumA = target + (sumA + sumB) ---> 整个数组的累加个
            2 * sumA = target + sum;
            sumA = (target + sum) / 2;
            所以最终转化成了0-1背包问题， 在nums数组中任意选择数字，最终累加和等于target的子序列有多少
     */

    // dp[i][j] 前i个数字，构成的累加和一定是j,有多少个子序列
    // 不要i位置的数字， dp[i-1][j]
    // 要i位置的数字， dp[i-1][j-nums[i]]
    // 上面两种结果相加
    public static int count(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[target+1];
        dp[0]= 1;
        for (int i = 1; i < n; i++) {
            for (int j = target; j >= nums[i]; j--)
                dp[j] += dp[j-nums[i]];
        }
        return dp[target];
    }
}
