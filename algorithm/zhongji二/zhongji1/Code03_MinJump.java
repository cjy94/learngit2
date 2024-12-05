package com.chenjunyi.zhongji二.zhongji1;

/**
 * 给出一组正整数，你从第一个数向最后一个数方向跳跃，每次至少跳跃1格，每个数的值表示你从这个位置可以跳跃的最长长度，计算如何以最少的跳跃次数跳到最后一个数
 * 例如： [2,3,2,1,2,1,5] output:3
 *
 * index 表示在数组的位置
 * cur 表示当前位置可以向右的边界
 * next 表示走一步后可以向右的边界
 *
 *       [2, 3, 2, 1, 2, 1, 5]
 * index  0  1  2  3  4  5  6
 *
 * (1) index=0时, step= 0, cur(当前位置可以达到的最右位置)=2, next=0
 * (2) index=1时，step=1, cur=2, next=1+3=4
 * (3) index=2时，step=1, cur=2, next=4;
 * (4) index=3时，step=2, cur=4, next=4;
 * (5) index=4时，step=2, cur=4, next=6;
 * (6) index=5时，step=3, cur=6, next=6;
 * (7) index=6时，step=3, cur=6, next=11
 * 结束
 *
 * 当index <= cur时，step和cur不需要更新，next选择最大值更新
 * 当index > cur时，step++， cur=next, next选择最大值更新
 */
public class Code03_MinJump {

    private static int minJump(int[] arr) {
        int step = 0;  // 到达数组最后一个数需要的步数， 最为最终答案进行返回
        int cur = 0;   // 当前index能过到达的最右右边界
        int next = 0;  // 如果做跳一步之后，可以到达的最右有边界位置

        for (int index = 0; index < arr.length; index++) {
            if(index<=cur) {
                next = Math.max(next, arr[index]+index);
            } else {
                step++;
                cur=next;
                next = Math.max(next, arr[index]+index);
            }
        }
        return step;
    }

    public static void main(String[] args) {
        int[] arr = {2,3,2,1,2,1,5};
        System.out.println(minJump(arr));
    }
}
