package com.chenjunyi.force;

/**
 * 货币找零问题
 */
public class CoinsWays {


    public static int way1(int[] arr, int aim) {
        return process(arr, 0 , aim);
    }

    private static int process(int[] arr, int i, int rest) {
        if (i == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int zhang = 0; zhang * arr[i] <= rest; zhang ++) {
            ways += process(arr, i+1, rest - zhang *arr[i]);
        }
        return ways;
    }
}
