package com.chenjunyi.DynamicProgramming.子数组最大累加和问题;

import java.util.ArrayList;

/**
 * 删掉一个数字后长度为k的子数组最大累加和
 *  给定一个数组Nums，求必须删除一个数字后的新数组中
 *  长度为k的子数组中最大累加和
 *
 *  原数组长度n  -----删除一个元素--->  删除数组后数组
 *  在删除后数组中长度为k的子数组，最大累加和
 *   ||
 *  等同于在原数组中k+1长度的子数组最大累加和
 *
 *
 */


public class Delete1NumSubArrayMaximumSum {

    public static void main(String[] args) {
        isContain();
        

    }



    public static int func(int[] nums, int k) {
        int n = nums.length;
        // 单调队列，队列中维持[l...l+k-1]这组元素中的最小值 队列中的元素是从小到大的顺序
        // 单调队列中存放是数组的下标
        int[] windows = new int[n];
        int ans = 0;
        int sum = 0;
        for (int l = 0, i = 0, r = 0; l < n; i++) {
            // 单调队列
            while (l < r && nums[windows[r-1]] >= nums[i]) {
                r--;
            }
            windows[r++] = i;
            // 单调队列

            sum += nums[i];
            if (i > k) {
                ans = Math.max(ans, sum - nums[windows[l]]);
                if (windows[l] == i-k)
                    l++;
                sum -= nums[i-k];
            }

        }
        return ans;
    }

    private static boolean isContain() {
        String[] s = {"Lucene", "Java", "c/c++"};
        for (String v : s) { 
            if (v.contains("c"))  {
                System.out.println(v);
                return true;
            }
        }
        return false;
    }
}
