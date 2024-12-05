package com.chenjunyi.zhongji二.zhongji8;

/**
 * 旋转数组问题
 */
public class RotateArray {
    // 问题1： 在旋转数组中找最小值问题
    public static int minValue(int[] arr) {   //{4,4,5,5,6,7,1,1,1,2,3}
        int l = 0;
        int r = arr.length-1;
        int m = -1;

        // 最小值一定在arr[l..r]范围上
        while (l < r) {
            if (l+1 == r) {
                break;
            }
            // 数组没旋转过
            if (arr[l] < arr[r]) {
                return arr[l];
            }
            m = (l+r)/2;
            if (arr[l] > arr[m]) {
                r = m;
                continue;
            }

            // arr[l] >= arr[r] && arr[l] <= arr[m]
            if (arr[m] > arr[r]) {
                l = m;
                continue;
            }
            //  arr[l] >= arr[r] && arr[l] <= arr[m] && arr[m] <= arr[r]
            //  来到此处的条件只能是： arr[l] == arr[r] == arr[m]
            while (l < m) {
                if (arr[l] == arr[m]) {
                    l++;
                } else if (arr[l] < arr[m]) {
                    return arr[l];
                } else { // arr[l] > arr[m]
                    r = m;
                    break;
                }
            }
        }
        return Math.min(arr[l], arr[r]);
    }



    // 问题2： 在旋转数组中查找某个值是否存在
    public static boolean isExist(int[] arr, int num) {
        int l = 0;
        int r = arr.length-1;
        int m = 0;
        while (l <= r) {            // {4,5,6,7,1,2,3} num=2
            m = (l+r)/2;
            if (arr[m] == num) {
                return true;
            }
            // arr[m] != num
            // 先讨论arr[l]、arr[m]、arr[r] 3个值可能相等的情况
            if (arr[l] == arr[m] && arr[m] == arr[r]) {
                while (l != m && arr[l] == arr[m]) {
                    l++;
                }
                if (l == m) {
                    l = m+1;
                    continue;
                }
            }


            // arr[l]、arr[m]、arr[r]不全都相等
            if (arr[l] != arr[m]) {
                if (arr[l] < arr[m]) {
                    if (arr[l] <= num && num < arr[m]) {
                        r = m-1;
                    } else {
                        l = m+1;
                    }
                }  else { // arr[l] > arr[m]
                    if (num > arr[m] && num <= arr[r]) {
                        l = m+1;
                    }  else {
                        r = m-1;
                    }
                }
            }  else {   // arr[m] != arr[r]
                if (arr[m] < arr[r]) {
                    if (arr[m] < num && num <= arr[r]) {
                        l = m+1;

                    }  else {
                        r = m-1;
                    }
                } else { // arr[m] > arr[r]
                   if (arr[l] <= num && num < arr[m]) {
                       r = m-1;
                   } else {
                       l = m+1;
                   }
                }
            }
        }
        return false;

    }

    public static void main(String[] args) {
        int[] arr = {4,5,6,7,1,2,3};
       // int value = minValue(arr);
        boolean exist = isExist(arr, 2);
        System.out.println(exist);
    }
}
