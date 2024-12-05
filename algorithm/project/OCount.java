package com.chenjunyi.project;

import java.util.Scanner;

/**
 * 唤醒字符串中o的数量时偶数次的最长子串
 *  长子字符串是 "oxdolxl"，由于是首尾连接在一起的，所以最后一个 'x' 和开头的 'l' 是连接在一起的，此字符串包含 2 个'o' 。
 *
 *  计算字符o出现的次数，如果是偶数次直接返回字符串长度，否则返回长度-1， 因为字符串是环形，子串直接是最后一个o后面到o前面的字符组成的子串
 */
public class OCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
           String str =  scanner.nextLine();
           int i = c_count(str);
            System.out.println(i);
        }

    }


    public static int oCount(String str) {
        int count = 0;
        for (char ch : str.toCharArray()) {
            if (ch == 'o')
                count++;
        }
        if (count % 2 == 0)
            return str.length();
        else
            return str.length() - 1;
    }

    public static int c_count(String str) {
        // 先遍历一遍字符串，计算'o'的数量
        int oTotalCount = 0;
        for (char ch : str.toCharArray()) {
            if (ch == 'o')
                oTotalCount++;
        }

        if (oTotalCount % 2 == 0)
            return str.length();
        else
            return str.length() -1;
    }
}
