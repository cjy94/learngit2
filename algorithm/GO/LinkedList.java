package com.chenjunyi.GO;

import com.chenjunyi.day02.Code01_ReverseList;

import java.util.Arrays;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  链表相关
 *  1、单链表 和 双向链表 反转
 *  2、打印两个有序链表的公共部分    1 2 5   和  0 2 3 5  公共部分2,5
 *  3、判断一个链表是否是回文结构
 *  4、将单链表按某值划分成左边小，中间相等，右边大的形式
 *      4.1 将节点数值放到数组中，进行partition
 *      4.2 6个变量  smallHead smallTail equalHead equalTail bigHead bigTail
 *          这3部分进行链接时，要讨论边界
 *          
 *  5、复制含有随机指针节点的链表
 *      可以利用hash表，       key是原节点  value是复制出来的节点
 *
 *  6、两个链表相交的一系列问题
 *
 *  7、两个链表相加
 *      给定两个链表的头节点head1和head2, 请生成代表两个正数相加值的结果链表
 *
 *  8、合并两个有序链表
 *
 *
 *  重要技巧：
 *  1) 额外数据结构记录 (哈希表)
 *  2) 快慢指针
 *
 *   快慢指针
 *   1) 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
 *   2) 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
 *   3) 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
 *   4) 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
 *
 */
public class LinkedList {
    static class ListNode {
        int value;
        ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }

