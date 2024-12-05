package com.chenjunyi.day06;

public class Code03_PrintAllFold {
    public static void main(String[] args) {
        //printAllFolds(3);
        int[] arr = {2,7,11,15};

    }

    
    public static void printAllFolds(int n) {
        printProcess(1, n, true);
    }

    // 递归过程，来到某一个节点
    // i是节点的层数，N一共的层数，down=true 凹 dowm=false 凸
    private static void printProcess(int i, int n, boolean down) {
        if (i > n)
            return;
        printProcess(i+1, n, true);
        System.out.println(down ? "凹":"凸");
        printProcess(i+1, n, false);
    }
}
