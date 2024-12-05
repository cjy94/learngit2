package com.chenjunyi.GO;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *  经典排序算法
 *  1、冒泡排序    稳定
 *  2、选择排序   不稳定排序
 *  3、插入排序   稳定
 *  4、归并排序   n * log(n)    空间复杂度(n)    稳定        
 *      归并问题：
 *      1、可以解决的问题有小和问题
 *      在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和，求一个数组的小和
 *      可以利用归并排序求解， 在进行merge操作时，arr[left] <arr[right]则进行结算arr[left]的结果，即产生了(len-r)个arr[left]的小和
 *      2、逆序对问题
 *      在一个数组中，左侧的数如果比右侧的数大，则这两个数构成一个逆序对，请打印逆序对数量，     计算右边有多少个数比当前数小
 *
 *  5、快速排序   (荷兰国旗问题)    使用递归调用，空间复杂度是logN    不稳定排序
 *  6、堆排序     时间复杂度(n * logN)   空间复杂度 有限几个变量      不稳定排序
 *
 *  不基于比较的排序， 但是对数据状况有要求，比如： 数据必须>=0   基于桶的排序
 *  7、计数排序
 *  8、基数排序 radix sort
 *
 *  Arrays.sort() 基础类型快排，对象类型是归并，考虑稳定性
 *
 */
