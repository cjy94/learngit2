package com.chenjunyi.Morris;

/**
 * 当前节点cur，一开始来到整棵树的头
 * 1、cur无左孩子，cur = cur.right
 * 2、cur有左孩子，找到左树的最右节点mostRight
 *  2.1 mostRight的右指针指向null的话，mostRight.right=cur, cur=cur.left
 *  2.2 mostRight的右指针指向cur的话，mostRight.right=null, cur = cur.right
 *
 *
 *  任何一个节点有左孩子，必会经过2次；无左树的节点只经过1次
 *
 *
 *  利用morris 遍历判断一颗树是否是BST
 *
 *  二叉树的最小高度, 所有叶节点中， 哪一个是离头部最短的，返回最短距离
 *  1、无左树， 往右孩子走，level+1
 *  2、有左树，第一次来到自己(cur == parent.left)，level++;
 *            第二次来到自己(cur == 左树上的最右节点)
 *
 *  如何判断某个节点是否是叶子节点?  第二次修改后，单独检查一次
 *
 *  什么时候使用morris
 *  需要左树信息和右树信息，不能使用， 使用二叉树的递归套路
 *  如果可以只收集一侧的信息，可以使用morris遍历
 *
 *
 *
 *  
 *
 */
public class Morris {

    public class Node {
        int value;
        Node right;
        Node left;

        Node(int v) {
            value = v;
        }
    }

    // 空间复杂度  O(1)
    // 时间复杂度 O(N)
    public static void morris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                // 有左树的情况下， 找到左树上的最右节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {    // 第一次访问
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }  else {    //mostRight.right == cur 第二次访问
                    mostRight.right = null;
                }
            }
            cur= cur.right;
        }
    }


    //
    public static void morrisPre(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                // 有左树的情况下， 找到左树上的最右节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {    // 第一次访问 右孩子
                    mostRight.right = cur;
                    System.out.print(cur.value+" ");
                    cur = cur.left;
                    continue;
                }  else {    //mostRight.right == cur 第二次访问 右孩子
                    mostRight.right = null;
                }
            }else {
                System.out.print(cur.value+" ");
            }
            cur= cur.right;
        }
        System.out.println();
    }

    // 一个节点，如果没有左树，(直接打印)， 打印完往右移动；
    // 如果有左树，第一次经过改完指针往左移动， 第二次经过改完指针往右移动
    // 所以 只要节点要往右移动了 就打印
    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                // 有左树的情况下， 找到左树上的最右节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {    // 第一次访问
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }  else {    //mostRight.right == cur 第二次访问
                    mostRight.right = null;
                }
            }
            System.out.print(cur.value+" ");
            cur= cur.right;
        }
        System.out.println();
    }

    // 当第二次回到该节点时， 进行打印，逆序打印左树的右边界
    // 最后单独打印整棵树的右边界，逆序
    public static void morrisPos(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                // 有左树的情况下， 找到左树上的最右节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {    // 第一次访问
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }  else {    //mostRight.right == cur 第二次访问
                    mostRight.right = null;
                    printEdge(cur.left);
                }
            }
            cur= cur.right;
        }
        printEdge(head);
        System.out.println();
    }

    // 打印node的所有右边界
    private static void printEdge(Node node) {
        Node tail = reverse(node);
        Node cur = tail;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        reverse(tail);
    }

    private static Node reverse(Node node) {
        Node pre = null;
        Node cur = node;
        Node next = null;
        while (cur != null) {
            next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    // 利用中序遍历二叉树时， 值是递增的特性进行校验
    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        Node cur = head;
        Node mostRight = null;
        Integer pre = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                // 有左树的情况下， 找到左树上的最右节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {    // 第一次访问
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }  else {    //mostRight.right == cur 第二次访问
                    mostRight.right = null;
                }
            }

            if (pre != null && cur.value <= pre) {
                return false;
            }  else {
                pre = cur.value;
            }
            cur= cur.right;
        }
        return true;
    }
}
