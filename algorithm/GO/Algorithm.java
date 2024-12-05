package com.chenjunyi.GO;

import java.awt.desktop.SystemEventListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 *
 *  1、链表反转
 *  2、字符串反转
 *      2.1) StringBuilder reverse()
 *      2.2) toCharArray() 后反转数组
 *  3、一个数组，实现偶数在前，奇数在后，不改变相对次序
 *  4、二分法找排序数组中绝对值最小的元素
 *  5、链表 两两 反转
 *  6、两个有序数组，有相同的元素，找出来
 *  7、二叉树怎么实现的    通过数组?
 *  8、快速排序、堆排序
 *  9、给定一个数组arr和一个整数sum，请返回累加和等于sum的子数组有多少个?   (预备知识)
 *      子数组必须以0、1、2...i结尾情况下，累加和等于sum的子数组有多少个?  需要前缀和数组 和 有序表
 *
 * 10、某个集合1000万数据如何高校计算
 *
 *   https://www.cnblogs.com/kkdd-2013/p/3460214.html
 *
 */
public class Algorithm {


    public static int myAtoi(String s) {
        if (s ==  null || s.length() == 0)
            return 0;

        int index = 0;
        int res = 0;
        int len = s.length();
        int sign = 1;  // 正负 符号表示
        // 处理空格
        while(index < len && s.charAt(index) == ' ') {
            index++;
        }
        // 检查正负号
        if (index < len && (s.charAt(index) == '+' || s.charAt(index) == '-')) {
            sign = s.charAt(index++) == '+' ? 1 : -1;
        }

        // 处理数字
        while (index < len && ((s.charAt(index) - '0') >= 0) && ((s.charAt(index)-'0') <= 9)) {
            int digit = s.charAt(index) - '0';
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && digit > 7))
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

