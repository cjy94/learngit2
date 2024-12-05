package com.chenjunyi.leetcode;

import java.util.HashMap;

/**
 * 象棋游戏
 */
public class ChainXiangQi {



    public static void main(String[] args) {
       byte i= 81;
       while (i-- > 0) {
           if (i / 9 % 3 == i %9 % 3)
               continue;
           System.out.println("A:" + (i/9+1) + ", B: " + (i%9+1));
       }


       for(int j = 81; j > 0; j--) {
           System.out.println("A: " +j/9 + ", B: " + j%9);
       }


        

    }


    
    


}
