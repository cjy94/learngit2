package com.chenjunyi.zhongji二.zhongji4;

import java.util.HashSet;

public class ZhengHeArray {


    public static int zheng(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int res = 0;
        for (int i =0; i < arr.length; i++) {
            set.add(arr[i]);
            max = arr[i];
            min = arr[i];
            for (int j =i+1; j< arr.length; j++) {
                if (!set.contains(arr[j])) {
                    set.add(arr[j]);
                    max = Math.max(max, arr[j]);
                    min = Math.min(min, arr[j]);
                }  else {
                    break;
                }
            }
            // 如果满足可整合数组的要求， 计算可整合数组的大小
            if (set.size()-1 == max-min) {
                res = Math.max(res, set.size());
            }
            set.clear();
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {5,5,3,2,6,4,3};
        System.out.println(zheng(arr));
    }
}
