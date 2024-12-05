package com.chenjunyi.force;

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
public class HanNuoTa {
    public static void main(String[] args) {
        System.out.println(printNoRepeatSubsequence("ccc"));
        // hanoi2(3);
//        Stack<Integer> stack = new Stack<>();
//        stack.push(1);
//        stack.push(2);
//        stack.push(3);
////        while (!stack.isEmpty()) {
////            System.out.println(stack.pop());
////        }
//        reverse(stack);
//        System.out.println("=======================");
//        while (!stack.isEmpty()) {
//            System.out.println(stack.pop());
//        }
//        printAllSubsequence("abcd");
//        System.out.println("==============================");
//        System.out.println(printAllSubsequence1("abcd"));

        permutation("aaa");

    }


    

    // 获取并移除栈底元素
    public static int getAndRemoveStackLastNum(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = getAndRemoveStackLastNum(stack);
            stack.push(result);
            return last;
        }
    }

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int result = getAndRemoveStackLastNum(stack);
        reverse(stack);
        stack.push(result);

        
    }


    /**
     * 子串：必须连续
     * abc == > a ab abc b bc c
     * 可以不适用递归，使用两个for循环就可以解决
     * 子序列： 不要求连续
     * abc == > a ab ac abc b bc c ""
     * @param str
     */
    public static void printAllSubstring(String str) {
        int len = str.length();
        for (int i =0; i < len; i++) {
            for (int j =i+1;j<=len; j++) {
                System.out.println(str.substring(i, j));
            }
        }
    }

    //====================================================== 打印全部子序列 ===========================================

    // 打印全部子序列
    public static void printAllSubsequence(String str) {
        if (str != null && str.length() > 0) {
            String sub = "";
            process(str, sub, 0);
        }
    }

    private static void process(String str,String sub, int i) {
        if (i == str.length()) {
            System.out.println(sub);
            return;
        }
        process(str, sub+str.charAt(i), i+1);
        process(str, sub, i+1);
    }

    public static List<String> printAllSubsequence1(String str) {
        char[] ch = str.toCharArray();
        String path = "";
        List<String> ans = new ArrayList<String> ();
        process1(ch, 0, ans, path);
        return ans;

    }


    private static void process1(char[] str,int index, List<String> ans, String path) {
        if (index == str.length) {
           ans.add(path);
           return;
        }
        String no = path;
        process1(str, index+1, ans, no);
        String yes = path + str[index];
        process1(str, index+1, ans, yes);


    }

    // ===========================================打印全部无重复字面值的子序列 ==================================

    // 将list 集合用set代替，因为set种不会右重复值
    public static Set<String> printNoRepeatSubsequence(String str) {
        char[] ch = str.toCharArray();
        String path = "";
        Set<String> ans = new HashSet<String>();
        noRepeatProcess(ch, 0, ans, path);
        return ans;

    }


    private static void noRepeatProcess(char[] str,int index, Set<String> ans, String path) {
        if (index == str.length) {
            ans.add(path);
            return;
        }
        String no = path;
        noRepeatProcess(str, index+1, ans, no);
        String yes = path + str[index];
        noRepeatProcess(str, index+1, ans, yes);


    }

    // ====================================打印一个字符串的全部排列 ==========================================
    // abc == > abc, acb, bac, bca, cba, cab
     public static void permutation(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        String sub = "";
        char[] ch = str.toCharArray();
        process5(ch,  0);

     }

    private static void process4(char[] str,int i) {
        if (i == str.length) {
            System.out.println(Arrays.toString(str));
            return;
        }

        for (int j =i; j< str.length; j++) {
            swap(str, i, j);
            process4(str, i+1);
            swap(str, i, j);   // 恢复现场(!!!)

        }
    }

    private static void process5(char[] str,int i) {
        if (i == str.length) {
            System.out.println(Arrays.toString(str));
            return;
        }

        // 分支限界 保证出现重复字符时，不再进行
        HashSet<Character> set = new HashSet<>();
        for (int j =i; j< str.length; j++) {
            if (!set.contains(str[j])) {
                set.add(str[j]);
                swap(str, i, j);
                process5(str, i + 1);
                swap(str, i, j);   // 恢复现场(!!!)
            }

        }
    }

    private static void swap(char[] str, int i, int j) {
        char tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }


    // ===================================== 汉诺塔问题 ======================================================

    /**
     * from to other
     * 1、 1-(n-1)圆盘从from 移动到 other上
     * 2、 第n个圆盘从from 移动到 to上
     * 3、 1-(n-1) 圆盘从other 移动到 to上
     * @param n
     */
    public static void hanoi2(int n) {
        if (n > 1) {
            func(n, "left", "mid", "right");
        }
        
        

    }

    private static void func(int n, String from, String other, String to) {
        if (n == 1) {
            System.out.println("move 1 from " + from + " to " + to);
            return;
        }

        func(n-1, from, to, other);
        System.out.println("move " + n + " from " + from + " to " + to);
        func(n-1, other, from, to);
    }


    public static void hanoi1(int n) {
        leftToRight(n);
    }

    // 请把1-n层圆盘，从左移动到右去
    //  1-(n-1)层圆盘从左边移动到中间，第n层圆盘从左边移动到右边去
    // 再把1-(n-1)从中间移动到右边
    private static void leftToRight(int n) {
        if (n == 1) {
            System.out.println("move 1 from left to right");
            return;
        }

        leftToMid(n-1);
        System.out.println("move " + n + " from left to right");
        midToRight(n-1);
    }

    // 请把1-n层圆盘，从中间动到右边去
    //  1-(n-1)层圆盘从中间移动到左边去，第n层圆盘从中间移动到右边去
    // 再把1-(n-1)从左边移动到右边
    private static void midToRight(int n) {
        if (n == 1) {
            System.out.println("move 1 from mid to right");
            return;

        }
        midToLeft(n-1);
        System.out.println("move " + n + " from mid to right");
        leftToRight(n-1);

    }

    // 请把1-n层圆盘，从中间移动到右边去
    //  1-(n-1)层圆盘从左边移动到右边去，第n层圆盘从中间移动到左边
    // 再把1-(n-1)从右边移动到左边
    private static void midToLeft(int n) {
        if (n == 1) {
            System.out.println("move 1 from mid to left");
            return;

        }
        midToRight(n-1);
        System.out.println("move " + n + " from mid to left");
        rightToLeft(n-1);

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

    // 请把1-n层圆盘，从左边移动到中间去
    //  1-(n-1)层圆盘从左边移动到右边去，第n层圆盘从左边移动到中间
    // 再把1-(n-1)从右边移动到中间
    private static void leftToMid(int n) {
        if (n == 1) {
            System.out.println("move 1 from left to mid");
            return;

        }
        leftToRight(n-1);
        System.out.println("move " + n + " from left to mid");
        rightToMid(n-1);
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


}
