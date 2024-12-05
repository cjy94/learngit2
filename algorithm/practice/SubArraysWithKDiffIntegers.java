package com.chenjunyi.practice;

import java.util.HashMap;

/**
 * 求一个数组中，有多少个子数组只包含K种数 
 *
 * 窗口问题： 数据具有某种单调性就可以使用窗口解决， 比如:随着窗口的扩大， 数字种类会随着增加
 *
 *
 * [3,1,1,2,2,5,7,5,4,5,4,7,8]  k=3
 *  
 */
public class SubArraysWithKDiffIntegers {



    // 在数组arr[]种有多少子数组包含的数字种数 <= k
    // 思路： j：满足要求(数组种类 <=k)的子数组的左边界，i：满足要求(数字种类<=k) 的子数组的右边界 子数组的个数： (右边界 - 左边界 + 1)
    //  
    public static int kInteger2(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        int j = 0;
        // 计算每一个arr[i]为结尾的情况
        for (int i =0; i < arr.length; i++) {
            // 是一个新来的数字
            if(map.getOrDefault(arr[i], 0) == 0) {
                k--;
            }
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
            while (k < 0) {
                map.put(arr[j], map.getOrDefault(arr[j], 0) -1);
                if (map.get(arr[j]) == 0) {
                    k++;
                }
                j++;
            }

            ans += i- j + 1;


        }
        return ans;
    }



    // 规定nums数组中的数字，不会超过nums的长度
    public static int subArraysWithKDiffIntegers(int[] nums, int k) {
        int n = nums.length;
        int[] lessCounts = new int[n+1];      // k-1种数的窗口
        int[] equalsCounts = new int[n+1];    // k种数的窗口
        int lessKinds = 0;
        int equalsKinds = 0;
        int lessLeft = 0;
        int equalsLeft = 0;
        int ans = 0;

        // 遍历数组，计算两个边界
        for (int i =0; i < n; i++) {
            if (lessCounts[nums[i]] == 0) {
                lessKinds++;
            }
            if (equalsCounts[nums[i]] == 0) {
                equalsKinds++;
            }
            lessCounts[nums[i]]++;
            equalsCounts[nums[i]]++;

            // 窗口左侧边界向右移
            while (lessKinds == k) {
               if (lessCounts[nums[lessLeft]] == 1) {
                   lessKinds--;
               }
               lessCounts[nums[lessLeft++]]--;
            }

            // 窗口左侧边界向右移
            while (equalsKinds > k) {
                if (equalsCounts[nums[equalsLeft]] == 1) {
                    equalsKinds--;
                }
                equalsCounts[nums[equalsLeft++]]--;
            }
            ans += equalsLeft - lessLeft;
        }
        return ans;
    }




    // 在数组arr[]种有多少子数组包含的数字种数 <= k
    // 以arr[j]为结尾情况，往前数有多少个子数组包含的数字种类 <= k
    public static int  kInteger(int[] arr, int k) {
        HashMap<Integer, Integer> count = new HashMap<>();     // {1,1,1,4,3,3,2,5,4} k =3
        int ans = 0;
        int j = 0;
        for(int i =0; i < arr.length; i++) {
            if (count.getOrDefault(arr[i], 0) == 0) {
                k--;
            }
            count.put(arr[i], count.getOrDefault(arr[i], 0)+1);

            while (k < 0) {
                 count.put(arr[j], count.get(arr[j]) -1);
                 if (count.get(arr[j]) == 0) {
                     k++;
                 }
                 j++;
            }
            ans += i-j+1;
        }
        return ans;

    }

    public static int kInteger1(int[] arr, int k) {
        HashMap<Integer, Integer> count = new HashMap<>();
        int ans = 0;
        int i = 0;
        for (int j =0; j < arr.length; j++) {
           if(count.getOrDefault(arr[j], 0) == 0) {
                k--;
           }
           count.put(arr[j], count.getOrDefault(arr[j], 0) + 1);
           while (k < 0) {
               count.put(arr[i], count.get(arr[i]) - 1);
               if (count.get(arr[i]) == 0) {
                   k++;
               }
               i++;
           }
           ans += j - i + 1;
        }

        return ans;
    }

        public static void main(String[] args) {
        int[] arr = {1,1,1,4,3,3,5,5,2};
        int i = kInteger1(arr, 3);
        int j = kInteger2(arr, 3);
        System.out.println(i);
        System.out.println(j);
    }

}
