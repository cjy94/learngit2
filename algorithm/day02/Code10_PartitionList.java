package com.chenjunyi.day02;

public class Code10_PartitionList {
    public static class Node {
        public int data;
        public Node next;
        Node(int data) {
            this.data = data;

        }
    }


    // 方式一： 将链表中的元素全部放到数组中， 数组中执行partition，之后再用链表组合起来
    // 方式二： 使用6个变量分别记录小的头和尾；相等的头和尾； 大的头和尾
    private static Node partition(Node head, int target) {
        if (head==null || head.next==null) return head;
        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node bH = null;
        Node bT = null;
        Node next = null;
        while (head!=null) {
            next=head.next;
            head.next=null;
            if (head.data<target) {
                if(sH==null) {
                    // 将head的引用赋给新的头节点，应该将head.next=null才可以
                    sH=sT=head;
                } else {
                    sT.next=head;
                    sT=head;
                }
            } else if(head.data==target) {
                if(eH==null) {
                    eH=eT=head;
                } else {
                    eT.next=head;
                    eT=head;
                }

            } else{
                if(bH==null) {
                    bH=bT=head;
                } else {
                    bT.next=head;
                    bT=head;
                }
            }
            head=next;
        }

        // 连接链表
        if(sH!=null) {
            sT.next=eH;
            // 因为下一步是等于区域的尾连接大于区域的头， 所以需要检查下eT!=null
            eT = eT == null ? sT : eT;
        }
        if (eT!=null) {
            eT.next=bH;
        }
        return sH != null ? sH : (eH != null ? eH : bH);


    }

    public static void main(String[] args) {
        Node n1 = new Node(3);
        Node n2 = new Node(4);
        Node n3 = new Node(6);
        Node n4 = new Node(7);
        Node n5 = new Node(1);
        Node n6 = new Node(8);
        Node n7 = new Node(9);
        Node n8 = new Node(5);
        Node n9 = new Node(5);


        n1.next=n2;
        n2.next=n3;
        n3.next=n4;
        n4.next=n5;
        n5.next=n6;
        n6.next=n7;
        n7.next=n8;
        n8.next=n9;

        Node head =  partition(n1, 4);
        while (head!=null) {
            System.out.println(head.data);
            head=head.next;
        }

    }
}
