package com.chenjunyi.zhongji一.zhongji6;

/**
 * 斐波那契数列套路
 */
public class Fibonacci {

    // 这样计算的世家复杂度是O(N)
    public static int fi1(int n) {
        if (n < 1) return 0;
        if (n == 1 || n==2) {
            return 1;
        }
        return fi(n-1) + fi1(n-2);

    }

    // 这样优化，可以是计算的时间复杂度是O(logN)
    public static int fi(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n==2) {
            return 1;
        }
        int[][] base = {{1,1},
                        {1, 0}};
        int[][] res = matrixPower(base, n-2);
        return res[0][0] + res[1][0];
    }

    private static int[][] matrixPower(int[][] base, int p) {
        // 类似求  base:5, p:75    5^75   75的二进制数： 1001011
        // 1 * base^1 * base^2 * base^4 * base^8 ....
        //    1         0        0         1         0          1       1
        // base^64   base^32   base^16   base^8    base^4     base^2   base^1


        int[][] res = new int[base.length][base[0].length];
        for (int i =0; i < res.length; i++) {
            res[i][i] = 1;
        }
        int[][] temp = base;
        // res 为单位矩阵，用单位矩阵和base^1 base^2 base^4 base^8 base^16..... 依次相乘
        for(; p != 0; p >>>= 1) {
            if((p & 1) != 0) {
                res =  matrixMul(res, temp);
            }
            temp = matrixMul(temp, temp);
        }
        return res;
    }

    /**
     * 两个矩阵相乘的 流程
     * @param m1
     * @param m2
     * @return
     */
    private static int[][] matrixMul(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i =0; i < m1.length; i++) {
            for (int j =0; j < m2[0].length; j++) {
                for (int k =0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(fi1(6));
    }
}
