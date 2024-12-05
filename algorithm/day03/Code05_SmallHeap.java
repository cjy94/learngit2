package com.chenjunyi.day03;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Code05_SmallHeap {
    public static void main(String[] args) {
        // 排好序后，数组是个几乎有序的数组，
        int[] arr = {2,4,1,-90,1,12};
        sort(arr, 5);
        System.out.println(Arrays.toString(arr));


    }

    // 再小根堆中放入k个元素，弹出堆顶元素（最小元素）放到数组的前面，在在数据中取元素放入小根堆
    //
    public static void sort(int[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int index =0;
        for (; index <= Math.min(arr.length-1, k) ;index++) {
            queue.add(arr[index]);
        }
        int i =0;
        for (; index < arr.length; index++,i++) {
            arr[i] = queue.poll();
            queue.add(arr[index]);
        }
        while (!queue.isEmpty()) {
            arr[i++] = queue.poll();
        }
        
    }

    private void heapify(int[] arr, int i, int size) {
        int t = arr[i];
        for (int k = 2*i+1; k < size; k = 2*k+1) {
            if (k+1 < size && arr[k] > arr[k+1]) {
                k++;
            }
            if (t > arr[k]) {
                arr[i] = arr[k];
                i =k;

            } else {
                break;
            }
        }
        arr[i] = t;

    }
}
