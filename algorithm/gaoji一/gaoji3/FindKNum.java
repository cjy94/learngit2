package com.chenjunyi.gaoji一.gaoji3;

public class FindKNum {
    // 在两个有序的数组中，不一定等长， 查找第k个数
    public static int findKthNum(int[] arr1, int[] arr2, int k) {
        if(arr1 == null || arr1.length == 0 || arr2 == null || arr2.length == 0) {
            throw new RuntimeException("");
        }
        if (k <1 || k > arr1.length + arr2.length) {
            throw new RuntimeException("");
        }

        int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
        int[] shorts = arr1.length < arr2.length ? arr1 : arr2;
        int l = longs.length;
        int s = shorts.length;
        // 第一种情况
        if (k >=1 && k <= s) {
            // 去求中位数把
            return getUpMedian(shorts, 0, k-1, longs, 0, k-1);
        }
        // 第3种情况
        if (k > l && k <= l+s) {
            if (shorts[k-l-1] >= longs[l-1]) {
                return shorts[k-l-1];
            }
            if (longs[k-s-1] >= shorts[s-1]) {
                return longs[k-s-1];
            }
            return getUpMedian(shorts, k-l, s-1, longs, k-s,l-1);
        }

        // 第2种情况
        if (k > s && k <= l) {
            if (longs[k-s-1] >= shorts[s-1]) {
                return longs[k-s-1];
            }
            return getUpMedian(shorts, 0, s-1, longs, k-s, k-1);
        }
        return -1;
    }

    // a1和a2一定要等长， a1[s1...e1]和a2[s2...e2]之间取中位数

    /**
     *  分别求出序列A 和B 的中位数，设为a 和b，求序列A 和B 的中位数过程如下：
     *
     * 1）若a=b，则a 或b 即为所求中位数，算法结束。
     *
     * 2）若a<b，则舍弃序列A 中较小的一半，同时舍弃序列B 中较大的一半,要求舍弃的长度相等；
     *
     * 3）若a>b，则舍弃序列A 中较大的一半，同时舍弃序列B 中较小的一半，要求舍弃的长度相等；
     *
     */
    private static int getUpMedian(int[] a1, int s1, int e1, int[] a2, int s2, int e2) {
        int mid1 = 0;
        int mid2 = 0;

        while (s1 != e1 || s2 != e2) {
            mid1 = s1 + (e1-s1)/2;
            mid2 = s2 + (e2-s2)/2;
            if (a1[mid1] == a2[mid1]) {
                return a1[mid1];  //条件1）的情况
            }

            if (a1[mid1] < a2[mid2]) { // 条件2）的情况
                if ((s1+e1) %2 == 0) { // 元素个数是奇数
                    s1 = mid1;  // 舍弃A中较小的一半
                    e2 = mid2;  // 舍弃B中较大的一半
                } else { // 元素个数是偶数
                    s1 = mid1 +1;
                    e2 = mid2;
                }
                
            } else { // 条件3）的情况
                if ((s1+e1) %2 == 0) { // 元素个数是奇数
                    e1 = mid1;  // 舍弃A中较大的一半
                    s2 = mid2;  // 舍弃B中较小的一半
                } else { // 元素个数是偶数
                    e1 = mid1;
                    s2 = mid2 + 1;
                }
            }
        }
        return Math.min(a1[s1], a2[s2]);

    }
}
