package com.chenjunyi.practice;

/**
 *  求某个数的质数
 *
 *  偶数肯定不是质数， 所以最初的数字n的质数个数最多可能时n/2
 */
public class ZhiShuProblem {


    public static void main(String[] args) {
        long sum = 1;
        for (int i =1; i <=20; i++) {
            sum *= i;

        }
        System.out.println(Long.MAX_VALUE);
        System.out.println(sum);

        System.out.println(zero(25));
    }

    // 求1-n 所有数字中有多少个质数
    public static int zhiShu(int n) {
        int count = n / 2;  // 把偶数排除掉
        // 1 不是质数，2和任何数相乘都是偶数，故从3开始计算
        // 3*3 3*5 3*7 3*9 ....
        // 5*5 5*7 5*9 5*11 ...
        boolean[] f = new boolean[n];
        for (int i = 3; i < n; i += 2) {
            if (f[i])
                continue;
            for (int j = i * i; j < n; j += 2 * i) {
                if (!f[i]) {
                    --count;
                    f[i] = true;
                }
            }
        }
        return count;
    }

    public static int zero(int n) {
        int ans = 0;
        while (n != 0) {
            n /= 5;
            ans += n;
        }
        return ans;
    }

    

}
