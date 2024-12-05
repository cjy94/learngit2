package com.chenjunyi.practice;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 给定一个数组nums，返回这个数组的等差数列  , 等差数列的子序列长度必须大于2, 能够成为等差数列的子序列有多少
 * 例如： [1,3,4,5,8,7,9]
 * 等差是2的子序列： [1,3,5,7,9] [1,3,5,7] [3,5,7,9]...
 *
 * 思路： 子序列问题，以某一个位置结尾的情况是什么
 *
 *
 * 数据大小：
 * 1 <= nums.length <= 1000
 * -2^31 <= nums[i] <= 2^31-1
 *
 *  
 */
public class DengChaShuLie {

    // nums.length = 10^3   时间复杂度：n^2 -> 10^6
    public static int numOfArithmeticSlices(int[] nums) {        //[7,8,9,0]
        int ans = 0;
        ArrayList<HashMap<Integer, Integer>> lists = new ArrayList<>();
        for (int i =0; i < nums.length; i++) {
            lists.add(new HashMap<>());
            for (int j = i-1; j >= 0; j--) {
                long diff = (long) nums[i] - (long) nums[j];
                if (diff < Integer.MIN_VALUE || diff > Integer.MAX_VALUE) {
                    continue;
                }
                int dif = (int) diff;
                int count =  lists.get(j).getOrDefault(dif, 0);
                ans += count;
                lists.get(i).put(dif, lists.get(i).getOrDefault(dif, 0) + count + 1);
            }
        }
        return ans;
        

    }

    public static void main(String[] args) {
        int[] array = {7,8,9,0};
        int i = numOfArithmeticSlices(array);
        System.out.println(i);
    }
}
