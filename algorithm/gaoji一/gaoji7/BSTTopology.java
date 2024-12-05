package com.chenjunyi.gaoji一.gaoji7;

import java.util.Map;

/**
 *   最大搜索二叉树 
 */
public class BSTTopology {

    public static class Node{
        int value;
        Node left;
        Node right;
        Node(int value) {
            this.value = value;
        }
    }

    public static class Record{
        int r;
        int l;
        Record(int left, int right) {
            this.l = left;
            this.r = right;
        }


    }

    public static int posOrder(Node h, Map<Node, Record> map) {
        if (h == null) {
            return 0;
        }
        int ls = posOrder(h.left, map);
        int rs = posOrder(h.right, map);
        modifyMap(h.left, h.value, map, true);
        modifyMap(h.right, h.value, map, false);
        Record lr = map.get(h.left);
        Record rr = map.get(h.right);
        int lbst = lr == null ? 0 : lr.l+lr.r+1;
        int rbst = rr == null ? 0 : rr.l+rr.r+1;
        map.put(h, new Record(lbst, rbst));
        return Math.max(lbst + rbst + 1, Math.max(ls, rs));
    }

    public static int modifyMap(Node n, int v, Map<Node , Record> m, boolean s) {
        if (n == null || (!m.containsKey(n))) {
            return 0;
        }
        Record r = m.get(n);
        if ((s && n.value > v) || (!s) && n.value < v) {
            m.remove(n);
            return r.l+ r.r + 1;
        } else {
            int minus = modifyMap(s ? n.right : n.left, v, m, s) ;
            if (s) {
                r.r = r.r - minus;
            } else {
                r.l = r.l - minus;
            }
            m.put(n, r);
            return minus;
        }

    }
}
