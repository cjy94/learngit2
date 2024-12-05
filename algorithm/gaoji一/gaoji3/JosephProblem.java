package com.chenjunyi.gaoji一.gaoji3;

/**
 * 约瑟夫环问题
 */
public class JosephProblem {

    static class Node{
        int value;
        Node next;
        public Node(int value){
            this.value = value;
        }
    }
    private static int getLive(int i, int m) {
        if (i == 1) {
            return 1;
        }
        return (getLive(i-1,m)+m -1) % i +1;
    }

    public  static Node kill1(Node head, int m) {
        if (head == null || head.next == head || m <1) {
            return head;
        }

        Node cur = head.next;
        int tmp = 1;
        while (cur != head) {
            tmp++;
            cur = cur.next;
        }
        tmp = getLive(tmp, m);
        while (--tmp != 0) {
            head = head.next;
        }
        head.next = head;
        return head;
    }
}
