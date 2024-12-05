package com.chenjunyi.DynamicProgramming.最长递增子序列问题;

/**
 * 只有一次修改机会的最长不下降子序列
 *  给定一个长度为n的数组arr,和一个整数k
 *  只有一次机会可以将其中连续的k个数全修改称任意一个值
 *  这次机会可以用也可以不用，请返回最长不下降子序列长度
 *
 *  枚举每一个i位置， (0...i-k-1上最长的递增子序列长度) + k + (i-k...n上必须以(nums[i-k])开头的最长的递增子序列长度)
 *
 */
public class OnlyOneModifyLongestNoDecreasingSubSequence {
    public static void main(String[] args) {
        print(1802815274876960L);
        print((1802815274876960L) >>> 52);
        System.out.println((byte) 0xFF);

    }

    public static void print(long value) {
        StringBuffer str = new StringBuffer();
        for (int i = 63; i >= 0; i--) {
            str.append((value & (1L << i)) == 0 ? "0" : "1");

        }
        System.out.println(str);

    }
}
