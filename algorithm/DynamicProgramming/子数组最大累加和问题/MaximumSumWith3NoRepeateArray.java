package com.chenjunyi.DynamicProgramming.子数组最大累加和问题;

/**
    三个无重复数组的最大和
    给你一个整数数组nums和一个整数k
    找出三个长度为k， 互不重叠，且全部数组和(3*k项)最大的子数组
    并返回这3个子数组
    以下标的形式返回  [start0, start1, start2]
    如果有多个结果，返回字典序最小的一个

 1) sums[] 必须以i开头， 往下数k个长度的累加和信息
 
 2) prefix[] 0...i 范围上，长度为k子数组中，最大累加和数组的开头位置在哪
 prefix[0] 0..0范围上， 长度为2的子数组中最大累加和的其实位置在哪  prefix[0]=没有
 prefix[1] 0..1范围上， 长度为2的子数组中最大累加和的其实位置在哪  prefix[1]=0
 prefix[2] 0..2范围上， 长度为2的子数组中最大累加和的其实位置在哪  prefix[2]=
 prefix[3] 0..3范围上， 长度为2的子数组中最大累加和的其实位置在哪  prefix[3]=

 3) suffix[]  i...n-1范围上，长度为k的子数组中，累最大累加和数组的开头位置在哪
 prefix[n-1] n-1...n-1范围上， 长度为2的子数组中最大累加和的其实位置在哪  prefix[n-1]=没有
 prefix[n-2] n-2...n-1范围上， 长度为2的子数组中最大累加和的其实位置在哪  prefix[n-2]=n-2
 prefix[n-3] n-3..n-1范围上， 长度为2的子数组中最大累加和的其实位置在哪  prefix[n-3]=

 */


public class MaximumSumWith3NoRepeateArray {

}
