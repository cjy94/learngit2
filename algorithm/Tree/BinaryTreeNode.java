package com.chenjunyi.Tree;

import java.util.LinkedList;

/**
 * 二叉树的节点 结构
 * 
 */
public class BinaryTreeNode {
    private int value;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public BinaryTreeNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }

    //=========================== 二叉树的遍历 ================================
    // 二叉树的遍历方式： 前序遍历、中序遍历、后续遍历、层序遍历
    // 递归实现二叉树的前中后序遍历
    public static void preOrder(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.getValue());
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void inOrder(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.println(root.getValue());
        inOrder(root.right);

    }

    public static void posOrder(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        posOrder(root.left);
        posOrder(root.right);
        System.out.println(root.getValue());
    }

    // 层序遍历
    public static void level(BinaryTreeNode root) {
        LinkedList<BinaryTreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        BinaryTreeNode tmp ;
        while (!queue.isEmpty()) {
            tmp = queue.pollFirst();
            System.out.println(tmp.getValue());
            if (tmp.getLeft() != null) {
                queue.addLast(tmp.getLeft());
            }

            if (tmp.getRight() != null) {
                queue.addLast(tmp.getRight());
            }
        }
        queue = null; // for GC
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        BinaryTreeNode n1 = new BinaryTreeNode(2);
        BinaryTreeNode n2 = new BinaryTreeNode(3);
        BinaryTreeNode n3 = new BinaryTreeNode(4);
        BinaryTreeNode n4 = new BinaryTreeNode(5);
        BinaryTreeNode n5 = new BinaryTreeNode(6);
        root.left = n1;
        root.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.left = n5;
        level(root);
    }








}
