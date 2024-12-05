package com.chenjunyi.force;

import java.util.Arrays;
import java.util.HashSet;

public class StringProblem {

    ///===============================
    // 打印一个字符串的全部子序列
    // 子序列不要求连续
    // abc ==> a、b、c、ab、ac、bc、abc
    public static void allSequence(String str) {
        if (str == null || str.length() == 0) {
            return;
        }

        String sub = "";
        process1(str, sub, 0);

    }

    private static void process1(String str, String sub, int index) {
        if (index == str.length()) {
            System.out.println(sub);
            return;
        }
        process1(str, sub, index+1);
        sub = sub+str.charAt(index);
        process1(str, sub, index+1);

    }
    //========================打印无重复的子序列
    // abbc ==> a、b、c、ab、ac、bc、abc
    public static void allSequenceNoRepeat(String str) {
        if (str == null || str.length() == 0) {
            return;
        }

        String sub = "";
        HashSet<String> set = new HashSet<>();
        processNoRepeat(str, set, sub,0);

        System.out.println(set);

    }

    private static void processNoRepeat(String str, HashSet<String> set,String sub, int i) {
        if (i == str.length()) {
            set.add(sub);
            return;
        }


        processNoRepeat(str, set, sub, i+1);
        sub = sub+str.charAt(i);
        processNoRepeat(str, set, sub, i+1);
    }


    //================================
    // 打印字符串的全部字串
    public static void allSubString(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        for (int i =0; i < str.length(); i++) {
            for (int j =i+1; j<= str.length(); j++) {
                System.out.println(str.substring(i,j));

            }
        }

    }

    //=====================打印字符串的全部排列
    // abc ==> abc acb bac bca cab cba
    public static void allPermutation(String str) {
        if (str == null || str.length() == 0) {
            return;
        }
        char[] ch = str.toCharArray();
        process2(ch, 0);

    }

    private static void process2(char[] str, int index) {
        if (index == str.length) {
            System.out.println(Arrays.toString(str));
            return;
        }

        for (int i =index; i < str.length; i++) {
            // 字符交换
            swap(str, index, i);
            process2(str, index + 1);
            swap(str, index, i); // 恢复现场

        }
    }

    private static void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;

    }


    public static void main(String[] args) {
        allPermutation("abc");
    }
}
