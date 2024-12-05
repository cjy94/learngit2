package com.chenjunyi.GO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *  二叉树相关
 *  先序、中序、后序遍历
 *
 *  递归序： 每个节点都会经过3次
 *
 */
public class BinaryTree {

    public static void main(String[] args) {
//        Node n1 = new Node(1);
//        Node n2 = new Node(2);
//        Node n3 = new Node(3);
//        Node n4 = new Node(4);
//        Node n5 = new Node(5);
//        n1.left = n2;
//        n1.right = n3;
//        n2.left = n4;
//        n2.right = n5;
//        List<Integer> l = in1(n1);
//        System.out.println(l);
//        pre(n1);
        //System.out.println(count(4));

//        int[] arr1 = {1,2,3,0,0,0};
//        int[] arr2 = {2,5,6};
//        merge(arr1, 3, arr2, 3);
        System.out.println(climbStairs(45));
        System.out.println(climbStairs1(45));

    }

    static class Node {
        int value;
        Node left;
        Node right;
        Node parent;
        public Node(int value) {
            this.value = value;
        }
    }

    /***  递归方式遍历二叉树 ********/
    public static void pre(Node head) {
        if (head == null)
            return;
        System.out.println(head.value);
        pre(head.left);
        pre(head.right);
    }

    public static void in(Node head) {
        if (head == null)
            return;
        pre(head.left);
        System.out.println(head.value);
        pre(head.right);
    }

    public static void pos(Node head) {
        if (head == null)
            return;
        pre(head.left);
        pre(head.right);
        System.out.println(head.value);
    }

    /***** 非递归方式遍历二叉树 ******/
    /**
     * 先序遍历流程
     *      头节点压入栈
     *      1、弹出节点就打印
     *      2、如果有右孩子 压入右孩子
     *      3、如果有左孩子 压入左孩子
     *
     *  中序遍历流程
     *      整条左边界，依次入栈
     *      左边界处理结束，弹出节点就打印， head=head.right
     *
     *
     *  后序遍历流程    利用两个栈结构
     *      头节点压入栈
     *      1、弹出节点压入栈
     *      2、如果有左孩子 压入左孩子
     *      3、如果有右孩子 压入右孩子
     *
     */

    public static void preWithStack(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.println(head.value);
                if (head.right != null)
                    stack.push(head.right);
                if (head.left != null)
                    stack.push(head.left);
            }
        }
    }

    // 整棵树是可以被左边界分解掉的
    public static void inWithStack(Node node) {
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                System.out.println(node.value);
                node = node.right;
            }
        }
    }

    // 二叉树的宽度优先遍历
    // 按层遍历
    public static void level(Node node) {
        Queue<Node> queue = new LinkedList<>();
        
    }

    /**
     *  二叉树按照中序遍历后的顺序，x 和 x+1  x是x+1的前驱节点； x+1是x的后继节点
     *  后继 和 前驱 节点
     *
     *   X有右树，那么x的后继节点就是右树上最左节点
     *   x没有右树，向上找，如果我是我父节点的左孩子了，这个父节点就是x的后继
     *
     *
     *   X有左树，那么x的后继节点就是左树上最右节点
     *   x没有左树，向上找，如果我是我父节点的右孩子了，这个父节点就是x的前驱
     */

    public static Node successor(Node node) {
        if (node == null)
            return node;

        if (node.right != null) {
            return getLeft(node.right);
        } else {
            Node parent = node.parent;
            while (parent != null && node == parent.right) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    private static Node getLeft(Node node) {
        if (node == null) {
            return node;
        }

        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     *  折纸问题
     *
     *
     *                1凹
     *             /      \
     *           2凹      2凸
     *          /  \     /   \
     *        3凹 3凸   3凹  3凸
     *
     */

    /**
     *  二叉树的递归套路
     * 1、给定一个以x为头的二叉树，判断是否是平衡二叉树
     */
    static class Info{
        boolean isBalance;
        int height;
        public Info(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }

    public static Info isBalance(Node x) {
        if (x == null)
            return new Info(true, 0);
        Info left = isBalance(x.left);
        Info right = isBalance(x.right);
        int height = Math.max(left.height, right.height) + 1;
        boolean isBalance = true;
        if (!left.isBalance || !right.isBalance || Math.abs(left.height - right.height) > 1) {
            isBalance = false;
        }
        return new Info(isBalance, height);
    }

    /**
     *  给定一颗二叉树头节点head，任何两个节点之间都存在距离
     *  返回整棵树的最大距离
     */

    static class InfoA{
        int height;
        int distance;
        public InfoA(int height, int distance) {
            this.distance = distance;
            this.height = height;
        }
    }

    public static InfoA maxDistance(Node x) {
        if (x == null) {
            return new InfoA(0, 0);
        }
        InfoA left = maxDistance(x.left);
        InfoA right = maxDistance(x.right);

        int height = Math.max(left.height, right.height) + 1;
        int distance = Math.max( Math.max(left.distance, right.distance), (left.distance + right.distance + 1));
        return new InfoA(height, distance);
    }

    /**
     *  给定一颗二叉树头节点head，返回这颗二叉树中最大二叉搜索子树的头节点
     *
     *  搜索二叉树，左边都比头我小，右边都比我大
     */


    /**
     *   派对的最大快乐值问题
     */

    /**
     *  二叉树是否对称 x节点
     *  x需要的信息： 左树是否对称， 左树的节点个数 ; 右树是否对称
     */

    public static boolean isPair(Node x) {
        return check(x.right, x.left);

    }

    private static boolean check(Node right, Node left) {
        if (right == null && left == null)
            return true;
        if (right == null || left != null)
            return false;

        return (right.value == left.value) && check(right.left, left.right) && check(right.right, left.left);
    }

    /**
     *  走台阶，一次可以走1步或者2步，n阶台阶有多少种走法，
     *  f(n) = f(n-1) + f(n-2)
     */
    public static int count(int n) {
        if (n == 0 || n ==1 || n ==2) {
            return n;
        }
        return count(n-1) + count(n-2);
    }

    /**
     *  二叉树的中序遍历
     */
    public static List<Integer> in1(Node node) {
        ArrayList<Integer> list = new ArrayList<>();
        if (node == null) {
            return list;
        }
        Stack<Node> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                list.add(node.value);
                node = node.right;
            }
        }
        return list;
    }

    /**
     *  合并两个有序数组， 最终结果存放在nums1中， nums1的长度时m+n
     *  nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     *
     *  [1,2,2,3,5,6]
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1, j = n-1, index = m+n-1;
        int cur = 0;
        while (i >= 0 || j >= 0 ) {
            if (i == -1) {
                cur = nums2[j--];
            } else if (j == -1) {
                cur = nums1[i--];
            } else if (nums1[i] > nums2[j]) {
                cur = nums1[i--];
            } else {
                cur = nums2[j--];
            }
            nums1[index--] = cur;
        }
    }
    public static int climbStairs1(int n) {
        if (n ==0 || n ==1 || n == 2)
            return n;
        return  climbStairs1(n-1) + climbStairs1(n-2);
        
    }

    /**
     * f(n) = f(n-1) + f(n-2)
     */
    public static  int climbStairs(int n) {
        if (n ==0 || n ==1 || n == 2)
            return n;
        int prePre = 1;
        int pre = 2;
        int sum = 0;
        for (int i = 3; i <= n; i++) {
            sum = pre + prePre;
            prePre = pre;
            pre = sum;
        }
        return sum;

    }



}
