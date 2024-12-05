package com.chenjunyi.force;

/**
 * 有一个数组，数组中的值代表每一种货币的面值，每种货币可以使用任意张，给定一个总面值aim,使用货币凑出aim的方法数有多少种
 */
public class TryModelForMoney {
    public static int ways(int[] arr, int aim) {
        if (arr==null || arr.length==0 || aim <= 0) {
            return 0;
        }

        return process(arr, aim, 0);
    }

    // 可以自由选择[0..n]位置的货币，搞顶rest的方法数
    private static int process(int[] arr, int rest, int i) {
        if (rest < 0) {
            return 0;
        }
        if (i == arr.length) {
            return rest == 0 ? 1 : 0;
        }

        int ways = 0;
        for (int zhang = 0; rest >= zhang * arr[i];zhang++) {
            ways += process(arr, rest-zhang*arr[i], i+1);
        }
        return ways;
    }

    public static int waysWithCache(int[] arr, int aim) {
        if (arr==null || arr.length==0 || aim <= 0) {
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n+1][aim+1];
        for (int i =0; i <=n;i++) {
            for (int j =0; j <= aim; j++) {
                dp[i][j] = -1;
            }
        }

        return process1(arr, aim, 0, dp);
    }

    private static int process1(int[] arr, int rest, int i, int[][] dp) {
        if (dp[i][rest] != -1) {
            return dp[i][rest];
        }
        if (rest < 0) {
            dp[i][rest] = 0;
            return dp[i][rest];
        }
        if (i == arr.length) {
            dp[i][rest] =  rest == 0 ? 1 : 0;
            return dp[i][rest];
        }

        int ways = 0;
        for (int zhang = 0; rest >= zhang * arr[i];zhang++) {
            ways += process1(arr, rest-zhang*arr[i], i+1, dp);
        }
        dp[i][rest] = ways;
        return dp[i][rest];

    }


    public static int waysdp(int[] arr, int aim) {
        if (arr==null || arr.length==0 || aim <= 0) {
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n+1][aim+1];
        dp[n][0] = 1; // dp[n][1...] =0
        for (int index = n-1; index>=0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int zhang = 0; rest >= zhang * arr[index];zhang++) {
                    ways += dp[index+1][rest-zhang*arr[index]];
                }
                dp[index][rest] = ways;
            }
        }
        //
        return dp[0][aim];
    }

    public static int ways4(int[] arr, int aim) {
        if (arr==null || arr.length==0 || aim <= 0) {
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n+1][aim+1];
        dp[n][0] = 1; // dp[n][1...] =0
        for (int index = n-1; index>=0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index+1][rest];
                if (rest - arr[index] >= 0) {
                    dp[index][rest] += dp[index][rest-arr[index]];
                }
            }
        }
        //
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int[] arr = {5, 10, 50, 100};
        System.out.println(ways(arr, 1000));
        System.out.println(waysWithCache(arr, 1000));
        System.out.println(waysdp(arr, 1000));
        System.out.println(ways4(arr, 1000));
    }
}
