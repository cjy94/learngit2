package com.chenjunyi.BinaryTree;

import java.util.Stack;

/**
 * 二叉树
 *
 */
public class Tree {
    class Node{
        int value;
        Node left;
        Node right;
        public Node(int value) {
            this.value = value;
        }
    }
    /**
     * 递归方式遍历二叉树
     */

    public static void pre(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.value);
        pre(head.left);
        pre(head.right);
    }

    public static void in(Node head) {
        if (head == null) {
            return;
        }
        pre(head.left);
        System.out.println(head.value);
        pre(head.right);
    }

    public static void pos(Node head) {
        if (head == null) {
            return;
        }
        pre(head.left);
        pre(head.right);
        System.out.println(head.value);
    }

    /*
    非递归方式遍历二叉树
     */
    // 先序遍历， 利用栈结构
    // 栈中弹出就打印； 有右孩子压入右孩子； 有左孩子压入左孩子
    public static void pre1(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.empty()) {
            Node node = stack.pop();
            System.out.print(node.value+" ");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        System.out.println();
    }

    // 利用两个栈结构
    // 一个栈的入栈顺序： 头右左
    // 栈中弹出就打印； 有左孩子压入左； 有右孩子压入右孩子
    public static void pos1(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Stack<Node> help = new Stack<>();
        stack.push(head);
        while (!stack.empty()) {
            Node node = stack.pop();
            help.push(node);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        while (!help.empty()) {
            System.out.print(help.pop().value+" ");
        }
        System.out.println();
    }

    public static void pos2(Node head) {
        if(head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        Node c = null;
        while (!stack.empty()) {
            c = stack.peek();
            if (c.left != null && head != c.left && head != c.right) {
                stack.push(c.left);
            }else if (c.right != null && head != c.right) {
                stack.push(c.right);
            }else {
                System.out.print(stack.pop().value+" ");
                head = c;      // head指向上一次打印的节点
            }

        }

    }

    // 将整棵树的左边界全部放入栈中
    // 没有左孩子了，来到右树在将右树的左边界全部压入栈
    public static void in1(Node head) {
        if (head == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        while (!stack.empty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                System.out.print(head.value+" ");
                head = head.right;
            }
        }
    }


}
