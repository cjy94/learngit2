package com.chenjunyi.day10_List;

/**
 * 检查链表的长度是奇数还是偶数
 *
 * 定义一个指针，从头节点开始移动，每次移动2个位置，如果最后指针指向了null，说明是偶数个否则奇数个
 */
public class IsListCountEven {

    public static class Node{
        int value;
        Node next;
        public Node(int value) {
            this.value = value;

        }

    }

    public static boolean isListCountEven(Node head) {
        Node cur = head;
        while (cur != null && cur.next!= null) {
            cur = cur.next.next;

        }
        // 节点个数是偶数个
        return cur != null;
    }

}
