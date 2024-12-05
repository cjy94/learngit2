package com.chenjunyi.gaoji一.gaoji4;

import java.util.*;

/**
 * 给定一个矩阵，构建整体的轮廓线
 * 构建大楼边界
 */
public class BuildOutLine {
    public static class Node{
        int x;          // 坐标值信息
        int height;     // 高度信息
        boolean isAdd;  // 高度是增加还是降低  true表示增加高度， false表示降低高度

        public Node(int x, boolean isAdd, int height){
            this.x = x;
            this.isAdd = isAdd;
            this.height = height;
        }
    }

    public static class NodeCmp implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            // 先按照x坐标从小到达排序
            if (o1.x != o2.x) {
                return o1.x - o2.x;
            }
            // 如果x轴坐标相等，增加的排在减少的前面
            if (o1.isAdd != o2.isAdd) {
                return o1.isAdd ? -1 : 1;
            }
            return 0;
        }
    }

    // 构建的主流程
    public static List<List<Integer>> buildOutline(int[][] matrix) {
        int n = matrix.length;
        Node[] nodes = new Node[n*2];
        // 将每个坐标高度信息拆分成2个维度的信息，高度的增加和高度的降低
        for (int i =0; i <n; i++) {
            nodes[i*2] = new Node(matrix[i][0], true, matrix[i][2]);
            nodes[i*2+1] = new Node(matrix[i][1], false, matrix[i][2]);
        }
        // 将Node按照x和isAdd排序
        Arrays.sort(nodes, new NodeCmp());

        // 构建2个TreeMap信息
        TreeMap<Integer, Integer> heightCountMap = new TreeMap<>();
        TreeMap<Integer, Integer> xHeightMap = new TreeMap<>();
        for (int i =0; i < nodes.length; i++) {
            // 往heightCountMap中添加诗句
            if (nodes[i].isAdd) { // 如果当前是高度增加的坐标
                if (heightCountMap.containsKey(nodes[i].height)) {
                    heightCountMap.put(nodes[i].height, heightCountMap.get(nodes[i].height+1));
                } else {
                    heightCountMap.put(nodes[i].height, 1);
                }

            } else {   // 如果当前是降低高度的操作
                if (heightCountMap.get(nodes[i].height) == 1) {
                    heightCountMap.remove(nodes[i].height);
                } else {
                    heightCountMap.put(nodes[i].height, heightCountMap.get(nodes[i].height)-1);
                }
            }
            // 往xHeightMap中添加数据， 坐标高度表
            if (!heightCountMap.isEmpty()) {
                xHeightMap.put(nodes[i].x, heightCountMap.lastKey());
            } else {
                xHeightMap.put(nodes[i].x, 0);
            }
        }

        // 都处理结束后，heightCountMap集合应该是空的， xHeightMap是最后生成结果的集合
        List<List<Integer>> res = new ArrayList<>();

        int start = 0;
        int preHeight = 0;
        for (Map.Entry<Integer, Integer> entry : xHeightMap.entrySet()) {
            int curX = entry.getKey();
            int curMaxHeight = entry.getValue();
            if (curMaxHeight != preHeight) {
                if (preHeight != 0) {
                    res.add(new ArrayList<Integer>(Arrays.asList(start, curX, preHeight)));
                }
                start = curX;
                preHeight = curMaxHeight;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] matrix ={
                {2,5,6},
                {1,7,4},
                {4,6,7},
                {3,6,5},
                {10,13,2},
                {9,11,3},
                {12,14,4}, 
                {10,12,5}
        };
        List<List<Integer>> ans = buildOutline(matrix);
        System.out.println(ans);
    }
}
