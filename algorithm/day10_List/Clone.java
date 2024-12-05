package com.chenjunyi.day10_List;

import com.chenjunyi.Joseph.JosephProblem;

import java.util.HashMap;

/**
 * 给定一个链表， 每个节点包含数据，下一个指针，和一个指向链表中某个节点的随机指针， 请设计一个赋值链表的算法
 *
 * 使用hash表 [key, value] key是原链表的节点地址，value是根据原节点重新生成的新节点地址
 */


public class Clone {

    public static class Node{
        public int value;
        public Node next;
        public Node random;

        public Node(int data) {
            this.value = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    public static Node clone(Node head) {
        Node cur = head;
        HashMap<Node, Node> map = new HashMap<>();
        while (cur != null) {
            Node node = new Node(cur.value);

            map.put(cur, node);

            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            Node node = map.get(cur);
            node.next = map.get(cur.next);
            node.random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }
}
