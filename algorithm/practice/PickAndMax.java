package com.chenjunyi.practice;

import java.util.Arrays;

/**
 * 给定一个数组arr，当拿走某个数a的时候，其他所有的数都+a
 * 请返回最终所有数都拿走的最大分数
 * 比如： [2,3,1]
 * 当拿走3时，获得3分，数组变成[5,4]
 * 当拿走5时，获得5分，数组变成[9]
 * 当拿走9时，获得9分，数组变成[]
 * 这是最大的拿取方式，返回总分17
 *
 * 思路： 从大到小取数
 * 重要公式： (ans * 2) + 当前数值
 */
public class PickAndMax {

    public static int pick(int[] arr) {
        Arrays.sort(arr);
        int ans = 0;
        for (int i = arr.length-1; i >= 0; i--) {
            ans = (ans << 1) + arr[i];
        }
        return ans;

    }
}
