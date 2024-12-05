package com.chenjunyi.project;

/**
 *  最多的座位
 */
public class MaxSeats {
    public static void main(String[] args) {
        String s = "0101";
        System.out.println(calculateMaxAudience(s));
    }

    private static int calculateMaxAudience(String seats) {
        int count = 0;
        int zeroCount = 0;
        int n =seats.length();
        for (int i = 0; i < n; i++) {
            if (seats.charAt(i) == '0'){
                if ((i == 0 && seats.charAt(i+1) =='0') || (i == n - 1 && seats.charAt(i-1) =='0')){  // 开头和结尾的情况
                    count++;
                } else {
                    zeroCount++;
                    if (zeroCount == 3) {
                        count++;
                        zeroCount = 0;
                    }
                }
            }
        }
        return count;
    }
}
