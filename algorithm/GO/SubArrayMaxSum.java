package com.chenjunyi.GO;

/**
 *  子数组最大累加和
 *  子数组最大累加和问题是非常经典的问题
 *
 *  1、子数组最大累加和
 *  附加题： 找到最大累加和子数组，返回left,right,sum  3个信息
 *
 *  2、不能选择相邻元素的最大累加和问题 (打家劫舍问题)   不是子数组，是子序列
 *  dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1], nums[i])
 *
 *  3、环形数组的子数组最大累加和问题
 *      nums是一个环形数组，下标0和下标n-1是连在一起的，返回环形数组中子数组的最大累加和
 *   
 *   可能的情况有2种
 *      3.1 组成的最大累加和子数组是不被隔断的，就是连续的，那么和题目2一样
 *      3.2 组成的最大累加和子数组是被隔断的，比如：[1 2 3 -6 3 -7 4 5 1] 那么可以转换成求数组中的连续最小累加和， 数组总和减掉最小累加和就是组成的最大累加和
 *          (all - 非环形数组的最小累加和) 就是3.2的答案
 *
 *       3.1 和 3.2 求max
 *
 *
 *  4、环形数组中不能选择相邻元素的最大累加和
 *      不考虑0位置的数字， 1 ~ n-1 范围上不选择相邻数字的最大累加和
 *      考虑0位置的数字， 2 ~ n-2 范围上不选择相邻数字的最大累加和
 *
 *  5、打家劫舍
 *
 *  6、子矩阵的最大累加和问题
 *      分解成子数组最大累加和问题
 *
 *
 *
 *
 *  1、 乘积最大的子数组
 *      因为乘积可能出现负负得正的情况， 所以需要求之前的最大乘积 和 之前的最小乘积
 *      dp[i] = Math.nax(nums[i], nums[i] * max[i-1], nums[i] * min[i-1])
 *       
 *  2、子序列累加和必须被7整除的最大累加和
 *      给定一个非负数组nums，可以任意选择数字组成子序列，但是子序列的累加和必须被7整除，返回最大累加和
 *
 *  3、魔法卷轴     
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

public class SubArrayMaxSum {
}
