package com.chenjunyi.MonotonicQueue.习题2;

/**
  不等式的最大值
    给你一个数组points和一个整数k
    数组中每个元素都表示二维平面上的点的坐标，并按照横坐标x的值从小到大排序
    也就是说points[i] = [xi, yi]
    并且再 1 <= i < j <= points.length 的前提下，xi < xj 成立
    请找出yi + yj + |xi - xj|的最大值
    其中|xi - xj| <= k 且 1 <= i < j <= points.length
    题目测试数据保证至少存在一堆能够满足|xi - xj| <= k 的点


    按照x轴从小到大排序， xi - xj <= 0总是成立，故|xi - xj| ==> xj - xi
    yi + yj + xj - xi = (后面点的y + 前面点的y ) + (后面点的x - 前面点的x) 的最大值    x轴上两个点的距离 <= k的前提下， 两个点的最大值
                      = 后面点的(x+y) + 前面点的(y-x) 尽量大，并且要保证 x轴的距离不超过k. 那也就是说一个再x和y轴计算个差值的前提下，能尽量王座延伸多少，满足前面的点距离我不超过k

    单调队列的单调性要根据题目确定好，

 */
public class InEqualityMaxValue {

    static int MAX = 10000;
    static int[][] queue = new int[MAX][2];
    static int h = 0;
    static int t = 0;

    public static int findMaxValueOfEquation(int[][] points, int k) {   // 
        int n = points.length;
        int ans = Integer.MIN_VALUE;
        for (int i =0; i < n; i++) {
            int x = points[i][0];
            int y = points[i][1];
            while (h < t &&  (queue[t-1][1] - queue[t-1][0]) <= (y-x)) {
                t--;
            }
            while (h < t && queue[h][0] + k < x) {
                h--;
            }
            if (h < t) {    // yi + yj + xj - xi
                ans = Math.max(ans, x+y+(queue[h][1]- queue[h][0]));
            }
            queue[t][0] = x;
            queue[t][1] = y;
            t++;

        }
        return ans;

    }
}
