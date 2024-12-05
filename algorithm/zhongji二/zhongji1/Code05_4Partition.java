package com.chenjunyi.zhongji二.zhongji1;

import java.util.HashMap;

/**
 * 给定一个长度大于3的数组arr, 返回该数组能不能分成4个部分，并且每个部分的累加和相等
 * 如果算法中频繁执行一个操作，比如计算某个范围上的和， 先生成一个预处理结构
 * 比如，该题目，对每一个区域求和，可以先生成区域和的表，
 * [3,3,9,2,4,7,6,8,1,5] 比如： 生成一个表，key值为和，value为位置，就是在该位置，前面的数和该位置的数的和
 * 前缀和数组[3,6,15,17,21,28,34,42,43,48]
 * 前缀和表<key, value> key为前缀和，value为位置
 * (3,0; 6,1; 15,2; 17,3; 21,4; 28,5; 34,6; 42,7; 43,8; 48,9)
 *
 * 分析：
 * 边界条件 分3份成4个区域并且不报看切合位置的数，那么数组中至少包含7个数字还可以
 * 1、从数组[0..i]位置从左往右依次检查，依次检查如果arr[i]位置的数，作为切割数字，那么arr[0..i-1]的sum+arr[i]+sum在前缀和表中是否包含这个key值，如果包含，那么作为第一个切割位置
 * 2、
 *
 */
public class Code05_4Partition {
    private static boolean partition(int[] arr) {
        // 边界条件判断
        if (arr.length < 7) return false;
        // 生成前缀和表
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = arr[0];
        map.put(sum, 0);
        for (int i =1; i<arr.length;i++) {
            sum = sum + arr[i];
            map.put(sum, i);
        }

        // sum是整个数组的和
        // 切割数检查， sump代表如果i位置作为切割的位置，那么他左侧的和就是sump
        int sump = arr[0];
        for(int s1=1; s1< arr.length; s1++) {
            // s1作为第一个切割的位置的话，他前面的sump+s1+sump应该在map表中存在，
            int checksum = sump * 2 + arr[s1];
            if(map.containsKey(checksum)) {
                // 取第二个切割的位置
                int s2 = map.get(checksum) +1;
                checksum = checksum + arr[s2] + sump;
                if (map.containsKey(checksum)) {
                    // 可以作为第二个切割的数字
                    int s3 = map.get(checksum) + 1;
                    if (checksum + arr[s3] + sump == sum) {
                        return true;
                    }
                }
            }
            sump += arr[s1];
        }
        return false;
    }


    public static void main(String[] args) {
        int[] arr = {3,3,9,2,4,7,6,8,1,5,2,3};
        System.out.println(partition(arr));
    }
}
