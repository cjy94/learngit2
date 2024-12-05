package com.chenjunyi.GO;


import com.chenjunyi.BinaryTree.Tree;

import java.util.Stack;

/**
 *  桶排序，不基于比较的排序
 *
 *  前缀树： 每个节点都有两个值pass、end
 *
 *  计数和基数排序： 对数据状况有要求
 *
 */

public class BucketSort {
    public static class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    /////  桶排序
    

    ////////////////////////////////////////////  前缀树  ///////////////////////////////////////////////////
    public static class Node {
        public int pass;
        public int end;
        public Node[] nexts;

        public Node() {
            pass = 0;
            end = 0;
            nexts = new Node[26];
        }
    }

    public static class Trie {
        private Node root;

        public Trie() {
            root = new Node();
        }

        // 插入单词
        public void insert(String word) {
            char[] ch = word.toCharArray();
            Node node = root;
            node.pass++;
            int path = 0;
            for (int i =0; i < ch.length; i++) {
                path = ch[i] - 'a';
                if (node.nexts[path] == null) {
                    node.nexts[path] = new Node();
                }
                node = node.nexts[path];
                node.pass++;
            }
            node.end++;
        }

        // 返回word这个单词之前加入过几次
        public int search(String word) {
            char[] ch = word.toCharArray();
            Node node = root;
            int path = 0;
            for (int i =0; i < ch.length; i++) {
                path = ch[i] - 'a';
                if (node.nexts[path] != null) {
                    return 0;
                }
                node = node.nexts[path];
            }
            return node.end;
        }

        // 返回word这个单词之前加入过几次
        public int prefixWord(String word) {
            char[] ch = word.toCharArray();
            Node node = root;
            int path = 0;
            for (int i =0; i < ch.length; i++) {
                path = ch[i] - 'a';
                if (node.nexts[path] != null) {
                    return 0;
                }
                node = node.nexts[path];
            }
            return node.pass;

        }

        // 删除单词
        public void delete(String word) {
            if (search(word) != 0) {
                char[] ch = word.toCharArray();
                Node node = root;
                node.pass--;
                int path = 0;
                for (int i = 0; i < ch.length; i++) {
                    path = ch[i] - 'a';
                    if (--node.nexts[path].pass == 0) {
                        node.nexts[path] = null;
                        return;
                    }
                    node = node.nexts[path];
                }
                node.end--;
            }
        }

    }


    //////////////////////////////////////////////// 二叉树 ////////////////////////////////////////////////
    public static void pre(TreeNode head) {
        if (head == null)
            return;
        System.out.println(head.val);
        pre(head.left);
        pre(head.right);
    }

    public static void in(TreeNode head) {
        if (head == null)
            return;
        in(head.left);
        System.out.println(head.val);
        in(head.right);
    }

    public static void pos(TreeNode head) {
        if (head == null)
            return;
        pos(head.left);
        pos(head.right);
        System.out.println(head.val);
    }

    //////  非递归遍历二叉树
    public static void pre1(TreeNode head) {    // 根 左 右
        if (head != null) {
            Stack<TreeNode> stack = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.println(head.val);
                if (head.right != null)
                    stack.push(head.right);
                if (head.left != null)
                    stack.push(head.left);
            }
        }
    }

    // 整条左边界依次入栈
    public static void in1(TreeNode head) {  // 左 根 右
        if (head != null) {
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.println(head.val);
                    head = head.right;
                }
            }
        }
    }

    // 利用两个栈结构
    // 一个栈的入栈顺序： 头右左
    // 栈中弹出就打印； 有左孩子压入左； 有右孩子压入右孩子
    public static void pos1(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<TreeNode> help = new Stack<>();
        stack.push(head);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            help.push(node);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        while (!help.empty()) {
            System.out.print(help.pop().val+" ");
        }
        System.out.println();
    }

    /**
     *  前驱节点：
     *  后继节点：
     */



}