            res = res * 10 + digit;
            index++;
        }
        return res * sign;

    }
    static final int COUNT = 10000000;
    // 每个task求和的规模
    private static final int SIZE_PER_TASK = 200000;
    // 一个集合中有1000万的数据，如何高效求和
    // 1、单线程方式
    public static void sum(int[] arr) {
        long start = System.currentTimeMillis();
        int sum = 0;
        for (int v : arr) {
            sum += v;
        }
        long end = System.currentTimeMillis();
        System.out.println("单线程执行时间： " + (end-start) + " ms");
    }

    // 2、使用多线程方式
    public static void longAdder(int[] arr) throws InterruptedException {
        int size = arr.length;
        List<List<int[]>> taskList = new ArrayList<>();
        List<int[]> ints = Arrays.asList(arr);
        for (int i = 0; i < size; i += SIZE_PER_TASK) {
            // 获取子列表
            taskList.add(ints.subList(i, Math.min(SIZE_PER_TASK+i, size)));
        }
        int tasks = taskList.size();
        int core = Runtime.getRuntime().availableProcessors() + 1;
        long start = System.currentTimeMillis();
        ExecutorService executorService = new ThreadPoolExecutor(core, core, 3, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        LongAdder sum = new LongAdder();
        CountDownLatch latch = new CountDownLatch(tasks);
        for (int i =0; i < tasks; i++) {
            int[] task = taskList.get(i).get(0);
            executorService.submit(() -> {
                try {
                    for (int v : task) {
                        sum.add(v);
                    }
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        long end = System.currentTimeMillis();
        System.out.println("LongAdder执行时间： " + (end-start) + " ms");
        executorService.shutdown();
    }

    public static void streamSum(List<Integer> arr) {
        long start = System.currentTimeMillis();
        int sum = arr.stream().mapToInt(num->num).sum();
        long end = System.currentTimeMillis();
        System.out.println("stream执行时间： " + (end-start) + " ms");
    }

    public static void parallelStreamSum(List<Integer> arr) {
        long start = System.currentTimeMillis();
        int sum = arr.parallelStream().mapToInt(num->num).sum();
        long end = System.currentTimeMillis();
        System.out.println("parallelStream执行时间： " + (end-start) + " ms");
    }

    // fork join
    static class ForkJoinTask extends RecursiveTask<Integer> {
        // 子任务计算区间开始
        private Integer left;
        // 子任务计算区间结束
        private Integer right;
        private int[] arr;

        public ForkJoinTask(int[] arr, int left, int right) {
            this.arr = arr;
            this.left = left;
            this.right = right;
        }
        @Override
        protected Integer compute() {
            if (right - left < SIZE_PER_TASK) {
                int sum = 0;
                for (int v : arr) {
                    sum += v;
                }
                return sum;
            } else {
                int mid = left + ((right - left) >>> 1);
                ForkJoinTask task1 = new ForkJoinTask(arr, left, mid);
                ForkJoinTask task2 = new ForkJoinTask(arr, mid+1, right);
                invokeAll(task1, task2);
                Integer t1 = task1.join();
                Integer t2 = task2.join();
                return t1 + t2;
            }
        }
    }

    public static int forkJoin(int[] arr) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long start = System.currentTimeMillis();
        ForkJoinTask task = new ForkJoinTask(arr, 0, arr.length);
        Integer sum = forkJoinPool.invoke(task);
        long end = System.currentTimeMillis();
        System.out.println("forkJoin执行时间： " +(end-start)+" ms");
        return sum;
    }





    public static ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet();
        for (ListNode a = headA; a != null; a = a.next) {
            set.add(a);
        }
        for (ListNode b = headB; b != null; b = b.next) {
            if (set.contains(b)) {
                return b;
            }
        }
        return null;
    }
    // 判断两个链表是否相交
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;
        int aLen = 0, bLen = 0;
        while (a != null) {
            aLen++;
            a = a.next;
        }
        while (b != null) {
            bLen++;
            b = b.next;
        }
        int diff = Math.abs(aLen-bLen);
        ListNode smallList = aLen > bLen ? headB : headA;
        ListNode bigList = aLen > bLen ? headA : headB;
        while (diff > 0) {
            bigList = bigList.next;
            diff--;
        }
        while (bigList != null && smallList != null) {
            if (bigList == smallList) {
                return bigList;
            } else {
                bigList = bigList.next;
                smallList = smallList.next;

            }
        }
        return null;
    }
    // 判断链表中师傅存在坏
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        boolean flag = false;
        while (head != null) {
            if (!set.contains(head)) {
                set.add(head);
                head = head.next;
            } else {
                flag = true;
                break;
            }
        }
        return flag;
    }
    // 删除链表中重复元素
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode cur = head;
        HashSet<Integer> set = new HashSet<>();
        ListNode pre = null;
        while (cur != null) {
            if (!set.contains(cur.val)) {
                set.add(cur.val);
                pre = cur;
                cur = cur.next;
            } else {
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                pre.next = cur.next;
                cur = cur.next;
            }
        }
        return head;
    }
    // 删除链表倒数第n个节点， 并返回链表的头节点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 先要找到倒数第n个节点
        ListNode fast = head;
        ListNode slow = head;
        ListNode pre = null;
        for (int i = 1; i <= n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next;
        }
        // slow 所在的节点就是要被删除的节点
        pre.next = slow.next;
        slow.next = null;
        return head;
    }




    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int value) {
            this.val = value;
        }
    }
    // 链表两两反转


    // 一个数组实现偶数在前，奇数在后
    public static void  evenOdd(int[] arr) {
        int l = 0, r = arr.length-1;
        while (l < r) {
            while (l < arr.length && arr[l] % 2 == 0) {
                l++;
            }
            while (r >= 0 && arr[r] % 2 != 0) {
                r--;
            }
            if (l < r)
                swap(arr, l, r);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public static void main(String[] args) {
        myAtoi("   -042");
        ListNode n1 = new ListNode(4);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(8);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);

        ListNode n11 = new ListNode(5);
        ListNode n12 = new ListNode(6);
        ListNode n13 = new ListNode(1);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        n11.next = n12;
        n12.next = n13;
        n13.next = n3;
        n3.next = n4;
        n4.next = n5;
        ListNode n33 = getIntersectionNode(n1, n11);
        ListNode n22 = getIntersectionNode1(n1, n11);



    }
}
