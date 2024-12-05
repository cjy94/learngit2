package com.chenjunyi.DynamicProgramming.习题2;

/**
 最小路径和
 给定一个包含非负整数的m*n的网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小
 说明： 每次只能向下或者向右走

 */
public class MinimumPathSum {


    // 从grid[0][0] -> grid[n-1][m-1] 最小路径和
    public int minPathSum(int[][] grid) {
        return f(grid, 0, 0);
    }

    private int f(int[][] grid, int row, int col) {
        if (row == grid.length -1 && col == grid[0].length - 1)
            return grid[row][col];

        int down = Integer.MAX_VALUE;
        int right = Integer.MAX_VALUE;

        if (row < grid.length)
            down = f(grid, row + 1, col);
        if (col < grid[0].length)
            right = f(grid, row, col + 1);

        return grid[row][col] + Math.min(down, right);

    }
}
