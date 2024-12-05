package com.chenjunyi.practice;

import java.util.Comparator;
import java.util.TreeSet;

/**
 *  3个有序数组，每个数组取一个数，使每两个数相加的绝对值的累加和最小
 *  
 */
public class MaxSum {

    static class Node{
        int value;
        int arrIndex;
        int ownIndex;

        public Node(int v, int a, int o) {
            this.value = v;
            this.arrIndex = a;
            this.ownIndex = o;
        }
    }

    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
             return o1.value != o2.value ? o1.value - o2.value : o1.toString().compareTo(o2.toString());
        }
    }

    public static int maxSum(int[][] matrix) {
        // 有序表
        TreeSet<Node> set = new TreeSet<>(new NodeComparator());
        for (int[] arr : matrix) {
            if (arr == null || arr.length == 0)
                return -1;
        }

        for (int i=0; i < matrix.length; i++) {
            set.add(new Node(matrix[i][0], i, 0));
        }

        int n = matrix.length;
        int min = Integer.MAX_VALUE;

        while (set.size() == n) {
            min = Math.min(min, set.last().value - set.first().value);
            Node first = set.pollFirst();
            int arrIndex = first.arrIndex;
            int ownIndex = first.ownIndex;
            if (ownIndex < matrix[arrIndex].length -1) {
                set.add(new Node(matrix[arrIndex][ownIndex+1], arrIndex, ownIndex+1));
            }
        }
        return min;

    }


}
