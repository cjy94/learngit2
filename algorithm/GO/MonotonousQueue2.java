package com.chenjunyi.GO;

/**
 *  单调队列
 *
 *  1、求至少为k的最短子数组
 *  给定一个数组arr, 其中的值可能为负、正、0
 *  给定一个正数k， 返回累加和>=k的所有子数组中，最短子数组的长度
 *
 *   sum >= k 的最短长度
 *   以每一个位置为结尾情况下的能向左侧延伸的最短距离
 *   在单调队列中，从小到大
 *
 *  2、满足不等式的最大值
 *  一个数组points和一个正数k, 数组中每个元素都表示二维平面上的点坐标，并按照横坐标x从小到大排序
 *  就也是points[i]=[xi,yi]
 *  并且在1 <= i < j <= points.length 的前提下，xi < yi总成立
 *  请找出yi + yj + |xi - xj| 的最大值  ===>  yi + yj + xj - xi ==> (yi-xi) + (xj+yj)
 *  其中 |xi - xj| <= k, 并且 1 <= i < j <= points.length
 *
 *
 *  求： 横坐标的差值在<=k ，且纵坐标和最大的两个点 ?
 *   也就是来到一个点，向前找x轴距离不超过k，并且后一个点(x+y) + 前一个点(y-x)尽量大
 *   因为后一个点的(x+y)其实是固定的，也就是在x轴距离不超过k的前提下，前面的这个点y-x的差值要最大就满足题目要求
 *
 *   所以单调队列中的放入的元素是(x=a,y=b,y-x=b-a)，并且维持着按照y-x差值从大到小的单调性
 *
 *  3、安排的最多任务数目
 *  tasks[] 任务的力量值
 *  workers[] 工人的力量值
 *  有pills个神奇药丸，可以给一个工人增加strength个力量值
 *  返回最多有多少个任务可以完成
 *
 *    二分 + 单调队列  求解
 *   
 */

public class MonotonousQueue2 {

    // 2、不等式的最大值 ,
    public static int max(int[][] points, int k) {
        int n = points.length;
        int[][] maxQueue = new int[n][points[0].length];     // 按照差值(y-x)从大到小进入的队列
        int h =0, t = 0;
        int ans = 0;
        for (int r =0, x, y; r < n; r++) {
            x = points[r][0];
            y = points[r][1];
            // 从头弹出, 单调队列头节点的x坐标和当前节点的x距离不能超过k
            while (h < t && x-maxQueue[h][0] > k) {
                h++;
            }
            if (h < t) {    // (yi-xi) + (xj+yj)
                ans = Math.max(ans, x + y + (maxQueue[h][1] - maxQueue[h][0]));
            }
            
            // 从尾部进入, 队列要维持一个从大到小的单调性
            while (h < t && maxQueue[t-1][1] - maxQueue[t-1][0] <= (y-x)) {
                t--;
            }
            maxQueue[t][0] = x;
            maxQueue[t++][1] = y;
        }
        return ans;
    }

    // 1、至少为k的最短子数组
    public static int sumKShortestSubArray(int[] arr, int k) {
        int n = arr.length;
        int[] minQueue = new int[n];
        int ans = Integer.MAX_VALUE;
        int[] sum = new int[n+1];
        for (int i =0; i < n; i++) {
            sum[i+1] = sum[i] + arr[i];
        }
        int h=0, t=0;
        for (int r =0; r <= n; r++) {
            // 从头部弹出
            while (h < t && sum[r] - sum[minQueue[h]] >= k) {
                ans = Math.min(ans, (r-minQueue[h++]));
            }
            
            // 从尾部加入
            while (h < t && sum[minQueue[t-1]] >= sum[r]) {
                t--;
            }
            minQueue[t++] = r;
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

}
