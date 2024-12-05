package com.chenjunyi.day03;

import java.util.Arrays;
import java.util.Random;

public class Code01_QuickSort {
    public static void main(String[] args) {
//        int size = 1000000;
//        int[] arr1 = new int[size];
//        int[] arr2 = new int[size];
//        int[] arr3 = new int[size];
//        Random r = new Random();
//        for (int i = 0; i < size; i++) {
//            int v = r.nextInt();
//            arr1[i] = v;
//            arr2[i] = v;
//            arr3[i] = v;
//        }
//        long start = System.currentTimeMillis();
//        quickSort1(arr1);
//        long end = System.currentTimeMillis();
//        System.out.println(end - start + " ms");
//        start = System.currentTimeMillis();
//        quickSort2(arr2);
//        end = System.currentTimeMillis();
//        System.out.println(end - start + " ms");
//        start = System.currentTimeMillis();
//        quickSort3(arr3);
//        end = System.currentTimeMillis();
//        System.out.println((end-start)+ " ms");
//        System.out.println(Arrays.equals(arr1, arr2));
//        System.out.println(Arrays.equals(arr2, arr3));
//        System.out.println(Arrays.equals(arr1, arr3));

//        int[] arr3 = {6,7,4,3,2,5};
//        quickSort2(arr3);
//        System.out.println(Arrays.toString(arr3));
//
//
//        int[] arr4 = {6,7,8,4,5,3,5,-90,90,-89,-78,67,34,12};  // [-90,-89,-78,3,4,5,5,6,7,8,12,34,67,90]
//        quickSort2(arr4);
//        System.out.println(Arrays.toString(arr4));
        test();

    }

    private static void test() {
        int[] arr = {2,3,1,4,3,1};
        quickSort1(arr);
    }

    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    private static void process1(int[] arr, int l, int r) {
        if (l >= r) return;
        //int m = partition1(arr, l, r);
        // 取出一次排序后，左侧所有值都比pivot小, 右侧所有值都比pivot大的这个pivot的索引位置，下次递归处理他的左边和右边
        int m = partition1(arr, l, r);
        process1(arr, l, m - 1);
        process1(arr, m+1, r);

    }

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    private static void process2(int[] arr, int l, int r) {
        if (l >= r) return;
        //int m = partition1(arr, l, r);
        // 取出一次排序后，左侧所有值都比pivot小, 右侧所有值都比pivot大的这个pivot的索引位置，下次递归处理他的左边和右边
        int[] m = netherlandsFlag(arr, l, r);
        process1(arr, l, m[0]);
        process1(arr, m[1], r);
    }

    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    private static void process3(int[] arr, int l, int r) {
        if (l >= r) return;
        //int m = partition1(arr, l, r);
        //swap(arr,(int)(Math.random() * (r-l+1)), r);
        // 取出一次排序后，左侧所有值都比pivot小, 右侧所有值都比pivot大的这个pivot的索引位置，下次递归处理他的左边和右边
        int[] m = netherlandsFlag(arr, l, r);
        process3(arr, l, m[0]);
        process3(arr, m[1], r);
    }

    private static int partition1(int[] arr, int l, int r) {
        if (l > r) return -1;
        if (l == r) return l;
        int lessEqual = l - 1;
        int index = l;
        while (index < r) {
            if (arr[index] <= arr[r]) {
                swap(arr, index, ++lessEqual);
                System.out.println("交换++");
            }
            index++;
        }
        swap(arr, ++lessEqual, r);
        System.out.println("交换++");
        return lessEqual;
    }

    private static void swap(int[] arr, int index, int i) {
        int temp = arr[index];
        arr[index] = arr[i];
        arr[i] = temp;
    }

    /**
     * 使用快速排序将数据分成两个区域， 比pivot小的元素都被放置到pivot的前面，比pivot大的元素都被放置到pivot的后面
     * 规则： int less=arr[l-1], index=l
     * 1、如果arr[index] < pivot, 那么index++, less++, arr[index]和arr[less]交换
     * 2、如果 arr[index] >= pivot, 那么index++
     */
    public static int partition(int[] arr, int l, int r) {
        if (l > r) {
            return -1;
        }
        if (l == r) {
            return l;
        }
        int pivot = arr[r];
        int lessEqual = l-1, index = l, R = r+1;
        while (index < R) {
            // 以最后一个元素作为基准值
            if (arr[index] <= pivot) {
                swap(arr, ++lessEqual, index);
               // System.out.println("交换++");
            }
            index++;
        }
        return lessEqual;
    }

    /**
     *  使用快速排序的特性，将一个数组分为3个区域，左侧区域中的数据都比pivot小，中间区域中的数据和pivot相等，右侧区域中的数据都比pivot大
     *  规则：定义一个less=l-1的位置和more=r+1的位置， index=0
     *  1、如果当前arr[index]==pivot,那么index++;
     *  2、如果当前arr[index]>pivot, 那么arr[index]和arr[more--]交换，将大的值放到数组的后面
     *  3、如果当前arr[index]<pivot, 那么arr[index++]和arr[less++]交换，将小的值放到数组的前面
     *  这样操作之后， 比pivot大的值都放置到了pivot的前面，比pivot小的值都放置到pivot的后面了
     *
     *  @return 返回int[2] ，int[0]中的元素是比pivot小的最大元素的索引位置，int[1]中的元素是比pivot大最小元素的索引位置。
     */
    public static int[] netherlandsFlag(int[] arr, int l, int r) {
        if (l > r) {
            return new int[] {-1, -1};
        }
        if (l == r) {
            return new int[] {l, r};
        }
        int pivot = arr[r];
        int less = l-1, more = r+1, index = l;
        while (index < more) {
            if (arr[index] == pivot) {
                index++;
            } else if(arr[index] < pivot) {
                less++;
                swap(arr, index, less);
                index++;

            } else {
                more--;
                swap(arr, index, more);
            }
        }
        return new int[] {less, more};
    }
}
