package com.chenjunyi.GO;

import java.util.ArrayList;

/**
 *  图中的节点
 */
public class Node {
    public int val;
    public int in;   // 入度数量
    public int out;  // 出度数量
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;

    public Node(int val) {
        this.val = val;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
