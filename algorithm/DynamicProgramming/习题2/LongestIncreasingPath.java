package com.chenjunyi.DynamicProgramming.习题2;

/*
* 给定一个m*n的矩阵matrix, 找出其中最长递增路径的长度
*
* */
public class LongestIncreasingPath {

    public int longestIncreasingPath(int[][] matrix) {

        // 枚举从任意一个位置出发可能出现最长递增路径的可能性
        int ans = -1;
        for (int i =0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                ans = Math.max(ans, f(matrix, i, j));
            }
        }
        return ans;
    }

    public int f(int[][] matrix, int i, int j) {
        int next = 0;

        if (i > 0 && matrix[i-1][j] > matrix[i][j])
            next = Math.max(next, f(matrix, i-1, j));
        if (i+1 < matrix.length && matrix[i+1][j] > matrix[i][j])
            next = Math.max(next, f(matrix, i+1, j));
        if (j > 0 && matrix[i][j-1] > matrix[i][j])
            next = Math.max(next, f(matrix, i, j-1));
        if (j+1 < matrix[0].length && matrix[i][j+1] > matrix[i][j])
            next = Math.max(next, f(matrix, i, j+1));

        return next + 1;

    }
}
