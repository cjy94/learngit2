package com.chenjunyi.practice;

import java.util.HashMap;

/**
 *  给定一个数组arr， 可以在每个数字之前决定+或者-，但是必须所有数字都参与，
 *  再给定一个数target，请问最后算出target的方法数是多少？
 */
public class TargetSum {

    public static int findTargetSum1(int[] arr, int target) {
        return process1(arr, 0, target);
        
    }

    public static int process1(int[] arr, int index, int target) {
        if (index == arr.length) {
            return target == 0 ? 1 : 0;
        }
        return process1(arr, index+1, target-arr[index]) + process1(arr, index+1, target+arr[index]);
    }

    public static int findTargetSum2(int[] arr, int target) {
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
        return process2(arr, 0, target, map);
    }

    public static int process2(int[] arr, int index, int target, HashMap<Integer, HashMap<Integer, Integer>> map) {
        if (map.containsKey(index) && map.get(index).containsKey(target)) {
            return map.get(index).get(target);
        }
        int ans = 0;
        if (index == arr.length) {
            ans = target == 0 ? 1 : 0;
        } else {
            ans = process1(arr, index + 1, target - arr[index]) + process1(arr, index + 1, target + arr[index]);
        }

        if (!map.containsKey(index)) {
            map.put(index, new HashMap<>());
        }
        map.get(index).put(target, ans);
        return ans;
    }



}
