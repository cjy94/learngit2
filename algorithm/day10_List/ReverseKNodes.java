package com.chenjunyi.day10_List;


/**
 * 将单链表每K个节点之间逆序
 */
public class ReverseKNodes {

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
     * 方式1：可以使用栈的方式进行逆序， 将栈中压入K个元素，依次弹出生成链表
     * 方式2： 用有限几个变量记录，原地反转链表
     *
     * 使用方式2
     */
    public static Node reverse(Node head, int k) {
        Node pre = null;
        Node cur = head;
        Node next = null;
        Node start = null;
        int count = 1;
        while (cur != null) {
            next = cur.next;
            if (k == count) {
                // 将k个元素组成的链表进行逆序
                start = pre == null ? head : pre.next; // 每组的开始节点
                head = pre == null ? cur : head;  // 第一组的第k个节点作为头节点返回，
                // 每组链表的开始节点start, 结束节点end, 以及start的左节点，end的右节点，
                // start和end部分的节点逆序后，left和right的连接需要改变
                reverseList(pre, start ,cur, next);
                pre = start;
                count = 0;
            }
            count++;
            cur = next;
        }
        return head;

    }

    /**
     * 将链表指定部分的节点start和end部分的节点进行逆序，
     * 其中left为逆序的左部分节点，left和逆序后的头节点连接也就是end节点连接  left.next = end;
     * 其中right为逆序的有部分节点，right和逆序后的尾节点连接也就是start节点连接 start.next = right
     */
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


    /**
     *
     * 将链表的每k个元素 逆序
     * 例如： 1->2->3->4->5->6->7   k=3
     *       3->2->1->6->5->4->7
     */
    public static Node reverse1(Node head, int k){
        Node pre = null;
        Node next = null;
        Node cur = head;
        Node start = null;
        int count = 1;
        while (cur != null) {
            next = cur.next;
            if (count == k) {
                // 执行逆序操作
                start = pre == null ? head : pre.next;
                head = pre == null ? cur : head;
                reverseList1(pre, start, cur, next);
                pre = start;
                count = 0;
            }
            cur = next;
            count++;

        }
        return head;

    }

    // 将链表中[start...end]之间的节点进行逆序
    private static void reverseList1(Node left, Node start, Node end, Node right) {
        Node pre = left;
        Node cur = start.next;
        Node next = null;
        while (cur != right) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = right;
        if (left != null) {
            left.next = end;
        }

    }








}
