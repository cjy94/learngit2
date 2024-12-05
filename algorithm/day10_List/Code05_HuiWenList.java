package com.chenjunyi.day10_List;

/**
 * 判断一个链表是否是回文链表
 */
public class Code05_HuiWenList {
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

    // 找到链表的中间节点，从中间节点到链表尾部开始反转单链表，
    // 反转后， 从头到尾部比较节点是否相同
    // 比较结束后，需要将链表反转回来
    public static boolean isHuiWenList(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node n1 = head;
        Node n2 = head;
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;    // -- > 中部
            n2 = n2.next.next;       //   --> 尾部
        }

        // s节点的后面节点是需要反转的节点
        //
        Node n3 = null;
        n2 = n1.next;  // 开始反转的节点
        n1.next = null; // 注： 将之前的链表和后面的部分断开连接
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }

        // n1 就是反转后链表的头
        n2 = head;
        n3 = n1;  // 反转后的头节点，保留，一会反转回来需要使用
        boolean res = true;

        while (n1 != null && n2 != null) {
            if (n1.value != n2.value) {
                res = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;

        }
        // 在返回res之前需要将反转的链表进行恢复
        // n3是反转后的头节点
        n1 = n3.next;
        n3.next = null;
        while (n1 != null) {
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
    }
}
