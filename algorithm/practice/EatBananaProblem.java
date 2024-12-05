package com.chenjunyi.practice;

/**
 * 可可可以决定吃香蕉的速度，每小时她将会选择一堆香蕉，从中吃掉K跟，如果这堆香蕉少于K根，
 * 她将吃掉这对的所有香蕉，然后这一个小时内不再吃更多的香蕉
 * 可可可以慢慢吃，单仍然想在警卫回来前吃掉所有的香蕉，
 * 返回她可以在H小时内吃掉所有香蕉的最小速度K
 *
 *
 * 给定一堆香蕉和一个时间， 可以在规定时间内，以最慢的速度吃完这对香蕉
 * 给定一个香蕉数组，每个元素代表该位置有多少根香蕉，在规定的时间内，以最小的速度吃完所有香蕉， 给出这个最小的速度值
 *
 *  思路： 二分
 *
 *  最小速度L，最大速度R， 求mid位置的值，以mid速度吃完所有香蕉的时间如果大于T，那么速度mid小了，需要到[mid+1..r]位置在找合适的速度；
 *                   如果小于等于T， 那么可能时合适的速度，记录下来，再在[l...mid-1]的位置上寻找是否有更合适的mid速度
 */
public class EatBananaProblem {

    public static int minEatingSpeed(int[] bananas, int hour) {
        int L = 1;
        int R = 0;
        for (int banana : bananas) {
            R = Math.max(R, banana);
        }

        int ans = 0;
        while (L <= R) {
            int mid = L + ((R-L) >> 1);
            if (hours(bananas, mid) <= hour) {
                ans = mid;
                R = mid-1;
            } else {
                L = mid + 1;
            }
        }
        return ans;

    }



    

    //    bananas[i]: 每一对香蕉的个数，
    // 以speed速度吃完所有香蕉的小时数
    // a/b 向上取整    (a+b-1)/b
    public static int hours(int[] bananas, int speed) {
        int ans = 0;
        for(int banana : bananas) {
            ans += ((banana+speed-1)/speed);
        }
        return ans;

    }
}
