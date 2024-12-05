package com.chenjunyi.practice;

/**
 * 超级水王问题
 *
 * 在一个数组中，是否有某个数的个数超过了半数（必须大于一半） N/2
 * 一次删掉两个不同值的数， 如果数组中存在水王数，那么最后这个数字会被剩下
 *
 * 如果无候选，当前数作为候选
 * 如果有候选，当前数和候选不同，count--； 当前数就是候选,count++
 *
 *
 * 扩展1： 摩尔投票
 * 扩展2： 给定一个正数k，返回所有出现次数 > N/K 的数     一次删掉（k）个数
 *  
 * 
 */
public class SuperWaterKing {

    public static void printWaterNumber(int[] arr) {
        int cand = 0;
        int count = 0;
        for (int i =0; i < arr.length; i++) {
            if (count == 0) {
                cand = arr[i];
            } else if (cand == arr[i]) {
                count++;
            } else {
                count--;
            }
        }

        if (count == 0) {
            System.out.println("No such number!");
            return;
        }

        count = 0;
        for (int i =0; i < arr.length; i++) {
            if (arr[i] == cand)
                count++;
        }
        if (count > arr.length / 2) {
            System.out.println(cand);
        } else {
            System.out.println("No such number!");
        }

    }
}