    // 反转单链表
    // 反转函数一定要有返回值，返回反转后新链表的头节点
    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    // 删除指定数值的节点
    // 有可能要删除的节点是链表的头部，所以需要先检查是否是链表的头节点
    public static ListNode removeNode(ListNode head, int num) {
        // 检查要删除的节点是否是头节点
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }
        // head指向的是第一个不需要删除的节点
        ListNode pre = head;
        ListNode cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }






    static class DoubleListNode<V> {
        V value;
        DoubleListNode<V> next;
        DoubleListNode<V> last;

        public DoubleListNode(V value) {
            this.value = value;
        }
    }

    static class Node1<V> {
        V val;
        Node1<V> next;
        public Node1(V v) {
            this.val = v;
            next = null;
        }
    }



    // 反转链表 ， 一定要设计一个返回值，记录反转后的链表的新头节点
    // 外层使用reverse(node)没有返回值的没有意义，获取不到反转后新链表的头节点
    // 外层必须node = reverse(node); node指向反转后新链表的头部 
    public static ListNode reverse(ListNode node) {
        ListNode pre = null;
        ListNode next = null;
        while (node != null) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    // 双链表的逆序
    public static DoubleListNode reverseDouble(DoubleListNode node) {
        DoubleListNode pre = null;
        DoubleListNode next = null;
        while (node != null) {
            next = node.next;
            node.next = pre;
            node.last = next;
            pre = node;
            node = next;
        }
        return pre;
    }

    // 用单链表结构实现队列
    static class MyQueue<V> {
        Node1<V> head;
        Node1<V> tail;
        int size;

        public MyQueue() {
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        // 从尾部插入
        public void push(V element) {
            Node1<V> cur = new Node1<V>(element);
            if (tail == null) {
                head = tail = cur;
            } else {
                tail.next = cur;
                tail = cur;
            }
            size++;
        }

        // 弹出节点，先进先出， 从头部弹出节点
        public V poll() {
            V ans = null;
            if (head != null) {   // 只有一个节点
               ans = head.val;
               head = head.next;
               size--;
            }

            // 所有数据都被删除了，tail节点需要被置空，很重要，清除脏数据
            if (head == null) {
                tail = null;
            }
            return ans;
        }

        public V peek() {
            V ans = null;
            if (head != null) {
                ans = head.val;
            }
            return ans;
        }
    }

    // 用单链表实现栈结构
    static class MyStack<V> {
        Node1<V> head;
        int size;
        public MyStack() {
            head = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        // 从头部插入
        public void push(V element) {
            Node1<V> cur = new Node1<V>(element);
            if (head == null) {
                head = cur;
            } else {
                cur.next = head;
                head = cur;
            }
            size++;
        }

        // 从头部弹出
        public V poll() {
            V ans = null;
            if (head != null) {
                ans = head.val;
                head = head.next;
                size--;
            }
            return ans;
        }

        // 查看尾部节点
        public V peek() {
            V ans = null;
            if (head != null) {
                ans = head.val;
            }
            return ans;
        }
        
    }

    // 用单链表支持不了双端队列
    // 使用双链表实现双端队列
    // 双端队列，尾部可插入和删除，头部可插入可删除
    static class MyDequeue<V> {
        DoubleListNode<V> head;
        DoubleListNode<V> tail;
        int size;

        public MyDequeue() {
            head = null;
            tail = null;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void pushHead(V value) {
            
        }

    }

     // K个节点的组内逆序调整

    // 从start结点开始，数够k个节点，包含start节点自己
    // 如果从start开始不够k个节点，则返回null
    public static Node getKGroupEnd(Node start, int k) {
        while (--k != 0 && start != null) {
            start = start.next;
        }
        return start;
    }

    // 将start到end之间的链表进行逆序
    public static void reverseFromStartToEnd(Node start, Node end) {
        end = end.next;
        Node pre = null;
        Node cur = start;
        Node next = null;
        while (cur != end) {
           next = cur.next;
           cur.next = pre;
           pre = cur;
           cur = next;
        }
        start.next = end;
    }

    public static Node reverseKthElement(Node head, int k) {
        Node start = head;
        // 1、从头节点开始往后数k个节点，包含头节点自己
        Node end = getKGroupEnd(start, k); // end节点是反转后链表的新头部节点
        if (end == null) {
            return  head;
        }
        head = end;  // head 节点是逆序后要返回的新头部，后面不会再改变
        reverseFromStartToEnd(start, end);
        Node lastEnd = start;      // 上一组的结尾节点
        while (lastEnd.next != null) {  // 每一轮的开始节点
            start = lastEnd.next;
            end = getKGroupEnd(start, k);
            if (end == null) {
                return head;
            }
            reverseFromStartToEnd(start, end);
            lastEnd.next = end;
            lastEnd = start;
        }
        return head;
     }


     // 两个链表相加 , 会产生一个新链表
    public static Node addTwoList(Node node1, Node node2) {
        if (node1 == null || node2 == null) {
            return node1 == null ? node2 : node1;
        }
        int sum = 0;
        int carry = 0;
        Node head = null;
        Node tail = null;
        while (node1 != null || node2 != null) {
            sum = carry;
            if (node1 != null) {
                sum += node1.value;
                node1 = node1.next;
            }
            if (node2 != null) {
                sum += node2.value;
                node2 = node2.next;
            }
            Node cur = new Node(sum % 10);
            if (tail == null) {
                head = tail = cur;
            } else {
                tail.next = cur;
                tail = cur;
            }

            carry = sum / 10;
        }
        if (carry > 0) {
            Node cur = new Node(carry);
            if (tail == null) {
                tail = head = cur;
            } else {
                tail.next = cur;
                tail = cur;
            }
        }
        return head;
    }
     
    // 两个链表相加，不会产生新链表，将相加的值覆盖掉长链表中的值
    public static Node addTwoList2(Node node1, Node node2) {
        int l1 = getLength(node1);
        int l2 = getLength(node2);
        Node largeNode = l1 > l2 ? node1 : node2;
        Node smallNode = l1 < l2 ? node1 : node2;
        Node ans = largeNode;
        Node pre = null;
        int sum = 0;
        int carry = 0;
        while (smallNode != null) {
            sum = carry + smallNode.value + largeNode.value;
            carry = sum / 10;
            largeNode.value = (sum % 10);
            pre = largeNode;
            largeNode = largeNode.next;
            smallNode = smallNode.next;

        }
        while (largeNode != null) {
            sum = carry + largeNode.value;
            carry = sum / 10;
            largeNode.value = sum % 10;
            pre = largeNode;
            largeNode = largeNode.next;
        }

        if (carry > 0) {
            Node n = new Node(carry);
            pre.next = n;
        }
        return ans;
    }

    private static int getLength(Node node) {
        int len = 0;
        while (node != null) {
            node = node.next;
            len++;
        }
        return len;
    }

    // 合并两个有序链表
    public static Node mergeList(Node n1, Node n2) {
        if (n1 == null || n2 == null) {
            return n1 == null ? n2 : n1;
        }
        Node head = n1.value <= n2.value ? n1 : n2;
        Node c1 = head.next;
        Node c2 = head == n1 ? n2 : n1;
        Node pre = head;
        while (c1 != null && c2 != null) {
            if (c1.value <= c2.value) {
                pre.next = c1;
                c1 = c1.next;
            } else {
                pre.next = c2;
                c2 = c2.next;
            }
            pre = pre.next;
        }
        pre.next = c1 == null ? c2 : c1;
        return head;
    }
























    // 合并两个有序链表
    public static ListNode mergeTwoLinkedList(ListNode node1, ListNode node2) {
        if (node1 == null || node2 == null) {
            return node1 == null ? node2 : node1;
        }
        ListNode head = node1.value < node2.value ? node1 : node2;
        ListNode pre = head;
        node1 = head == node1 ? node1.next : node1;
        node2 = head == node2 ? node2.next : node2;
        while (node1 != null && node2 != null) {
            if (node1.value < node2.value) {
                pre.next = node1;
                node1 = node1.next;
            } else {
                pre.next = node2;
                node2 = node2.next;
            }
            pre = pre.next;
        }
        pre.next = node1 == null ? node2 : node1;
        return head;
    }
    // 两个链表做加法
    public static ListNode addLinkedList(ListNode c1, ListNode c2) {
        ListNode node1 = reverse(c1);
        ListNode node2 = reverse(c2);
        int sum = 0, carry = 0;
        ListNode pre = null;
        while (node1 != null || node2 != null) {
            sum = carry;
            if (node1 != null) {
                sum += node1.value;
                node1 = node1.next;
            }
            if (node2 != null) {
                sum += node2.value;
                node2 = node2.next;
            }

            ListNode n = new ListNode(sum % 10);
            n.next = pre;
            pre = n;
            carry = sum / 10;
        }
        if (carry > 0) {
            ListNode n = new ListNode(carry);
            n.next = pre;
            pre = n;
        }
        return pre;
    }


    // 合并两个有序链表
    public static  ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }
        ListNode head = list1.value < list2.value ? list1 : list2;
        ListNode pre = head;
        list1 = list1 == head ? list1.next : list1;
        list2 = list2 == head ? list2.next : list2;
        while (list1 != null && list2 != null) {
            if (list1.value < list2.value) {
                pre.next = list1;
                list1 = list1.next;

            } else {
                pre.next = list2;
                list2 = list2.next;

            }
            pre = pre.next;
        }
        pre.next = list1 == null ? list2 : list1;
        return head;
    }
    // 反转链表
    public static Node reverse1(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static Node mergeTwoList(Node head1, Node head2) {
        Node head = head1.value < head2.value ? head1 : head2;
        Node cur1 = head == head1 ? head1 : head2;  // 值较小的指针
        Node cur2 = head == head1 ? head2 : head1;  // 值较大的指针
        Node pre = null;
        Node next = null;
        while (cur1 != null && cur2 != null) {
            if (cur1.value <= cur2.value) {
                pre = cur1;
                cur1 = cur1.next;
            } else {
                next = cur2.next;
                pre.next = cur2;
                cur2.next = cur1;
                pre = cur2;
                cur2 = next;
            }
        }
        pre.next = (cur1 == null) ? cur2 : cur1;
        return head;
    }

    // 1)
    public static Node upMid(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node slow = head;
        Node fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 2)
    public static Node downMid(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }


    // 3) 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
    public static Node upMidPre(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 4) 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
    public static Node downMidPre(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        if (head.next.next == null) {      // 2个节点
            return head;
        }
        
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    public static int[] plusOne(int[] digits) throws ArithmeticException {
       int len = digits.length;
       int[] ans = new int[len+1];
       int sum = 0, carry = 1;
       for (int i = len-1; i>= 0; i--) {
           sum = carry + digits[i];
           ans[len--] = sum % 10;
           carry = sum / 10;
       }
       try {
           int i = 10 /0;
       } catch (ArithmeticException e) {
           throw e;

       }
       if (carry > 0) {
           ans[len--] = carry;
       }
       if (len >= 0) {
           int[] copy = Arrays.copyOfRange(ans, len+1, ans.length);
           return copy;
       } else {
           return ans;
       }


    }




    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(4);
        ListNode n3 = new ListNode(6);
        n1.next=n2;
        n2.next=n3;


        ListNode n11 = new ListNode(2);
        ListNode n12 = new ListNode(3);
        ListNode n13 = new ListNode(7);
        ListNode n14 = new ListNode(8);
        n11.next = n12;
        n12.next = n13;
        n13.next = n14;
        ListNode listNode = addLinkedList(n1, n11);
        while (listNode!= null) {
            System.out.println(listNode.value);
            listNode = listNode.next;
        }


//        n1.next=n2;
//        n2.next=n5;
//
//        Node n11 = new Node(0);
//        Node n12 = new Node(2);
//        Node n13 = new Node(3);
//        Node n14 = new Node(5);
//        n11.next = n12;
//        n12.next = n13;
//        n13.next = n14;
//
//        printCommon(n1, n11);

    }

    static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    static class DoubleNode {
        int value;
        DoubleNode next;
        DoubleNode pre;
        public DoubleNode(int value) {
            this.value = value;
        }
    }

    static class RandomNode {
        int value;
        RandomNode next;
        RandomNode rand;
        public RandomNode(int value) {
            this.value = value;
        }
    }
    // 双向链表反转
    public static void reverseDouble(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.pre = next;
            pre = head;
            head = next;
        }
    }

    // 单链表反转
    public static Node reverse(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     *  打印公共部分
     */
    public static void printCommon(Node head1, Node head2) {
        while (head1 != null && head2 != null) {
            if (head1.value == head2.value) {
                System.out.println(head1.value);
                head1 = head1.next;
                head2 = head2.next;
            } else if (head1.value < head2.value) {
                head1 = head1.next;
            } else {
                head2 = head2.next;
            }
        }
    }


    /**
     *  回文结构
     *  1、放到栈中； 2、反转链表
     */
    public static boolean huiWen(Node head) {
        Node curr = head;
        Stack<Node> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        while (curr != null) {
            if (curr.value != stack.pop().value) {
                return false;
            }
            curr = curr.next;
        }
        return true;
    }

    // 快慢指针
    public static boolean huiwen1(Node head) {
        // 只有一个节点
        if (head == null || head.next == null)
            return true;
        //debug
        Node test = head;
        while (test != null) {
            System.out.println(test.value);
            test= test.next;
        }
        //debug
        Node fast = head.next;
        Node slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // slow 在链表中点位置
        Node pre = null;
        Node next = null;
        while (slow != null) {
            next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }

        // pre指向反转后右侧链表的头节点，head是正常链表左侧头节点
        slow = pre;
        boolean flag = true;
        Node cur = head;
        while (pre != null && cur != null) {
            if (pre.value != cur.value) {
                flag = false;
                break;
            }
            cur = cur.next;
            pre = pre.next;
        }

        // 再将链表反转回来
        next = null;
        pre = null;
        while (slow != null) {
            next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }

        //debug
        System.out.println("=======");
        test = head;
        while (test != null) {
            System.out.println(test.value);
            test= test.next;
        }
        System.out.println("==========");
        //debug
        return flag;
    }


    public static void cloneNode(RandomNode head) {
        RandomNode cur = head;
        RandomNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = new RandomNode(cur.value);
            cur.next.next = next;
            cur = next;
        }

        // 拆分node
        cur = head;
        RandomNode curCopy = null;
        while (cur != null) {     // 值设置rand节点
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.rand = cur.rand != null ? cur.rand.next : null;
            cur = next;
        }

        cur = head;
        RandomNode res = cur.next;   // clone出来新链表的头节点， 作为返回值
        while (cur != null) {
            curCopy = cur.next;
            next = cur.next.next;
            cur.next = next;
            curCopy.next = next != null ? next.next : null;
        }
    }

    public static Node addList(Node head1, Node head2) {
        // 反转链表
        Node c1 =reverse(head1);
        Node c2 = reverse(head2);
        int carry = 0;
        int sum = 0;
        Node res = null;

        while (c1 != null || c2 != null) {
            sum = carry;
            if (c1 != null) {
                sum += c1.value;
                c1 = c1.next;
            }
            if (c2 != null) {
                sum += c2.value;
                c2 = c2.next;
            }
            Node cur = new Node(sum % 10);
            carry = sum / 10;
            cur.next = res;   // 头插入
            res = cur;
        }

        if (carry > 0) {
            Node n = new Node(carry);
            res.next = n;
        }
        return res;
    }
    
}
