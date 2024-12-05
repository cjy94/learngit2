package com.chenjunyi.project;

/*
  与n二进制中1数量相同的比n大的最小值
  计算出n中1的个数，oneCount
  从n+1...开始查找比n大并且oneCount相同的数m
 */
public class SameOneCount {
    public static void main(String[] args) {
        System.out.println(oneCount(45));

    }

    public static int oneCount(int n) {
        int m = n+1;
        int nCount = bitsCount(n);
        while (bitsCount(m) != nCount) {
            m++;
        }
        return m;

    }

    public static int bitsCount(int n) {
        int count = 0;
        while (n != 0) {
            count += (n & 1);
            n = n >> 1;
        }
        return count;
    }


}
