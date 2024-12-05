package com.chenjunyi.sort;

import java.util.Arrays;

/**
 * 性能比对  100w
 * 1、冒泡排序 8w 13多s
 *
 * 2、选择排序 8w 3s多
 *
 *
 * 3、插入排序
 * 100多s
 *
 * 4、希尔排序
 * 200-300ms
 *
 * 5、快速排序
 * 100-200ms
 *
 * 6、归并排序
 * 100-200ms
 */

public class Practice {
    public static void main(String[] args) {
        Practice sort = new Practice();
        int size = 5;
        int[] arr = new int[size];
        for (int i =0; i < size; i++) {
            arr[i] = (int)(Math.random() * size);
        }
        System.out.println(Arrays.toString(arr));
        //sort.bubble(arr);
        //sort.select(arr);
        //sort.insert(arr);insert                               i
        //sort.shell(arr);
       // sort.quick(arr, 0, arr.length-1);
       // int[] temp = new int[arr.length];
       // sort.mergeSort(arr, 0, arr.length-1,temp );
        System.out.println(Arrays.toString(arr));

        int size1 = 80000;
        int[] array = new int[size1];
        for (int i =0; i < size1; i++) {
            array[i] = (int)(Math.random() * size1);
        }
        long start = System.currentTimeMillis();
        sort.bubble(array);
       // sort.select(array);
       // sort.insert(array);
        //sort.shell(array);
       // sort.quick(array, 0, array.length-1);
//        int[] temp = new int[array.length];
//        sort.mergeSort(array, 0, array.length-1, temp);
        long end = System.currentTimeMillis();
        // System.out.println(Arrays.toString(array));
        System.out.println("took: " + (end-start) + " ms");


    }

    private void swap(int[] arr, int i , int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 冒泡排序
    public void bubble(int[] arr) {
        boolean flag = false;

        for (int j =arr.length - 1; j > 0; j--) {

            for (int i = 0; i < j; i++) {
                if (arr[i] > arr[i+1]) {
                    swap(arr, i, i + 1);
                    flag = true;
                }
            }
            // 说明在一趟排序中， 一次交换都没有发生，数组已经有序
            if (flag == false) {
                break;
            } else {
                flag = false;
            }
        }
    }

    // 选择排序
    public void select(int[] arr) {
        for (int i = 0 ; i <= arr.length - 1; i ++) {
            int minIndex = i;
            for (int j = i + 1; j <= arr.length -1; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(arr, i, minIndex);
            }
        }

    }

    // 插入排序, 依次将元素插入到有序数组中，arr[0]即为第一个有序数组
    public void insert(int[] arr) {
        for (int i =1; i < arr.length; i++) {
            int value = arr[i];
            int j =i;
            while (j-1 >= 0 && value < arr[j-1]) {
                arr[j] = arr[j-1];
                j--;
            }
            arr[j] = value;
        }
    }

    // 希尔排序， 对插入排序的改进，插入排序的步长为1， 希尔排序的步长为arr.length/2
    public void shell(int[] arr) {
        for (int gap = arr.length/2; gap > 0; gap /= 2) {
            for (int i =gap; i < arr.length; i++) {
                int value = arr[i];
                int j =i;
                while (j-gap >= 0 && value < arr[j-gap]) {
                    arr[j] = arr[j-gap];
                    j -= gap;

                }
                arr[j] = value;
            }
        }
    }

    // 快速排序: 找到一个基准值pivot, 一次排序后比基准值小的元素都放在基准值的左边，比基准值大的元素都放到基准值的右边
    public void quick(int[] arr, int left , int right) {
        // 基准值左侧指针
        int l = left;
        // 基准值右侧指针
        int r = right;
        // 基准值
        int pivot = arr[(l + r) / 2];

        // 如果
        while (l < r) {
            // 找到基准值左侧比基准值大 的 索引位置
            while (arr[l] < pivot) {
                l++;
            }
            // 找到基准值右侧比基准值小的 索引位置
            while (arr[r] > pivot) {
                r--;
            }

            // 说明pivot 左边的数都比pivot小，右边的数都比pivot大
            if (l >= r)
                break;

            // 否则交换
           int temp = arr[l];
           arr[l] = arr[r];
           arr[r] = temp;

           if (arr[l] == pivot) {
               r--;
           }
           if (arr[r] == pivot) {
               l++;
           }

        }

        // 为了避免栈溢出
        if (l == r) {
            l++;
            r--;
        }

        // 向左递归
        if (left < r)
            quick(arr, left, r);
        // 向右递归
        if (right > l)
            quick(arr, l, right);

    }


    // 归并排序：（分-治） 将数组进行拆分，在一次将每个数组进行排序，最后将整个部分有序的数组进行排序
    public void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid+1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }


    // 归并排序：最后的合并阶段
    private void merge(int arr[], int left , int mid, int right, int[] temp) {
        int i =left, j = mid + 1, t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                temp[t] = arr[j];
                t++;
                j++;

            }
        }

        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }

        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }

        // 将temp数组中的元素拷贝到arr数组中
        t = 0;
        for (int p =left; p<= right; p++) {
            arr[p] = temp[t];
            t++;
        }


    }





}
