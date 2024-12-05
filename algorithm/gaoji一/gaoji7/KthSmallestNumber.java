package com.chenjunyi.gaoji一.gaoji7;

/**
 * 在一个无序数组中，找到第k小的数
 *  
 */
public class KthSmallestNumber {


    // 在一个无序数组种，找到第k小的数
    public static int kthSmallestNumber2(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (k > arr.length) {
            return arr.length;
        }

        return processKthSmallest(arr, 0, arr.length-1, k);
        
    }

    private static int processKthSmallest(int[] arr, int l, int r, int k) {
        if (l== r) {
            return arr[l];
        }
        swap(arr, l + (int)(Math.random()*(r-l+1)) , r);
        int[] m = netherlandsFlag(arr, l, r);  // m[0]: 小于arr[r]的区域， m[1]大于arr[r]的区域， (m[0]..m[1])的区域等于arr[r]
        if (k >= m[0] && k <= m[1]) {
            return arr[k];
        } else if (k < m[0]) {
            return processKthSmallest(arr, l, m[0]-1, k);
        } else {
            return processKthSmallest(arr, m[1]+1, r, k);
        }
    }


    public static int kthSmallestNumber(int[] arr, int k) {
        return process(arr,0, arr.length-1, k);
    }

    // 在arr[l...r]范围上求第k小的数
    // 方式一 使用随机选择一个数的算法
    private static int process(int[] arr, int l, int r, int k) {
        if (l == r) {
            return arr[l];
        }
            // 随机选择一个位置
            // Math.random() 随机产生一个[0,1)之间的小数
            // Math.random*n 随机产生一个[0,n)之间的小数
            // (int)Math.random()*n 随机产生一个[0,n)之间的整数
            swap(arr, l + (int)(Math.random()*(r-l+1)), r);
            int m = partition(arr, l, k);
            // 计算m在arr数组中是第几个位置的数值
            int i = m-l+1;
            if (i == k) {  // 如果partition后返回的索引位置就是要找的第k个位置，那么直接返回arr[m]
                return arr[m];
            } else if (i < k) {  // 如果partition后计算的索引位置比k大，那么去右边查找第k小的
                return process(arr, m+1, r, k-i);
            }  else {    // 否则去左边查找第 k-i小的数
                return process(arr, l, m-1, i);
            }
    }

    private static int partition(int[] arr, int l, int r) {
        int less = l-1;
        int pivot = arr[r];
        while (l <= r) {
            if (arr[l] <= pivot) {
                swap(arr, ++less, l);
            }
            l++;
        }
        return less;
    }

    /**
     * 荷兰国旗问题partition
     * 使用快排的特性，将一个数组分割成3个区域, 左侧区域小于pivot；中间区域等于pivot； 右侧区域大于pivot
     * 规则： 定义两个变量一个less=l-1 , more = r+1   index=0
     *  1、pivot == arr[l], l++
     *  2、pivot < arr[l] 交换arr[++less]和arr[index++]，将小值放到左侧
     *  3、pivot > arr[l] 交换arr[--more]和arr[index]
     *
     */
    public static int[] netherlandsFlag(int[] arr, int l, int r) {
        if (l > r) {
            return new int[] {-1,-1};
        }

        if (l == r) {
            return new int[] {l,r};
        }
        int pivot = arr[r];
        int less = l-1;
        int more = r+1;
        int index = l;
        while (index < more) {
            if (arr[index] == pivot) {
                index++;
            }  else if (arr[index] < pivot) {
                swap(arr, ++less, index++);
            } else {
                swap(arr, --more, index);
            }
        }
        return new int[] {less, more};
    }




    

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {3,1,4,5,-90,87,65,67,45,67,56};
        System.out.println(kthSmallestNumber2(arr, 9));
        //System.out.println(kthSmallestNumber(arr, 9));
    }
}
