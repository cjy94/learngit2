package com.chenjunyi.project;

/**
 *  堆一个数组构建前缀累加和 和 后缀累加和
 */
public class PrefixSumAndSuffixSum {

    public static void main(String[] args) {
        int[] arr = {4,2,2,3};
        compute1(arr, 2);
    }


    /**
     *  将数组分成leftSum 和 rightSum ， 首先全部选择左侧即，[0...k] 计算leftSum
     *  然后开始左侧舍弃一个，右侧添加一个，计算sum并选择最大值; 左侧在舍弃2个，右侧添加2个，计算sum并选择最大的，依次类推，直到左侧一个也不选择，全部选择右侧。在这个过程中取最大的sum
     *
     */
    public static void compute1(int[] arr, int k) {
        int len = arr.length;
        int leftSum = 0; // 左边选择0个
        int rightSum = 0;
        for (int i = len-k; i < len; i++) {
            rightSum += arr[i];
        }
        if (k == len) {
            System.out.println(rightSum);
            return;
        }

        int sum = rightSum + leftSum;
        int ans = sum;
        int l = 0;
        int r = len - k;
        while (l < k) {
            sum = sum + arr[l++] - arr[r++];
            ans = Math.max(ans, sum);
        }
        System.out.println(ans);
    }

    public static void compute(int[] arr, int k) {
        int len = arr.length;
        if (k == len) {
            int sum = 0;
            for (int v : arr) {
                sum += v;
            }
            System.out.println(sum);
        }

        int preFix[] = new int[arr.length];
        int suffix[] = new int[arr.length];
        preFix[0] = arr[0];
        suffix[arr.length-1] = arr[arr.length-1];

        for (int i =1; i < preFix.length; i++) {
            preFix[i] = preFix[i-1] + arr[i];

        }

        for (int i = suffix.length-2; i >= 0; i--) {
             suffix[i] = suffix[i+1] + arr[i];
        }

        int res = preFix[k-1] > suffix[len-k] ? preFix[k-1] : suffix[len-k];
        System.out.println(res);
    }
}
