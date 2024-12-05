package com.chenjunyi.gaoji一.gaoji4;

import java.util.HashMap;

/**
 * 最长子数组系列问题1
 *
 */
public class MaxSubArray {

    /**
     * 系列问题1： 数组中的元素都是正数的时候
     */
    public static int maxSubArray1(int[] arr, int k) {
        int l = 0;
        int r = 0;
        int ans = 0;
        int sum = arr[0];
        while (r < arr.length) {
            if (sum == k) {
                ans = Math.max(ans, r-l+1);
                // 相等的时候 r++, 需要判断r++之后是否会越界，提前退出的问题

//                r++;
//                if (r == arr.length) {
//                    break;
//                }
//                sum += arr[r];
                // 也可以相等时候l++,
                sum -= arr[l];
                l++;
            } else if (sum < k) {
                r++;
                if (r == arr.length) {
                    break;
                }
                sum += arr[r];
            } else {
                sum -= arr[l];
                l++;
            }
        }
        return ans;

    }


    /**
     * 系统问题2： 数组中的元素可正、可负、 可0   , 类似于异或和的问题
     * 使用map表
     */
    public static int maxSubArray2(int[] arr, int k) {
        // map中记录，累加和 和 索引的关系
        HashMap<Integer, Integer> map = new HashMap<>();
        //
        int sum = 0;
        int ans = 0;
        map.put(0, -1);
        for (int i =0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum -k)) {
                ans = Math.max(ans, i- map.get(sum-k));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return ans;

    }


    /**
     * 系统问题3： 数组中的元素可正、可负、 可0   累加和小于等于k的最长子数组长度
     *
     */
    public static int maxSubArray3(int[] arr, int k) {
        int[] minSum = new int[arr.length];
        int[] minSumEnd = new int[arr.length];
        for (int i =arr.length-1; i>=0; i--) {
            if (i+1 < arr.length && arr[i]+minSum[i+1] < arr[i]){
                minSum[i] = arr[i]+ minSum[i+1];
                minSumEnd[i] = minSumEnd[i+1];
            }  else {
                minSum[i] = arr[i];
                minSumEnd[i] = i;
            }
        }
        int res = 0;
        int sum = 0;
        int end = 0;
        for (int i =0; i < arr.length; i++) {
            /**
             * while退出条件
             * 1、如果以i开头的情况下，累加和<=k的最长子数组时arr[i..end-1], 检查这个子数组长度能不能更新res
             * 2、如果以i开头的情况下，累加和<=k的最长子数组比arr[i..end-1]短，更新还是不更新res都不会影响结果
             */
            while (end < arr.length && sum + minSum[end] <= k) {
                sum += minSum[end];
                end = minSumEnd[end] +1;
            }
            res = Math.max(res, end -i);
            if (end-i > 0) {    // 窗口内还有数
                sum -= arr[i];
            } else {           // 窗口内没有数， 换个窗口的开头
                end = i+1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {-1,3,4,-6,8,5};
        int len = maxSubArray3(arr, 3);
        System.out.println(len);

    }

}
