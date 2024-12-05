package com.chenjunyi.GO.FromLeftToRight;

import java.util.ArrayList;

/**
 *  打印字符串的全排列
 *  abc: abc acb bac bca cab cba
 *
 *  进阶：打印字符串的全排列，要求不要出现重复的排列
 */
public class PrintAllPermutation {
    public static void main(String[] args) {
        String s = "aaa";
        printPermutation(s);

    }

    public static void printPermutation(String s) {
        if (s == null || s.length() == 0)
            return;
        ArrayList<String> res = new ArrayList<>();
        process1(s.toCharArray(), 0, res);
        for (String str : res) {
            System.out.println(str);
        }
    }

    private static void process(char[] ch, int index, ArrayList<String> res) {
        if (index == ch.length) {
            res.add(String.valueOf(ch));
            return;
        }

        for (int j = index; j < ch.length; j++) {   // index往后的所有字符都有机会来到index位置，
            swap(ch, j, index);
            process(ch, index+1, res);
            swap(ch, j, index);      // 恢复现场
        }
    }

    // 进阶： 不允许重复的全排列
    // 采用分支限界，来避免重复的全排列
    private static void process1(char[] ch, int index, ArrayList<String> res) {
        if (index == ch.length) {
            res.add(String.valueOf(ch));
            return;
        }
        boolean[] visit = new boolean[26];
        for (int j = index; j < ch.length; j++) {
            if (!visit[ch[j]-'a']) {
                visit[ch[j]-'a'] = true;
                swap(ch, j, index);
                process1(ch, index+1, res);
                swap(ch, j, index);      // 恢复现场
            }
        }
    }

    private static void swap(char[] ch, int i, int j) {
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
    }
}
