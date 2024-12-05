package com.chenjunyi.Stack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 有一个整形数组arr和一个大小为m的窗口从数组的最左边滑倒最右边，窗口每次
 * 向右边滑一个位置
 *
 * 例如： 数组[4,3,5,4,3,3,6,7] 窗口大小为3时：
 * [4 3 5]4 3 3 6 7
 * 4[3 5 4] 3 3 6 7
 * 4 3 [5 4 3] 3 6 7
 * 4 3 5 [4 3 3] 6 7
 * 4 3 5 4 [3 3 6] 7
 * 4 3 5 4 3 [3 6 7]
 *
 * 结果是： [5 5 5 4 6 7]
 *
 * 利用队列实现，队列中的元素始终保持从大到小的顺序，那么指定的范围内[l...r] 队列头的元素一定是该范围内的最大值
 * 这个窗口中的
 *
 *
 * 滑动窗口解决类似于"请找到满足xx的最x的区间（子串、子数组）的xxx" 这类问题
 */
public class HuaDongChuangKou {
    public static class WindowsMax{
        private int l;
        private int r;
        private int[] arr;
        private LinkedList<Integer> queue = new LinkedList<>();

        public WindowsMax(int[] arr) {
            this.arr = arr;
            l = -1;
            r = 0;
        }

        public void addNumFromRight(){
            if (r == arr.length) {
                return;
            }
            while (!queue.isEmpty() && arr[queue.peekLast()] <= arr[r]) {
                queue.pollLast();
            }
            queue.addLast(r);
            r++;
        }

        public void removeNumFromLeft() {
            if ( l >= r-1) {
                return;
            }
            l++;
            if (queue.peekFirst() == l) {
                queue.pollFirst();
            }
        }

        public Integer getMax() {
            if (!queue.isEmpty()) {
                return arr[queue.peekFirst()];
            }
            return null;
        }
    }


    public static int[] maxVlaue(int[] arr, int m) {
        int n = arr.length;
        int[] res = new int[n-m+1];
        int index = 0;
        // 队列中存储的时索引信息，不是数据
        LinkedList<Integer> queue = new LinkedList<>();
        // 窗口的右边界right,  左边界就是right-m
        for (int right =0; right < n; right++) {
            while (!queue.isEmpty() && arr[queue.peekLast()] <= arr[right]) {
                queue.pollLast();
            }
            queue.addLast(right);
            if (queue.peekFirst() == right - m) {
                queue.pollFirst();
            }
            if (right >= m -1) {
                res[index++] = arr[queue.peekFirst()];
            }
        }
        return res;
    }


    public static int[] sum(int[] arr, int m) {
        int n = arr.length;
        int index = 0;
        int sum = 0;
        int[] res = new int[n-m+1];
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i =0; i < n; i++) {
            queue.addLast(i);
            sum += arr[i];
            if (queue.peekFirst() == i-m) { // 过期

                sum -= arr[queue.peekFirst()];
                queue.pollFirst();

            }
            if (i >= m-1) {
                res[index++] = sum;
            }

        }
        return res;
    }

    //=========================== 尽可能使字符串相等 ===========================

    /**
     * 给你两个长度相同的字符串，s 和 t。
     *
     * 将 s 中的第 i 个字符变到 t 中的第 i 个字符需要 |s[i] - t[i]| 的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。
     *
     * 用于变更字符串的最大预算是 maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
     *
     * 如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。
     *
     * 如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0
     *
     * 例如：
     * 输入：s = "abcd", t = "bcdf", cost = 3
     * 输出：3
     * 解释：s 中的 "abc" 可以变为 "bcd"。开销为 3，所以最大长度为 3。
     * right=0   b-a = 1   sum=1
     * right=1   c-b = 1   sum=2
     * right=2   d-c = 1   sum=3
     * right=3   d-f = 2   sum=5 > maxCost, 所以left右移
     *
     *
     */
    public static int equalssubString(String s, String t, int maxCost) {
        int left = 0;
        int right = 0;
        int sum = 0;
        int res = 0;
        while (right < s.length()) {
            sum += Math.abs(s.charAt(right) - t.charAt(right));
            right++;
            // 如果窗口中的答案超过了maxCost, 那么left右移
            while (sum > maxCost) {
                sum -= Math.abs(s.charAt(left) - t.charAt(left));
                left++;

            }
            res = Math.max(res, right - left);
        }
        return res;
        
    }


    // ================= 最大元音字符的长度 =====================
    /**
     *    给你字符串 s 和整数 k 。
     *
     * 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
     *
     * 英文中的 元音字母 为（a, e, i, o, u）。
     *
     * 例如：
     * 输入：s = "abciiidef", k = 3
     * 输出：3
     * 解释：子字符串 "iii" 包含 3 个元音字母。
     *
     * abc 包含a,  length=1
     * bci 包含i,  length=1
     * cii 包含ii, length=2
     * iii 包含iii, length=3
     * iid 包含ii, length=2
     * ide 包含ie, length=2
     * def 包含e, length=1
     * 
     *
     */

    public static int maxYuan(String s, int m ) {

        int right = 0;
        int max = 0;
        int sum = 0;
        int left = 0;
        while (right < s.length()) {
            sum += isYuan(s.charAt(right));  // 该字符是原因，则长度加1
            right++;
            
            if (right >= m) {
                max = Math.max(max, sum);
                // 前面加了一个新的字符，现在窗口右移，需要减去一个字符
                sum -= isYuan(s.charAt(right - m));

            }

        }
        return max;
    }

    private static int isYuan(char s) {
        return s == 'a' || s == 'e' || s == 'i' || s== 'o' || s == 'u' ? 1 : 0;
    }


    public static void main(String[] args) {
        int[] arr = {4,3,5,4,3,3,6,7};
        int[] ints = maxVlaue(arr, 3);
        int[] ans = sum(arr, 3);
        System.out.println(Arrays.toString(ans));
    }

}
