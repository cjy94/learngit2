package com.chenjunyi.Code_PriorityQueue;

import com.chenjunyi.day03.Code03_Heap01;

import javax.sound.midi.Soundbank;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 优先级队列的操作
 * 采用数组实现优先级队列
 * 堆和二叉堆：
 *
 * 相关问题：
 * 1、给定一个小根堆，给出找到最大元素的算法
 */
public class MyPriorityQueue {
    // 声明一个堆
    /// 默认是一个大顶堆
    public static class Heap{
        public int[] array;    // 实际存储元素的数组
        public int count;      // 堆中元素的个数
        public int capacity;   // 堆的大小, array的大小
        public Heap(int capacity) {
            this.capacity = capacity;
            this.array = new int[capacity];
            this.count = 0;
        }

        // 获取节点的最孩子
        public int leafChild(int i) {
            return i * 2 +1 >= count ? -1 : i*2+1;
        }

        public int rightChild(int i) {
            return i*2+2 >= count ? -1 : i*2+2;
        }

        public int parent(int i) {
            if (i <= 0 || i >= count) {
                return -1;
            }
            return (i-1) >> 1;
        }

        public int getMax() {
            if (count == 0) {
                return -1;
            }
            return array[0];
        }

        // 从i位置开始向下调整，使其满足大顶堆或者小顶堆
        //  时间复杂度logn
        public void percolateDown(int i) {
            int tmp = array[i];
            for (int l = i*2+1; l < count; l = l*2+1) {
                if (l+1 < count && array[l+1] > array[l]) {
                    l = l+1;
                }
                if (tmp < array[l]) {
                    array[l] = array[i];
                    i = l;
                } else {
                    break;
                }
            }
            array[i] = tmp;
        }


        // 删除元素
        // 只可以删除堆顶元素 , 时间复杂度logn
        public int deleteMax() {
            if (count == 0) {
                return -1;
            }
            int data = array[0];
            array[0] = array[count-1];
            count--;
            percolateDown(0);
            return data;
        }

        // 将元素插入数组的尾部，并调整堆依然为大顶堆
        public void insert(int data) {
            if (count == capacity) {
                resizeHeap();
            }
            int index = count; // 元素要插入的位置
            count++;
            // 从下往上调整成大顶堆
            // 如果索引没有越界，并且比父节点的值大，就向上调整
            while (index > 0 && data > array[(index-1)>>1]) {
                array[index] =  array[(index-1) >> 1];
                index = (index -1) >> 1;
            }
            array[index] = data;
        }

        // 堆扩容
        private void resizeHeap() {
            int newCapacity = capacity * 2;
            array = Arrays.copyOf(array, newCapacity);
            capacity = newCapacity;
        }

        // 清空堆
        public void destoryHeap() {
            count = 0;
            array = null;
        }
        // 将一批数据插入到空堆中
        public void buildHeap(int[] arr, int n) {
            array = new int[n];
            System.arraycopy(arr,0,array,0,n);
            count = n;

            // 从最后一个非叶节点开始往上调整
            for (int i =(n-1)/2; n >=0; n--) {
                percolateDown(i);
            }
        }
    }


    // 堆排序
    public void heapSort(int[] arr) {
        Heap heap = new MyPriorityQueue.Heap(arr.length);
        for (int i =0; i < arr.length; i++) {
            heap.insert(arr[i]);
        }

        swap(heap.array, 0, --heap.count);
        for (int i =heap.count; i >=0; i--) {
           heap.percolateDown(i);
           swap(heap.array, 0, i);
           heap.count--;
        }
    }
    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    // 问题1
    // 小根堆中找最大值，小根堆的定义是父节点的值要比子节点的值小，所以最大值一定都在叶子节点上面，
    // 也就是找出所有叶子节点，在叶子节点中找出最大值
    public static int maxValue(Heap p) {
        int max = Integer.MIN_VALUE;
        // 第一个非叶子节点是size-1/2
        // 所以叶子节点的位置就是(size-1/2)+1
        for (int i = (p.count-1)/2+1; i < p.count; i++) {
            max = Math.max(p.array[i], max);

        }
        return max;
    }

    // 删除队中指定位置的元素
    public static int deleteValue(Heap p, int i ) {
        int key = p.array[i];
        p.array[i] = p.array[p.count-1];
        p.count--;
        p.percolateDown(i);
        return key;
    }

    // 删除小顶堆中第k小的元素
    public static int getKthLargestEle(PriorityQueue<Integer> queue, int k) {
        for (int i =0; i < k-1; i++) {
            queue.poll();
        }
        return queue.poll();
    }

    public static class Info{
        int value;
        int index;
        int n;
        public Info(int value, int n, int index) {
            this.value = value;
            this.n = n;
            this.index = index;
        }
    }

    public static class InfoComp implements Comparator<Info> {
        @Override
        public int compare(Info o1, Info o2) {
            return o1.value - o2.value;
        }
    }

   // 滑动窗口
    public static int[] maxWindows(int[] arr, int w) {
        int n = arr.length;
        int[] res = new int[n-w+1];
        int index = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        for (int right=0; right <n;right++) {
            while (!queue.isEmpty() && arr[queue.peekFirst()] <= arr[right]) {
                queue.pollLast();
            }
            queue.addLast(right);
            // 顶堆元素已经过期了
            if (queue.peekFirst() == right -w) {
                queue.pollFirst();

            }
            if (right >= w-1) {
                res[index++] = arr[queue.peekFirst()];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.toString(maxWindows(arr, 3)));

        String[] files = {"f","g"};
        String[] oldFiles =  {"a","b","c","d","e"};
        List list = Arrays.asList(oldFiles);
        for (String file : files) {
            if (!list.contains(file)) {
                System.out.println("directory not exist file: " + file);
            }
        }
        
    }
}
