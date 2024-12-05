package com.chenjunyi.practice;

/**
 * 对某数n开根号
 * 二分法
 */
public class Sqrt {
    public static void main(String[] args) {

    }

    public static int mySqrt(int n) {
        if (n == 0) {
            return 0;
        }
        if (n < 3) {
            return 1;
        }
        int l = 1;
        int r = n;
        int ans = 1;
        while (l < r) {
            int mid = l + ((r-l) >> 1);
            if (mid * mid <= n) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }

        }

        return ans;

    }

}
