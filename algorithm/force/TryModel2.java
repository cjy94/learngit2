package com.chenjunyi.force;

/**
 * 范围上的尝试模型  [L...R]
 *
 *
 * 给定一个整型数组，代表数组不同的纸牌拍成一条线，玩家A和玩家B依次拿走每张牌，规定玩家A先拿，玩家B后拿，
 * 但是每个玩家每次只能拿走最左或者最右的纸牌，
 * 玩家A个玩家B都绝顶聪明， 请返回最后的获胜者分数
 *
 *
 */
public class TryModel2 {


    public static int win1(int[] arr) {
        if (arr == null || arr.length==0) {
            return 0;
        }

        return Math.max( f(arr, 0, arr.length-1) ,  s(arr, 0, arr.length-1));
    }

    // 先手的尝试
    private static int f(int[] arr, int l, int r) {
        if (r-l == 0) {
            return arr[l];
        }
        // A在l位置的纸牌的情况下，B做出选择
       return Math.max( arr[l] +  s(arr, l+1, r),

                        // A 在拿r位置的纸牌，B做出的选择
                        arr[r] + s(arr, l, r-1) );
    }

    // 后手的尝试
    private static int s(int[] arr, int l, int r) {
        // 只有一张拍的话，后手必输
        if (l == r) {
            return 0;
        }

        // 先手选择的l位置的情况 和 选择了r位置的情况，选择最差的情况给到后手
        return Math.min(f(arr, l+1, r), f(arr, l , r-1));
    }


    public static int dpWin(int[] arr) {
        if (arr == null || arr.length==0) {
            return 0;
        }
        int n = arr.length;
        int[][] fdp = new int[n][n];
        int[][] sdp = new int[n][n];
        // 填对角线的值
        for (int i = 0; i< n; i++) {
            fdp[i][i] = arr[i];
        }
        
        // s[i][i]= 0;
        for (int i =1; i <n; i++) {
            int l = 0;
            int r = i;
            // 填对角线
            while (l<n && r < n) {
               fdp[l][r] =  Math.max(arr[l] + sdp[l+1][r], arr[r] + sdp[ l] [r-1] );
               sdp[l][r] = Math.min(fdp[l+1][r], fdp[l][r-1]);
                l++;
                r++;
            }
        }

        return Math.max(fdp[0][n-1], sdp[0][n-1]);
    }

}
