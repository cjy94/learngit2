package com.chenjunyi.day09_DP;

import java.util.Arrays;

public class Code03_MinSum {

    // 求数组的最小累加和问题
    // 使用dp方式
    public static int minSum(int[] arr) {
        int n = arr.length;
        // 代表arr[0..n]上的数字自由组合，是否可以组成dp[j]
        int sum = arr[0];
        int min = arr[0];
        for (int i =1; i < n; i++) {
            sum += arr[i];
            min = Math.min(min, arr[i]);

        }
        boolean[][] dp = new boolean[n][sum+1];


        // 填写第一行
        dp[0][arr[0]] = true;

        // 普遍位置讨论
        // 1) 不用arr[i]位置的数字，那么arr[0..i-1]的数字是否可以组合成sum
        // 2) 用arr[i]位置的数字，那么j-arr[i] 位置上的数字能否组合成sum-arr[i]
        for (int i =1; i < n; i++) {
            for (int j =1; j <= sum; j++) {  // 第一列不需要管，
                if (arr[i] == j) {
                    dp[i][j] = true;
                } else if (dp[i-1][j]) {
                    dp[i][j] = true;
                } else if (j-arr[i] >= 0 && dp[i-1][j-arr[i]]) {
                    dp[i][j] = true;
                }
            }
        }

        // 遍历最后一行
        int ans = min;
        for (; ans <= sum; ans++) {
            if (dp[n-1][ans] == false) {
                return ans;
            }
        }
        // 否则就是sum+1的位置是最小不可组成和
        return sum+1;


    }

    public static double getNum() {
        return Math.max(Math.random(), Math.random());
    }

    public static int[] searchRange(int[] nums, int target) {
        int l = 0;
        int r = nums.length-1;
        // 查找target的索引位置
        int start = bound(nums, l, r, target);
        // 查找target+1的索引位置
        int end = bound(nums, l, r, target + 1) - 1;

        // 如果start是正确的索引位置，start>=0 && start<nums.length
        // 并且nums[start]上的数值是target
        if(start < nums.length && nums[start] == target){
            return new int[]{start, end};
        }
        return new int[]{-1, -1};




    }

    // 在{l...r] 范围上 二分查找某一个数
    public static int bound(int[] nums, int l, int r, int target) {
        while (l <= r) {
            int mid = l + ((r-l) >> 1);
            if (nums[mid] < target) {
                l = mid+1;
            } else {
                r = mid-1;
            }

        }
        return l;


    }

    public static void random() {
        int[] arr = {3, 2, 5};
        System.out.println(minSum(arr));

        int testTime = 10000000;
        double x = 0.6;
        int count = 0;
        for (int i =0;i < testTime; i++) {
            if (getNum() < x) {      // 等概率返回[0,x]
                count++;
            }
        }
        System.out.println((double)(count) / (double)(testTime));
    }

    public static void main(String[] args) {
        int[] arr = {5,7,7,8,8,10};
        System.out.println(Arrays.toString(searchRange(arr, 5)));

    }
}
