package com.chenjunyi.gaoji一.gaoji1;


import java.util.HashMap;

public class MaxEORPart {

    // 异或和为0的最多区域
    public static int maxEor(int[] arr) {
        int n = arr.length;
        int xor = 0;
        int[] dp = new int[n];
        // key: 异或和
        // value: 最近的索引位置
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);  // 一个数也没有的时候，异或和是0的部分，-1
        for (int i =0; i < n; i++) {
            xor ^= arr[i];
            if (map.containsKey(xor)) {
                // [pre+1...i]是最优划分中， 异或和为0的最后一块
                // 那之前的划分是什么呢？ dp[pre]
                Integer pre = map.get(xor);   // 上一次xor出现的索引位置
                dp[i] = pre == -1 ? 1 : (dp[pre] + 1);
            }
            if (i > 0) {
                dp[i] = Math.max(dp[i - 1], dp[i]);
            }
            map.put(xor, i);
        }
        return dp[n-1];
    }
}
