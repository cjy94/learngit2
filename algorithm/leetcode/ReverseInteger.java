package com.chenjunyi.leetcode;

public class ReverseInteger {
    public int reverse(int x) {

        long sum = 0;
            // 大于0的情况
            while (x!=0) {
                int lastDigit = x%10;
                sum += lastDigit;
                sum = sum * 10;
                x = x/ 10;
            }
            sum = sum /10;
            if (sum > Integer.MAX_VALUE || sum < Integer.MIN_VALUE) {
                return 0;
            }

        return  (int) sum;

    }

    public static void main(String[] args) {
        ReverseInteger test = new ReverseInteger();
        System.out.println(test.reverse(-123));
        System.out.println(10000000*9);


    }

}
