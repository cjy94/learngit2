package com.chenjunyi.force;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * （1）有一个正数数组，arr[3，2，7], 代表每台咖啡机冲咖啡的时间 ， 有3台咖啡机，每台咖啡机工作时间是3，2，7
 * （2）N 有n个人喝咖啡，每人喝1杯
 * （3） a 只有1台洗咖啡杯的机器，并且洗咖啡杯的时间是a，一杯一杯洗
 * （4）b 咖啡杯不洗， 自然挥发也能变干净的时间
 *
 * 问 从n个人选择泡咖啡开始，到咖啡杯全部干净的时间
 * 求解流程：
 * （1）求出每个人喝咖啡的最少时间，利用堆
 * （2）洗咖啡杯的时间，暴力递归
 *
 */
public class Coffe {
    public static class Machine{
        public int workTime;
        public int timePoint;
        public Machine(int timePoint, int workTime) {
            this.timePoint = timePoint;
            this.workTime = workTime;
        }
    }

    public static  class MachineCompare implements Comparator<Machine> {
        @Override
        public int compare(Machine o1, Machine o2) {
            return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
        }
    }

    public static int minTimes2(int[] arr, int n, int a, int b) {
        PriorityQueue<Machine> heap = new PriorityQueue<>(new MachineCompare());
        for (int i=0; i < arr.length; i++) {
            heap.add(new Machine(0, arr[i]));
        }
        // 每个人喝完一杯咖啡的最少时间
        int[] drinks = new int[arr.length];
        for (int i =0; i < n; i++) {
            Machine cur = heap.poll();
            cur.timePoint += cur.workTime;
            drinks[i] = cur.timePoint;
            heap.add(cur);
        }
        return process(drinks, a, b, 0, 0);
    }




    // 洗咖啡杯的时间
    // 假设洗咖啡的机器在washLine的时间有空， 如果要洗完从index出发往后所有的咖啡杯，返回最少的时间
    // 暴力过程求解
    public static int process(int[] drinks, int a, int b, int index, int washLine) {
        // 洗最后一个杯子的最少时间
        if (index == drinks.length-1) {
            // 自然风干的时间
            int x1 = drinks[index] + b;
            // 咖啡机洗的时间
            int x2 = Math.max(drinks[index], washLine) + a;

            return Math.min(x1, x2);

        }
        // 还有咖啡杯可洗
        // 在当前杯子选择用咖啡机洗, 则咖啡机洗完这杯咖啡之后的空闲时间是max(当前的最早空闲时间, 喝完咖啡杯子的空闲时间) + a
        int wash = Math.max(drinks[index] , washLine) + a;
        // 当前杯子选择用咖啡机洗之后，后续杯子的最早结束时间
        int p1 = process(drinks, a, b, index+ 1, wash);
        // 洗完所有咖啡杯的最早时间，当前咖啡杯洗完， 所有咖啡杯全部洗完才叫所有时间
        
        // 当前杯子选择自然风干的情况下，剩余杯子的时间
        int p2Next = process(drinks, a, b, index+1, washLine);
        return Math.min(Math.max(p1, wash), Math.max(p2Next, drinks[index]+b));
    }


    // 动态规划
    public static int dpWays(int[] drinks, int a, int b) {
        int n = drinks.length;
        int maxWash = 0;
        for (int i =0; i< drinks.length; i++) {
            maxWash = Math.max(drinks[i], maxWash) + a;
        }
        int[][] dp = new int[n][maxWash+1];

        //int[][] dp = new int[n][drinks[n-1]+n*a];
        // 填最后一行的元素
        for (int index =0; index < dp[0].length; index++) {
            dp[n-1][index] = Math.min(drinks[n-1] + b, Math.max(drinks[n-1], index) + a);
        }

        for (int row = n-2; row >= 0; row--) { // 咖啡杯的编号
            for (int col = 0; col < maxWash; col++) { //
                int wash = Math.max(drinks[row] , col) + a;
                dp[row][col] = Math.min(Math.max(dp[row+1][wash], wash), Math.max(dp[row+1][col], drinks[row]+b));
                
            }
        }
        return dp[0][0];
    }
}
