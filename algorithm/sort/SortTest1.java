package com.chenjunyi.sort;

import java.util.Arrays;

public class SortTest1 {

    // 选择排序
    private static void select(int[] arr) {
        for (int i =0;i < arr.length-1; i++) {
            int minIndex = i;
            for (int j = i+1; j<arr.length; j++) {
                if (arr[j]<arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    private static void bubble(int[] arr) {
        for (int i=arr.length-1;i>=0; i--) {
            for (int j =0; j< i; j++) {
                if (arr[j] > arr[j+1]) {
                    swap(arr, j , j+1);
                }
            }
        }
    }

    private static void merge(int[] arr) {
        processMerge(arr, 0, arr.length-1);
    }

    private static void processMerge(int[] arr, int l, int r) {
        if (l >= r) return;
        int mid = ((r-l)>>1) + l;
        processMerge(arr, l, mid);
        processMerge(arr, mid+1, r);
        merge1(arr, l, mid, r);
    }

    private static void merge1(int[] arr, int left, int mid, int right) {
        int l = left;
        int r = mid+1;
        int index = 0;
        int[] temp = new int[right-left+1];
        while (l<=mid && r <= right) {
            if(arr[l] < arr[r]) {
                temp[index++]=arr[l++];
            } else {
                temp[index++]=arr[r++];
            }
        }

        while (l <= mid) {
            temp[index++] = arr[l++];
        }
        while (r<=right) {
            temp[index++] = arr[r++];
        }
        index = 0;
        for (int i=left; i <= right; i++, index++) {
            arr[i] = temp[index];
        }
    }


    private static void quick(int[] arr) {
        processQuick(arr, 0, arr.length-1);
    }

    private static void processQuick(int[] arr, int l , int r) {
        if (l>=r) return;
        int m = partition(arr, l ,r);
        processQuick(arr, l, m-1);
        processQuick(arr, m, r);

    }

    private static int partition(int[] arr, int l, int r) {
        int less = l-1;
        int pivote = arr[r];

        while (l<=r) {
            if(arr[l] <= pivote) {
                swap(arr, l, ++less);
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

    private static int[] produceData(int size, int maxValue) {
        int[] array = new int[size];
        for (int i =0; i < size; i++) {
            // Math.random() 是[0,1)范围内的小数
            //  Math.random() * n [0,n) 范围内的小数
            // （int）Math.random() * n [0,n) 范围内的正数
            array[i] = (int)(Math.random() * maxValue);
        }
        return array;
    }

    private static void heap(int[] arr) {
        // 见数组构建成大顶堆
        for (int i =0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int heapSize = arr.length;
        // 将最大的元素和最后一个元素交换。并调整交换后，剩余元素为大顶堆
        swap(arr, 0, --heapSize);
        while (heapSize>0) {
            // 调整堆为大顶堆
            heapify(arr, 0, heapSize);
            // 交换0为末尾元素
            swap(arr, 0, --heapSize);
        }
    }

    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index-1)/2]) {
            swap(arr, index, (index-1)>>1);
            index = (index-1) >> 1;

        }
    }

    private static void heapify(int[] arr, int index, int size) {
        int temp = arr[index];
        for (int k =index*2+1; k < size; k=k*2+1) {
            if (k+1 < size && arr[k] < arr[k+1]) {
                k++;
            }
            if (arr[k] > temp) {
                arr[index]=arr[k];
                index=k;
            } else {
                break;
            }
        }
        arr[index] = temp;
    }

    private static void test() {
        System.out.println("start");

        for (int i =0; i < 1000; i++) {
            int[] a1 = produceData(10000,1000);
            int[] a2 = Arrays.copyOf(a1, a1.length);
            int[] a3 = Arrays.copyOf(a1, a1.length);
            int[] a4 = Arrays.copyOf(a1, a1.length);
            int[] a5 = Arrays.copyOf(a1, a1.length);
            int[] a6 = Arrays.copyOf(a1, a1.length);
            int[] a7 = Arrays.copyOf(a1, a1.length);

            Arrays.sort(a1);
            select(a2);

            bubble(a3);

            merge(a4);

            quick(a5);

//            netherlandSort(a6);
           heap(a7);
            if (Arrays.equals(a1, a7) == false) {
                System.out.println("heap Oops!");
            }
//            if (Arrays.equals(a1, a6) == false) {
//                System.out.println("6 Oops!");
//            }
            if (Arrays.equals(a1, a5) == false) {
                System.out.println("quick Oops!");
            }
            if (Arrays.equals(a1, a4) == false) {
                System.out.println("merge Oops!");
            }
            if (Arrays.equals(a1, a3) == false) {
                System.out.println("bubble Oops!");
            }
            if (Arrays.equals(a1, a2) == false) {
                System.out.println("select Oops!");
            }


        }

    }
    public static void main(String[] args) {
        test();
        int[] arr = {3,4,1,2,1,1,2};
        heap(arr);
        System.out.println(Arrays.toString(arr));
    }
}
