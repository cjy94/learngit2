package com.chenjunyi.project;

/**
 *  给定一个数组arr，求解数组中长度为k的递增子序列个数
 */
public class IncreasingSubsequence {

    public static void main(String[] args) {

    }


    // 统计满足条件的三元组数量
    public static int countTriplets(int[] levels) {
        int ans = 0;
        int first = 0;
        int n = levels.length;
        boolean flag = false; // 标志是升序还是降序
        while (first < n -2) {
            int x = levels[first];  // 第一个元素
            int second = first+1;
            while (second < n-1) {
                int y = levels[second];
                if (x < y) {
                    flag = false;  //升序
                } else {
                    flag = true;  // 降序
                }
                int third = second+1;
                while (third < n) {
                    int z = levels[third];
                    if (flag) {
                        ans += z < y ? 1 : 0;
                    }  else {
                        ans += z > y ? 1 : 0;
                    }
                    third++;
                }
                second++;
            }
            first++;
        }
        return ans;
    }

}
