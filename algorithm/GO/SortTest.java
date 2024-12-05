package com.chenjunyi.GO;

import java.util.Arrays;

/**
 *  归并排序    稳定性排序    空间复杂度：O(N)
 *  快速排序    不稳定排序    空间复杂度：O(logN)
 *  堆排序      不稳定排序    空间复杂度：O(1)
 */
public class SortTest {
    public static void main(String[] args) {
        int[] arr = {-90,465,321,21,43,21,1,1,2,2,3,4,343,-34,-23,-90};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        mergeSort(arr, 0, arr.length-1);
    }

    private static void mergeSort(int[] arr, int l , int r) {
        if (l < r) {
            int mid = l + ((r-l)>>1);
            mergeSort(arr, l, mid);
            mergeSort(arr, mid+1, r);
            merge(arr, l, mid, r);
        }
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r-l+1];
        int left = l;
        int right = mid+1;
        int index = 0;
        while (left <= mid && right <= r) {
            help[index++] = arr[left] < arr[right] ? arr[left++] : arr[right++];
        }

        while (left <= mid) {
            help[index++] = arr[left++];
        }

        while (right <= r) {
            help[index++] = arr[right++];
        }
        index = 0;
        for (int i =l; i <= r; i++) {
            arr[i] = help[index++];
        }
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length == 0)
            return;
        quickSort(arr, 0, arr.length-1);
    }

    private static void quickSort(int[] arr, int l, int r) {
       if (l < r) {
           swap(arr, (l + (int)(Math.random() * (r-l+1))), r);
           int[] p = partition(arr, l, r);
           quickSort(arr, l, p[0]-1);
           quickSort(arr, p[1]+1, r);
       }
    }

    private static int[] partition(int[] arr, int l, int r) {
        if (l > r) {
            return new int[] {-1, -1};
        }

        if (l == r)
            return new int[] {l, r};

        int less = l-1;
        int more = r;
        int pivot = arr[r];
        while (l < more) {
            if (arr[l] == pivot) {
                l++;
            } else if (arr[l] < pivot) {
                swap(arr, ++less, l++);
            } else {
                swap(arr, --more, l);
            }
        }
        swap(arr, more, r);
        return new int[] {less+1, more};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length == 0)
            return;
        for (int i =0; i < arr.length; i++) {
            heapInsert(arr, i);
        }

        int n = arr.length;
        while (n > 0) {
            swap(arr, 0, --n);
            heapify(arr, 0, n);
        }
    }

    private static void heapInsert(int[] arr, int i) {
        while (arr[i] > arr[(i-1)/2]) {
            swap(arr, i, (i-1)/2);
            i = (i-1) / 2;
        }
    }

    private static void heapify(int[] arr, int index, int limit) {
        int left = index * 2 + 1;
        while (left < limit) {
            int largest = (left + 1 < limit) && (arr[left] < arr[left+1]) ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index)
                break;
            swap(arr, largest, index);
            index = largest;
            left = 2 * left + 1;
        }
    }
}
