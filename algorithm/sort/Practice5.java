package com.chenjunyi.sort;

import java.util.Arrays;

/**
 * 排序相关问题
 * 1、冒泡排序
 */
public class Practice5 {
    public static void bubble(int[] arr) {
        for (int i =arr.length-1; i >= 0; i --) {
            for (int j =0; j < arr.length-1; j++) {
                if (arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                }
            }

        }
    }

    public static void select(int[] arr) {
        for (int i =0; i < arr.length-1; i++) {
            int minIndex = i;
            for (int j = i+1; j< arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            swap(arr, minIndex, i);
        }
    }

    // 如果数组基本有序， 可以使用插入排序
    public static void insert(int[] arr) {
        for (int i =1; i < arr.length; i++) {
            int value = arr[i];
            int j =i;
            while (j-1>=0 && arr[j-1] > value) {
                arr[j] = arr[j-1];
                j--;
            }
            arr[j] = value;
        }
    }

    // 时间复杂度是n*logn
    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int mid = l + ((r-l) >> 1);
            mergeSort(arr, l, mid);
            mergeSort(arr, mid+1, r);
            merge(arr, l, mid, r);
        }

    }

    // 将2个小的有序的样本， 合并成整体有序的样本
    public static void merge(int[] arr, int l, int mid, int r) {
        int left = l;
        int right = mid+1;
        int[] temp = new int[arr.length];
        int index = 0;
        while (left <= mid && right <= r) {
            if (arr[left] < arr[right]) {
                temp[index++] = arr[left++];
            } else {
                temp[index++] = arr[right++];
            }
        }

        while (left <= mid) {
            temp[index++] = arr[left++];
        }
        while (right <= r) {
            temp[index++] = arr[right++];
        }


        index = 0;
        for (int i =l; i <= r; i++) {
            arr[i] = temp[index++];
        }

    }

    // 堆排序 ， 时间复杂度 n*logn 不稳定排序，空间复杂度O(1)
    // 将数组按照大根堆组成
    public static void heapSort(int[] arr) {
        for (int i =0; i<arr.length; i++) {
            heapInsert(arr, i);
        }

        int size = arr.length;
        swap(arr, 0, --size);
        while (size > 0) {
            heapify(arr, 0, size);
            swap(arr, 0, --size);
        }

    }

    public static void heapify(int[] arr, int i, int size) {
        int value = arr[i];
        for(int k = i*2+1; k < size; k=k*2+1) {
            if (k+1 < size && arr[k+1] > arr[k]) {
                k++;
            }
            if (value > arr[k]) {
                break;
            }  else {
                arr[i] = arr[k];
                i = k;
            }
        }
        arr[i] = value;
    }


    public static void heapInsert(int[] arr, int i) {
        // i是新插入数据的位置，需要向上检查父节点的值是否比
        while (arr[(i-1)/2] < arr[i]) {
            swap(arr, i, (i-1)/2);
            i = (i-1)/2;
        }
    }

    public static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = partition(arr, l, r);
            quickSort(arr, l, m-1);
            quickSort(arr, m+1, r);
        }
    }

    public static void quickSortRandom(int[] arr, int l, int r) {
        if (l < r) {
            // Math.random 随机产生[0,1)之间的小数
            // (int)Math.random 随机产生[0,1)之间的整数
            // (int)Math.random()*(r-l+1) 随机产生[0,(r-l+1))之间的整数
            swap(arr,  l + (int)(Math.random() * (r-l+1)), r);
            int m = partition(arr, l, r);
            quickSort(arr, l, m-1);
            quickSort(arr, m+1, r);
        }
    }

    // 选择r作为pivot
    public static int partition(int[] arr, int l, int r) {
        int pivot = arr[r];
        int less = l-1;
        while (l <= r) {
            if (arr[l] <= pivot) {
                swap(arr, ++less, l);
            }
            l++;
        }
        return less;


    }


    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {-89,-9, 23,123,45,24,56,1,2,3,89,78};
        int[] arr1 = {-89,-9, 23,123,45,24,56,1,2,3,89,78};
        int[] arr2 = {-89,-9, 23,123,45,24,56,1,2,3,89,78};
        int[] arr3 = {-89,-9, 23,123,45,24,56,1,2,3,89,78};
        int[] arr4 = {-89,-9, 23,123,45,24,56,1,2,3,89,78};
        int[] arr5 = {-89,-9, 23,123,45,24,56,1,2,3,89,78};
        int[] arr6 = {-89,-9, 23,123,45,24,56,1,2,3,89,78};
        int[] arr7 = {-89,-9, 23,123,45,24,56,1,2,3,89,78};
        bubble(arr);
        System.out.println(Arrays.toString(arr));
        select(arr1);
        System.out.println(Arrays.toString(arr1));
        insert(arr2);
        System.out.println(Arrays.toString(arr2));
        mergeSort(arr3, 0, arr3.length-1);
        System.out.println(Arrays.toString(arr3));
        heapSort(arr4);
        System.out.println(Arrays.toString(arr4));
        quickSort(arr5, 0, arr5.length-1);
        System.out.println(Arrays.toString(arr5));
    }
}
