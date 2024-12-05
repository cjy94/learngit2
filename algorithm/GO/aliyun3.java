package com.chenjunyi.GO;

/**
 *    1、一个无无序数组数值范围(0-65535)，需要将数组进行排序，找出排序后数字之间的最大差值
 *
 *    思路: 将数组进行排序，排序后比较元素间差值最大的进行返回
 *      但是要求时间复杂度是O(n) 基于比较的排序时间复杂度最好的时间复杂度是O(n*logN), 不符合题目要求
 *      故想到了不基于比较的排序算法，基数排序
 *      所以是先使用基数排序，将数组排好序，在遍历一遍找到相邻元素间差值最大的即可
 */
public class aliyun3 {
    public static void main(String[] args) {
        int[] arr = {3,8,6,9,5,15};
        int i = maxDiff(arr);
        System.out.println(i);
    }

    public static int maxDiff(int[] arr) {
        radixSort(arr);
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            res = Math.max(res, arr[i] - arr[i - 1]);
        }
        return res;
    }

    // 基数排序流程
    public static void radixSort(int[] arr) {
        // 先获取数组中的最大值，计算出最大值的位数
        int max = arr[0];
        for (int v : arr) {
            max = Math.max(max, v);
        }
        //根据最大数值，计算有多少位
        int digit = maxBits(max);
        final int radix = 10;
        int[] help = new int[arr.length];

        for (int wei = 1; wei <= digit; wei++) {
            int[] count = new int[radix];
            for (int value : arr) {
                int d = getDigit(value, wei);
                count[d]++;
            }
            
            for(int i =1; i< count.length; i++) {
                count[i] = count[i]+count[i-1];
            }
            
            for(int i =help.length-1; i>=0;i--) {
                int d = getDigit(arr[i], wei);
                help[count[d]-1] = arr[i];
                count[d]--;
            }

            for (int i=0; i < help.length; i++) {
                arr[i] = help[i];
            }
        }
    }

    // 获取num的位数
    private static int maxBits(int max) {
        int res = 0;
        while (max != 0) {
            max = max/10;
            res++;
        }
        return res;
    }
    // 获取num指定位置上的数字， 例如 123的个位上的数字是3
    private static int getDigit(int value, int wei) {
        int res = 0;
        for(int i =1; i <= wei; i++) {
            if (i==wei) {
                res = value%10;
            } else {
                value /= 10;
            }
        }
        return res;
    }
}
