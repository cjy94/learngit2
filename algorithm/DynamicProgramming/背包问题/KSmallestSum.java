package com.chenjunyi.DynamicProgramming.背包问题;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *  非负数组前k个最小的子序列累加和
 *  给定一个数组nums，含有n个数字，都是非负数
 *  给定一个正数k，返回所有子序列中累加和最小的前k个累加和 , 子序列包含空集
 *
 *  [3,1,2,0,8,6,4,2,1,3,5]  k=4，
 *  就是nums的所有子序列全部列举出来， 将所有子序列求和， 累加和中前k个最小的返回
 *
 *  数组排序： [0 1 1 2 2 3 3 4 5 6 8]
 *
 *  最优解：排序 + 堆(小根堆)
 *
 *  取前k个值的问题，基本使用堆或者有序表进行求解
 *
 */
public class KSmallestSum {
    @Test
    public void test() {
        System.out.println("tet");
    }

//    static class Info {
//        int lastIndex;
//        int sum;
//
//        Info(int lastIndex, int sum) {
//            this.lastIndex = lastIndex;
//            this.sum = sum;
//        }
//    }
//
//
//    public static int[] count(int[] nums, int k) {
//        Arrays.sort(nums);
//        PriorityQueue<Info> queue = new PriorityQueue<Info>((a, b) -> a.sum - b.sum );
//
//        int[] ans = new int[k];
//        ans[0] = 0;
//        queue.add(new Info(0, nums[0]));
//        for (int i =1; i < k; i++) {
//            Info info = queue.poll();
//            int index = info.lastIndex;
//            int sum = info.sum;
//            ans[i] = sum;
//            if (index + 1 < nums.length) {
//                queue.add(new Info(index + 1, sum + nums[index + 1]));
//                queue.add(new Info(index+1, sum - nums[index] + nums[index+1]));
//            }
//        }
//        return ans;
//    }

}
