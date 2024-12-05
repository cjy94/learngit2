package com.chenjunyi.practice;

/**
 * 给定一个只由0和1组成的字符串S，假设下标从1开始，规定i位置的字符价值V[i]计算方式如下：
 * 1) i==1 时， V[i] =1;
 * 2) i>1 时，如果S[i] != S[i-1], V[i] = 1              相邻字符不一样，价值回到1
 * 3) i>1 时，如果S[i] == S[i-1], V[i] = V[i-1]+1;     相邻字符一样 ， 价值加1
 * 可以随意删除S中的字符，返回整个S的最大价值
 * 字符串长度<=5000
 *
 *
 * 动态规划： 从左往右尝试模型， 来到i位置，要么保留要么删除 (类似背包?)
 */
public class MaxValue {

    /**
     *  lastNum: 之前离index位置最近的没有被删掉的数值  1，0 两种情况
     *  value: index位置之前保留的价值  arr长度
     *  index: 当前要处理的位置   数组常速
     *  时间复杂度 n^2
     */
    public static int process1(int[] arr, int index, int lastNum, int value) {
        if (index == arr.length) {
            return 0;
        }
        // 保留的情乱搞： 当前值和lastNum一样，价值加1； 否则价值回到1
        int curValue = arr[index] == lastNum ? value + 1 : 1;
        // 删除index位置的值
        int p1 = process1(arr, index+1, lastNum, value);
        // 保留index位置的值
        int p2 = process1(arr, index+1, arr[index], curValue);
        return Math.max(p1, p2);
    }

}
