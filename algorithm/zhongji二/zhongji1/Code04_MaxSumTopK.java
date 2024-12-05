package com.chenjunyi.zhongji二.zhongji1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定两个有序数组，arr1和arr2，在给定一个正数k, 返回来自arr1和arr2的两个数相加和最大的前k个，两个数必须来自两个数组
 * 例如： arr1[1,2,3,4,5] arr2[3,5,7,9,11] 返回数组[16,15,14,14]
 * 要求时间复杂度k*logk
 */
public class Code04_MaxSumTopK {
    public static class Node{
        int i1;
        int i2;
        int sum;
        public Node(int index1, int index2, int sum) {
            this.i1 = index1;
            this.i2 = index2;
            this.sum = sum;
        }
    }


    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.sum - o1.sum;
        }
    }


    public static int[] topK(int[] arr1, int[] arr2, int topK) {
        // 边界条件讨论
        if (arr1 == null || arr2 == null || topK < 1) {
            return null;
        }
        int n = arr1.length;
        int m = arr2.length;
        // 先修正topK, 防止取过大的topK个
        topK = Math.min(topK, n * m);

        int[] ans = new int[topK];
        int ansIndex = 0; // 用于记录ans的数据索引位置，当到ans.length时，直接返回结果


        PriorityQueue<Node> queue = new PriorityQueue<>(new NodeComparator());
        
        // 某个位置的元素不被重复加入
        // set[i][j] == false, arr1[i] arr2[j]没有进去过
        // set[i][j] == true, arr1[i] arr2[j] 进去过
        boolean[][] set = new boolean[n][m];

        queue.add(new Node(n-1, m-1, arr1[n-1]+arr2[m-1]));
        set[n-1][m-1] = true;
        while (ansIndex != topK) {
            Node topNode = queue.poll();
            int i1 = topNode.i1;
            int i2 = topNode.i2;
            if (i1-1 >= 0 && set[i1-1][i2] == false) {
                Node node1 = new Node(i1 - 1, i2, arr1[i1 - 1] + arr2[i2]);
                queue.add(node1);
                set[i1-1][i2] = true;
            }
            if (i2-1 >= 0 && set[i1][i2 - 1] == false) {
                Node node2 = new Node(i1, i2 - 1, arr1[i1] + arr2[i2 - 1]);
                queue.add(node2);
                set[i1][i2 - 1] = true;
            }
            ans[ansIndex++] = topNode.sum;
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr1 = {1,2,3,4,5};
        int[] arr2 = {3,5,7,9,11};
        int[] res = topK(arr1, arr2, 4);
        System.out.println(Arrays.toString(res));
    }



}
