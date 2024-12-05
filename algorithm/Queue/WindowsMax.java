package com.chenjunyi.Queue;

import java.util.LinkedList;

/**
 * 滑动窗口中的最大值问题
 * 给定一个滑动窗口大小为w的数组A[]， 该滑动窗口从数组的最左边向最右边移动。假设只能看到再窗口中的w个数字，
 * 且每次窗口向右移动一个位置，求每次窗口中的最大值
 */
public class WindowsMax {
    public int[] windowsMax(int[] arr, int m) {
        int n = arr.length;
        int[] res = new int[n-m+1];
        int index = 0; // 用于res的索引信息
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i=0; i < n; i++) {
            while (!queue.isEmpty() && arr[queue.peekFirst()] <= arr[i]) {
                queue.pollLast();
            }
            queue.addLast(i);

            if (queue.peekFirst() == i -m ) {  // 队列首部的元素过期了
                queue.pollFirst();

            }
            if (i >= m-1) {
                res[index++] = arr[queue.peekFirst()];
            }
        }
        return res;
    }
}
