package com.chenjunyi.zhongji二.zhongji7;

/**
 * 在一个有序数组中，非降序，并且这个数组可能进行过旋转
 * {1,2,3,4,5,6,7} 有序数组，并且没有进行过旋转
 * {6,7,1,2,3,4,5}  旋转过的数组，旋转之前是有序数组
 * 在这样的数组中（可能旋转也可能没有旋转）找到最小值
 *
 * 数组可能会存在重复元素
 * 1、如果arr[]
 *
 *
 *
 */
public class XuanArray {

    public static int maxValue(int[] arr) {
        int l = 0;
        int r = arr.length-1;
        while (l < r) {
            // 如果数组中只有两个元素
            if (r-l == 1) {
                break;
            }
            int mid = l + ((r-l) >> 1);

            // 如果arr[l] < arr[r]
            // 说明没有旋转过，最小值就是arr[l]的位置
            if (arr[l] < arr[r]) {
                return arr[l];
            }

            // 如果有过旋转
            // arr[l] >= arr[r]
            if (arr[l] > arr[mid])  {
                r = mid;
                continue;

            }

            // arr[l] >= arr[r] && arr[l] <= arr[mid]
            if (arr[mid] > arr[r]) {
                l = mid;
                continue;
            }

            // arr[l] >= arr[r] && arr[l] <= arr[mid] && arr[mid] <= arr[r]
            // 这种情况只有一种， 就是arr[l] == arr[mid] == arr[r]
            while (l < mid) {
                if (arr[l] == arr[mid]) {  // {2,2,2,1,2,2}
                    l++;
                } else if (arr[l] < arr[mid]) {
                    return arr[l];
                } else { // arr[l] > arr[mid]  {2,1,2,2} 去左侧继续二分找最小
                    r = mid;
                    break;
                }
            }


        }
        return Math.min(arr[l], arr[r]);
    }

    public static void main(String[] args) {
        int[] arr = {2,2,2,1,2,2};
        System.out.println(maxValue(arr));

    }
}
