package com.chenjunyi.DynamicProgramming.最长递增子序列问题;

import java.util.Arrays;

/**
 *  最长数对链
 *  给你一个由n个数对组成的数对数组pairs
 *  其中pairs[i] = [left, right] 且left < right
 *  现在，我们定义一种跟随关系，当且仅当 b < c时
 *  数对p2 = [c, d]才可以跟在p1=[a,b]后面
 *  我们用这种形式的构造数对链找出并返回能够形成的最长数对链的长度
 *
 *  比如： [1,2] --> [7,8] --> [4,5]
 *     最长跟随链[1,2] -> [4,5] -> [7,8]
 *
 *
 *   将数对按照left从小到大进行排序
 *
 *
 *   也可以使用贪心算法解决
 *   将pairs按照right从小到大进行排序
 *   
 *
 *
 *
 *
 */
public class LongestPairLink {


    public static int longest(int[][] pairs) {
        Arrays.sort(pairs, (a,b) -> {
            return a[0] - b[0];
        });
        int n = pairs.length;
        int[] ends = new int[n];
        int len = 0;
        for (int i =0, find = 0; i < n; i++) {
            find = find(ends, len, pairs[i][0]);
            if (find == -1)
                ends[len++] = pairs[i][1];
            else
                ends[find] = Math.min(ends[find], pairs[i][1]);
        }
        return len;
    }

    private static int find(int[] ends, int len, int num) {
        int l = 0, r = len -1;
        int ans = -1;
        while (l <= r) {
            int mid = ((r-l)/2 + l);
            if (ends[mid] >= num) {
                ans = mid;
                r = mid -1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }
}
