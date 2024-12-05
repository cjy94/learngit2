package com.chenjunyi.practice;

import com.chenjunyi.lock.SemaphoreTest;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 *   窗口内最大值最小值问题
 *   bulbs[i] == x, 代表在第(i+1)天将打开x位置的灯泡
 *   bulbs[]={9,8,3,4,2,5,6,1,7}
 *   bulbs[0]: 第1小时的时候9号灯泡点亮
 *   bulbs[1]: 第2小时的时候8号灯泡点亮
 *   bulbs[2]: 第3小时的时候3号灯泡点亮
 *   ...
 *
 *   k: 是否有哪一个时刻，两个灯泡中间间隔K个灯泡没有亮，这两个灯泡是亮的
 *   问题： 最早到来的这个时刻是第几个小时?
 *   
 *   灯泡数组转成小时数组
 *   {9,8,3,4,2,5,6,1,7}
 *    0 1 2 3 4 5 6 7 8
 *
 *    第9个灯泡在 第0个小时亮
 *    第8个灯泡在 第1个小时亮
 *    第3个灯泡在 第2个小时亮
 *    第4个灯泡在 第3个小时亮
 *    第2个灯泡在 第4个小时亮
 *    第5个灯泡在 第5个小时亮
 *    第6个灯泡在 第6个小时亮
 *    第1个灯泡在 第7个小时亮
 *    第7个灯泡在 第8个小时亮
 *
 *    1号灯泡什么时候亮； 2号灯泡什么时候亮
 *   {7,4,2,3,5,6,8,1,0}      k=4
 *
 */
public class KGuanBiBulbProblem {

    public static int kEmptySlots(int[] bulbs, int k) {
        // 将灯泡数组转成小时数组
        int N = bulbs.length;
        int[] hours = new int[N];

        for (int i =0; i < N; i++) {
            hours[bulbs[i]] = i;
        }
        int ans = -1;
        for (int L = 0, index = L + 1, R = L+k+1; R < N; index++) {
            // 1、index在hours[L...R]之间验证的时候，没通过
            // 2、index 撞上了 R
            if (hours[index] <= Math.max(bulbs[L], bulbs[R])) {  // L...R 不满足条件
                if (index == R) {
                    ans = Math.min(ans, Math.max(hours[L], hours[R]));
                }
            }
            L = index;
            R = L + k + 1;
        }
        return ans;

    }
}
