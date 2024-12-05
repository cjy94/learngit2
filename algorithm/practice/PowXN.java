package com.chenjunyi.practice;

/**
 *
 * 10^75
 * 暴力方法： 10 乘 75次
 *
 *
 * 计算  x^n     时间复杂度： log2^N
 * x=10, n=75     10^75
 * ans=1    a=x
 * 75的二进制是： 1001011
 *   64 32 16 8 4 2 1
 *   1  0  0  1 0 1 1
 *
 *   1001011 & 1
 *   75 & 1 != 0  -->   ans *= a;  a *= a;
 *   75 >> 1;
 *
 *   100101 & 1   != 0
 *   ans *= a    a *= a
 *   100101 >> 1
 *
 *   10010 & 1 == 0
 *   a *= a
 *   10010 >> 1
 *
 *   1001 & 1  != 0
 *   ans *= a    a *= a
 *   1001 >> 1
 *
 *   100 & 1 == 0
 *   a *= a
 *   100 >> 1
 *
 *   10 & 1 == 0
 *   a *= a
 *   10 >> 1
 *
 *   1 & 1  != 0
 *   ans *= a    a *= a
 *
 *   1 >> 1  == 0   结束
 *
 *   最终ans就是 x^n的答案
 *
 *   求某一个数的开根     利用二分法
 */
public class PowXN {

    // 当x是整数， n是正数的方法
    public static int pow(int x, int n) {
        int ans = 1;
        int a = x;

        while (n != 0) {
            if ((n & 1) != 0) {
                ans *= a;
            }
            a *= a;
            n >>= 1;
        }
        return ans;
    }

    // n可能是负数
    public static double pow(double x, int n) {
        double ans = 1D;
        double t = x;
        // 如果n是负数， 2^(-7) 先按照正数的流程求解， 2^7, 最后 [1 /（2^7）] 返回
        // 所以先对n进行处理， 对负数取绝对值，变成正数
        int pow = Math.abs(n == Integer.MIN_VALUE ? n+1 : n);
        while (pow != 0) {
            if ((pow & 1) != 0) {
               ans *= t;
            }
            pow >>= 1;
            t *= t;
        }

        // 最小值，无法使用绝对值转成正数， 越界
        if (n == Integer.MIN_VALUE)
            ans *= x;

        return n < 0 ? (1D / ans) : ans;
        
    }

    public static int sqrt(int x) {
        if (x == 0) {
            return 0;
        }

        if (x <= 3) {
            return 1;
        }

        long ans = 0;
        long L = 1;
        long R = x;
        while (L <= R) {
            long M = L + ((R-L) >> 1);
            if (M * M <= x) {
                ans = M;
                L = M + 1;
            } else {
                R = M -1;
            }
        }

        return (int)ans;


    }



    public static void main(String[] args) {
        System.out.println(pow(10D, Integer.MIN_VALUE));

        System.out.println(Math.pow(10D, Integer.MIN_VALUE));
    }
}
