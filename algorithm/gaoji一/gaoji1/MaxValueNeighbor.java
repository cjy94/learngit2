package com.chenjunyi.gaoji一.gaoji1;

/**
 * 给定一个数组，求如果排序之后， 相邻两数的最大差值 ， 要求时间复杂度O(N), 且要求不能用非基于比较的排序
 * 那么就不能用基于比较的排序，因为基于比较的排序，最好的时间复杂度是O(N logN)
 */
public class MaxValueNeighbor {
    public static int maxDifferent(int[] arr) {
        // 找出数组中的最大值和最小值
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i =0; i < arr.length ; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }
        if (min == max) {
            return 0;
        }
        int n = arr.length;
        boolean[] hasNum = new boolean[n+1];
        int[] maxs = new int[n+1];
        int[] mins = new int[n+1];
        int bid = 0;   // 桶号
        for (int i =0; i < n; i++) {
            bid = bucket(arr[i], n, min, max);
            mins[bid] = hasNum[bid] ? Math.min(mins[bid], arr[i]) : arr[i];
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], arr[i]) : arr[i];
            hasNum[bid] = true;
        }
        // 最大差值，一定在相邻桶中的最大值和最小值的差值；
        int res = 0;
        int lastMax = maxs[0];  // 上一个非空桶中的最大值
        for (int i =1; i <= n; i++) {
            if (hasNum[i]) {
                res = Math.max(res, mins[i]-lastMax);
                lastMax = maxs[i];
            }
        }
        return res;

    }

    // 计算在长度为n, 最大值是min,最小值是max的数组中，第i个元素应该放置在第几号桶中
   private static int bucket(int num, int len, int min, int max) {
        return (int)((len * (num-min)) / (max-min));
    }


    public static void main(String[] args) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
        int[] arr = {-34,12,34,78,90,5,4,67,34,25,89,65};
        System.out.println(maxDifferent(arr));
        
    }
}
