package com.chenjunyi.day09_TreeDP;

import java.awt.event.MouseAdapter;
import java.util.List;

/**
 * 树形DP问题
 * 1、二叉树节点间的最大距离
 * 
 */
public class Code01_TreeDP {
    private static class TreeNode{
        int value;
        TreeNode left;
        TreeNode right;
        TreeNode parent;
        TreeNode(int value) {
            this.value = value;
        }
    }
    private static class Info {
        int maxDistance;
        int height;
        Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    private static int getMaxDistance(TreeNode x) {
        return process(x).maxDistance;
        
    }
    private static Info process(TreeNode x) {
        if (x==null) {
            return new Info(0,0);
        }
        // 向左树要信息
        Info leftInfo =process(x.left);
        // 向右树要信息
        Info rightInfo = process(x.right);

        //封装自己的信息返回
        // x 节点不参与情况下的最大距离，左树的树高和右树的树高 最大值

        int leftDistance = leftInfo.maxDistance;
        int rightDiatance = rightInfo.maxDistance;
        // x节点参与下的最大距离，左树的树高，右树的树高最大距离
        int height = leftInfo.height + rightInfo.height + 1;

        int maxDistance = Math.max(height, Math.max(leftDistance, rightDiatance));
        int maxHeight = Math.max(leftInfo.height, rightInfo.height)+1;
        return new Info(maxDistance, maxHeight);

    }


    private class Employee {
        int happy;
        List<Employee> subordinates;  // 直接下级
    }

    private static class Info1{
        int laiMaxHappy;
        int buMaxHappy;
        Info1(int laiMaxHappy, int buMaxHappy) {
            this.buMaxHappy = buMaxHappy;
            this.laiMaxHappy = laiMaxHappy;
        }


    }

    private static int maxHappy(Employee e) {
        Info1 info = process1(e);
        return Math.max(info.buMaxHappy, info.laiMaxHappy);
    }

    private static Info1 process1(Employee x) {
        // base case 基层员工的最大快乐值，来就是happy的值， 不来就是0
        if (x.subordinates.isEmpty() == true) {
            return new Info1(x.happy, 0);
        }

        // 封装自己的信息
        int lai = x.happy;
        int bu = 0;

        // 向他的直接下级要信息
        for (Employee next : x.subordinates) {
            Info1 nextInfo = process1(next);

            // 如果x参与，那么他的下级一定不能来，所以加上下级不来情况下的最大快乐值，进行累加
            lai += nextInfo.buMaxHappy;

            // 如果x不参与，那么下级可能来，可能不来，获取再来或者不来情况下的最大快乐值，进行累加
            bu += Math.max(nextInfo.buMaxHappy, nextInfo.laiMaxHappy);
        }
        return new Info1(lai, bu);
    }
    
}
