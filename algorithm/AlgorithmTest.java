package com.chenjunyi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class AlgorithmTest {
    public static void main(String[] args) {
        AlgorithmTest test = new AlgorithmTest();
//        int[] arr = {0,34,2,1,0,78,0,45,1,0,3};
//        test.moveZero(arr);
//        System.out.println(Arrays.toString(arr));
        int[][] mat = new int[3][3];
        int[] a1 = {1,5,9};
        int[] a2 = {10,11,13};
        int[] a3 = {12,13,15};
        mat[0] = a1;
        mat[1] = a2;
        mat[2] = a3;
        test.kthSmallest1(mat, 8);


       


    }


    public void moveZero(int[] arr) {
        int index = 0;
        for (int num : arr) {
            if (num != 0) {
                arr[index++] = num;
            }
        }
        while (index < arr.length) {
            arr[index++] = 0;
        }

    }


    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int row = mat.length;
        int column = mat[0].length;
        if (row*column != r * c) {
            return mat;
        }
        int index = 0;
        int[][] newMat = new int[r][c];
        for (int i =0; i < r; i++) {
            for (int j =0; j < c; j++) {

                newMat[i][j] = mat[index / column][index % column];
                index++;

            }
        }
        return newMat;

    }

    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        int n = matrix.length;

        // 把第一列的元素放入堆中 int[] arr 数组中arr[0]:数值  arr[1]:行号   arr[2]:列号
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{matrix[i][0], i, 0});
        }
        for (int i = 0; i < k - 1; i++) {
            int[] now = pq.poll();
            if (now[2] != n - 1) {
                pq.offer(new int[]{matrix[now[1]][now[2] + 1], now[1], now[2] + 1});
            }
        }
        return pq.poll()[0];


    }

    public int kthSmallest1(int[][] matrix, int k) {
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (check(matrix, mid, k, n)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }                                                                              

    public boolean check(int[][] matrix, int mid, int k, int n) {
        int i = n - 1;
        int j = 0;
        int num = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                num += i + 1;
                j++;
            } else {
                i--;
            }
        }
        return num >= k;
    }

}


