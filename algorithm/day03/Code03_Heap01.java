package com.chenjunyi.day03;

/**
 * 大根堆
 * 树中有N个节点，那么树高是logN
 *
 * 堆结构：1、将无序数组，调整成一个大根堆； 2、将0和(n-1)位置的元素进行交换，并调整堆为大根堆
 *
 */

public class Code03_Heap01 {

    public static void main(String[] args) {
        MyHeap heap = new MyHeap(12);
        heap.push(2);
        heap.push(4);
        heap.push(5);
        heap.push(1);
        heap.push(6);
        heap.push(-90);
        heap.push(-90);
        heap.push(3);
        heap.push(9);
        heap.push(34);
        heap.push(56);
        while (!heap.isEmpty()) {
            System.out.println(heap.pop());
        }
    }
    public static class MyHeap {
        int heapSize = 0;
        int limit =0;
        int[] arr;

        public MyHeap(int size) {
            limit = size;
            arr = new int[size];
        }

        public void push(int value){
            if (limit == heapSize) {
                throw new RuntimeException("堆满");
            }
            arr[heapSize] = value;
            heapInsert(arr, heapSize++);

        }

        public int pop() {
            int value = arr[0];
            // heapSize 位置上的元素已经不再有效了
            swap(arr, 0, --heapSize);
            // 删掉节点后，需要再次重新调整堆， 从根节点出发开始调整堆
            heapify(arr, 0, heapSize);
            return value;
        }

        /**
         * 根节点到指定heapSize索引位置处的节点开始调整堆为大顶堆
         * 规则： 找到i节点的左孩子节点，如果i还有右节点，并且右节点的值比左节点的值大，那么用右节点和父节点比较大小，
         *          如果右孩子比父节点大，那么替换父节点，并且将父节点的索引位置更新为右孩子的索引位置；
         *          如果左孩子比父节点大，那么替换父节点，并且将父节点的索引位置更新为左孩子的索引位置；
         *       最终比较整个范围比较结束后，i位置放置temp的元素，
         *
         *      父节点下沉的结束条件：1）孩子节点都不比父节点大； 2）已经没有孩子节点了
         *
         * @param arr 数组
         * @param i   父节点的索引位置
         * @param heapSize  标记的有效范围
         */
        private void heapify(int[] arr, int i, int heapSize) {
            // 最后一个非叶子节点
            int temp = arr[i];
            for (int x=2*i+1; x < heapSize; x = 2*x+1) {
                if (x+1 < heapSize && arr[x] < arr[x+1]) {
                    x++;
                }
                if (temp < arr[x]) {
                    arr[i] = arr[x];
                    i = x;
                } else {
                    break;
                }
            }
            arr[i] = temp;
        }

        private void heapInsert(int[] arr, int heapSize) {
            while (arr[heapSize] > arr[(heapSize-1)/2]) { // 比父节点的值大，则调整堆
                swap(arr, heapSize, ((heapSize-1)/2));
                heapSize = (heapSize-1)/2;

            }
        }

        public boolean isEmpty() {
            return heapSize == 0 ? true : false;
        }

        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}
