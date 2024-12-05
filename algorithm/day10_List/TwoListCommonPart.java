package com.chenjunyi.day10_List;

/**
 * 两个单链表，可能有环也可能无环，给定两个单链表，这两个单链表可能相交也可能不相交，请实现一个函数，如果两个链表相交，请返回相交的第一个节点，
 * 如果不相交，返回null即可。
 *
 *
 * 拆分步骤：
 * 1、先实现一个函数，判断一个链表是否有环， 有环则返回第一个入环节点，否则返回null
 *
 * 2、如何判断两个无环链表是否相交，相交则返回第一个相交节点，否则返回null
 * 3、如何判断两个有环链表是否相交，相交则返回第一个相交节点，否则返回null
 */
public class TwoListCommonPart {
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

    // 判断一个链表是否存在环
    // 定义两个指针，一个快指针，每次走两步，一个慢指针每次走1步， 两个节点相碰则存在环
    // 碰到后，一个节点返回head ， 两个指针每次走一步，相交的地方就是第一个入环节点
    public static Node getLoopNode(Node head) {
        if(head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node n1 = head.next;
        Node n2 = head.next.next;
        while (n1 != n2) {
            // 节点指向null, 说明不存在环
            if (n2.next == null || n2.next.next == null) {
                return null;
            }
            n1 = n1.next;
            n2 = n2.next.next;
        }

        // 能走到这里说明 链表是环形链表，并且n1和n2指向同一个节点
        n1 = head;
        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        // 第一个入环节点
        return n1;
    }

    // 判断两个无环链表是否相交，相交则返回第一个相交的节点，否则返回null
    // 如果两个无环链表相交，那么相交后面的部分一定重叠，
    public static Node noLoop(Node head1, Node head2) {
        // 遍历两个链表，求两个链表的长度
        int n1 = 0;  // head1 链表的长度
        int n2 = 0;  // head2 链表的长度
        Node c1 = head1;
        Node c2 = head2;
        while (c1 != null || c2 != null) {
            if (c1 != null) {
                c1 = c1.next;
                n1++;
            }
            if (c2 != null) {
                c2 = c2.next;
                n2++;
            }
        }
        int cha = Math.abs(n1 - n2);
        // c1 是长度较长的链表; c2 是长度较短的链表
        c1 = n1 > n2 ? head1 : head2;
        c2 = c1 == head1 ? head2 : head1;

        // c1 走cha步
        while (cha != 0) {
            c1 = c1.next;
            cha--;
        }
        // c1 和 c2一起走，如果两个几点相等，说明相交，否则不相交
        while (c1 != null || c2 != null) {
            if (c1 == c2) {
                return c1;
            }
            c1 = c1 == null ? null : c1.next;
            c2 = c2 == null ? null : c2.next;

        }
        return null;
    }

    // 判断两个有环链表是否相交
    public static Node bothLoop(Node head1, Node loop1,  Node head2, Node loop2) {
        // 前提条件是head1和head2都是有环链表
        // 返回head1的第一个入环节点
        Node c1 = head1;
        Node c2 = head2;

        // 如果两个链表的入环节点相同，那么就是noLoop的相交节点
        if (loop1 == loop2) {
            int n1 = 0;
            int n2 = 0;
            while (c1 != loop1 || c2 != loop2) {
                if (c1 != loop1) {
                    n1++;
                    c1 = c1.next;
                }
                if (c2 != loop2) {
                    n2++;
                    c2 = c2.next;
                }
            }
            int n = Math.abs(n1 - n2);
            c1 = n1 > n2 ? head1 : head2;
            c2 = c1 == head1 ? head2 : head1;
            while (n != 0) {
                c1 = c1.next;
                n--;
            }

            // c1 和 c2 一起走 相等则是第一个相交节点
            while (c1 != c2) {
                c1 = c1.next;
                c2 = c2.next;
            }
            return c1;
        } else {
            // 两个链表的入环节点不一样
            // 有两种情况
            c1 = loop1.next;
            while (c1 != loop1) {
                if (c1 == loop2) {
                    return loop1;
                }
                c1 = c1.next;
            }
            return null;
        }
    }

    // 最终结果， 求两个链表的相交节点，两个链表可能有环也可能无环
    public static Node getIntersectNode(Node head1, Node head2) {
        // 获取两个链表的第一个入环节点
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if(loop1 == null && loop2 == null) {
            return  noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }

}
