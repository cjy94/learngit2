package com.chenjunyi.GO.FromLeftToRight;

/**
 *  打印字符串的全部子序列
 *
 *  进阶： 打印字符串的全部子序列，要求不出现重复字面值的子序列  可以使用set集合进行去重
 */
public class PrintAllSubSequence {
    public static void main(String[] args) {
        String s = "abc";
        printSubSequence(s);
    }
    // 打印一个字符串的全部子序列
    // 子序列的个数是len=s.length()    2^len
    public static void printSubSequence(String s) {
        if (s == null || s.length() == 0)
            return;
        StringBuilder path = new StringBuilder();
        process1(s.toCharArray(), 0, path);
    }

    private static void process1(char[] ch, int index, StringBuilder path) {
        if (index == ch.length) {
            System.out.println(path.toString());
            return;
        }
        // 不要ch[index]的字符
        process1(ch, index+1, path);
        // 要ch[index]的字符
        path.append(ch[index]);
        process1(ch, index+1, path);
        path.deleteCharAt(path.length()-1);       // 恢复现场
    }
}
