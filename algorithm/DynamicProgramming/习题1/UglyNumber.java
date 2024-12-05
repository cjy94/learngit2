package com.chenjunyi.DynamicProgramming.习题1;

/**
 * 丑数 只包含质数因子2 3 或者5的正整数
 *  默认第一个丑数是1,前几项丑数为：
 *      1、2、3、4、5、6、7、8、9、10、12、15、16、18、20、24、25、27、30...
 *
 *   给你一个整数n，请找出并返回第n个丑数
 *   比如： n = 37, 返回125
 *
 *
 *  时间复杂度是 O(n)
 *
 */

public class UglyNumber {

    // 返回第n个丑数
    public static int ugly(int n) {
        int[] arr = new int[n+1];
        arr[1] = 1;
        int l2 = 1, l3 = 1 ,l5 = 1;
        for (int i =2; i <= n; i++) {
            int a = arr[l2] * 2;
            int b = arr[l3] * 3;
            int c = arr[l5] * 5;
            int cur = Math.min(Math.min(a,b), c);
            if (cur == a)
                l2++;
            if (cur == b)
                l3++;
            if (cur == c)
                l5++;
            arr[i] = cur;
        }
        return arr[n];
    }

    public static void main(String[] args) {
        System.out.println(ugly(37));
    }
}
