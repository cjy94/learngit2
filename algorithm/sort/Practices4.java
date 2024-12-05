package com.chenjunyi.sort;

import java.util.Arrays;



/**
 * 排序算法
 *           时间复杂度       空间复杂度
 * 冒泡排序    O(N^2)           O(1)
 * 选择排序    O(N^2)           O(1)
 * 插入排序    O(N^2)           O(1)
 *
 * 归并排序     O(N logN)       O(N)
 * 随机快排     O(N logN)       O(logN)
 *  堆排序     O(N logN)       O(1)
 *
 */
public class Practices4 {
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }
    //冒泡排序
    public static void bubble(int[] arr) {
        for (int i =arr.length-1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j+1]) {
                    swap(arr, j, j+1);
                }
            }
        }
    }

    // 选择排序
    public static void select(int[] arr) {
        int len = arr.length;
        for (int i =0; i < len-1; i++) {
            int minIndex = i;
            for (int j = i+1; j< len; j++) {
                if (arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            swap(arr, minIndex, i);
        }
    }

    // 插入排序
    public static void insert(int[] arr) {
        for (int i =1; i < arr.length; i++) {
            int value = arr[i];
            int j = i;
            while (j-1 >= 0 && value < arr[j-1]) {
                arr[j] = arr[j-1];
                j--;
            }
            arr[j] = value;

        }
    }

    // 归并排序
    public static void merge(int[] arr) {
        process(arr, 0, arr.length-1);
    }

    private static void process(int[] arr, int l, int r) {
        if (l < r) {
            int mid = l + ((r-l) >> 1);
            process(arr, l, mid);            process(arr, mid+1, r);
            reduce(arr, l, mid, r);
        }

    }

    private static void reduce(int[] arr, int l, int mid, int r) {
        int[] temp = new int[arr.length];
        int L = l;
        int R = mid+1;
        int index = 0;
        while (L <= mid && R <= r) {
            if (arr[L] < arr[R]) {
                temp[index++] = arr[L++];
            } else {
                temp[index++] = arr[R++];
            }
        }
        while (L <= mid) {
            temp[index++] = arr[L++];
        }
        while (R <= r) {
            temp[index++] = arr[R++];
        }
        index = 0;
        for (int i = l; i <= r; i++, index++) {
            arr[i] = temp[index];
        }
    }

    public static void quick(int[] arr) {
        processQuick(arr, 0, arr.length-1);
    }

    private static void processQuick(int[] arr, int l, int r) {
        if (l >= r) return;
        int partition = partition(arr, l, r);
        processQuick(arr, l, partition-1);
        processQuick(arr, partition, r);
    }

    // 改进的快排，荷兰国旗
    public static void quick2(int[] arr) {
        processQuick2(arr, 0, arr.length-1);
    }

    private static void processQuick2(int[] arr, int l, int r) {
        if (l < r) {
            swap(arr, l + (int)Math.random() * (r-l+1), r);
            int[] m = partition2(arr, l, r);
            processQuick2(arr, l, m[0]-1);
            processQuick2(arr, m[1]+1, r);

        }
    }

    //将小于pivot的数据放到less区域，大于的放到more区域，等于的不动
    private static int[] partition2(int[] arr, int l, int r) {
//        if (l>r) return new int[] {-1, -1};
//        if (l==r) return new int[] {l, r};
//        int[] temp = new int[2];
        int less = l-1;
        int more = r;
        while (l < more) {
            if (arr[l] < arr[r]) {
                swap(arr, ++less, l++);
            } else if(arr[l] > arr[r]) {
                swap(arr, --more, l);
            } else {
                l++;
            }
        }
        return new int[]{less, more};

    }

    private static int partition(int[] arr, int l, int r) {
        int less = l-1;
        int pivot = arr[r];
        while (l <= r) {
            if (arr[l] <= pivot) {
                swap(arr, l, less+1);
                less++;
            }
            l++;
        }
        return less;
    }


    private static void heap(int[] arr) {
        // 将数组调整成大顶堆
        for (int i =0;i < arr.length; i++) {
            heapInsert(i, arr);
        }

        int size = arr.length;
        // 交换 堆顶和堆尾元素
        swap(arr, 0, --size);
        while (size > 0) {
            heapify(arr, 0, size);
            swap(arr, 0, --size);
        }
    }

    private static void heap1(int[] arr) {
        // 将数组调整成大顶堆
        for (int i =0;i < arr.length; i++) {
            heapInsert1(i, arr);
        }

        int size = arr.length;
        // 交换 堆顶和堆尾元素
        swap(arr, 0, --size);
        while (size > 0) {
            heapify(arr, 0, size);
            swap(arr, 0, --size);
        }
    }

    private static void heapInsert1(int index, int[] arr) {
        // i 是要插入节点的位置，需要和父节点比对
        // 父节点 位置 i-1/2

        while (arr[index] > arr[(index-1)/2]) {

             swap(arr, index, (index-1)/2);
            index = (index -1)/2;
        }
       // arr[index] = temp;
    }

    private static void heapify(int[] arr, int i, int size) {
        int temp = arr[i];
        // 左子节点： i*2+1
        for (int l = i*2+1; l < size; l = l*2+1) {
            if (l+1 < size && arr[l] < arr[l+1]) {
                l++;
            }
            if (temp > arr[l]) {
                break;
            } else {
                arr[i] = arr[l];
                i = l;
            }

        }
        arr[i] = temp;
    }

    private static void heapInsert(int index, int[] arr) {
        // i 是要插入节点的位置，需要和父节点比对
        // 父节点 位置 i-1/2

        while (arr[index] > arr[(index-1)/2]) {

            swap(arr, index, (index-1)/2);
            index = (index -1)/2;
        }




    }

    // ============================== 对数器 =========================
    public static void sort(int[] arr) {
        Arrays.sort(arr);
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

    private static void test() {
        System.out.println("start");

        for (int i =0; i < 1; i++) {
            int[] a1 = produceData(1000000,100);
            int[] a2 = Arrays.copyOf(a1, a1.length);
            int[] a3 = Arrays.copyOf(a1, a1.length);
            int[] a4 = Arrays.copyOf(a1, a1.length);
            int[] a5 = Arrays.copyOf(a1, a1.length);
            int[] a6 = Arrays.copyOf(a1, a1.length);
            int[] a7 = Arrays.copyOf(a1, a1.length);
            int[] a8 = Arrays.copyOf(a1, a1.length);

            if (Arrays.equals(a1, a2) == false) {
                System.out.println("Oops!");
            }
            long start = System.currentTimeMillis();
            Arrays.sort(a1);
            long end = System.currentTimeMillis();
            System.out.println("system sort took: " + (end-start) + " ms");
//            start = System.currentTimeMillis();
//            select(a2);
//            end = System.currentTimeMillis();
//            System.out.println("select sort took: " + (end -start) + " ms");
//            start = System.currentTimeMillis();
//            bubble(a3);
//            end = System.currentTimeMillis();
//            System.out.println("bubble sort took: " + (end-start) + " ms");
//            start = System.currentTimeMillis();
//            merge(a4);
//            end = System.currentTimeMillis();
//            System.out.println("merge sort took: " + (end-start) + " ms");
            start = System.currentTimeMillis();
            quick(a5);
            end = System.currentTimeMillis();
            System.out.println("quick sort took: " + (end-start) + " ms");
            start = System.currentTimeMillis();
            quick2(a8);
            end = System.currentTimeMillis();
            System.out.println("quick2 took: " + (end-start) + " ms");

//            start = System.currentTimeMillis();
//            insert(a6);
//            end = System.currentTimeMillis();
//            System.out.println("insert took: " + (end-start) + " ms");
            start = System.currentTimeMillis();
            heap(a7);
            end = System.currentTimeMillis();
            System.out.println("heap tooK: " + (end-start) + " ms");
            if (Arrays.equals(a1, a7) == false) {
                System.out.println("heap Oops!");
            }
//            if (Arrays.equals(a1, a6) == false) {
//                System.out.println("insert Oops!");
//            }
            if (Arrays.equals(a1, a5) == false) {
                System.out.println("quick Oops!");
            }
//            if (Arrays.equals(a1, a4) == false) {
//                System.out.println("merge Oops!");
//            }
//            if (Arrays.equals(a1, a3) == false) {
//                System.out.println("bubble Oops!");
//            }
//            if (Arrays.equals(a1, a2) == false) {
//                System.out.println("select Oops!");
//            }


        }

    }

    public static void main(String[] args) {
        test();
//        int[] arr = {-1,2,21,4,-1,5, 9, 21,23};
//        int[] arr2 = {-1,2,21,4,-1,5, 9, 21,23};
//        heap(arr);
//        heap1(arr2);
//        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(arr2));

    }


    public static void select2(int[] arr) {
        for (int i =0; i < arr.length-1; i++) {
            int minIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                minIndex = arr[minIndex] > arr[j] ? j : minIndex;
            }
            swap(arr, minIndex, i);
        }
    }

    public static void bubble2(int[] arr) {
        for (int i = arr.length-1; i >=0 ; i--) {    // 0~N, 0~N-1, 0~N-2, 0~N-3 ... 0~1 上
            for (int j =1; j <= i; j++) {
                if (arr[j-1] > arr[j]) {
                    swap(arr, j-1, j);
                }
            }
        }
    }

    public static void insert2(int[] arr) {
        for (int i =1; i < arr.length; i++) {
            int j = i;
            while (j-1 >= 0 && arr[j] < arr[j-1]) {
                swap(arr, j, j-1);
                j--;
            }
        }

    }
}
