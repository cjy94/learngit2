package com.chenjunyi.sort;

import java.util.Arrays;

public class SortTest {
    /**
     * 选择排序
     * 冒泡排序
     * 归并排序
     * 快速排序
     * 堆排序
     */


    private static void select(int[] arr) {
        for (int i =0; i < arr.length-1; i++) {
            int minIndex = i;
            for (int j =i+1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            swap(arr, minIndex, i);
        }
    }

    private static void bubble(int[] arr) {
        for (int i =0; i < arr.length-1; i++) {
            for (int j = i+1; j< arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }

    private static void merge(int[] arr) {
        if (arr==null || arr.length==0) return;

        // 将数组arr,0-N 范围内的数据有序
        process(arr, 0, arr.length-1);
    }

    private static void process(int[] arr, int L, int R) {
        if (L < R) {
            int mid = (L + ((R-L)>>1));
            process(arr, L, mid);
            process(arr, mid+1, R);
            // 将每一个小部分进行合并
            merge1(arr, L, mid, R);
        }
    }

    private static void merge1(int[] arr, int L, int mid, int R) {
        int[] temp =  new int[arr.length];
        int index = 0;
        int p1=L;
        int p2=mid+1;
        while (p1<=mid && p2<=R) {
            if (arr[p1] < arr[p2]) {
                temp[index++]=arr[p1++];
            } else {
                temp[index++]=arr[p2++];
            }
        }
        while (p1<=mid) {
            temp[index++]=arr[p1++];
        }
        while (p2<=R) {
            temp[index++]=arr[p2++];
        }
        index = 0;
        for (int i = L; i <= R; i++,index++) {
            arr[i]=temp[index];
        }

    }


    private static int processMinSum(int[] arr, int L, int R) {
        if (L < R) {
            int mid = (L + ((R-L)>>1));

            return processMinSum(arr, L, mid) +

                    processMinSum(arr, mid+1, R) +
                    // 将每一个小部分进行合并
                    minSum(arr, L, mid, R);
        }
        return 0;
    }

    // 利用归并排序求小和
    // 小和，在一个数组中，他左边有几何比他小的数，这小小数求和，即为小和
    // 再归并的过程中，如果右边有序数组中的元素严格比左边有序数组中的元素大，那么会产生小和
    // 所以当左边比右边大于等于的时候，不会产生小和，左边索引位置+1,只有当左边严格小于右边的时候，产生小和
    private static int minSum(int[] arr, int L, int mid, int R) {
        int[] temp =  new int[arr.length];
        int minSum = 0;
        int index = 0;
        int p1=L;
        int p2=mid+1;
        while (p1<=mid && p2<=R) {
            if (arr[p1] < arr[p2]) {
                minSum = minSum + (arr[p1]*(R-p2+1));
                temp[index++]=arr[p1++];

            } else {
                temp[index++]=arr[p2++];
            }
        }
        while (p1<=mid) {
            temp[index++]=arr[p1++];
        }
        while (p2<=R) {
            temp[index++]=arr[p2++];
        }
        index = 0;
        for (int i = L; i <= R; i++,index++) {
            arr[i]=temp[index];
        }
        return minSum;

    }


    /*
    选择数组中的最后一个元素，使这个元素在数组他前面位置的元素都比他小，他后面位置的元素都比他大
     */
    private static void quick(int[] arr) {
        if (arr==null || arr.length < 2) return;
        processQuick(arr, 0, arr.length-1);

    }

    // 递归一定要有base case即最基础的返回条件，否则就一直递归死循环调用
    private static void processQuick(int[] arr, int L, int R) {
        // base case
        if (L>=R) return;

        int partition = partition(arr, L, R);
        // 让partiton左侧的部分再继续执行最前面的元素都比pivot小，后面的元素都比pivot大
        processQuick(arr, L, partition-1);

        // 让partiton右侧的部分再继续执行最前面的元素都比pivot小，后面的元素都比pivot大
        processQuick(arr, partition, R);
    }

    // 将小于等于的数值全部放到数组的左边
    private static int partition(int[] arr, int L, int R) {
        if (L > R) return -1;
        if (L == R) return L;
        int less = L-1;
        int index = L;
        int pivot = arr[R];
        while (index <= R) {
            if(arr[index] <= pivot) {
                swap(arr,++less, index);
                index++;
            } else{
                index++;
            }
        }
        return less;
    }


    private static void netherlandSort(int[] arr) {
        if (arr==null || arr.length < 2) return;
        processNetherland(arr, 0, arr.length-1);
    }

    private static void  processNetherland(int[] arr, int L, int R) {
        if (L >= R) return;
        int[] m = partionNetherland(arr, L, R);
        processNetherland(arr, L, m[0]);
        processNetherland(arr, m[1], R);
    }

    private static int[] partionNetherland(int[] arr, int L, int R) {
        if (L>R) return new int[] {-1, -1};
        if (L==R) return new int[] {L, R};
        int[] temp = new int[2];
        int less = L-1;
        int more = R+1;
        int index = L;
        // 将一个数组中按照某个元素分成3个区域
        // 第一个区域：所有元素都比pivot小
        // 第二个区域：所有元素都和pivot相等
        // 第三个区域：所欲元素都比pivot大
        int pivot = arr[R];
        while (index<more) {
            if (arr[index] < pivot) {
                swap(arr, ++less, index);
                index++;
            } else if(arr[index] > pivot) {
                swap(arr, --more, index);
            } else {
                index++;
            }

        }
        temp[0]=less;
        temp[1]=more;
        return temp;

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



    private static void heapSort(int[] arr) {
        // 将数组调整成大顶堆
        for (int i=0; i < arr.length; i++) {
            //
            heapInsert(arr, i);

        }

        // 将堆顶元素后最后一个元素交换
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        // 在调整堆位大顶堆
        while (heapSize>0) {
            heapify(arr,0, heapSize);
            swap(arr, 0, --heapSize);
        }

    }

    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index-1)/2]) {
            swap(arr, index, (index-1)/2);
            index = (index -1)/2;
        }


    }

