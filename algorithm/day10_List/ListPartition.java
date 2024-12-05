package com.chenjunyi.day10_List;

import java.util.concurrent.Callable;

/**
 * 将单链表按照某个值调整成 左边小， 中间相等， 右边大的一个链表
 */
public class ListPartition {
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

    // 可以将链表中的元素全部放入数组中，数组在按照随机快排的方式进行排序，之后再将节点用Node连接起来
    // 也可以使用6个变量进行保存
    // sHead,sTail;  eHead,eTail;  lHead,lTail

    public static Node listPartition(Node head, int pivot) {
        Node sHead = null;
        Node sTail = null;
        Node eHead = null;
        Node eTail = null;
        Node lHead = null;
        Node lTail = null;
        Node next = null; // 保存下一个节点

        while (head != null) {
            next = head.next;
            head.next = null;

            if (head.value < pivot) {
                if (sHead == null) {
                    sHead = sTail = head;
                } else {
                    sTail.next = head;
                    sTail = head;
                }
            } else if(head.value == pivot) {
                if (eHead == null) {
                    eHead = eTail = head;
                } else {
                    eTail.next = head;
                    eTail = head;
                }

            }  else {
                if (lHead == null) {
                    lHead = lTail = head;
                } else {
                    lTail.next = head;
                    lTail = head;
                }
            }
            head = next;
        }

        // 小于的和等于的相连
        if (sTail != null) {
            sTail.next = eHead;
            eTail = eTail == null ? sTail : eTail;

        }
        // 等于的和大于的相连
        if (eTail != null) {
            eTail.next = lHead;

        }

        // 返回 sHead, 如果sHead为null, 用eHead， 如果eHead为null,用lHead返回
        return sHead != null ? sHead : eHead != null ? eHead : lHead;

    }


    public static void main(String[] args) {
        
    }
}
