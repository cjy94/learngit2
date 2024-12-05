package com.chenjunyi.Tree;

import java.util.Stack;

/**
 * 二叉树的遍历
 * 前中后序，层序遍历
 */
public class TreeTraversal {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }


    // 递归实现前序遍历
    public static void preOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.println(root.value);
        preOrder(root.left);
        preOrder(root.right);
    
    }


    // 非递归方式实现前序遍历
    public static void preOrder1(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> s = new Stack<>();
        s.push(root);
        Node tmp;
        while (!s.isEmpty()) {
            tmp = s.pop();
            System.out.println(tmp.value);
            if (tmp.right != null) {
                s.push(tmp.right);
            }

            if (tmp.left != null) {
                s.push(tmp.left);
            }

        }

    }


    public static void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.println(root.value);
        inOrder(root.right);
    }

    // 非递归的方式实现中序遍历
    public static void inOrder1(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> s = new Stack<>();
        Node head = root;
        Node tmp;
        while (!s.isEmpty() || head != null) {
            if (head != null) {
                // 一直向左查找， 将左孩子全部压入栈
                s.push(head);
                head = head.left;
            } else {
                // 如果没有左孩子了，那么处理当前节点，弹出打印
                tmp = s.pop();
                System.out.println(tmp.value);
                // 指向当前节点的右孩子，处理右孩子所在树上的中序遍历
                head = tmp.right;
            }
        }
    }


    public static void posOrder(Node root) {
        if (root == null) {
            return;
        }
        posOrder(root.left);
        posOrder(root.right);
        System.out.println(root.value);
    }

    // 非递归实现后续遍历
    public static void posOrder1(Node root) {
        if (root == null) {
            return;
        }
        Node handler = root;
        Node cur = null;
        Stack<Node> s = new Stack<>();
        s.push(root);
        while (!s.isEmpty()) {
            cur = s.peek();
            if (cur.left != null && handler != cur.left && handler != cur.right) {
                s.push(cur.left);
            }else if (cur.right != null && handler != cur.right) {
                s.push(cur.right);
            } else {
                System.out.println(s.pop().value);
                handler = cur;
            }
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node n1 = new Node(2);
        Node n2 = new Node(3);
        Node n3 = new Node(4);
        Node n4 = new Node(5);
        Node n5 = new Node(6);
        Node n6 = new Node(7);
        root.left = n1;
        root.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.left = n5;
        n2.right = n6;
        posOrder(root);
        System.out.println("===================");
        posOrder1(root);

    }


}
