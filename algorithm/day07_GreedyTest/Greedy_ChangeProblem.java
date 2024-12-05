package com.chenjunyi.day07_GreedyTest;

public class Greedy_ChangeProblem {
    public static final int COUNT_25 = 25;
    public static final int COUNT_10 = 10;
    public static final int COUNT_5 = 5;
    public static final int COUNT_1 = 1;


    public static void main(String[] args) {
        zhaoLingQianProblem(41);
    }


    public static void zhaoLingQianProblem(int money) {
        int total = money;
        int num_25 = 0, num_10 =0, num_5 = 0, num_1 = 0;

        while (money >= COUNT_25) {
            num_25++;
            money -= COUNT_25;
        }

        while (money >= COUNT_10) {
            num_10++;
            money -= COUNT_10;
        }

        while (money >= COUNT_5) {
            num_5++;
            money -= COUNT_5;
        }

        while (money >= COUNT_1) {
            num_1++;
            money -= COUNT_1;
        }
        System.out.println("money: " + total + " [25: " + num_25 + ", 10: " + num_10 + ", 5: " + num_5 + ", 1: " + num_1+"]");

    }

}
