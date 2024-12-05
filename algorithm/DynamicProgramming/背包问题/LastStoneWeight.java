package com.chenjunyi.DynamicProgramming.背包问题;

/**
 *  最后一块石头的重量
 *   有一堆石头，用整数数组stones表示，其中stones[i]表示第i块石头重量
 *   粉碎石头的规则如下：  一块重量x，一块重量y， 且 x<=y
 *   1) x == y, 那么两块石头都会被粉碎
 *   2) x != y, 那么重量为x的石头将完全粉碎，重量为y的石头新重量为(y-x)
 *   最后，最多只剩下一块石头，返回此石头的最小可能重量，如果没有石头剩余，返回0
 *
 *   将石头分成两堆数字，使得每堆数字的sum接近totalSum/2
 *   <= j 
 */
public class LastStoneWeight {
}
