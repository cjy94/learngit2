package com.chenjunyi.day10_List;

/**
 * 删除一个链表的中间节点
 */
public class Code03_RemoveMidNode {
    public static class Node{
        public int value;
        public Node next;

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

    /**
     * 删除中间节点
     */
    public static Node removeMidNode(Node head) {
        // 头节点或者只有一个头节点时，不进行删除操作
        if (head == null || head.next == null) {
            return head;
        }

        // 只有两个节点，则将头节点删除，直接返回head.next
        if (head.next.next == null) {
            return head.next;
        }
        // 找打链表的中间节点
        // 链表长度是奇数，则中间节点；如果链表长度是偶数则，中间左侧节点
        Node f = head.next.next;
        Node s = head;
        while (f.next != null && f.next.next != null) {
            f = f.next.next;
            s = s.next;
        }

        // s所在的位置就是中间节点的前一个位置
        s.next = s.next.next;
        return head;


    }


    // 删除a/b 位置的节点. 根据 链表长度n, 和整数a, b 决定出要删除的节点
    public static Node removeByRatio(Node head, int a, int b){
        // 获取链表长度
        int len = 0;
        Node cur = head;
        while (cur != null) {
            cur = cur.next;
            len++;
        }
        // 计算要删除的节点位置
        int index = (int) Math.ceil((double) (a * len) / (double) b);
        if (index == 1) {
            return head.next;
        }
        if (index > 1) {
            cur = head;
            while (--index != 1) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }
        return head;
    }
}
