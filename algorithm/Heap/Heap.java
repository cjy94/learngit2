package com.chenjunyi.Heap;


import java.util.Arrays;

public class Heap {

    /**
     * 大根堆
     */
    public static class MyMaxHeap {
        public int[] heap;
        public int heapSize;       // 当前堆中由多少元素，也就是heap数组中的有效位置
        public final int limit;   // heap数组的大小，不可改变的一个值

        public MyMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }


        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        // 向堆中插入一个元素
        // 插入位置是 heapSize
        public void push(int value) {
            if (isFull()) {
                System.out.println("heap is full!!");
                return;
            }
            heap[heapSize] = value;
            heapInsert(heap, heapSize);
            heapSize++;
        }

        // 弹出堆顶元素
        // 并且将堆顶元素arr[0]和heapSize位置的元素交换，即数组中的元素个数-1
        public int pop() {
            int ans = heap[0];
            swap(heap, 0, heapSize);
            heapSize--;
            heapify(heap, 0, heapSize);
            return ans;

        }

        // 在arr[index]位置插入元素， 向上调整为大顶堆
        private void heapInsert(int[] arr, int index) {
            // 父节点位置  (index-1)/2
            while (arr[index] > arr[(index - 1) / 2]) {
                swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        // 将数组arr[], 从index...heapSize位置的元素调整为大根堆
        private void heapify(int[] arr, int index, int heapSize) {
            // 左孩子的位置
            int left = index * 2 + 1;
            while (left <= heapSize) {
                int largest = left + 1 <= heapSize && arr[left] < arr[left + 1] ? left + 1 : left;
                largest = arr[largest] > arr[index] ? largest : index;
                if (largest == index) {
                    break;
                }
                swap(arr, index, largest);
                index = largest;
                left = index * 2 + 1;
            }

        }

        private void swap(int[] arr, int i , int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;

        }

    }


    /**
     * 将无序数组arr, 利用堆思想进行排序
     * @param arr
     */
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length == 0 || arr.length == 1) {
            return;
        }
        int n = arr.length;
        // 将数组调整成了大根堆
//        for (int i =0; i < arr.length; i++) {
//            heapInsert(arr, i);  // log(n)
//        }

        for (int i = n-1; i>=0; i--) {
            heapify(arr, i, n);
        }

        // 将arr[0]和arr[n-1]位置的元素交换
        swap(arr, 0, n-1);// 最大值被放置到了数组的最后
        n--;
        // 在重新调整数组成大根堆
        while (n > 0) {
            heapify(arr, 0, n);
            swap(arr, 0, n-1);
            n--;
        }

    }

    private static void heapInsert(int[] arr, int index) {
        // 父节点的位置 (index - 1) / 2
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private static void heapify(int[] arr, int index, int heapSize) {
        // 左孩子的位置
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left] < arr[left + 1] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, index, largest);
            index = largest;
            left = index *2 + 1;
        }
    }

    private static void swap(int[] arr, int i , int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }
    

    public static void main(String[] args) {
//        int[] arr = {-1, 0, 89,3,45,21,234,65,12};
//        heapSort(arr);
//        System.out.println(Arrays.toString(arr));

        MyMaxHeap  queue = new MyMaxHeap(10);
        queue.push(3);
        queue.push(2);
        queue.push(5);
        queue.push(6);
        queue.push(4);

        while (!queue.isEmpty()) {
            System.out.println(queue.pop());
        }

    }



}
