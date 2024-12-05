package com.chenjunyi.GO;

public class Test {
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 归并排序
    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int mid = l + ((r-l) >> 1);
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

    // 快速排序
    public static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            swap(arr, (l + (int) Math.random()* (r-l+1)), r);
            int[] partition = partition(arr, l, r);
            quickSort(arr, l, partition[0]-1);
            quickSort(arr, partition[1]+1, r);
        }
    }

    private static int[] partition(int[] arr, int l, int r) {
        if (l == r) {
            return new int[] {l,r};
        }
        int less = l-1;
        int more = r;
        int pivot = arr[r];
        while (l < more) {
            if (arr[l] < pivot) {
                swap(arr, ++less, l++);
            } else if(arr[l] == pivot) {
                l++;
            } else {
                swap(arr, --more, l);
            }
        }
        swap(arr, more, r);
        return new int[] {less+1, more};
    }

    public static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i =0;i < n; i++) {
            heapInsert(arr, i);
        }
        while (n > 0) {
            swap(arr, 0, --n);
            heapify(arr, 0, n);
        }
    }

    public static void heapInsert(int[] arr, int i) {
        while (arr[i] > arr[(i-1)/2]) {
            swap(arr, i, (i-1)/2);
            i = (i-1) / 2;
        }
    }

    public static void heapify(int[] arr, int i, int limit) {
        int left = 2 * i + 1;
        while (left < limit) {
            int largest = (left + 1 < limit) && (arr[left] < arr[left+1]) ? left + 1 : left;
            largest = arr[largest] > arr[i] ? largest : i;
            if (largest == i)
                break;
            swap(arr, largest, i);
            i = largest;
            left = i * 2 + 1;
        }

    }

    

}
