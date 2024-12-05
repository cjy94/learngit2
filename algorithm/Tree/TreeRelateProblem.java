package com.chenjunyi.Tree;

import java.util.LinkedList;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 和二叉树相关的问题
 * 1、给出二叉树查找最大元素的算法
 * 2、给出二叉树中搜索某个元素的算法
 * 3、实现将一个元素插入到二叉树中
 * 4、给出二叉树获取节点个数的算法
 * 5、实现删除树的算法
 * 6、给出算法， 逆向逐层输出树中的元素
 * 7、求二叉树深度的算法
 * 8、实现查找二叉树中最深节点的算法 （层序遍历）
 * 9、获取二叉树中叶子节点的个数
 * 10、实现二叉树中满节点的个数（满节点就是既有左孩子又有右孩子）
 * 11、实现二叉树中半节点的个数（半节点就是只有左孩子或者只有右孩子）
 * 12、给定两棵树，判断他们的结构是否相同
 * 13、求二叉树的直径（）     也就是树中任意两个节点的最大距离
 * 14、找出二叉树中同一层节点的数据之和最大的层
 * 15、给定一个二叉树，输出所有从根节点到叶子节点的路径(???  问题20)
 * 16、给定一个算法判断是否存在路径的数据和等于给定值。也就是说，判断是否存在一条从根节点到任意节点的路径，其所经过的数据和为给定值
 * 17、求出二叉树所有节点数据之和
 * 18、实现一颗树转换为镜像的算法（???  问题24）
 * 19、给定两棵树，检查是否为镜像
 * 20、给出一个算法， 根据给定的中序遍历和前序遍历来构建二叉树
 * 21、给定两个遍历序列，能否构建一颗唯一的二叉树？？  如果其中一个是中序遍历，则可以
 * 中序和后序； 中序和前序； 中序和层序
 * 22、涉及一个算法，打印二叉树中某节点的所有祖先节点
 * 23、涉及算法查找二叉树中两个几点的最近公共祖先节点
 * 24、Zigzag遍历树
 *        1
 *     /   \
 *    2    3
 *   /\    /\
 *  4 5   6 7
 *  ziazag: 1->3->2->4->5->6->7
 *
 *  25、设计算法找到二叉树中的垂直和（??? 问题31）
 *  26、假设一颗树，叶子节点用'L'表示，内部节点用'I'表示，同时假定每个节点只能有0个或者2个孩子节点。根据这棵树的前序序列，构建这棵树
 *  'ILILL'
 *      I
 *     / \
 *    L  I
 *      /\
 *     L  L
 *
 */
public class TreeRelateProblem {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class NodeWithSibling {
        public int value;
        public NodeWithSibling left;
        public NodeWithSibling right;
        public  NodeWithSibling sibling;

        public NodeWithSibling(int value) {
            this.value = value;
        }
    }

    // 递归方式，左子树的最大值，右子树的最大值，和当前根节点比较去最大值
    public static int findMax(Node root) {
        int max = Integer.MIN_VALUE;
        if (root == null) {
            return 0;
        }

        int left = findMax(root.left);
        int right = findMax(root.right);
        int value = Math.max(root.value, Math.max(left, right));
        return Math.max(max, value);
    }

