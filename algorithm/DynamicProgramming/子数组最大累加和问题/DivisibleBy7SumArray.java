package com.chenjunyi.DynamicProgramming.子数组最大累加和问题;

import java.util.ArrayList;

/**
  子序列累加和必须被7整除的最大累加

    给定一个非负数组nums，可以任意选择数字组成子序列，但是子序列的累加和必须被7整除
    返回最大累加和

                                                                                                                                                 
    暴力尝试： 就是枚举出该数组的所有子序列，在将子序列求和，看所有能被7整除的子序列中最大的累加和

    dp[i]: nums[0...i]所有子序列中能被7整除的数组最大累加和

    dp[i][j]: 数组前i个数的累加和%7 == j      返回dp[n][0] 也就是 在nums[0..n-1]中所有子序列累加和%7==0的最大累加和

    dp含义解释
    dp[4][4]: 数组前4个元素，凑出的累加和%7 = 4
    dp[7][0]: 数组前7个元素，凑出的累加和%7 = 0


 
    复习：
    子序列的累加和必须被7整除， 返回最大累加和
 dp[4][4]  数组前4个数值，凑出的累加和%7==4，
    1) 不使用当前值凑出的累加和%7==4， dp[i-1][j]
    2) 使用当前值凑出的累加和%7==4
        int k = nums[i] % 7;
        (x + k ) % 7 == j
        x = (j+7)-k
    dp[i][j+7-(nums[i]%7)]+nums[i]

 两种情况取最大值


 */
public class DivisibleBy7SumArray {

    public static int maxSum1(int[] nums, int k) {
        int n = nums.length;
        //dp[i] = nums[0...i-1]
        // nums前i个数形成的子序列， 子序列%7 == j     余数一定是严格等于j
        int[][] dp = new int[n+1][k];
        dp[0][0] = 0;  // nums前0个数, 形成的子序列累加和只能是0,   (0 % 7 = 0)
        for (int j = 1; j < k; j++) {
            dp[0][j] = -1;    // nums前0个数组成的累加和是0， 0 % 7 != j 所以填写-1
        }

        for (int i = 1; i <= n; i++) {
            int cur = nums[i-1];
            int curMode = cur % k;
            for (int j = 0; j < k; j++) {
                // 不使用nums[i]的值
                dp[i][j] = dp[i-1][j];
                // 使用nums[i]的值
                int need = curMode <= j ? (j - curMode) : (j + k - curMode);
                if (dp[i-1][need] != -1)
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][need] + nums[i]);
            }
        }

        return dp[n][0];  // 最终返回： 在整体形成的子序列累加和%7==0， 就是题目要求的数组nums能被7整除的子序列最大累加和
    }

    public static int maxSum2(int[] nums, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        return func(nums, k, list, 0);

    }

    private static int func(int[] nums, int k, ArrayList<Integer> list, int index) {
        if (index == nums.length) {  // 校验这组选出的子序列是否能被 7整除
            int curSum = 0;
            for (Integer v : list) {
                curSum += v;
            }

            if (curSum % k == 0)
               return curSum;
            else
                return 0;
        }
        // 不要当前的数
        int p1 = func(nums, k, list, index+1);
        // 要当前的数
        int temp = nums[index];
        list.add(temp);
        int p2 = func(nums, k, list, index+1);
        list.remove(temp);
        return Math.max(p1, p2);

    }



}
