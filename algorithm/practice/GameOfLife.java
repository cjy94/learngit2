package com.chenjunyi.practice;

/**
 * 生命游戏问题： 一个二维数组， 0代表不存在生命 1代表存在生命
 * 遵循以下4中生存规律：
 * 1) 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 2) 如果活细胞周围八个位置有两个或者三个活细胞，则该位置活细胞仍然存活；
 * 3) 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 4) 如果死细胞周围八个位置有三个活细胞，则该位置死细胞存活
 */
public class GameOfLife {

    public void gameOfLife(int[][] board) {
        for (int i =0; i < board.length; i++) {
            for (int j =0; j < board[0].length; j++) {
                int counts = neighbors(board, i, j);
                if (counts == 3 || (board[i][j] == 1 && counts == 2)) {
                    board[i][j] |= 2;
                }
            }
        }

        for (int i =0; i < board.length; i++) {
            for (int j =0; j < board[0].length; j++) {
                board[i][j] >>= 1;
            }
        }

    }


    public static int neighbors(int[][] b, int i, int j) {
        return f(b, i-1, j) + f(b, i+1,j) + f(b, i, j-1) + f(b, i, j+1) +
               f(b, i-1, j-1) + f(b, i-1, j+1) + f(b, i+1, j-1) + f(b, i+1, j+1);
    }

    // 返回board[i][j]是1，就返回1； 否则返回0
    public static int f(int[][] board, int i, int j) {
        return  (i >= 0 && i < board.length && j >= 0 && j < board[0].length && board[i][j] == 1) ? 1 : 0;
    }
}
