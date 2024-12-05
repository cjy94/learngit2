package com.chenjunyi.day10_List;

/**
 * 一个环形单链表从头节点head开始不降序， 同时由最后的节点指回头节点。给定这样一个环形单链表的头节点head
 * 和一个正数num，请生成节点值为num的新节点，并插入到这个环形链表中，保证调整后的链表有序
 *
 * 1、新的num生成的节点定义为Node newNode = new Node(num);
 * 2、定义一个pre指针和cur指针，如果pre.value <= num && cur.num >= num，那么newNode就插入到pre和cur之间
 * 3、返回头节点时，如果num < head.value,就返回newNode最为新头节点；
 */
public class InsertNodeIntoSortList {

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


    public static Node insertNode(Node head, int num) {
        Node newNode = new Node(num);
        if (head == null) {
            newNode.next = newNode;
            return newNode;
        }
        Node pre = head;
        Node cur = head.next;
        while (cur != head) {
            if (pre.value <= num && cur.value >= num) {
                break;
            }
            pre = cur;
            cur = cur.next;
        }
        // 将newNode 插入到pre和cur之间
        pre.next = newNode;
        newNode.next = cur;
        return head.value > num ? newNode : head;
    }
}
