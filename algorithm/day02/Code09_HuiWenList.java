package com.chenjunyi.day02;

import java.util.Stack;

/**
 * 方式1： 遍历链表将元素放入栈中，遍历结束后，将栈中的元素弹出，和链表中的元素进行比对
 * 方式2： 只将链表的后半部分放入栈中
 *          怎么只将后半部分放入栈中呢？  快慢指针，当快指针到达尾部时，慢指针到中间的位置
 * 方式3：
 */
public class Code09_HuiWenList {

    public static class Node {
        public int data;
        public Node next;
        Node(int data) {
            this.data = data;

        }
    }

    // 方式1： 将链表中的元素全部压入栈
    private static boolean method1(Node head) {
        if (head==null || head.next==null) return true;
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur!=null) {
            stack.push(cur);
            cur = cur.next;
        }
        cur = head;

        while (stack.isEmpty() == false && cur!=null) {
            if (stack.pop().data != cur.data) {
                return false;
            }
            cur=cur.next;
        }
        return true;
    }


    // 方式2： 将链表中的后半部分元素压入栈，
    // 使用快慢指针
    private static boolean method2(Node head) {
        if (head==null || head.next==null) return true;
        Node f = head;
        Node s = head;
        while (f.next!=null && f.next.next!=null) {
            f=f.next.next;
            s=s.next;
        }
        // s 到达链表的中间部分，奇数则为中间，偶数则为
        Stack<Node> stack = new Stack<>();
        while (s.next!=null) {
            s=s.next;
            stack.push(s);
        }
        s = head;
        while (stack.isEmpty() == false) {
            if (stack.pop().data != s.data) {
                return false;
            }
            s=s.next;
        }
        return true;
    }

    /**
     * 将链表的后半部分进行反转，比对后半部分链表个前半部分链表是否一致
     * 比对结束后，需要将链表返回回去
     * 空间复杂度O(1)
     * @param head
     * @return
     */
    private static boolean method3(Node head) {
        if(head==null || head.next==null) return true;
        Node n1 = head;
        Node n2 = head;
        while (n2.next!=null && n2.next.next!=null) {
            n1=n1.next;
            n2=n2.next.next;
        }

        // n1 指向链表的中间节点，n1后面的节点需要进行反转
        Node n3 = null;
        n2 = n1.next;
        n1.next=null;
        while (n2!=null) {
            n3 = n2.next;
            n2.next=n1;
            n1=n2;
            n2=n3;
        }
        // n1 执行后半部分反转链表的头部
        n2=head; // 原始链表的头部
        n3=n1;   // 后半部分反转链表的头部
        boolean res = true;

        while (n1!=null && n2!=null) {
            if(n2.data != n1.data) {
                res = false;
                break;
            }
            n1=n1.next;
            n2=n2.next;
        }
        // 反转链表
        // n3是后半部分的头节点
        n2=n3.next;
        n3.next=null;
        while (n2!=null) {
            n1=n2.next;
            n2.next=n3;
            n3=n2;
            n2=n1;
        }
        return res;

    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(4);
        Node n6 = new Node(3);
        Node n7 = new Node(2);
        Node n8 = new Node(1);
        Node n9 = new Node(1);


        n1.next=n2;
        n2.next=n3;
        n3.next=n4;
        n4.next=n5;
        n5.next=n6;
        n6.next=n7;
        n7.next=n8;
        n8.next=n9;

        System.out.println(method3(n1));
    }
}
