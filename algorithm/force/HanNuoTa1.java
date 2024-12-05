package com.chenjunyi.force;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 暴力递归： 就是尝试
 * 1、把问题转化为规模缩小的同类问题的子问题
 * 2、有明确base case
 * 3、不记录每个子问题的解
 *
 * 熟悉什么叫尝试
 * 1、打印n层汉诺塔从最左边移动到最右边   （步数是(2^n)-1）
 *
 *
 * 2、打印一个字符串的全部子序列
 * 3、打印一个字符串的全部子序列，要求不要出现重复字面值的子序列
 * 4、打印一个字符串的全部排列
 * 5、打印一个字符串的全部排列，要求不要出现重复的排列
 *
 *
 *
 * 给你一个栈， 请你逆序这个栈 , 不能申请额外的数据结构
 */
public class HanNuoTa1 {
    // ======================================汉诺塔问题=====================
    public static void hanoi1(int num) {
        leftToRight(num);

    }

    public static void hanoi2(int num) {
        if (num > 1) {
            f(num, "left", "mid", "right");
        }
    }

    private static void f(int num, String from, String other, String to) {
        if (num == 1) {
            System.out.println("move 1 from " + from + " to " + to);
            return;
        }
        f(num-1, from, to, other);
        System.out.println("move " + num + " from " + from + " to " + to);
        f(num-1, other, from, to);
    }

    /**
     * 要求将n个圆盘从左边全部移动到右边
     * 1、将1-(n-1)层圆盘 从左边移动到中间
     * 2、 将第n层圆盘从左边移动到右边
     * 3、 将1-（n-1）层圆盘从中间移动到右边
     */
    private static void leftToRight(int num) {
        if (num == 1) {
            System.out.println("move 1 from left to right");
            return;
        }

        leftToMid(num-1);
        System.out.println("move " + num + " from left to right");
        midToRight(num-1);
    }

    /**
     * 要求将n个圆盘从中间全部移动到右边
     * 1、将1-(n-1)层圆盘 从中间移动到左边
     * 2、 将第n层圆盘从中间移动到右边
     * 3、 将1-（n-1）层圆盘从左边间移动到右边
     */
    private static void midToRight(int num) {
        if (num == 1) {
            System.out.println("move 1 from mid to right");
            return;
        }
        midToLeft(num-1);
        System.out.println("move " + num + " from mid to right");
        leftToRight(num-1);
    }

    /**
     * 要求将n个圆盘从中间全部移动到左边
     * 1、将1-(n-1)层圆盘 从中间移动到右边
     * 2、 将第n层圆盘从中间移动到左边
     * 3、 将1-（n-1）层圆盘从右边间移动到左边
     */
    private static void midToLeft(int num) {
        if (num == 1) {
            System.out.println("move 1 from mid to left");
            return;
        }
        midToRight(num-1);
        System.out.println("move " + num + " from mid to left");
        rightToLeft(num-1);
    }

    /**
     * 要求将n个圆盘从右边全部移动到左边
     * 1、将1-(n-1)层圆盘 从右边移动到中间
     * 2、 将第n层圆盘从右边移动到左边
     * 3、 将1-（n-1）层圆盘从中间间移动到左边
     */
    private static void rightToLeft(int num) {
        if (num == 1) {
            System.out.println("move 1 from right to left");
            return;
        }
        rightToMid(num-1);
        System.out.println("move " + num + " from right to left");
        midToLeft(num-1);
    }

    // 请把1-n层圆盘，从右边移动到中间
    //  1-(n-1)层圆盘从右边移动到左边，第n层圆盘从右边移动到中间
    // 再把1-(n-1)从左边移动到中间
    private static void rightToMid(int n) {
        if (n == 1) {
            System.out.println("move 1 from right to mid");
            return;
        }
        rightToMid(n-1);
        System.out.println("move " + n + " from right to mid");
        leftToMid(n-1);
    }

    /**
     * 要求将n个圆盘从左边全部移动到中间
     * 1、将1-(n-1)层圆盘 从左边移动到右边
     * 2、 将第n层圆盘从左边移动到中间
     * 3、 将1-（n-1）层圆盘从右边移动到中间
     */
    private static void leftToMid(int num) {
        if (num == 1) {
            System.out.println("move 1 from left to mid");
            return;
        }
        leftToRight(num-1);
        System.out.println("move " + num + " from left to mid");
        rightToMid(num-1);
    }


    //========================打印一个字符串的全部子序列=============
    public static void printAllSubsequence(String str) {
        if (str==null || str.length()==0) {
            return;
        }
        String sub = "";
        process1(str, sub, 0);
    }

    private static void process1(String str, String sub, int i) {
        if (i == str.length()) {
            System.out.println(sub);
            return;
        }
        // 选择当前字符组成子序列的情况
        process1(str, sub+str.charAt(i), i+1);
        // 不选择当前字符组成子序列的情况
        process1(str, sub, i+1);
    }

    // 不出现重复字面值的子序列
    public static List<String> printAllSubsequenceNoRepeat(String str) {
        if (str==null || str.length()==0) {
            return new ArrayList<String>();
        }
        Set<String> set = new HashSet<String>();
        String ans = "";
        process2(str, set, ans, 0);
        ArrayList<String> list = new ArrayList<>();
        list.addAll(set);
        return list;

    }

    private static void process2(String str, Set<String> set, String ans, int i) {
        if (i == str.length()) {
            set.add(ans);
            return;
        }
        // 选择当前字符组成子序列的情况
        process2(str, set, ans+str.charAt(i), i+1);
        // 不选择当前字符组成子序列的情况
        process2(str, set, ans, i+1);
    }

    //=====================================打印一个字符串的全排列=======================
    public static void printPermutation(String str) {
        if (str==null || str.length() == 0) {
            return;
        }

        char[] chars = str.toCharArray();
        ArrayList<Character> list = new ArrayList<>();
        processPermutation(chars,  0);
    }

    private static void processPermutation(char[] chars,  int i) {
        if (i == chars.length) {
            System.out.println(Arrays.toString(chars));
            return;
        }

        for (int j =i; j < chars.length; j++) {
            swap(chars, i, j);
            processPermutation(chars, i+1);
            swap(chars, j, i);

        }
    }

    private static void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    public static void main(String[] args) {
        printAllSubsequence("aabc");
        System.out.println("===================");
        System.out.println(printAllSubsequenceNoRepeat("abc"));
        printPermutation("abc");
    }






}
