package com.chenjunyi.GO;

/**
 *  栈 和 队列 相关
 *  可以通过双向链表或者数组实现
 *
 *
 *  如何用栈结构实现队列结构    两个栈互相倒
 *  
 *  如何用队列结构实现栈结构   两个队列互相倒
 */
public class StackAndQueue {

    ////////////////////////////////////////////////// 用链表实现队列和栈 /////////////////////////////////////////////////

    static class Node<T> {
        T val;
        Node<T> next;
        Node<T> last;

        public Node(T val) {
            this.val = val;
        }
    }

    public static class DoubleQueue<T> {
        public Node<T> head;
        public Node<T> tail;

        public void addFromHead(T num) {
            Node<T> cur = new Node<>(num);
            if (tail == null) {
                head = tail = cur;
            } else {
                cur.next = head;
                head.last = cur;
                head = cur;
            }
        }

        public void addFromTail(T num) {
            Node<T> cur = new Node<>(num);
            if (tail == null) {
                head = tail = cur;
            } else {
                tail.next = cur;
                cur.last = tail;
                tail = cur;
            }
        }

        public T popFromHead() {
            if (head == null)
                return null;
            Node<T> cur = head;
            if (head == tail) {     // 如果链表中只有一个节点
                head = tail = null;
            } else {
                head = cur.next;
                cur.next = null;
                head.last = null;
            }
            return cur.val;
        }

        public T popFromTail() {
            if (tail == null)
                return null;
            Node<T> cur = tail;
            if (head == tail)   // 如果链表中只有一个节点
                head = tail = null;
            else {
                tail = tail.last;
                tail.next = null;
                cur.last = null;
            }
            return cur.val;
        }
    }

    // 从头部进， 从头部弹出
    public static class MyStack<T> {
        DoubleQueue<T> queue;

        public MyStack() {
            queue = new DoubleQueue<T>();
        }

        public void push(T value) {
            queue.addFromHead(value);
        }

        public T pop() {
            T t = queue.popFromHead();
            return t;
        }
    }

    // 从头部进入, 从尾部弹出
    public static class MyQueue<T> {
        DoubleQueue<T> queue;

        public MyQueue() {
            queue = new DoubleQueue<T>();
        }

        public void push(T value) {
            queue.addFromHead(value);
        }

        public T pop() {
            T t = queue.popFromTail();
            return t;
        }

    }

    //////////////////////////////////////////////////// 用数组实现队列和栈 ///////////////////////////////////////////////
    public static class MyQueueWithArray{
        int[] array;
        int limit;
        int size;
        int pullIndex;
        int popIndex;

        public MyQueueWithArray(int limit) {
            array = new int[limit];
            this.limit = limit;
            size = 0;
            pullIndex = 0;
            popIndex = 0;
        }

        public void push(int num) {
            if (size == limit) {
                throw new RuntimeException("queue is full !!!");
            }
            size++;
            array[pullIndex] = num;
            pullIndex = nextIndex(pullIndex);
        }

        public int pop() {
            if (size == 0)
                throw new RuntimeException("queue is empty !!!");
            size--;
            int val = array[popIndex];
            popIndex = nextIndex(popIndex);
            return val;
        }

        private int nextIndex(int index) {
            return index + 1 < limit ? index + 1 : 0;
        }
    }

}
