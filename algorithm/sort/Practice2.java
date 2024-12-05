package com.chenjunyi.sort;

import java.security.PublicKey;
import java.util.Arrays;


public class Practice2 {
    public static void main(String[] args) {
        Practice2 sort = new Practice2();
        int size = 10000;
        int[] arr = new int[size];
        int[] test = new int[size];
        for (int i =0; i < size; i++) {
            arr[i] = (int) (Math.random() * size);
        }
        System.arraycopy(arr, 0, test, 0, size);
//        System.out.println(Arrays.toString(test));
   //    System.out.println(Arrays.toString(arr));
       // long start = System.currentTimeMillis();
        // sort.bubble1(arr);
       //sort.select(arr);
      //  sort.insert(arr);
      // sort.shell(arr);
     //  sort.quick(arr, 0, arr.length-1);
        //int[] temp = new int[arr.length];
        sort.merge(arr, 0, arr.length-1);

        Arrays.sort(test);
//       System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(test));
        //sort.selectSort(arr);
        // sort.insertSort(arr);      // 10w 数据，2s多
       //sort.shellSort(arr);     // 100w数据，排序时间200多ms  ， 1000w数据， 3-4s
       // sort.quickSort(arr, 0, arr.length-1);   // 1000w 1-2s

        // merge sort, 需要额外的内存空间
//        int[] temp = new int[arr.length];
//        sort.mergeSort(arr, 0, arr.length-1, temp);  // 1000w 2s
       // Arrays.sort(arr);              // 1000w, 1-2s
      //  long end = System.currentTimeMillis();
       // System.out.println("took: " + (end-start) + " ms");


       // System.out.println("arr : "+Arrays.toString(arr));
        if (Arrays.equals(arr, test)) {
            System.out.println("true");
        }


    }
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * 冒泡排序: 一趟排序将最大值放置到数组的尾部
     * @param array
     */
    public void bubble(int[] array) {
        for (int i =1; i < array.length; i++) {
            for (int j = 0; j < array.length-i; j++) {
                if (array[j] > array[j+1]) {
                    swap(array, j , j+1);
                }
            }
        }

    }

    /**
     * 冒泡排序
     * @param array
     */
    public void bubble1(int[] array) {
        boolean flag = false;
        for (int i = array.length -1; i > 0; i--) {
            for (int j =0; j < i; j++) {
                if (array[j] > array[j+1]) {
                    flag = true;
                    swap(array, j ,j + 1);
                }
            }
            // 如果一趟排序中没有发生过交换，说明数组已经有序了
            if (flag == false) {
                break;

            } else {
                flag = false;
            }
        }

    }

    /**
     * 选择排序：一趟排序后，最小的元素被放置在数组的头部
     * 选择排序有两种方式：1、交换法：每次比较结束后都进行交换； 2、移位法，记录出一趟比较的最小值所在的位置，最后进行一次交换
     * 本方法中采用移位法，
     * @param array
     */
    public void select(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j =i+1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i)
                swap(array, minIndex, i);

        }

    }

    /**
     * 插入排序：将数组中的元素依次插入到有序数组中，数组中的第一个元素就看作一个有序的数组
     * @param array
     */
    public void insert(int[] array) {
        for (int i =1; i < array.length; i++) {
            int j = i;
            int value = array[j];
            while (j-1 >= 0 && value < array[j-1]) {
                array[j] = array[j-1];
                j--;
            }
            array[j] = value;
        }

    }

    /**
     * 希尔排序：对插入排序的一种改进， 可以指定每次的步长
     * 也称为 缩小增量排序
     * 希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；随着增量逐渐减少，
     *      每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。
     * @param array
     */
    public void shell(int[] array) {
        for (int gap = array.length/2; gap > 0; gap = gap / 2) {
            for (int i =gap; i < array.length; i++) {
                int j = i;
                int value = array[j];
                while (j-gap >= 0 && value < array[j-gap]) {
                    array[j] = array[j-gap];
                    j = j - gap;
                }
                array[j] = value;
            }

        }

    }

    /**
     * 快排：找到一个基准值，使其依次排序后，比基准值小的元素都排到基准值的左侧，比基准值大的元素都排到基准值的右侧
     * @param array
     */
    public void quick(int[] array, int left, int right) {
        int l = left;
        int r = right;
        int pivot = array[((right-left) >> 1) + left];
        while (l < r) {
            while (array[l] < pivot) {
                l++;
            }
            while (array[r] > pivot) {
                 r--;
            }

            // 说明基准值左边的元素已经比基准值小，基准值右边的元素已经比基准值大
            if (l >= r) {
                break;
            }

            // 否则交换
            int temp = array[l];
            array[l] = array[r];
            array[r] = temp;

            if (array[l] == pivot) {
                r--;
            }
            if (array[r] == pivot) {
                l++;
            }
        }
        if (l==r) {
            l++;
            r--;
        }

        if (l < right) {
            quick(array, l , right);
        }
        if (r > left) {
            quick(array, left, r);
        }

    }

    /**
     * 归并排序： 分治的思想
     * @param array
     * @param left
     * @param right
     */
    public void merge(int[] array, int left, int right) {
        if (left < right) {
            int mid = ((right - left) >> 1) + left;

            merge(array, left, mid);
            merge(array, mid+1, right);

            mergeSort(array, left, mid, right);
        }



    }

    private void mergeSort(int[] array, int left, int mid, int right) {
        int l = left, r = mid + 1;
        int[] temp = new int[array.length];
        int  t =0;
        while (l <= mid && r <= right) {
            if (array[l] < array[r]) {
                temp[t] = array[l];
                t++;
                l++;
            } else {
                temp[t] = array[r];
                t++;
                r++;
            }
        }
        while (l <= mid) {
            temp[t] = array[l];
            t++;
            l++;
        }
        while (r <= right) {
            temp[t] = array[r];
            t++;
            r++;
        }

        t = 0;
        for (int x = left; x<= right; x++, t++) {
            array[x] = temp[t];
        }
    }

}
