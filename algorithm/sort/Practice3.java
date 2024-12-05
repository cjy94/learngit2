package com.chenjunyi.sort;

import java.util.Arrays;
import java.util.Random;

public class Practice3 {
    public static void main(String[] args) {
        Practice3 test = new Practice3();
        Random r = new Random();
        int[] array = new int[100];


        for (int i =0; i < 100; i++) {
            array[i] = r.nextInt();

        }
       // int[] array = {-9,2,3,2,2,1,-20};

        int[] arr1 = array;
        
        //System.out.println(Arrays.toString(array));
        //test.bubble(array);
        //test.select(array);
        //test.insert(arr1);
       // test.shell(arr1);
        test.merge(arr1, 0, array.length-1);
        test.quick(arr1, 0, array.length-1);
        Arrays.sort(arr1);
        System.out.println(arr1.equals(array));

    }
    private void swap(int[] arr, int i , int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 一趟排序，最大的元素放到数组尾部
     * @param arr
     */
    public void bubble(int[] arr) {
        boolean flag = false;
        for (int i=arr.length-1; i >0; i--) {
            for (int j =0; j < i;j++) {
                if (arr[j] > arr[j+1]) {
                    flag =true;
                    swap(arr,j, j+1);
                }
            }
            // 如果一趟排序没有发生过交换，说明数组已经有序了
            if (flag == false) {
                break;
            } else {
                flag = false;
            }
        }
    }

    /**
     * 选择排序 ：一趟排序将最小的元素防止到数组的首部
     * @param arr
     */
    public void select(int[] arr) {
        for (int i =0; i < arr.length; i++) {
            int min = i;  // 最小的元素
            for (int j =i+1; j<arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(arr,i,min);
            }
        }

    }

    /**
     * 插入排序： 首次将第一个元素作为一个有序数组，一次将后面的元素插入该有序数组中
     * @param arr
     */
    public void insert(int[] arr) {
        for (int i =1; i < arr.length; i++) {
            int j = i;
            int value = arr[i];
            while (j-1 >= 0 && arr[j-1] > value) {
                arr[j] = arr[j-1];
                j--;
            }
            arr[j] = value;
        }
    }

    /**
     * 希尔排序，对插入排序的改进
     * @param arr
     */
    public void shell(int[] arr) {
        for (int gap = arr.length/2; gap > 0; gap = gap /2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int value = arr[i];
                while (j - gap >= 0 && arr[j - gap] > value) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = value;
            }
        }


    }

    /**
     * 快速排序： 找到一个基准值pivot， pivot前面的元素都比pivot小，pivot后面的元素都比pivot大
     * @param arr
     * @param left
     * @param right
     */
    public void quick(int[] arr, int left, int right) {
        int pivot = arr[left + ((right-left) >> 1)];
        int l = left;
        int r = right;

        while (l < r) {
            while (arr[l] < pivot) {
                l++;
            }
            while (arr[r] > pivot) {
                r--;
            }

            // l>=r 说明pivot两边的元素都刚好处在应该呆的位置上， pivot左边的元素比pivot小，pivot右边的元素比pivot大
            if (l >= r) {
                break;
            }
            // 交换 l和r的元素
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] =temp;

            if (arr[l] == pivot) {
                r--;
            }
            if (arr[r] == pivot) {
                l++;
            }

        }

        if (l==r) {
            l++;
            r--;
        }

        if (l < right) {
            quick(arr, l ,right);
        }
        if (r > left) {
            quick(arr, left, r);

        }



    }

    /**
     * 归并排序： 分治的思想
     * @param arr
     * @param left
     * @param right
     */
    public void merge(int[] arr, int left, int right) {
        if (left < right) {
            int middle = left + ((right - left) >> 1);
            merge(arr, left, middle);
            merge(arr, middle+1, right);
            mergeSort(arr, left, middle, right);
        }

    }
    private void mergeSort(int[] arr, int left, int middle, int right) {
        int l = left, r = middle+1;
        int[] temp = new int[arr.length];
        int  t= 0;
        while (l <= middle && r <= right) {
            if (arr[l] < arr[r]) {
                temp[t] = arr[l];
                t++;
                l++;
            } else {
                temp[t] = arr[r];
                t++;
                r++;
            }
        }
        while (l <= middle) {
            temp[t] = arr[l];
            t++;
            l++;
        }
        while (r <= right) {
            temp[t] = arr[r];
            t++;
            r++;
        }
        t=0;
        for (int x = l; x <= right; x++) {
            arr[x] = temp[t];
            t++;
        }

    }
}
