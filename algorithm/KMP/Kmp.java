package com.chenjunyi.KMP;

import com.chenjunyi.sort.Practices4;

import java.util.Arrays;

/*
    KMP 实现字符串匹配，
    该算法整体时间复杂度是O(N)， 其中N是较长的字符串的长度

    bfprt: 在一个无序数组中， 找出第k小的数    类似快速排序

        1、随机选择一个数 (和快排唯一不同的地方)
        2、荷兰国旗问题
        3、命中直接返回，小于左边递归， 大于右边递归

        如何选择这个数
        1、0~4、5~9、10~14 ... 算作一组数
        2、0~4 内部排序  5~9内部排序  10~14内部排序...
        3、将每组中的中位数取出m0,m1,m2,m3...  [m0,m1,m2,m3...mn] m数组
        4、在m数组中找到中位数， 递归调用bfprt()

        再一个无序数组中找到第k小的数，


        bfprt：在一个无序数组中，找到中位数
 */
public class Kmp {

    // arr[l...r] 如果排序的话，位于k位置上的数什么
    // k一定在[l..r]范围上
    public static int bfprt(int[] arr, int l , int r, int index) {
        if(l == r) {
            return arr[l];
        }

        // 精挑细选的一个数
        int pivot = medianOfMedians(arr, l , r);
        int[] range = partition(arr, l, r, pivot);

        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return bfprt(arr, l, range[0]-1, index);
        } else {
            return bfprt(arr, range[1]+1, r, index);
        }

    }

    private static int medianOfMedians(int[] arr, int l, int r) {
        int size = r-l+1;
        int offset = size % 5 == 0 ? 0 : 1;
        int mArr[] = new int[size / 5 + offset];

        for (int team = 0; team < mArr.length; team++) {
            int teamFirst = l + team * 5;
            mArr[team] = getMedian(arr, teamFirst, Math.min(r, teamFirst+4));
        }
        return bfprt(mArr, 0, mArr.length-1, mArr.length / 2);
    }

    private static int getMedian(int[] arr, int l, int r) {
        insertSort(arr, l , r);
        return arr[(l+r)/2];


    }

    private static void insertSort(int[] arr, int l, int r) {
        for (int start = l+1; start <= r; start++) {
            int i = start;
            while (i-1 >= 0 && arr[i-1] > arr[i]) {
                swap(arr, i, i-1);
                i--;
            }
        }
    }

    public static void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }


    // 在arr 上找第K小的数，
    // arr[l..r] 范围上，如果排序的话， 找到位于index的数
    // index 一定位于[l...r]中间
    public static int process(int[] arr, int l , int r, int index) {
        if(l == r) {
            return arr[l];
        }
        int pivot = l + (int)(Math.random() * (r-l+1));
        int[] range = partition(arr, l, r, pivot);

        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return process(arr, l, range[0]-1, index);
        } else {
            return process(arr, range[1]+1, r, index);
        }

    }

    private static int[] partition(int[] arr, int l, int r, int pivot) {
        return new int[] {-1};
    }


    public static int getIndexOf(String s1, String s2) {
        if (s1 ==null || s2 == null || s2.length() < 1 || s2.length() > s1.length()) {
            return -1;
        }

        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int i1 = 0;
        int i2 = 0;
        //获取字符串str2的next数组
        int[] next = getNextArray(str2);
        // 两个字符串匹配
        while (i1 < str1.length && i2 < str2.length) {
            if (str1[i1] == str2[i2])  {
                i1++;
                i2++;
            } else if (i2 < 0) {
                i1++;

            } else {
                i2 = next[i2];
            }

        }

        return i2 == str2.length ? i1-i2 : -1;


    }

    private static int[] getNextArray(char[] s) {
        if (s.length == 1) {
            return new int[] {-1};
        }
        int[] next = new int[s.length];
        next[0] = -1;
        next[1] = 0;
        int i =2;
        int nextChar = 0;
        while (i < s.length) {
            if (s[i-1] == s[nextChar]) {
                next[i++] = ++nextChar;
            }
            else if(nextChar > 0) {
                nextChar = next[nextChar];

            } else {
                next[i++] = 0;
            }
        }
        return next;
    }
}
