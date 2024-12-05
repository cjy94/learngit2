package com.chenjunyi.day06;

import java.util.PriorityQueue;
import java.util.Stack;

public class Code01_RecursiveTraversalBT {
    public static class Node{
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;

        }
    }

    /**
     * 前序遍历二叉树： 根左右
     * @param head
     */
    public static void pre(Node head) {
        if (head == null) return;
        System.out.println(head.value);
        pre(head.left);
        pre(head.right);

    }

    /**
     * 中序遍历二叉树： 左根右
     * @param head
     */
    public static void in(Node head) {
        if (head == null) return;
        pre(head.left);
        System.out.println(head.value);
        pre(head.right);
    }

    /**
     * 后序遍历二叉树：左右根
     * @param head
     */
    public static void pos(Node head) {
        if (head == null) return;
        pre(head.left);
        pre(head.right);
        System.out.println(head.value);

    }

    /**
     * 使用非递归的方式实现 前序遍历
     * 规则：
     * 先将头节点放入栈中；
     * 弹出栈中元素并打印；
     * 如果有右孩子，将右孩子压入栈中；
     * 如果有左孩子，就将左孩子压入栈中。 
     * @param head
     */
    public static void pre1(Node head) {
        if (head == null) return;
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
            System.out.println(head.value);
            if (head.right!=null) {
                stack.push(head.right);
            }
            if (head.left!=null) {
                stack.push(head.left);
            }
        }
    }

    /**
     * 1、整条左边界压入栈
     * 2、弹出节点就打印，如果右孩子则压入栈中
     * @param head
     */
    public static void in1(Node head) {
        if (head == null) return;
        Stack<Node> stack = new Stack<>();

        while (!stack.isEmpty() || head != null) {
            if (head!=null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                System.out.println(head.value);
                head = head.right;
               // stack.push(head.right);
            }

        }
    }

    /**
     * 使用非递归的方式实现 后序遍历
     * 规则：
     * 先将头节点放入栈中；
     * 弹出栈中元素并打印；
     * 如果有左孩子，将左孩子压入栈中；
     * 如果有右孩子，就将右孩子压入栈中。
     * 该种方式出来的顺序是： 根右左； 那么逆序打印出来就是，左右根
     * @param head
     */
    public static void pos1(Node head) {
        if (head == null) return;
        Stack<Node> stack = new Stack<>();
        Stack<Node> stack1 = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            head = stack.pop();
           // System.out.println(head);
            stack1.push(head);
            if (head.left!=null) {
                stack.push(head.left);
            }
            if (head.right!=null) {
                stack.push(head.right);
            }
        }
        while (!stack1.isEmpty()) {
            System.out.println(stack1.pop().value);
        }
    }

    /**
     * 按层遍历二叉树
     * @param head
     */
    public static void leavel(Node head) {
        if (head == null) return;
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            System.out.println(head.value);
            if (head.left!=null) queue.add(head.left);
            if (head.right!=null) queue.add(head.right);
        }
        
    }
}
