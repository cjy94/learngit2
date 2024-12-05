package com.chenjunyi.UnionSet;

/**
 * 
 */
public class isIands {
    
    
    public static int countIsLand(int[][] arr) {
        int res = 0;
        int n = arr.length;
        int m = arr[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[n][m] == 1) {
                    res++;
                    infect(arr, i, j, n, m);
                } 
            }
        }
        return res;
                
    }

    private static void infect(int[][] arr, int i, int j, int n, int m) {
        // base case 检查
        // 如果越界，或者arr[i][j] 不是1， 直接退出
        if (i < 0 || i >= n || j < 0 || j >= m || arr[i][j] != 1) {
            return;
        }

        arr[i][j] = 2;
        infect(arr, i-1, j, n, m);
        infect(arr, i+1, j, n, m);
        infect(arr, i, j-1, n, m);
        infect(arr, i, j+1, n, m);
    }

}
