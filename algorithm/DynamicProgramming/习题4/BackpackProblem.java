package com.chenjunyi.DynamicProgramming.习题4;

import java.util.ArrayList;

/**
    1和0 (多维背包问题)
    给你一个二进制字符数组strs和两个整数m,n
    请找出并返回strs的最大子集的长度， 该子集中最多包含m个0和n个1

    strs：["10", "0001", "111001", "1", "0"]   M=5,n=3
    最多包含5个0和3个 1的最大子集是{"10", "0001", "1", "0"}， 答案是4

    f(strs, m,n, index, list)
    尝试： 每一个strs[i]的字符串有要和不要两种选择，
    if (index == strs.length)

    不要index的字符串， f(strs, m, n, index+1, list)
    要index的字符串， f(strs, m, n, index, list.add(strs[index]));





 */
public class BackpackProblem {

    public static void main(String[] args) {
        String[] strs = {"10", "0001", "111001", "1", "0", "01010", "01000", "00011", "01", "111", "010"};
        ArrayList<String> list = new ArrayList<>();
        int process = process(strs, 10, 8, 0, list);
        int process1 = process1(strs, 0, 10, 8);
        System.out.println(process + " ====== " + process1);

    }

    // strs[i...] 自由选择，希望0的数量不超过z，1的数量不超过o
    // 最多能选多少字符串
    public static int process1(String[] strs, int i, int z, int o) {
        if (i == strs.length)
            return 0;
        // 不要strs[i]位置的字符串
        int p1 = process1(strs, i + 1, z, o);
        // 要strs[i]位置的字符串
        // 先计算strs[i]字符串的0和1数量
        int p2 = 0;
        int[] num = numZeroAndOne(strs[i]);
        if (num[0] <= z && num[1] <= o)
            p2 = 1 + process1(strs, i + 1, z - num[0], o - num[1]);

        return Math.max(p1, p2);
        
    }

    private static int[] numZeroAndOne(String s) {
        int zero =0 ,one = 0;
        for (int i =0; i < s.length(); i++) {
            if (s.charAt(i) == '0')
                zero++;
            else
                one++;
        }
        return new int[] {zero, one};
    }

    public static int process(String[] str, int z, int o, int index, ArrayList<String> list) {
        // base case中一定要保证任何条件都能正确被return， 否则就会出现栈溢出的bug
        if (index >= str.length) {
            int zero = 0;
            int one = 0;
            for (String s : list) {
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '0')
                        zero++;
                    else
                        one++;
                }
            }
            if (zero <= z && one <= o)                
                return list.size();
            else
                return 0;   // 很重要
        }
        // 不要index的字符串
        int p1 = process(str, z, o, index+1, list);
        // 要index的字符串
        String temp =  str[index];
        list.add(temp);
        int p2 = process(str, z, o, index+1, list);
        list.remove(temp);
        return Math.max(p1, p2);
    }
}
