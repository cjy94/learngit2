package com.chenjunyi.DynamicProgramming.习题4;

/**
  骑士在棋盘的概率问题

  n*n 的国际象棋棋盘上，一个骑士从单元个(row, col)开始，并尝试进行k次移动，
 行和列从0开始，所以坐上单元格时(0,0)， 右下单元格是(n-1,n-1)
 象棋骑士有8种可能走法，
 每次骑士要移动时，它都会随机从8种可能的移动中选择一种，然后移动到哪里，
 骑士继续移动，直到它走了k步或者离开棋盘，返回骑士在棋盘停止移动后仍然留在棋盘上的概率

 留在棋盘的概率： 最终k步后还在棋盘的次数 / 所有尝试次数(k步后还在棋盘的方法数 + 不在棋盘的次数)

 8种走法,  马走日
 row-2, col+1
 row-1,col+2
 row+1,col+2
 row+2,col+1
 row+2,col-1
 row+1,col-2
 row-1,col-2
 row-2,col-1


 if (k <= 0)     没有步数可走了，还在棋盘上
    return 1;   肯定存活

 if(row < 0 || row >= len || col < 0 || col >= len)      不在棋盘上, 还有步数可走
    return 0;    没活着


    

 
 */
public class CheckerBoardProblem {
}
