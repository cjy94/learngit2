package com.chenjunyi.day09_DP;

/**
 * 给定一个整形数组arr，代表数值不同的纸牌拍成一条线，玩家A和玩家B依次拿走每张纸牌，
 * 规定玩家A先拿， 玩家B后拿，但是每个玩家每次只能拿走最左或最右的纸牌，玩家A和玩家B都决定聪明，请返回最后的获胜者分数
 */
public class Code06_ZhiPaiJingJi {
    public static int maxCount(int[] arr) {
        if (arr==null || arr.length == 0) {
            return 0;
        }

        // 定义2个函数，代表先手函数和后手函数，
        // 分别记录先手拿牌的最大分数 和 后手拿牌的最大分数
        return Math.max(f(arr, 0, arr.length-1), s(arr, 0, arr.length-1));
    }

    // 先手函数
    private static int f(int[] arr, int l, int r) {
        // 只有一张纸牌的情况， 因为是先手函数，所以一定是先手将牌拿走，先手赢
        // base case
        if ( l == r) {
            return arr[l];
        }
        // 多张纸牌的情况
        // 1、先手拿走最左边纸牌的前提下，后手拿剩下纸牌的最小分数 是先手的得分情况
        // 2、先手拿走最右纸牌的前提下，后手拿剩下纸牌的最小分数，是先手的得分情况
        // 两种情况中最大的得分就是，先手的分数
        return Math.max(arr[l] + s(arr, l+1, r), arr[r] + s(arr, l, r-1));

    }

    // 后手函数
    private static int s(int[] arr, int l, int r) {
        // 如果只有一张纸牌，作为后手，没得拿，因为只有一张牌，先手拿完后，后手没有牌可那
        // base case
        if (l == r) {
            return 0;
        }
        // 多张纸牌的情况
        // 1、在先手拿了最左边纸牌后，后手接着拿的情况
        // 2、在先手拿了最右边指派后，后手接着拿的情况
        return Math.min(f(arr, l+1, r), f(arr, l, r-1));
    }

    public static int maxCountDp(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        // 创建两张dp表，一张先手表，一张后手表
        int n = arr.length;
        // 从左到右的范围尝试模型, l 和 r的变化范围是[0..n]
        int[][] f = new int[n][n];
        int[][] s = new int[n][n];
        // 填写对角线
        // dp[i][j] 的含义是，先手和后手分别在[l..r]范围上拿牌的得分情况
        // 所以对角线就是dp[i,i], 即只有一张牌的情况
        // 那么 先手的对角线就是arr[l]， 后手的对角线就是0,默认值
        for (int i =0; i < n; i++) {
            f[i][i] = arr[i];
        }

        // 普遍位置
        // 因为每个位置都依赖她的下面位置和左边位置，最终返回的是max(f(0, n), s(0,n)), 最右上角的位置
        // 所以dp表从下往上，从左往右依次填满
        for (int r =0; r< n; r++) {
            for (int l= r-1;l >= 0; l--) {
                // 先手
                f[l][r] = Math.max(arr[l] + s(arr, l+1, r), arr[r] + s(arr, l, r-1));
                // 后手
                s[l][r] = Math.min(f(arr, l+1, r), f(arr, l, r-1));
            }
        }
        return Math.max(f[0][ arr.length-1], s[0][arr.length-1]);

    }


    public static void main(String[] args) {
        int[] arr = {1,2,100,4};
        System.out.println(maxCount(arr));
        System.out.println(maxCountDp(arr));
    }
}
