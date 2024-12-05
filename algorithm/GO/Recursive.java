package com.chenjunyi.GO;

import java.util.Stack;

/**
 *
 *  递归
 *  master 公式
 *
 *  hash表增删改查，在使用时 时间复杂度O(1)
 *
 *  归并排序:
 *  快速排序: 时间复杂度O(n * logN)
 *  堆排序 : 时间复杂度O(n * logN)    空间复杂度O(1)
 *
 * 
 *  堆结构： java PriorityQueue 优先级队列 ， 默认是小根堆
 *  1、先让整个数组都编程大根堆结构，建立堆的过程：
 *      1) 从上往下的方法， 时间复杂度O(N * logN)
 *      2) 从下往上的方法， 时间复杂度O(N)
 *  2、把堆的最大值和末尾的值交换，然后减少堆的大小之后，再去调整堆，一直周而复始，时间复杂度O(N * logN)
 *  3、堆的大小减小到0之后，排序完成
 *
 *
 *  comp(O o1, O o2)    Comparator接口
 *   负数， o1排在前面
 *   正数， o2排在前面
 *
 *
 *  暴力递归问题：不记录每一个问题的解
 *   汉诺塔问题：只能小压大，不能大压小     
 *   
 *  给你一个栈，请逆序这个栈
 *
 */

public class Recursive {


    // 逆序一个栈， 比如stack:{1,2,3}  ---> stack:{3,2,1} 不能使用额外的数据结构，只能使用递归
    public static int getStackBottomElement(Stack<Integer> stack) {
        // 获取栈底元素
        Integer ele = stack.pop();
        if (stack.isEmpty()) {
            return ele;
        } else {
            Integer last = getStackBottomElement(stack);
            stack.push(ele);
            return last;
        }
    }
    // 逆序一个栈
    public static void reverseStack(Stack<Integer> stack) {
        if (stack.isEmpty())
            return;
        Integer bottom = getStackBottomElement(stack);
        reverseStack(stack);
        stack.push(bottom);
    }
    ///////////////////////////////////////////////////////////////////////////////////

    // 堆排序
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

    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index -1) /2]) {
            swap(arr, index, (index-1)/2);
            index = (index -1) / 2;
        }
    }

    private static void heapify(int[] arr, int index, int limit) {
        int left = 2 * index + 1;
        while (left < limit) {
            int largest = (left + 1 < limit) && (arr[left] < arr[left+1]) ? (left + 1) : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index)
                break;
            swap(arr, largest, index);
            index = largest;
            left = 2 * index + 1;
        }
    }

    // 归并排序
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length == 0)
            return;
        mergeSort(arr, 0, arr.length-1);
    }

    private static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int mid = l + ((r-l) >>1);
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
        for (int i =l;i <= r; i++) {
            arr[i] = help[index++];
        }
    }
     
    // 快速排序
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        quickSort(arr, 0, arr.length-1);
    }

    private static void quickSort(int[] arr, int l, int r) {
        if (l >= r)
            return;
        swap(arr, (l + (int)(Math.random() * (r-l + 1))), r);
        int[] partition = partition(arr, l, r);
        quickSort(arr, l, partition[0]-1);
        quickSort(arr, partition[1]+1, r);
    }

    /**
     * 1) [i] == num, i++
     * 2) [i] < num, [i]与 <区域 右一个交换，<区域 右扩， i++
     * 3) [i] > num, [i]与 >区域 左一个交换，>区域 左扩， i原地不动
     */
    private static int[] partition(int[] arr, int l, int r) {
        if (l > r)
            return new int[] {-1, -1};
        if (l == r)
            return new int[] {l, r};
        
        int less = l-1;
        int more = r;
        int pivot = arr[r];
        int index = l;
        while (index < more) {
            if (arr[index] == pivot) {
                index++;
            } else if (arr[index] < pivot) {
                swap(arr, ++less, index++);
            } else {
                swap(arr, --more, index);
            }
        }
        // l...less(小于)   less+1 ... more-1(等于)    more....r-1(大于)    arr[r]是pivot值
        swap(arr, more, r);
        return new int[] {less+1, more};
    }

    private static void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }
    

}
