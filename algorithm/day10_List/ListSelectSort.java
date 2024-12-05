package com.chenjunyi.day10_List;

/**
 * 给定一个无序单链表的头节点head, 实现单链表的选择排序
 * 方式一： 遍历链表将元素放到一个集合中，在集合中将元素按照从小到达的顺序进行排序，排好序后再用链表连接起来
 *          该方式空间复杂度是O(N)
 *
 * 方式二： 依次找到链表中的最小值，然后放到排好序部分的尾部，逐渐将未排序的部分缩小
 *
 */
public class ListSelectSort {
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


    public static Node selectSort(Node head) {
        Node small = null;
        Node cur = head;
        Node smallPre = null;
        Node tail = null;
        while (cur != null) {
            // 找到链表中的最小值，返回最小值的前一个节点
            smallPre = getSmallPreNode(cur);
            small = smallPre.next;
            smallPre.next = small.next;
            // cur位置的调整
            cur = small == cur ? cur.next : cur;
            if (tail == null) {
                tail = head = small;

            } else {
                tail.next = small;
                tail = small;
            }
        }
        return head;
    }

    private static Node getSmallPreNode(Node head) {
        Node small = head;
        Node smallPre = null;
        Node cur = head.next;
        Node pre = head; // 始终在cur的前一个节点
        while (cur != null) {
            if (cur.value < small.value) {
                small = cur;
                smallPre = pre;
            }
            pre = cur;
            cur = cur.next;
        }
        return smallPre;
    }


}
