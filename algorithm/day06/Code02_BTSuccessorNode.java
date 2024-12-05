package com.chenjunyi.day06;

import java.util.ArrayList;

public class Code02_BTSuccessorNode {
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);

        n1.parent = null;
        n1.left = n2;
        n1.right = n3;
        // n2
        n2.parent = n1;
        n2.right=n4;
        n4.parent = n2;
        n3.parent = n1;
        n3.left=n5;
        n3.right=n6;
        n5.parent=n3;
        n6.parent=n3;

        /**
         *         n1
         *       /   \
         *      n2   n3
         *       \   / \
         *       n4  n5 n6
         * 中序遍历的结果是： n2 n4 n1 n5 n3 n6
         *
         *
         * 
         */

        Node s =  n1.successor1(n2);
        System.out.println(s.value); // 4
        s =  n1.successor1(n1);
        System.out.println(s.value);  // null
        s =  n1.successor(n1);
        System.out.println(s.value);
        s =  n1.successor(n5);
        System.out.println(s.value);
        s =  n1.successor(n3);
        System.out.println(s.value);
        
    }

    public static class Node{
        int value;
        Node left;
        Node right;
        Node parent;

        public Node(int value) {
            this.value = value;
        }

        /**
         * 根据根节点，找出某个节点的后继节点
         * 后继节点： 一颗二叉树中，在中序遍历的顺序中，一个节点的后面是谁，谁就是当前节点的
         * 思路：
         *  1、找到整棵树的头节点。因为在当前环境中，没有头节点，但是每个节点有指向父节点的指针，所以当给出一个节点时，可以根据父节点找到整棵树的根节点
         *  2、从头节点按照中序遍历的方式将节点依次放入一个集合中
         *  3、在集合中找到该节点，节点的下一个索引位置就是后继节点
         *
         * @param node
         * @return
         */
        public Node successor(Node node) {
            // 1、根据node.parent找到整个树的头节点
            Node cur = node;
            Node root;
            while (cur.parent!=null) {
                cur = cur.parent;

            }
            root = cur; // root节点就是整棵树的头节点
            // 1、按照中序遍历的方式，遍历整个树
            ArrayList<Node> nodes = new ArrayList<>();
            inOrder(root, nodes); // 中序遍历的时间复杂度，递归序是 O(N)
            // 2、在集合中查找该节点的索引位置
            for (int i =0; i < nodes.size(); i++) {
                if (nodes.get(i).value == node.value && i != nodes.size()-1) {
                    return nodes.get(i+1);
                }
            }
            return null;
        }

        /**
         * 后继节点，
         * 一、如果存在右子树，则向右子树的左孩子递归，找到最左的节点
         * 二、不存在左孩子的节点, 向父亲查找
         *      满足当前节点是父亲节点的左孩子即可，否则同时更新父节点和孩子节点
         * @param node
         * @return
         */
        public Node successor1(Node node) {
            if (node == null) return node;
            if(node.right!=null) {
                // 右树上找最左节点
                while (node.left!=null) {
                    node = node.left;
                }
                return node;
            }else { // 如果左子树不存在，则向父亲节点查找
                Node parent = node.parent;
                while (parent!=null && parent.left != node) {
                    node = parent;
                    parent = node.parent;
                }
                return parent;
            }

        }

        private void inOrder(Node root, ArrayList<Node> nodes) {
            if (root == null) return;
            inOrder(root.left, nodes);
            nodes.add(root);
            inOrder(root.right, nodes);


        }


    }


}