public class Sort {
    // 插入排序、快速排序、堆排序==========================================================================================
    public static void insert1(int[] arr) {
        for (int i =1; i < arr.length; i++) {
            int temp = arr[i];
            int tempIndex = i;
            while (tempIndex -1 >= 0 && temp < arr[tempIndex-1]) {
                arr[tempIndex] = arr[tempIndex-1];
                tempIndex--;
            }
            if (tempIndex != i)
                arr[tempIndex] = temp;
        }
    }

    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length == 0)
            return;
        quick1(arr, 0, arr.length-1);
    }

    private static void quick1(int[] arr, int l, int r) {
        if (l < r) { // swap(arr, (l + (int)(Math.random() * (r-l+1))), r);
            swap(arr, (l + (int)(Math.random() * (r-l+1))) ,r);
            int[] par = partition1(arr, l, r);
            quick1(arr, l, par[0]-1);
            quick1(arr, par[1]+1, r);
        }
    }

    private static int[] partition1(int[] arr, int l, int r) {
        if (l == r)
            return new int[] {l, r};
        int less = l-1;
        int more = r;
        int pivot = arr[r];
        while (l < more) {
            if (arr[l] < pivot) {
                swap(arr, ++less, l++);
            } else if (arr[l] == pivot) {
                l++;
            } else {
                swap(arr, --more, l);
            }
        }
        swap(arr, more, r);
        return new int[] {less+1, more};
    }

    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        merge1(arr, 0, arr.length-1);
    }

    public static void merge1(int[] arr, int l, int r) {
        if (l < r) {
            int mid = l + ((r-l) >>> 1);   // 书写的时候一定要注意添加好括号，否则可能会出现l=3,r=5 计算出来的mid=2的错误情况
            //int mid = l + (r-l) >>> 1;
          //  System.out.println("mid: " + mid + ", l: " + l + ", r: "+ r);
            merge1(arr, l, mid);
            merge1(arr, mid+1, r);
            mergeArray(arr, l, mid, r);
        }
    }

    private static void mergeArray(int[] arr, int l, int mid, int r) {
        int[] help = new int[r-l+1];
        int index = 0;
        int left = l;
        int right = mid+1;
        while (left <= mid && right <= r) {
            if (arr[left] < arr[right]) {
                help[index++] = arr[left++];
            } else {
                help[index++] = arr[right++];
            }
        }
        while (left <= mid) {
            help[index++] = arr[left++];
        }

        while (right <= r) {
            help[index++] = arr[right++];
        }

        index = 0;
        for (int i = l; i <= r; i++) {
            arr[i] = help[index++];
        }
    }

    // ==========================================================================================



    public static void main(String[] args) {
        int[] arr = {-90,89,34,21,-12,245,64,-7843,5665,2,2,2,3,4,5};
        //int[] arr = {-3,2,1,2,4,-7};
        quickSort1(arr);

        System.out.println(Arrays.toString(arr));
        
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 冒泡排序: 一次排序将最大的元素放到数组的最后
    public static void bubble(int[] arr) {
        for (int i = arr.length-1; i>= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                }
            }
        }

    }

    // 选择排序： 一次排序将最小的元素放到数组的前面
    public static void select(int[] arr) {
        for (int i =0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] > arr[j])
                    swap(arr, i, j);
            }
        }
    }

    public static void insert(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int tempIndex = i;
            while (tempIndex - 1 >= 0 && temp < arr[tempIndex-1]) {
                arr[tempIndex] = arr[tempIndex-1];
                tempIndex--;
            }
            if (i != tempIndex)
                arr[tempIndex] = temp;
        }
    }

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        mergeSort(arr, 0, arr.length-1);
    }

    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + ((r-l) >> 1);
            mergeSort(arr, l, m);
            mergeSort(arr, m+1, r);
            merge(arr, l, m, r);
        }
    }

    public static void merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r-l+1];
        int left = l;
        int right = m+1;
        int index = 0;

        while (left <= m && right <= r) {
            help[index++] = arr[left] < arr[right] ? arr[left++] : arr[right++];

        }

        while (left <= m) {
            help[index++] = arr[left++];
        }

        while (right <= r) {
            help[index++] = arr[right++];
        }

        index = 0;
        for (int i = l; i <= r; i++) {
            arr[i] = help[index++];
        }
    }

    // 由归并排序引申的 小和问题
    public static int process(int[] arr, int l, int r) {
        if (l < r) {
            int m = l + ((r-l) >> 1);
            return process(arr, l, m) + process(arr, m+1, r) + merge1(arr, l, m, r);
        }
        return 0;
    }

    public static int merge1(int[] arr, int l, int m, int r) {
        int[] help = new int[r-l+1];
        int left = l;
        int right = m+1;
        int index = 0;
        int minSum = 0;
        while (left <= m && right <= r) {
            if (arr[left] < arr[right]) {
                minSum += (arr[left] * (r - right +1));   // 只有左侧严格小于右侧，才产生小和
                help[index++] = arr[left++];
            } else {
                help[index++] = arr[right++];
            }
        }
        while (left <= m) {
            help[index++] = arr[left++];
        }

        while (right <= r) {
            help[index++] = arr[right++];
        }

        index = 0;
        for (int i = l; i <= r; i++) {
            arr[i] = help[index++];
        }
        return minSum;
    }

    /**
     *  快速排序相关
     *  荷兰国旗问题  根据一个pivot 将数组分成3个区域，小于pivot的在数组左侧，等于pivot的在数组中间，大于pivot的在数组右侧
     *  返回int[2] int[0]:等于区域的左侧位置，int[1]等于区域的右侧位置
     *
     */
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        quickSort(arr, 0, arr.length-1);
    }

    public static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            swap(arr, (l + (int)(Math.random() * (r-l+1))), r);
            int[] p = partition(arr, l, r);
            quickSort(arr, l, p[0]-1);
            quickSort(arr, p[1]+1, r);
        }
    }

    // arr[r]是pivot
    private static int[] partition(int[] arr, int l, int r) {
        int less = l-1;
        int more = r;
        while (l < more) {
            if (arr[l] < arr[r]) {
                swap(arr, ++less, l++);
            } else if (arr[l] > arr[r]) {
                swap(arr, --more, l);
            } else {
                l++;
            }
        }
        swap(arr, r, more);
        return new int[] {less+1, more};

    }

     // 堆排序
    public static void heapSort(int[] arr) {
        for (int i =0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int n = arr.length;
        while (n > 0) {
            swap(arr, 0, n-1);
            n--;
            heapify(arr, 0, n);
        }
    }

    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index-1)/2]) {
            swap(arr, index, (index -1) / 2);
            index = (index -1) / 2;
        }
    }

    private static void heapify(int[] arr, int index, int limit) {
        int left = 2 * index + 1;
        while (left  < limit) {
            int largest = (left + 1 < limit) && (arr[left+1] > arr[left]) ? left+1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index)
                break;
            swap(arr, index, largest);
            index = largest;
            left = 2 * index + 1;
        }
    }

    /**
     *  堆排序引申的问题
     *  已知一个几乎有序的数组，几乎是指，如果把数组排好序的话，每个元素移动的距离可以不超过k，并且k相对于数组来说比较小，请选择一个合适的排序算法针对这个数据进行排序
     *  使用小顶堆，将[0...k+1]和元素放到小顶堆中，将堆顶的元素放到数组的0位置，因为放到0位置的元素是(0...k+1)范围的数字， 根据题目要求，每个元素移动的距离不会超过k，
     *  那么k+1后面的元素绝对不会是数组0位置的元素，只能是0..k+1范围内的数字，在数组的0位置
     *
     *  jdk中自带的PriorityQueue 默认是数值类型的小顶堆
     */

    public static void kHeapSort(int[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int index = 0;
        for (; index <= Math.min(arr.length,k); index++) {
            queue.add(arr[index]);
        }

        int i=0;
        for (;index < arr.length; index++) {
            arr[i++] = queue.poll();
            queue.add(arr[index]);
        }

        while (!queue.isEmpty()) {
            arr[i++] = queue.poll();
        }
    }


}
