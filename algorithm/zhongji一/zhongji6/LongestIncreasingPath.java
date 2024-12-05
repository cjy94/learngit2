package com.chenjunyi.zhongji一.zhongji6;

/**
 * 给定一个整形数组，nums[][]， 每个位置可向上、下、左、右4个方向走，找到其中最长的递增路径
 *
 * 尝试模型，枚举nums[i][j]第i行第j列每一个位置，最为最长路径的出发位置，取其中最大的
 */
public class LongestIncreasingPath {

    public static int getLongestIncreasingPath(int[][] nums) {
        int ans = Integer.MIN_VALUE;
        int n = nums.length;
        int m = nums[0].length;
        int[][] dp = new int[n][m];
        for (int row =0; row < n; row++) {
            for(int col =0; col < m; col++) {
                ans = Math.max(ans, dpWithCache(nums, row, col, dp));
               // ans = Math.max(ans, dp(nums, row, col));
            }
        }
        return ans;

    }

    // 暴力递归的方式
    public static int dp(int[][] nums, int row, int col) {
        // 能不能往上走
        int nextUp = 0;
        if (row-1 >= 0 && nums[row-1][col] > nums[row][col]) {
            // 能向上走的前提下，后面尝试的答案
            nextUp = dp(nums, row-1, col);
        }
        // 能不能往下走
        int nextDown = 0;
        if (row+1 < nums.length && nums[row+1][col] > nums[row][col]) {
            nextDown = dp(nums, row+1, col);
        }
        // 能不能往左走
        int nextLeft = 0;
        if (col-1 >= 0 && nums[row][col-1] > nums[row][col]) {
            nextLeft = dp(nums, row, col-1);
        }
        // 能不能往右走
        int nextRight = 0;
        if (col+1 < nums[0].length && nums[row][col+1] > nums[row][col]) {
            nextRight = dp(nums, row, col+1);
        }
        return Math.max(Math.max(nextLeft, nextRight), Math.max(nextUp, nextDown)) + 1;

    }


    // 添加缓存的方式，记忆化搜索
    // 如果暴力递归过程中，位置依赖关系不好整理，所以直接添加缓存，不需要改成杨哥动态规划方式
    public static int dpWithCache(int[][] nums, int row, int col, int[][] dp) {
        if (dp[row][col] != 0) {
            return dp[row][col];

        }
        // 能不能往上走
        int nextUp = 0;
        if (row-1 >= 0 && nums[row-1][col] > nums[row][col]) {
            // 能向上走的前提下，后面尝试的答案
            nextUp = dpWithCache(nums, row-1, col, dp);
        }
        // 能不能往下走
        int nextDown = 0;
        if (row+1 < nums.length && nums[row+1][col] > nums[row][col]) {
            nextDown = dpWithCache(nums, row+1, col, dp);
        }
        // 能不能往左走
        int nextLeft = 0;
        if (col-1 >= 0 && nums[row][col-1] > nums[row][col]) {
            nextLeft = dpWithCache(nums, row, col-1, dp);
        }
        // 能不能往右走
        int nextRight = 0;
        if (col+1 < nums[0].length && nums[row][col+1] > nums[row][col]) {
            nextRight = dpWithCache(nums, row, col+1, dp);
        }
        int ans = Math.max(Math.max(nextLeft, nextRight), Math.max(nextUp, nextDown))+1;
        dp[row][col] = ans;
        return  ans;

    }

    public static void main(String[] args) {
        int[][] nums = {
                {9,9,4},
                {6,6,8},
                {2,1,1}
        };

        System.out.println(getLongestIncreasingPath(nums));
    }

}
