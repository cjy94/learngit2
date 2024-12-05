package com.chenjunyi.zhongji二.zhongji1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Code04_MinValueTopK {
    public static class Node{
        int i1;
        int i2;
        int value;
        public Node(int index1, int index2, int value) {
            this.i1 = index1;
            this.i2 = index2;
            this.value = value;
        }
    }


    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.value - o2.value;
        }
    }

    public static int[] topK(int[][] martix, int topK) {
        // 边界条件讨论
        if (martix == null || topK < 1) {
            return null;
        }
        int n = martix.length;
        int m = martix[0].length;
        // 先修正topK, 防止取过大的topK个
        topK = Math.min(topK, n * m);

        int[] ans = new int[topK];
        int ansIndex = 0; // 用于记录ans的数据索引位置，当到ans.length时，直接返回结果


        PriorityQueue<Node> queue = new PriorityQueue<>(new NodeComparator());

        // 某个位置的元素不被重复加入
        // set[i][j] == false, arr1[i] arr2[j]没有进去过
        // set[i][j] == true, arr1[i] arr2[j] 进去过
        boolean[][] set = new boolean[n][m];
        int i1 = 0;
        int i2 = 0;

        queue.add(new Node(i1, i2, martix[i1][i2]));
        set[i1][i2] = true;
        while (ansIndex != topK) {
            Node topNode = queue.poll();
            i1 = topNode.i1;
            i2 = topNode.i2;
            if (i1+1 < m && set[i1+1][i2] == false) {
                Node node1 = new Node(i1 + 1, i2, martix[i1 + 1][i2]);
                queue.add(node1);
                set[i1+1][i2] = true;
            }
            if (i2+1 < n && set[i1][i2 + 1] == false) {
                Node node2 = new Node(i1, i2 + 1, martix[i1][i2 + 1]);
                queue.add(node2);
                set[i1][i2 + 1] = true;
            }
            ans[ansIndex++] = topNode.value;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] martix = {
                {1,2,3,4,5,6,7},
                {2,6,8,9,10,11,13},
                {3,7,9,11,14,16,19},
                {8,17,29,34,54,67,99}
        };  // 二维数组中，最小的前5个数是： 1，2，2，3，3

        int[] ints = topK(martix, 5);
        System.out.println(Arrays.toString(ints));
    }
}
