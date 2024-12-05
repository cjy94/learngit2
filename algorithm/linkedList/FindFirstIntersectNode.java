package com.chenjunyi.linkedList;

/**
 * 两个可能无环也可能有环的单链表，头节点head1和head2。 请实现一个函数，如果两个链表相交，返回相交的第一个节； 如果不相交，返回null
 */
public class FindFirstIntersectNode {
    class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }


    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }

    /**
     * 返回有环链表中的第一个入环节点，如果是无环链表，返回null
     */
    public static Node getLoopNode(Node head) {
        if(head == null || head.next == null || head.next.next == null) {
            return null;
        }

        Node slow = head.next;
        Node fast = head.next.next;     // 为了(slow != fast)
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     *  返回两个无环单链表相遇的第一个节点， 如果没有相交返回null
     */
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }

        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }

        // 两个单链表不相交
        if (cur1 != cur2) {
            return null;
        }

        cur1 = n > 0 ? head1 : head2;  // 长链表
        cur2 = cur1 == head1 ? head2 : head1; // 短链表

        // 长链表走n步
        n = Math.abs(n);
        while (n != 0) {
            cur1 = cur1.next;
            n--;
        }

        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        return cur1;
    }


    /**
     *  两个有环链表的相交节点
     *
     */
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;

        //
        if (loop1 == loop2) {
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }

            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }

            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;

            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }  else {   // loop1 != loop2
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }


    }

    

    
}
