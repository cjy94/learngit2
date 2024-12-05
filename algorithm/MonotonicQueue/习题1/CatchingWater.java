package com.chenjunyi.MonotonicQueue.习题1;

import java.util.Arrays;

/**
接取落水的最小花盆

 给出N滴水坐标，y表示水滴的高度，x表示他下落到x轴的位置
 你需要把花盆放在x轴上的某个位置
 使得从被花盆接住的第一滴水开始，到被花盆接着的最后一滴水结束，之间的时间差至少是D，
 给出N滴水的坐标和D的大小，请算出最小的花盆宽度W
 */
public class CatchingWater {

    static int MAX = 10000;
    static int[] maxQueue = new int[MAX];    // 维护一个最大值的单调队列
    static int[] minQueue = new int[MAX];    // 维护一个最小值的单调队列
    static int minh, mint, maxh, maxt;
    static int[][] water = new int[MAX][2];    // 每一个水滴有2个信息，[0]: x轴的位置； [1] 到x轴的高度
    static int N;
    static int D;


    //
    public static int water1(int[][] matrix, int limit) {
        water = matrix;
        N = matrix[0].length;
        D = limit;
        // 将水滴的x轴信息进行排序
        Arrays.sort(water, 0, N, (a,b) -> a[0] - b[0]);
        minh = mint = maxh = maxt = 0;
        int ans = Integer.MAX_VALUE;
        for (int l =0, r = 0; l < N; l++) {
            // [l,r)
            while (!ok() && r < N) {  // 如果现在窗口中的max-min < limit, 并且还有水滴，进入队列中
                push(r++);
            }
            if (ok())
                ans = Math.min(ans, water[r-1][0] - water[l][0]);
            pop(l);
        }

        return ans;

    }

    private static boolean ok() {
        int max = maxh < maxt ? water[maxQueue[maxh]][1] : 0;
        int min = minh < mint ? water[minQueue[minh]][1] : 0;
        return max - min >= D;
    }

    private static void push(int i) {
        // i的值小于maxt-1的值才可以进入队列
        while ( maxh < maxt && water[maxQueue[maxt-1]][1] <= water[i][1]) {
            maxt--;
        }
        maxQueue[maxt++] = i;

        while (minh < mint && water[minQueue[mint-1]][1] >= water[i][1]) {
            mint--;
        }
        minQueue[mint++] = i;
    }

    private static void pop(int i) {
        if (maxh < maxt && maxQueue[maxh] == i)     // 如果是过期的下标
            maxh++;

        if (minh < mint && minQueue[minh] == i)
            minh++;

    }
}
