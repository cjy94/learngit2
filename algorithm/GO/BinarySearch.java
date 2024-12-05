package com.chenjunyi.GO;

/**
 *  二分查找
 *  1、在一个有序数组中如何确定一个数字是否存在？
 *  2、在有序数组中找>=num的最左位置
 *  3、在有序数组中找<=num的最右位置
 *  4、二分搜索不一定发生发生在有序数组上（比如寻找峰值问题）
 *  
 */
public class BinarySearch {

                                                          
    // 在一个有序数组中查找num是否存在
    // 前提是arr数组有序
    public static boolean exist(int[] arr, int num) {
        if (arr == null || arr.length == 0)
            return false;

        int l = 0;
        int r = arr.length;
        while (l < r) {
            int mid = l + ((r-l)>>1);
            if (arr[mid] < num) {
                l = mid+1;
            } else if (arr[mid] > num){
                r = mid-1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static int leftMost (int[] arr, int num) {
        int ans = -1;
        int l = 0;
        int r = arr.length;
        while (l < r) {
            int mid = l + ((r-l)>>1);
            if (arr[mid] >= num) {
                ans = mid;
                r = mid-1;
            } else {
                l = mid+1;
            }
        }                                                                                                                                    
        return ans;
    }
}
