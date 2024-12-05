package com.chenjunyi.day09_DP;

public class Code02_1ZhaoLingProblem {

    /**
     * 从一组硬币中，最小选择几枚硬币，可以组成aim钱数的最少货币数量
     * 每种货币数量可以自由使用
     * 如果组不成则返回-1
     * @param arr     硬币种类
     * @param aim     最终钱数
     * @return
     */

    private static int process1(int[] arr, int aim) {
        return f1(arr, aim, 0);

    }

    private static int f2(int[] arr, int start, int aim) {
        if (aim == 0) {
            return 0;
        }
        if (start >= arr.length) {
            return Integer.MAX_VALUE;
        }
        int min = Integer.MAX_VALUE;
        for (int j = 0; j <= aim/arr[start]; j++) {
            int res = f2(arr, start + 1, aim - (j * arr[start]));
            if (res != Integer.MAX_VALUE){
                // 说明凑的齐
                min = Math.min(min, j + res);
            }

        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    // 暴力递归的方式
    // 说明： arr[0..i]位置的货币任意选择，可以组成rest的最少货币数量
    private static int f1(int[] arr, int rest, int i) {
        //  目标值是0
        if (rest == 0) {
            return 0;
        }

        if (rest < 0) {
            return -1;
        }

        // 没有货币可以选择
        // 进入if的潜台词是aim>=0, 但是货币没了，说明无法组成
        if (i == arr.length) {
            return -1;
        }

        int zhang = Integer.MAX_VALUE;
        for (int j = 0; j * arr[i] <= rest; j++) {
            // 每一种货币可以使用0-任意张数
           int p1 = f1(arr, rest - arr[i]*j, i+1);
           if (p1 != -1) {
               // 说明凑得齐
               zhang = Math.min(zhang ,j + p1);
           }

        }
        //
        return zhang == Integer.MAX_VALUE ? -1 : zhang;

    }

    // 动态规划的方式
    // dp[i][j] 代表货币从[0...index]自由组合，必须组成aim的最小或i数量
    private static int dpWays(int[] arr, int aim) {
        int n = arr.length;
        int[][] dp = new int[n][aim+1];
        // 默认dp[0][0] = 0, 即使用arr[0]的货币组成0元，最少使用0个货币
        // 先填好第一行的数值。
        // 第一行的意义是： 当使用arr[0]的货币任议长可以组成aim[0...aim]的最少货币数量
        for (int i = 1; i <= aim; i++) {
            dp[0][i] = Integer.MAX_VALUE;
            if (i-arr[0] >= 0 && dp[0][i-arr[0]] != Integer.MAX_VALUE) {
                dp[0][i] = dp[0][i-arr[0]] + 1;
            }

        }

        // 第1列的数值，没意义， 代表自由使用arr[0..i]的货币可以组成0元的数量，默认使用0个货币，不需要填写
        // 从第一行和第一列的位置开始从左往右， 从上往下填
        int left = 0;
        for (int i= 1; i < n; i++) {
            for (int j = 1; j <= aim; j++) {
                left = Integer.MAX_VALUE;
               if (j - arr[i] >= 0 && dp[i][j-arr[i]] != Integer.MAX_VALUE) {
                   left = dp[i][j-arr[i]] + 1;

               }
               dp[i][j] = Math.min(left, dp[i-1][j]);
            }
        }
        // 返回右下角的值，即dp[n][aim] arr[0..n]的货币自由选择， 必须组成aim的最少货币数量
        return dp[n-1][aim] != Integer.MAX_VALUE ? dp[n-1][aim] : -1;

    }

    // ================================ 货币不允许自由使用，每种货币只有1张的问题============
    /**
     * 从一组硬币中，最小选择几枚硬币，可以组成aim钱数的最少货币数量
     * 每种货币数量只有1张
     * 如果组不成则返回-1
     * @param arr     硬币种类
     * @param aim     最终钱数
     * @return
     */

    private static int process2(int[] arr, int aim) {
        return f3(arr, aim, 0);

    }

    // arr[0..i]位置的每种货币只使用1张，必须组成aim的最少货币数量
    private static int f3(int[] arr, int rest, int i) {
        //  目标值是0
        if (rest == 0) {
            return 0;
        }

        if (rest < 0) {
            return -1;
        }

        // 没有货币可以选择
        // 进入if的潜台词是aim>=0, 但是货币没了，说明无法组成
        if (i == arr.length) {
            return -1;
        }

        // 使用0张
        int p1 = f3(arr, rest, i+1);
        // 使用1张
        int p2 = f3(arr, rest-arr[i], i+1);
        if (p1 == -1 && p2 == -1) {
            // 无法组成
            return -1;
        } else {
            if (p1 == -1) {
                return p2+1;
            } else if(p2 == -1) {
                return p1;
            } else {
                return Math.min(p1, p2+1);
            }
        }
    }

    // 动态规划的方式
    // dp[i][j] 代表货币从[0...index]自由组合，每种货币使用1张，必须组成aim的最小数量
    private static int dpWays2(int[] arr, int aim) {
        int n = arr.length;
        int[][] dp = new int[n][aim+1];
        // 默认dp[0][0] = 0, 即使用arr[0]的货币组成0元，最少使用0个货币
        // 先填好第一行的数值。
        // 第一行的意义是： 当使用arr[0]的货币任议长可以组成aim[0...aim]的最少货币数量
        for (int i = 1; i <= aim; i++) {
            dp[0][i] = Integer.MAX_VALUE;
            // 只是用1张，不能重复使用
            if (arr[0] == i) {
                dp[0][i] = 1;
            }
        }

        // 第1列的数值，没意义， 代表自由使用arr[0..i]的货币可以组成0元的数量，默认使用0个货币，不需要填写
        // 从第一行和第一列的位置开始从左往右， 从上往下填
        int left = 0;
        for (int i= 1; i < n; i++) {
            for (int j = 1; j <= aim; j++) {
                left = Integer.MAX_VALUE;
                // 在使用arr[i]货币的使用，检查dp[i-1][j-arr[i]]
                if (j - arr[i] >= 0 && dp[i-1][j-arr[i]] != Integer.MAX_VALUE) {
                    left = dp[i-1][j-arr[i]] + 1;

                }
                // dp[i-1][j] 不适用arr[i]的货币
                dp[i][j] = Math.min(left, dp[i-1][j]);
            }
        }
        // 返回右下角的值，即dp[n][aim] arr[0..n]的货币自由选择， 必须组成aim的最少货币数量
        return dp[n-1][aim] != Integer.MAX_VALUE ? dp[n-1][aim] : -1;

    }


    // ====================== 凑钱的方法数问题
    // arr[0..index]中的货币任意使用，可以组成aim的方法数由几种
    private static int method(int[] arr, int aim) {
        return process10(arr, aim ,0) ;

    }

    // 暴力递归
    private static int process10(int[] arr, int aim, int i) {
        if (i == arr.length) {
            return aim == 0 ? 1 : 0;
        }

        int ways = 0;
        for (int j =0; j * arr[i] <= aim; j++) {
            ways += process10(arr, aim - arr[i] * j, i+1);
        }
        return ways;

    }

    // 记忆搜索
    private static int method2(int[] arr, int aim) {
        int n = arr.length;
        int[][] dp = new int[n+1][aim+1];
        return process11(arr, aim ,0, dp) ;

    }

    private static int process11(int[] arr, int aim, int i, int[][] dp) {

        if (i == arr.length) {
            dp[i][aim] =  aim == 0 ? 1 : 0;
            return  dp[i][aim];
        }

        int ways = 0;
        for (int j =0; j * arr[i] <= aim; j++) {
            ways = dp[i+1][aim-arr[i]*j];
            if (ways != 0) {
                dp[i][aim] += ways;
            } else {
                dp[i][aim] += process11(arr, aim - arr[i] * j, i + 1, dp);
            }
        }
        return dp[i][aim];
    }

    // 说明该dp[i][j] 表示arr[0..i]上的货币自由选择，必须组成j的方法数有多少
    private static int dpWays3(int[] arr, int aim) {
        int n = arr.length;
        int[][] dp = new int[n][aim+1];
        dp[0][0] = 1;
        // 第一列，
        for(int j =1; j < n; j++) {
            dp[j][0] = 1;
        }
        // 第一行
        for (int i =1; i <= aim; i++) {
            dp[0][i] = i % arr[0] == 0 ? 1 : 0;
        }
        // 普遍位置
        // dp[i][j] = 不适用arr[i]位置的货币的方法数 + 使用arr[i](任意张)的货币的方法数
        for (int i =1; i < n; i++) {
            for (int j =1; j <= aim; j++) {

                // 使用arr[i]0张、 1张、 2张...的方法数
                // 方法一：
//                for (int zhang = 0; zhang * arr[i] <= j ;zhang++) {
//                    dp[i][j] += dp[i-1][j-arr[i]*zhang];
//
//                }

                // 方法二：
                // 枚举过程的优化
                dp[i][j] = dp[i-1][j];

                    if (j - arr[i] >= 0)
                        dp[i][j] += dp[i][j-arr[i]];





            }
        }
        // 右下角： 选择arr[0..n]位置上的货币任意张，组成aim的方法数
        return dp[n-1][aim];


    }


    public static void main(String[] args) {
        int[] arr = {5,10,25,1};
        System.out.println(method(arr, 15));
        System.out.println(method2(arr, 15));
        System.out.println(dpWays3(arr, 15));

    }



}