    // 非递归方式
    public static int findMaxNoRecursive(Node root) {
        if (root == null) {
            return 0;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(root);
        int max = Integer.MIN_VALUE;
        Node tmp;
        while (!queue.isEmpty()) {
            tmp = queue.pollFirst();
            max = Math.max(tmp.value, max);

            if (tmp.left != null) {
                queue.addLast(tmp.left);
            }
            if (tmp.right != null) {
                queue.addLast(tmp.right);
            }

        }
        queue = null; // help GC
        return max;
    }

    // 递归的方式查找某个元素
    public static boolean findElement(Node root, int data) {
        if (root == null) {
            return false;
        }

        if (root.value == data) {
            return true;
        }

        boolean left = findElement(root.left, data);
        boolean right = findElement(root.right, data);
        return left || right;
    }

    // 非递归的方式查找某个元素
    public static boolean findElementNoRecursive(Node root, int data) {
        if (root == null) {
            return false;
        }

        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(root);
        Node tmp;
        while (!queue.isEmpty()) {
            tmp = queue.pollFirst();
            if (tmp.value == data) {
                return true;
            }
            if (tmp.left != null) {
                queue.addLast(tmp.left);
            }
            if (tmp.right != null) {
                queue.addLast(tmp.right);
            }

        }
        queue = null; // help GC
        return false;

    }

    // 将一个元素插入到二叉树中
    public static void insertElement(Node root, int data) {
        Node node = new Node(data);
        if (root == null) {
            root = node;
            return;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(root);
        Node tmp;
        while (!queue.isEmpty()) {
            tmp = queue.pollFirst();
            if (tmp.left != null) {
                queue.addLast(tmp.left);
            } else {
                tmp.left = node;
                queue = null; //help GC
                return;
            }
            if (tmp.right != null) {
                queue.addLast(tmp.right);
            } else {
                tmp.right = node;
                queue = null; // help GC
                return;
            }
        }
        queue = null; // help GC
        return;
    }

    public static int sizeOfBinaryTree(Node root) {
        if (root == null) {
            return 0;
        }
        int left = sizeOfBinaryTree(root.left);
        int right = sizeOfBinaryTree(root.right);
        return left + 1 + right;
    }

    public static int sizeOfNoRecursive(Node root) {
        if (root == null) {
            return 0;
        }
        int ans = 0;

        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(root);
        Node tmp;
        while (!queue.isEmpty()) {
            tmp = queue.pollFirst();
            ans++;
            if (tmp.left != null) {
                queue.addLast(tmp.left);
            }
            if (tmp.right != null) {
                queue.addLast(tmp.right);
            }

        }
        queue = null; // help GC
        return ans;
    }


    public static void delete(Node root) {
        if (root == null) {
            return;
        }
        delete(root.left);
        delete(root.right);
        root = null;
    }

    public static void printAll(Node root) {
        if (root == null) {
            return;
        }
        LinkedList<Node> queue = new LinkedList<>();
        Stack<Node> s = new Stack<>();
        queue.addLast(root);
        Node tmp;
        while (!queue.isEmpty()) {
            tmp = queue.pollFirst();
            if (tmp.left != null) {
                queue.addLast(tmp.left);
            }
            if (tmp.right != null) {
                queue.addLast(tmp.right);
            }
            s.push(tmp);
        }
        while (!s.isEmpty()) {
            System.out.println(s.pop().value);
        }
        queue = null;
        s = null;  // help GC
        return;

    }

    public static int getDepth(Node root) {
        if (root == null) {
            return 0;
        }

        int left = getDepth(root.left);
        int right = getDepth(root.right);
        return Math.max(left, right) + 1;


    }

    public static int getDepthNoRecursive(Node root) {
        if (root == null) {
            return 0;
        }

        LinkedList<Node> queue = new LinkedList<>();
        int ans = 0;
        queue.addLast(root);
        queue.addLast(null);
        Node tmp;
        while (!queue.isEmpty()) {
            tmp = queue.pollFirst();
            if (tmp == null) {  // 本层结束

                if (!queue.isEmpty()) {
                    queue.addLast(null);
                }
                ans++;

            } else {
                if (tmp.left != null) {
                    queue.addLast(tmp.left);
                }
                if (tmp.right != null) {
                    queue.addLast(tmp.right);
                }
            }

        }
        return ans;

    }

    public static Node deepestNode(Node root) {
        if (root == null) {
            return null;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(root);
        Node tmp = null;
        while (!queue.isEmpty()) {
            tmp = queue.pollFirst();

            if (tmp.left != null) {
                queue.addLast(tmp.left);
            }
            if (tmp.right != null) {
                queue.addLast(tmp.right);
            }
        }
        queue = null; //help GC
        return tmp;
    }


    public static int getLeafNodeCount(Node root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int left = getLeafNodeCount(root.left);
        int right = getLeafNodeCount(root.right);
        return left + right;
    }

    public static int getLeafCountNoRecursive(Node root) {
        if (root == null) {
            return 0;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        int ans = 0;
        Node tmp;
        while (!queue.isEmpty()) {
            tmp = queue.pollFirst();
            if (tmp.left==null && tmp.right == null) {
                ans++;
            }
            if (tmp.right != null) {
                queue.addLast(tmp.right);
            }
            if (tmp.left != null) {
                queue.addLast(tmp.left);
            }

        }
        queue = null; // help GC
        return ans;

    }

    public static int getFullCountNoRecursive(Node root) {
        if (root == null) {
            return 0;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        int ans = 0;
        Node tmp;
        while (!queue.isEmpty()) {
            tmp = queue.pollFirst();
            if (tmp.left != null && tmp.right != null) {
                ans++;
            }
            if (tmp.right != null) {
                queue.addLast(tmp.right);
            }
            if (tmp.left != null) {
                queue.addLast(tmp.left);
            }

        }
        queue = null; // help GC
        return ans;

    }

    public static int getHalfCountNoRecursive(Node root) {
        if (root == null) {
            return 0;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(root);
        int ans = 0;
        Node tmp;
        while (!queue.isEmpty()) {
            tmp = queue.pollFirst();
            if (tmp.left != null && tmp.right == null ||
                tmp.right != null && tmp.left == null) {
                ans++;
            }
            if (tmp.right != null) {
                queue.addLast(tmp.right);
            }
            if (tmp.left != null) {
                queue.addLast(tmp.left);
            }

        }
        queue = null; // help GC
        return ans;

    }

    public static boolean isSame(Node n1, Node n2) {
        if (n1 == null && n2 == null) {
            return true;
        }
        if (n1 == null && n2 !=null) {
            return false;
        }
        if (n1 != null && n2 == null) {
            return false;
        }
        return n1.value == n2.value && isSame(n1.left, n2.left) && isSame(n1.right, n2.right);
    }


    public static int[] findLevelWithMaxSum(Node root) {
        if (root == null) {
            return new int[]{0,0};
        }
        int curSum = 0;
        int maxSum = Integer.MIN_VALUE;
        int currLevel = 0;
        int maxLevel = 0;
        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(root);
        queue.addLast(null);
        Node tmp;
        while (!queue.isEmpty()) {
            tmp = queue.pollFirst();
            if (tmp == null) {
                if (curSum > maxSum) {
                    curSum = maxSum;
                    maxLevel = currLevel;
                }
                curSum = 0;
                currLevel++;
                if (!queue.isEmpty()) {
                    queue.addLast(null);
                }
            }  else {
                curSum += tmp.value;
                if (tmp.left != null) {
                    queue.addLast(tmp.left);
                }
                if (tmp.right != null) {
                    queue.addLast(tmp.right);
                }
            }
        }
        return new int[] {maxSum, maxLevel};

    }

    public static boolean hasPathSum(Node root, int sum) {
        if (root == null) {
            return sum == 0;
        }
        int sumSub = sum - root.value;
       return hasPathSum(root.left, sumSub) ||
                hasPathSum(root.right, sumSub);
    }

    public static int getAllSum(Node root) {
        if (root == null) {
            return 0;
        }
        int left = getAllSum(root.left);
        int right = getAllSum(root.right);
        return left + right + root.value;
    }

    public static int getAllSumNoRecursive(Node root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(root);
        Node tmp;
        while (!queue.isEmpty()) {
            tmp = queue.pollFirst();
            sum += tmp.value;
            if (tmp.left != null) {
                queue.addLast(tmp.left);
            }
            if (tmp.right != null) {
                queue.addLast(tmp.right);
            }

        }
        queue = null; //help GC
        return sum;
    }

    public static boolean isMirror(Node n1, Node n2) {
        if (n1 == null && n2 == null) {
            return true;
        }
        if (n1 == null || n2 == null) {
            return false;
        }
        return n1.value == n2.value && isMirror(n1.left, n2.right) && isMirror(n1.right, n2.left);
    }


    public static Node build(int[] inOrder, int[] preOrder, int inStart, int inEnd) {
        if (inStart > inEnd) {
            return null;
        }
        // 根节点 ,前序遍历中的第一个节点就是根节点
        int preIndex = 0;
        Node root = new Node(preOrder[preIndex]);
        preIndex++;
        if (inStart == inEnd) {
            return root;
        }

        int inIndex = inStart;
        for (; inIndex <= inEnd; inIndex++) {
            if (inOrder[inIndex] == preOrder[preIndex]) {
                break;
            }
        }
        // 左子树
        root.left = build(inOrder, preOrder, inStart, inIndex-1);
        // 右子树
        root.right = build(inOrder, preOrder, inIndex+1, inEnd);
        return root;

    }

    public static int printAllAncestors(Node root, Node node) {
        if (node == null) {
            return 0;
        }
        if (root.left!=null && root.left == node ||
            root.right!=null && root.right == node ||
            printAllAncestors(root.left, node) ==1 ||
            printAllAncestors(root.right, node) ==1) {
            System.out.println(root.value);
            return 1;
        }
        return 0;
    }

    // 23 两个节点的最近公共祖先节点
    public static Node LCA(Node root, Node a, Node b) {
        if (root == null || root == a || root == b) {
            return root;
        }

        Node left = LCA(root.left, a, b);
        Node right = LCA(root.right, a, b);

        // 公共祖先节点是a和b两个节点都要在我形成的树下面才可以
        if (left != null && right != null) {
            return root;
        }  else {
            return left!=null ? left : right;
        }



    }


    public static void printZigZag(Node root) {
        if (root == null) {
            return;
        }

        Stack<Node> currentStack = new Stack<>();
        Stack<Node> nextStack2 = new Stack<>();
        boolean leftToRight = true;
        currentStack.push(root);
        Node tmp;
        while (!currentStack.isEmpty()) {
            tmp = currentStack.pop();
            if (tmp != null) {
                System.out.println(tmp.value);
                if (leftToRight) {     //如果从左往右打印，则先加入左孩子，再加入右孩子
                    if (tmp.left!=null) {
                        nextStack2.push(tmp.left);
                    }
                    if (tmp.right != null) {
                        nextStack2.push(tmp.right);
                    }
                } else {      // 如果从右往左打印，则先加入右孩子，再加入左孩子
                    if (tmp.right != null) {
                        nextStack2.push(tmp.right);
                    }
                    if (tmp.left != null) {
                        nextStack2.push((tmp.left));
                    }
                }
                if (currentStack.isEmpty()) {
                    leftToRight = false;
                    Stack<Node> s1 = currentStack;
                    currentStack = nextStack2;
                    nextStack2 = s1;

                }

            }

        }

    }

    /*
     *  'ILILL'
     *      I
     *     / \
     *    L  I
     *      /\
     *     L  L
     */

    public static Node buildTreeFromString(char[] s, int i) {
        if (s == null) {
            return null;
        }
        Node root = new Node(s[i]);
        if (s[i] == 'L') {
            return root;
        }
        i = i+1;
        root.left = buildTreeFromString(s, i);
        i = i+1;
        root.right = buildTreeFromString(s, i);
        return root;
    }

    // 给定一颗带有3个指针（左指针，右指针，和下一个兄弟指针）的二叉树，假设下一个兄弟指针初始化为空，设计算法来填充下一个兄弟指针
    public static void FillNextSiblings(NodeWithSibling root) {
        if (root == null) {
            return;
        }
        LinkedList<NodeWithSibling> queue = new LinkedList<>();
        queue.addLast(root);
        NodeWithSibling tmp;
        while (!queue.isEmpty()) {
            tmp = queue.pollFirst();
            if (tmp == null) {
                if (!queue.isEmpty()) {
                    queue.addLast(null);
                }
            } else {
                tmp.sibling = queue.peekFirst();
                if (tmp.left != null) {
                    queue.addLast(tmp.left);
                }
                if (tmp.right != null) {
                    queue.addLast(tmp.right);
                }
            }
        }

    }

    public static class Info{
        int maxDistance;
        int height;
        public Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    // 求树的最大半径   任意两个节点的最大距离
    public static int getMaxDistance(Node root) {
        return process(root).maxDistance;
    }

    private static Info process(Node x) {
        if (x == null) {
            return new Info(0,0);
        }
        // 左树的信息，包含左树的高度和最大距离
        Info left = process(x.left);

        // 右树的信息，包含右树的高度和最大距离
        Info right = process(x.right);

        // 构建x节点的高度和最大距离
        // x节点高度是左树和右树的最大高度 + 1
        int height = Math.max(left.height, right.height) + 1;

        // x节点的最大距离是 通过x的最大距离是左树的高度+右树的高度+1
        //                不通过x的最大距离是左树的最大距离和右树的最大距离中的最大值
        int distance = Math.max(Math.max(left.maxDistance, right.maxDistance),
                        left.height + right.height +1);
        
        return new Info(distance, height);
    }


    // 查找二叉树直径的函数。请注意，
    // 函数返回以给定节点为根的子树的高度，
    // 并且直径在函数内被更新，因为它被传递
    // 使用 `AtomicInteger` 类进行引用。
    public static int getDiameter(Node root, AtomicInteger diameter)
    {
        // 基本情况：树为空
        if (root == null) {
            return 0;
        }

        // 获取左右子树的高度
        int left_height = getDiameter(root.left, diameter);
        int right_height = getDiameter(root.right, diameter);

        // 计算“通过”当前节点的直径
        int max_diameter = left_height + right_height + 1;

        // 更新最大直径（注意直径“不包括”当前
        // 以当前节点为根的子树中的节点已经更新
        // 因为我们正在进行后序遍历）
        diameter.set(Math.max(diameter.get(), max_diameter));

        // 返回根的子树的高度很重要
        // 当前节点
        return Math.max(left_height, right_height) + 1;
    }

    public static int getDiameter(Node root)
    {
        AtomicInteger diameter = new AtomicInteger(0);
        getDiameter(root, diameter);

        return diameter.get();
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
//        System.out.println(findMax(root));
//        System.out.println(findMaxNoRecursive(root));
//        System.out.println(getDepth(root));
//        System.out.println(getDepthNoRecursive(root));
//        System.out.println(getAllSum(root));
//        System.out.println(getAllSumNoRecursive(root));
//        System.out.println(LCA(root, n4, n3).value);
//        printZigZag(root);
//        char[] ch = {'I','L','I','L','L'};
//        buildTreeFromString(ch, 0);
        System.out.println(getDiameter(root));
        System.out.println(getMaxDistance(root));
    }


}
