package com.chenjunyi.practice;

/**
 * 子数组的最大累乘积 , 乘法的特殊性： 负负得正，故需要两个变量，即前面取得的最大值，和最小值
 */
public class MaximumProductSubarry {

    public static double maxProduct(double[] arr) {
        double max = arr[0];
        double min = arr[0];
        double ans = arr[0];
        for (int i =0; i < arr.length; i++) {
            double curMin = Math.min(arr[i], Math.min(max * arr[i], min * arr[i]));
            double curMax = Math.max(arr[i], Math.max(max * arr[i], min * arr[i]));
            max = curMax;
            min = curMin;
            ans = Math.max(ans, max);
        }
        return ans;

    }
}
