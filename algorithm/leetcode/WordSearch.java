package com.chenjunyi.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class WordSearch {

    // 前缀树
    public class TrieNode {
        int end = 0;
        int pass = 0;
        TrieNode[] nexts;
        public TrieNode() {
            nexts = new TrieNode[26];
            end = 0;
            pass = 0;

        }

    }

    // 给定一个字符矩阵，可以从这个矩阵中的任意位置出发，能否组成word字符串问题
    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
         int m = board.length;
        int n = board[0].length;
        int index = 0;
        String sub = "";
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (board[row][col] == word.charAt(index)) {
                    // 枚举在board的每一个位置出发，是否能组成word的情况
                    if (process(board, word, row, col, sub, index, m, n)) {
                        return true;
                    }
                }
            }
        }
        return false;

    }

    // 从board[row][col]的位置出发
    private boolean process(char[][] board, String word, int row, int col, String sub, int i, int m, int n) {

        // base case
        // 比对到了word的结尾处
        if (i == word.length()) {
            return true;
        }

        if ( row < 0 || row == m || col < 0 || col == n || board[row][col] == 0 || word.charAt(i) != board[row][col]) {
            return false;
        }
        char ch = board[row][col];

        // 防止重复走
        board[row][col] = 0;
        // 尝试往上走
        boolean up = process(board, word, row - 1, col, sub, i+1, m, n);
        // 往下走
        boolean down = process(board, word, row + 1, col, sub, i+1, m, n);
        // 往左走
        boolean left = process(board, word, row, col - 1, sub, i+1, m, n);
        // 往右走
        boolean right = process(board, word, row, col + 1, sub, i+1, m, n);

        board[row][col] = ch;
        return up || down || left || right;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
//                {'B', 'A', 'C', 'E'},
//                {'S', 'F', 'C', 'S'},
//                {'A', 'D', 'E', 'E'}
                {'A'}

        };
        WordSearch test = new WordSearch();
        System.out.println(test.exist(board, "A"));


    }
}
