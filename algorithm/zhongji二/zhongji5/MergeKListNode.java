package com.chenjunyi.zhongji二.zhongji5;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKListNode {
    public static void main(String[] args) {

    }

    class ListNode {
        int value;
        ListNode next;

        ListNode(int v, ListNode n) {
            value = v;
            next = n;
        }
    }

    class ListNodeComparator implements Comparator<ListNode> {

        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.value - o2.value;
        }
    }

    public ListNode mergeKListNode(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(new ListNodeComparator());
        // 将每个链表的头节点放入优先级队列中
        for (ListNode node : lists) {
            queue.add(node);
        }

        ListNode head = null;
        ListNode pre = null;
        
        while (!queue.isEmpty()) {
            ListNode cur = queue.poll();
            head = head == null ? cur : head;
            if (pre != null) {
                pre.next = cur;
            }
            pre = cur;
            if (cur.next != null) {
                queue.add(cur.next);
            }

        }
        return head;
    }
}
