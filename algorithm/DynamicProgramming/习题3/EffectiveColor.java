package com.chenjunyi.DynamicProgramming.习题3;

/**
 *  有效的涂色问题
 *  给定n，m两个参数，一共有n个格子，每个格子可以涂上一种颜色， 颜色在m种里选，
 *  当涂满n个格子，并且m种颜色都使用了，叫一种有效方法，
 *  求一共有多少种有效涂色法
 *
 *
 *  dp[i][j]: [0...i-1]个格子涂了j种颜色的方法数
 *  尝试在第j个格子上选择一种颜色
 *  不新增颜色, 复用前一种： dp[i-1][j] * j
 *  新增一种颜色: dp[i-1][j-1] * (m-j-1)
 *
 *  以上两种情况相加
 *
 */
public class EffectiveColor {
}
