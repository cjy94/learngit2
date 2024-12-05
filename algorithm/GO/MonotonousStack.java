package com.chenjunyi.GO;

/**
 *  单调栈
 *
 *   1、数组中无重复值
 *    遍历阶段(满足条件的下标进栈) --- 清算阶段(栈中还有元素，需要依次弹出结算)
 *  
 *  2、数组中有重复值
 *    遍历阶段(满足条件的下标进栈) --- 清算阶段(栈中还有元素，需要依次弹出结算) --- 修正阶段(因为栈是严格单调的，当值相同时，结算会出现不准确的情况，要从后往前遍历检查正确性)
 *
 *  题目：
 *  1、每日温度
 *  给定一个整数数组，表示每天的温度，返回一个数组answer，其中answer[i]是指对于第i天，下一个更高的温度出现几天后
 *  如果气温在这之后都不会升高，请在该位置用0来代替
 *
 *  右侧比我大(严格大于)离我最近的温度在哪一天出现， 栈中单调性是从大到小
 *
 *  2、子数组的最小值之和
 *  给定一个整数数组arr, 找到min(b)的总和，其中b的范围为arr的每个连续子数组
 *  [3,4,6,4,5]
 *  子数组是[3] [4] [5] [3,4] [4,5] [3,4,5]
 *  每个子数组的最小值为：3、 4、 5、 3、 4、 3
 *  累加和是22
 *
 *  遍历数组，以每一个位置arr[i]组成一个子数组，子数组必须包含arr[i]，并且arr[i]还得是子数组中的最小值
 *
 *  3、柱状图中最大矩形
 *  以每一个位置进行讨论，arr[i]作为矩形的高， 左边比他小的离他最近的是这个矩形的左边界，右边比他小的离它最近的是这个矩形的右边界，这两个边界中间的长度是矩形的长，arr[i]是矩形的高?
 *
 *  4、最大矩形  时间复杂度是O(n * m), 需要看完整个矩阵就可以
 *  给定一个仅包含0和1、大小为rows*cols的二维二进制矩阵，找出只包含1的最大矩形，并返回面积
 *  数组压缩技巧  !!!!!
 *  矩阵问题，求子数组，第0行的全是1并且 最大累加和
 *                    第1行+第0行的全是1并且 最大累加和
 *  
 */

public class MonotonousStack {
    // 每日温度， 只有遍历阶段，没有清算阶段，因为题目要求没有结果是0，数组stack默认值就是0，清算阶段也是将这些位置填上0，可以不需要单独处理
    public static int[] temperature(int[] arr) {
        int n = arr.length;
        int[] stack = new int[n];   // 栈，存放是下标，不是数据
        int[] ans = new int[n];     // 返回结果
        int r = 0;
        for (int i =0; i < n; i++) {
            // **********相等也将数据加入单调栈中
            while (r > 0 && arr[stack[r-1]] < arr[i]) {
                int cur = stack[--r];
                ans[cur] = i-cur;
            }
            stack[r++] = i;
        }
        return ans;
    }

    static final int MOD = 1000000007;
    public static int sum(int[] arr) {
        int n = arr.length;
        int[] stack = new int[n]; // 从小到大
        int r = 0;
        long ans = 0;
        for (int i =0; i < n; i++) {   // 相等的情况如何处理? 
            while (r > 0 && arr[stack[r-1]] >= arr[i]) { // r-1 弹出   
                int cur = stack[--r];
                int left = r != 0 ? stack[r-1] : -1;
                ans = (ans + (long) arr[cur] * (cur-left) * (i-cur)) % MOD;
            }
            stack[r++] = i;
        }

        // 清算阶段
        while (r > 0) {
            int cur = stack[--r];
            int left = r != 0 ? stack[r] : -1;
            ans = (ans + (long) (arr[cur] * (cur -left) * (n-cur))) % MOD;
        }
        return (int)ans;
    }
}
