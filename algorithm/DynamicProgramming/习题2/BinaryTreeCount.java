package com.chenjunyi.DynamicProgramming.习题2;

/**
  节点数为n高度不大于m的二叉树个数
  现在有n个节点，计算出有多少个不同结构的二叉树， 满足节点个数为n且树的高度不超过m的方案
  比如： n =3, m=3  节点数不超过3并且高度也不超过3的二叉树有多少个

    一共有5中二叉树结构
      A      A         A       A         A
     /       \        /         \       / \
    A         A      A           A     A   A
   /           \     \           /
  A             A     A         A


 */

public class BinaryTreeCount {

    // 二叉树的节点数是n， 高度不超过m
    // 结构数返回
    public int compute(int n, int m) {
        // 空树
        if (n == 0)
            return 1;
        // 节点数大于0， 但是高度不能超过0, 没有这样的结构
        if (m == 0)
            return 0;

        // 枚举方式：
        // 头节点占1， 左树节点个数0， 右树节点个数 n-1
        //            左树节点个数1， 右树节点个数 n-1-1
        //            左树节点个数2， 右树节点个数 n-1-2
        long ans = 0;
        for (int k = 0; k < n; k++) {  // 枚举所有的节点个数情况
            int left = compute(k, m-1);
            int right = compute(n-1-k, m-1);
            ans += (long) (left * right);
        }
        return (int)ans;

    }
}
