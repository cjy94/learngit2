package com.chenjunyi.GO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class GraphGenerator {

    public Graph createGraph(Integer[][] matrix) {
        Graph graph = new Graph();
        for (int i =0; i < matrix.length; i++) {
            int weight = matrix[i][0];
            int from = matrix[i][1];
            int to = matrix[i][2];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(i, new Node(from));
            }

            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(i, new Node(to));
            }

            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);

            Edge edge = new Edge(weight, fromNode, toNode);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(edge);
            graph.edges.add(edge);
        }
        return graph;
    }

    ///// 图的宽度优先遍历   队列+set       弹出就打印
    public static void bfs(Node head) {
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();      // 保证每个节点直进入队列一次， 因为图中可能有环
        queue.add(head);
        set.add(head);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.val);
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }

    ///// 图的深度优先遍历   栈 + set    进栈就打印
    public static void dfs(Node head) {
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();      // 保证每个节点直进入队列一次， 因为图中可能有环
        stack.add(head);
        set.add(head);
        System.out.println(head.val);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.val);
                    break;
                }
            }
        }
    }

    //////////////////////////////// 拓扑排序： 类似程序的编译顺序，循环引用???

    public static List<Node> sortedTopology(Graph graph) {
        HashMap<Node, Integer> inMap = new HashMap<>();
        Queue<Node> zeroInNode = new LinkedList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0)   // 第一批入度为0的节点
                zeroInNode.add(node);
        }

        ArrayList<Node> res = new ArrayList<>();
        while (!zeroInNode.isEmpty()) {
            Node cur = zeroInNode.poll();
            res.add(cur);
            for (Node next : cur.nexts) {
                inMap.put(next, inMap.get(next)-1);
                if (inMap.get(next) == 0)
                    zeroInNode.add(next);
            }
        }
        return res;
    }

    //////////////////////////////////////// 最小生成树 /////////
    // k 算法    将边按照从小到大进行排序， 使用并查集将每一个节点连接在一起

    // p 算法

    // Dijkstra 算法  最短距离问题 ， 要求图中不能存在环


}
