package com.chenjunyi.day09_DP;

import java.util.HashMap;

/**
 * 给定一个无序数组arr, 返回其中最长的连续序列的长度
 * 思路： 连续子序列，首先需要是连续的数值，但是是子序列，所以不要求在数组中的位置是连续的
 * 简单解法就是，cur=arr[0..n], 在数组中依次查找cur+1,cur+2,cur+3...  直到数组尾部，记录max值
 * 需要有个容器将遍历过的元素进行保存，map<Interge,Integer>
 *
 *
 *   在hash表中判断一个数是否存在的时间复杂度是O(1)
 */
public class Code08_LongestConsecutive {

    // 简单解法，时间复杂度较高，O(N^2)的时间复杂度
    public static int method1(int[] arr) {
        int max = 1;
        // map中保存着 每一个值在连续区域的长度
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i =0; i < arr.length; i++) {   // {100,1,200,4,3,2,101}
            if(!map.containsKey(arr[i])) {
                map.put(arr[i], 1);
                // 查找arr[i]左边的值
                if (map.containsKey(arr[i]-1)) {
                    // 将左边的值和arr[i]进行合并，合并成一个区域，
                    // 100， 找到了99，[99,100] {99，2}， {100，2}
                    max = Math.max(max, merge(map, arr[i]-1, arr[i]));

                }
                // 查找arr[i]右边的值
                if (map.containsKey(arr[i]+1)) {
                    max = Math.max(max, merge(map, arr[i], arr[i]+1));

                }
            }
        }
            
        return max;

    }

    // 将less 和 more合并成一个区域
    private static int merge(HashMap<Integer, Integer> map, int less, int more) {
        // 找到less的最左侧区域
        int left = less - map.get(less) + 1;
        int right = more + map.get(more) -1;
        // 最左侧区域和最右侧区域的长度
        int len = right - left +1;
        map.put(left, len);
        map.put(right, len);
        return len;
    }

    public static void main(String[] args) {
        int[] arr = {5, 100, 4, 200, 1, 3, 2, 6, 7, 101};
        System.out.println(method1(arr));

    }
}
