package com.chenjunyi.DynamicProgramming.习题4;

/**
  矩阵种能被k整除的路径

    给定一个下标从0开始的n*m整数矩阵grid和一个整数k，
 从起点(0,0)出发，每步只能往下走或者往右，想要到达终点(m-1, n-1)
 请返回路径和能被k整除的路径数目


 余数问题：
 总目标是      某个数 % 7 = 3
 其中某个过程出现  数 %  7 = 2， 那么后续的余数应该是多少

 
 */
public class KDivisible {

    public static void main(String[] args) {
        int[][] grid = {
                {5,2,4},{3,0,5},{0,7,2}
        };

        int res = path(grid, 3, 0, 0, 0);

        System.out.println(res + " ============= " );
    }

    // 这种尝试方法，sum变量的范围过大
    // 想其他的尝试方法
    public static int path(int[][] grid, int k, int i, int j, int sum) {
        if (i == grid.length - 1 && j == grid[0].length - 1)
            return (sum + grid[i][j]) % k == 0 ? 1 : 0;

        int right = 0;
        int down = 0;
        if (i < grid.length -1)
            right = path(grid, k, i+1, j, sum + grid[i][j]);
        if (j < grid[0].length - 1)
            down = path(grid, k, i, j+1, sum+ grid[i][j]);

        return right + down;
    }

    public int numberOfPaths(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;
        int[][][] dp = new int[n][m][k];

        for (int i =0; i < n; i++)
            for (int j =0; j < n; j++)
                for (int c = 0; c < k; c++)
                    dp[i][j][c] = -1;

        return path1(grid, n, m, k, 0, 0, 0, dp);
    }

    /**
     * 从位置(i,j)出发，最终一定要到达(n-1,m-1)
     * 期间的路径和 % k = r的路径条数有多少
     *
     *
     * 其中r余数要作为参数在递归函数中传递，所以需要计算出后续调用递归函数时r的值是多少
     * 计算后续r的公式：
     *  (k + r - (当前数 % k)) % k;
     */
    public static int path1(int[][] grid, int n, int m, int k, int i, int j, int r, int[][][] dp) {
        if (dp[i][j][r] != -1)
            return dp[i][j][r];

        if (i == n -1 && j == m-1)
            return grid[i][j] % k == r ? 1 : 0;

        int need = (k + r - (grid[i][j] % k)) % k;
        int ans = 0;
        if (i + 1 < n)
            ans = path1(grid, n, m, k, i+1, j, need, dp);
        if (j + 1 < m)
            ans = ans + path1(grid, n, m, k, i, j+1, need, dp);

        dp[i][j][r] = ans ;
        return dp[i][j][r];
    }



}
