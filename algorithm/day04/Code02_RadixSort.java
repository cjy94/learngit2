package com.chenjunyi.day04;


import java.util.Arrays;

public class Code02_RadixSort {
    public static void main(String[] args) {
//        int[] arr1 = {-1, -1};
//        radixSort(arr1);
//        System.out.println(Arrays.toString(arr1));
        for (int i =0; i < 20; i++) {
            System.out.println(Math.random()); // 等概率返回[0,1) 所有小数
        }
    }

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
        // 从最低位，即个位开始遍历，
        for (int wei = 1; wei <= digit; wei++) {
            // 先遍历数组中的每个元素，取出该元素的各位值
            int[] count = new int[radix];
            for (int value : arr) {
                // 获取该数值，指定位数上的数字
                int d = getDigit(value, wei);
                count[d]++;
            }
            // count'数组，统计词频
            for(int i =1; i< count.length; i++) {
                count[i] = count[i]+count[i-1];
            }

            // 根据词频确定 应该放置的位置
            for(int i =help.length-1; i>=0;i--) {
                int d = getDigit(arr[i], wei);
                help[count[d]-1] = arr[i];
                count[d]--;
            }

            // 将按照位排好序的数组，拷贝回原数组，进行下一位的继续遍历
            for (int i=0; i < help.length; i++) {
                arr[i] = help[i];
            }

        }
    }

    /**
     * 获取该数值指定位置上的数字， 例如 123的各位上的数字是3
     * @param value 指定的数值
     * @param wei  指定的位数
     * @return
     */
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

    // 获取数值的位数
    private static int maxBits(int max) {
        int res = 0;
        while (max != 0) {
            max = max/10;
            res++;
        }
        return res;
    }
}
