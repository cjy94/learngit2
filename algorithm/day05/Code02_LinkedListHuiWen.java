package com.chenjunyi.day05;
import java.util.ArrayList;

/**
 * 回文字符串： ”12321“、"abcba" 正着读和反着读结果都一样
 */
public class Code02_LinkedListHuiWen {
    public static void main(String[] args) {
        Node root = new Node(1);
        Node n1 = new Node(2);
        Node n2 = new Node(2);
        Node n3 = new Node(1);

        root.next = n1;
        n1.next = n2;
        n2.next = n3;

        boolean huiWenLinkedList = isHuiWenLinkedList3(root);
        System.out.println(huiWenLinkedList);
    }

    public static class Node {
        int value;
        Node next;
        public Node(int value) {
            this.value = value;
            
        }
    }



    // 笔试的时候处理回文情况 ， 将链表的全部元素放入集合中，进行比对
    public static boolean isHuiWenLinkedList1(Node head) {
        // 如果长度是偶数，一定不是回文，直接返回
        if(head == null) return false;
        Node cur = head;
        ArrayList<Integer> list = new ArrayList();
        while (cur != null) {
            list.add(cur.value);
            cur = cur.next;
        }

        int l =0;
        int r = list.size()-1;
        while(l < r) {
            if (list.get(l).equals(list.get(r))) {
                l++;
                r--;
            } else {
                return false;
            }
        }
        return true;


    }

    // 将链表的一半放入集合中，将集合中的元素和链表的剩下部分进行比对
    public static boolean isHuiWenLinkedList2(Node head) {
       return false;
    }

    // 不使用容器，将链表原地反转。 使用快慢指针。获取链表的中点位置，将中点后面的位置进行逆序
    // 再将结果返回之前要将链表调整成初始状态
    public static boolean isHuiWenLinkedList3(Node head) {
        // 一些界限判断
        if (head == null || head.next == null) {
            return true;
        }
        // 先找到链表的中间节点
        Node n1 = head;
        Node n2 = head;
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;  // -->mid
            n2 = n2.next.next;   // --> end
        }
        // slow节点就是中间节点，slow后面的节点逆序
        n2 = n1.next;
        Node n3 = null;
        n1.next = null;
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;  // 指向新逆序后的头节点
            n2 = n3;
        }
        boolean res = true;
        n2 = head;
        n3 = n1;    // n3用于记录 后半部分逆序链表的头节点，为了恢复链表时使用
        while (n1 != null && n2 != null) {
            if(n1.value != n2.value) {
                res = false;
                break;
            } else {
                n1 = n1.next;
                n2 = n2.next;
            }
        }
        // 恢复链表初始结构
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
