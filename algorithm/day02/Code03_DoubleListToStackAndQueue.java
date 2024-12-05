package com.chenjunyi.day02;

/**
 * 用双向链表实现队列和栈结构
 *  链表中的头插和尾插，决定队列或者栈的push和pop
 *
 */
public class Code03_DoubleListToStackAndQueue {
    public static void main(String[] args) {
        MyStack<String> myStack = new MyStack<>();
        myStack.push("12");
        myStack.push("23");
        myStack.push("34");
        myStack.push("56");
        myStack.push("67");
        myStack.push("89");
        while (myStack.isEmpty() == false) {
            System.out.println(myStack.pop());
        }

        System.out.println("===================");

        MyQueue<String> myQueue = new MyQueue<>();
        myQueue.push("12");
        myQueue.push("23");
        myQueue.push("34");
        myQueue.push("56");
        myQueue.push("67");
        myQueue.push("89");
        while (myQueue.isEmpty() == false) {
            System.out.println(myQueue.pop());
        }
    }


    public static class Node<T> {
        public T value;
        public Node<T> next;
        public Node<T> pre;

        public Node(T value) {
            this.value = value;
        }
    }

    public static class DoubleList<T> {
        public Node<T> head;
        public Node<T> tail;

        public void addFromHead(T value) {
            Node<T> cur = new Node<T>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.next=head;
                head.pre = cur;
                head = cur;
            }

        }

        public void addFromTail(T value) {
            Node<T> cur = new Node<T>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.pre = tail;
                tail.next = cur;
                tail = cur;
            }

        }

        public T popFromHead() {
            if (head == null) {
                return null;
            }
            Node<T> cur = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.pre = null;
                cur.next = null;
            }
            return cur.value;
        }

        public T popFromTail() {
            if (head == null) {
                return null;
            }
            Node<T> cur = tail;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.pre;
                cur.pre = null;
                tail.next = null;
            }
            return cur.value;
        }

        public boolean isEmpty() {
            return head == null;
        }
    }

    public static class MyStack<T> {
        private DoubleList<T> queue;

        public MyStack() {
            queue = new DoubleList<T>();
        }

        public void push(T value) {
            queue.addFromHead(value);
        }

        public T pop() {
            return queue.popFromHead();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    public static class MyQueue<T> {
        private DoubleList<T> queue;

        public MyQueue() {
            queue = new DoubleList<T>();
        }

        public void push(T value) {
            queue.addFromHead(value);
        }

        public T pop() {
            return queue.popFromTail();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    
}
