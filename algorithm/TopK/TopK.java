package com.chenjunyi.TopK;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * 由两个数组，返回前k个累加和最大的元素，累加和的元素必须来自两个数组
 */
public class TopK {
    public static class Node{
        int index1;
        int index2;
        int sum;

        public Node(int index1, int index2, int sum) {
            this.index1 = index1;
            this.index2 = index2;
            this.sum = sum;
        }
    }


    // 定义一个比较器，按照sum从大到小排序
    public static class MaxHeapComp implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.sum - o1.sum;
        }
    }

    public static int[] topkSum(int[] arr1, int[] arr2, int topK) {
        if (arr1 == null || arr2 == null || topK < 1) {
            return null;
        }
        topK = Math.min(topK, arr1.length * arr2.length);
        // 建立一个topK大小的数组
        int[] res = new int[topK];
        // 用于表示结束位置
        int resIndex = 0;
        // 根据sum组织的大根堆，sum大的在堆顶
        PriorityQueue<Node> queue = new PriorityQueue<>(new MaxHeapComp());
        // set[i][j] = false; arr1[i] arr2[j] 之前没进来过
        // set[i][j] = true; arr1[i] arr2[j] 之前进来过
        int n1 = arr1.length;
        int n2 = arr2.length;
        boolean[][] set = new boolean[n1][n2];
        queue.add(new Node(n1-1, n2-1, arr1[n1-1]+arr2[n2-1]));
        set[n1-1][n2-1] = true;
        while (resIndex != topK) {
            Node tmp = queue.poll();
            res[resIndex++] = tmp.sum;
            n1 = tmp.index1;
            n2 = tmp.index2;

            if (n1-1 >= 0 && set[n1-1][n1] == false) {
                queue.add(new Node(n1-1, n2, arr1[n1-1]+arr2[n2]));
                set[n1-1][n2] = true;
            }

            if (n2 -1 >= 0 && set[n1][n2-1] == false) {
                queue.add(new Node(n1, n2-1, arr1[n1]+arr2[n2-1]));
                set[n1][n2-1] = true;
            }
        }
        return res;

    }


    public static class Node1{
        int x;
        int y;
        public Node1(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }


    public static int maxPoints(Node1[] nodes) {
        int n = nodes.length;
        int res = 0;
        for (int i =0; i < n-1; i++) {
            // i i+1  i i+2  i i+3
            for (int j = i+1; j< n; j++) {

                int sameXY = 1;
                int sameXdiffY = 0;
                int sameYdiffX = 0;

                HashMap<String , Integer> map = new HashMap<>();
                int diffxy = 0;

                if (nodes[i].x == nodes[j].x && nodes[i].y == nodes[j].y) {
                    sameXY++;
                } else if (nodes[i].x == nodes[j].x) {
                    sameXdiffY++;
                } else if(nodes[i].y == nodes[j].y) {
                    sameYdiffX++;
                } else {
                    // 存在斜率的情况， 不能用小数表示，存在精度损失，需要用最大公约数
                    
                }




            }
        }
        return res;
    }

    // 求两个数的最大公约数 , 辗转相除法
    public static int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }












}
