package com.chenjunyi.day09_DP;

public class Code02_ChangeProblem {

    /**
     * 从一组硬币种，最小选择几枚硬币，可以组成aim钱数
     * @param arr     硬币种类
     * @param aim     最终钱数
     * @return
     */
    private static int process1(int[] arr, int aim) {
        return f1(arr, aim, 0);

    }

    private static int f1(int[] arr, int aim, int index) {
        // 钱数剩余负数，无效的过程
        if (aim < 0) {
            return -1;
        }
        // 剩余钱数==0， 搞定了，还需要需要0枚硬币
        if (aim == 0) {
            return 0;
        }
        // aim>0, 但是已经没有硬币了，无效选择
        if (index == arr.length) {
            return -1;
        }

        // aim>0,并且也有硬币可选的情况
        // （1）不选当前硬币, 0+ 进行后续选择的硬币数量
        int p1 = f1(arr, aim, index+1);
        // (2) 选择当前硬币， 1+ 进行后续选择的硬币数量
        int p1Next = f1(arr, aim-arr[index], index+1);
        // 有可能p1和p1Next 是-1 无效选择的情况
        if (p1==-1 && p1Next==-1) {
            return -1;
        }  else {
            if (p1==-1) {
                return p1Next+1;
            } else if(p1Next==-1) {
                return p1;
            } else {
                return Math.min(p1, p1Next+1);
            }
        }
    }



    // 添加缓存的缓存，
    private static int process2(int[] arr, int rest) {
        int n = arr.length;
        int[][] dp = new int[n+1][rest+1];
        for (int i =0; i <=n; i++) {
            for (int j =0; j<= rest; j++) {
                dp[i][j] = -2;
            }
        }
        return f2(arr, rest, 0, dp);

    }

    private static int f2(int[] arr, int rest, int index, int[][] dp) {
        // rest为负数时，dp会出现越界的情况
        if (rest < 0) {
            return -1;
        }
        if (dp[index][rest] !=-2) {
            return dp[index][rest];
        }
        // 剩余钱数==0， 搞定了，还需要需要0枚硬币
        if (rest == 0) {
            dp[index][rest] = 0;
            return dp[index][rest];
        }
        // aim>0, 但是已经没有硬币了，无效选择
        if (index == arr.length) {
            dp[index][rest] = -1;
            return dp[index][rest];
        }

        // aim>0,并且也有硬币可选的情况
        // （1）不选当前硬币, 0+ 进行后续选择的硬币数量
        int p1 = f2(arr, rest, index+1, dp);
        // (2) 选择当前硬币， 1+ 进行后续选择的硬币数量
        int p1Next = f2(arr, rest-arr[index], index+1, dp);
        // 有可能p1和p1Next 是-1 无效选择的情况
        if (p1==-1 && p1Next==-1) {
            dp[index][rest] = -1;
        }  else {
            if (p1==-1) {
                dp[index][rest] = p1Next+1;
            } else if(p1Next==-1) {
                dp[index][rest] = p1;
            } else {
                dp[index][rest] = Math.min(p1, p1Next+1);
            }
        }
        return dp[index][rest];
    }

    public static void main(String[] args) {
        int[] arr = {5,3,2};
        System.out.println(process1(arr, 20));

    }


}



