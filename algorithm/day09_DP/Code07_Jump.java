package com.chenjunyi.day09_DP;

/**
 * 给定数组arr, arr[i]==k代表可以从位置i向右跳跃1~k个距离。比如：arr[2]=3，代表从位置2可以跳跃到位置3、位置4、位置5
 * 如果从位置0开始，返回最少跳几次能跳到arr最后的位置上
 *
 * 定义4个变量
 * step: 到当前位置上已经跳了几步
 * cur: 当前位置
 * max: 在当前位置下，可以跳到的最远距离
 * next: 再多跳一步可以到达的最远距离
 *
 * 每次到达一个新位置后cur++, 检查是否到了max即最远距离，到了需要step++
 * 否则更新next，即多条了一步，可以到了更远的位置
 */
public class Code07_Jump {
    public static int maxJump(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int step = 0;
        int cur = 0;
        int max = 0;
        int next = 0;
        while (cur < arr.length) {
            if (max < cur) {
                step++;
                max = next;

            }
            next = Math.max(next, arr[cur]+cur);
            cur++;
        }
        return step;

    }

    public static void main(String[] args) {
        int[] arr = {3,2,3,1,1,4};
        System.out.println(maxJump(arr));
    }
}
