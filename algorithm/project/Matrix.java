package com.chenjunyi.project;

/**
 * 螺旋矩阵
 *
 */
public class Matrix {

    public static void main(String[] args) {
        int a = 120;  // 个数
        int b = 7;   // 行数
        // 计算列数

        int[][] matrix = new int[b][a % b == 0 ? a / b : a / b + 1];
        int loop = 1;
        int index = 1;
        while ((loop - 1) * 2 < matrix.length && (loop - 1) * 2 < matrix[0].length) {
            index = func(index, a, loop, matrix);
            loop++;
        }
        System.out.println(matrix);

    }

    private static int func(int index, int a, int loop, int[][] matrix) {
        if (loop * 2 == matrix.length + 1) {
            for (int i = loop - 1; i <= matrix[0].length - loop; i++) {
                if (index <= a) {
                    matrix[loop - 1][i] = index;
                    index++;
                } else {
                    matrix[loop - 1][i] = 0;
                }
            }
            return index;
        }
        if (loop * 2 == matrix[0].length + 1) {
            for (int i = loop - 1; i <= matrix.length - loop ; i++) {
                if (index <= a) {
                    matrix[i][loop - 1] = index;
                    index++;
                } else {
                    matrix[i][loop - 1] = 0;
                }
            }
            return index;
        }

        for (int i = loop - 1; i < matrix[0].length - loop; i++) {
            if (index <= a) {
                matrix[loop - 1][i] = index;
                index++;
            } else {
                matrix[loop][i] = 0;
            }
        }
        for (int i = matrix.length - loop; i > loop - 1; i--) {
            if (index <= a) {
                matrix[i][loop - 1] = index;
                index++;
            } else {
                matrix[i][loop - 1] = 0;
            }
        }
        return index;

    }
}