    private static void heapify(int[] arr, int index, int size) {
        int temp = arr[index];
        for (int i = index*2+1; i < size; i=i*2+1) {
            if (i+1 <size && arr[i] < arr[i+1])
                i++;
            if (temp < arr[i]) {
                arr[index] = arr[i];
                index = i;
            } else {
                break;
            }

        }
        arr[index] = temp;

    }

    private static void test() {
        System.out.println("start");

        for (int i =0; i < 1; i++) {
            int[] a1 = produceData(100000,1000);
            int[] a2 = Arrays.copyOf(a1, a1.length);
            int[] a3 = Arrays.copyOf(a1, a1.length);
            int[] a4 = Arrays.copyOf(a1, a1.length);
            int[] a5 = Arrays.copyOf(a1, a1.length);
            int[] a6 = Arrays.copyOf(a1, a1.length);
            int[] a7 = Arrays.copyOf(a1, a1.length);

            if (Arrays.equals(a1, a2) == false) {
                System.out.println("Oops!");
            }
            long start = System.currentTimeMillis();
            Arrays.sort(a1);
            long end = System.currentTimeMillis();
            System.out.println("system sort took: " + (end-start) + " ms");
            select(a2);
            bubble(a3);
            start = System.currentTimeMillis();
            merge(a4);
            end = System.currentTimeMillis();
            System.out.println("merge sort took: " + (end-start) + " ms");
            
            quick(a5);
            netherlandSort(a6);
            heapSort(a7);
            if (Arrays.equals(a1, a7) == false) {
                System.out.println("6 Oops!");
            }
            if (Arrays.equals(a1, a6) == false) {
                System.out.println("6 Oops!");
            }
            if (Arrays.equals(a1, a5) == false) {
                System.out.println("5 Oops!");
            }
            if (Arrays.equals(a1, a4) == false) {
                System.out.println("4 Oops!");
            }
            if (Arrays.equals(a1, a3) == false) {
                System.out.println("3 Oops!");
            }
            if (Arrays.equals(a1, a2) == false) {
                System.out.println("2 Oops!");
            }


        }

    }
    public static void main(String[] args) {
        test();
//        int[] arr = {3,4,1,2,6,8,7,9,4,3};
//        netherlandSort(arr);
    }
}
