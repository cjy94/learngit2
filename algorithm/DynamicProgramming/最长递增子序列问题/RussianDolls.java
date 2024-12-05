package com.chenjunyi.DynamicProgramming.最长递增子序列问题;

import java.util.Arrays;

/**
 *  俄罗斯套娃问题
 *   给定一个二维数组envelopes, 其中envelopes[i]=[wi, hi]
 *    表示第i个信封的宽度和高度
 *    当另一个信封的宽度和高度都比这个信封大时
 *    这个信封就可以放进另一个信封里，如同俄罗斯套娃一样
 *    请计算最多能有多少个信封组成一组'俄罗斯套娃'信封
 *    即可以把一个信封放到另一个信封里面，不允许旋转信封
 *
 *
 *    信封按照宽度从小到大排序，如果宽度一样的，高度按从大到小排序
 *
 *    取出高度数组，计算高度数组的最大累加子序列
 *
 *    
 *     
 */
public class RussianDolls {

    public static int maxEnvelopes(int[][] envelopes) {
        // 将envelopes二维数组，按照0维上的数据从小到大排序，如果0维度上的值相同，那么再按照1维上的数值从大到小排序
        Arrays.sort(envelopes, (a, b) -> {
            return a[0] != b[0] ? (a[0] - b[0]) : (b[1] - a[1]);
        });

        int n = envelopes.length;
        int[] ends = new int[n];
        int len = 0;
        for (int i =0, find = 0; i < n; i++) {
            int num = envelopes[i][1];
            find = find(ends, len, num);
            if (find == -1)
                ends[len++] = num;
            else
                ends[find] = num;
        }

        return len;
    }

    // 再ends[0...len]范围上找到大于等于num最左的位置
    private static int find(int[] ends, int len, int num) {
        int l = 0;
        int r = len - 1;
        int ans = -1;
        while (l <= r) {
            int mid = ((r-l) / 2) + l;
            if (ends[mid] >= num) {
                ans = mid;
                l = mid - 1;
            } else {
                r = mid + 1;
            }
        }
        return ans;
    }
}
