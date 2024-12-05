package com.chenjunyi.zhongji二.zhongji2;

import java.util.HashMap;

/**
 *  给定一个长度大于3的数组arr， 返回该数组能不能分成4个部分，并且每个部分的累加和相等
 */
public class SplitKPart {

    public static boolean split(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = arr[0];
        for (int i =0; i < arr.length; i++) {
            map.put(sum, i);
            sum += arr[i];
        }
        int lsum = arr[0];
        for (int s1 =1; s1 < arr.length-5; s1++) {
            int checkSum = lsum * 2 + arr[s1];
            if (map.containsKey(checkSum)) {
                int s2 = map.get(checkSum);
                checkSum += lsum + arr[s2];
                if (map.containsKey(checkSum)) {
                    int s3 = map.get(checkSum);
                    if (checkSum + arr[s3] + lsum == sum) {
                        return true;
                    }
                }
            }
            lsum += arr[s1];
        }
        return false;
    }
}
