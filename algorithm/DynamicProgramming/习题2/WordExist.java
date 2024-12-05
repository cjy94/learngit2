package com.chenjunyi.DynamicProgramming.习题2;

/**
 给定一个二维网格board，返回一个字符串单词word，如果word存在与网格中，返回true, 否则返回false
 */
public class WordExist {

    // 尝试从网格的任意位置出发，word是否存在
    public boolean exist(char[][] board, String word) {
        char[] s = word.toCharArray();
        for (int i =0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (f(board, s, i, j, 0))
                    return true;
            }
        }
        return false;
    }


    // 从board[i][j]位置出发，可以上下左右4个方向任意走，能否走出字符串s
    // len: 已经形成的长度
    private boolean f(char[][] board, char[] s, int i, int j, int len) {
        if (len == s.length)
            return true;

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != s[len])
            return false;

        // board[i][j] == s[len], 当前位置的字符满足，尝试想上下左右4个方向走
        char temp = board[i][j];
        board[i][j] = 0;
        boolean result = f(board, s, i-1, j, len+1) || f(board, s, i+1, j, len+1)
                        || f(board, s, i, j-1, len+1) || f(board, s, i, j+1, len+1);
        board[i][j] = temp;
        return result;
    }
}
