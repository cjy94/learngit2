package com.chenjunyi.day10_List;

/**
 * 如何逐对逆置 链表中的节点
 * 比如: 1—>2->3->4->null   2->1->4->3->null
 */
public class ReversePairList {

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

    // 每两个节点进行逆序操作
    public static Node reverse(Node head) {
        Node pre = null;
        Node start = head;
        Node cur = head;
        Node next = null;
        int count = 1;
        while (cur != null) {
            next = cur.next;
            if (count == 2) {
                start = pre == null ? head : pre.next;
                head = pre == null ? cur : head;
                reverseList(pre, start, cur, next);
                pre = start;
                count = 0;

            }
            cur = next;
            count++;
        }
        return head;

    }

    // 将start和end范围内的节点进行逆序
    private static void reverseList(Node left, Node start, Node end, Node right) {
        Node pre = start;
        Node cur = start.next;
        Node next = null;
        while (cur != right) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        if (left != null) {
            left.next = end;
        }
        start.next = right;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        System.out.println(reverse(n1));
    }


}
