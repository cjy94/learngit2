package com.chenjunyi.gaoji一.gaoji9;

/**
 * 给定两个字符串，检查两个字符串是否互为旋变串
 *
 */
public class RotateString {


    public static boolean isRotate(String s1, String s2) {
        if ((s1 == null && s2 != null) || (s1 != null && s2 == null)) {
            return false;
        }
        if (s1 == null && s2 == null) {
            return true;
        }
        if (s1.equals(s2)) {
            return true;
        }

        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        if (!isSameTypeAndSameNumber(str1, str2)) {
            return false;
        }
        int len = s1.length();
        return process(str1, str2, 0,0,len);

    }

    private static boolean isSameTypeAndSameNumber(char[] str1, char[] str2) {
        return true;
    }

    /*
      尝试 s1[l1...] size个字符 和s2[l2...]size个字符，是否互为旋变串
     */

    private static boolean process(char[] s1, char[] s2, int l1, int l2, int size) {
        if (size == 1) {
            return s1[l1] == s2[l2];
        }

        for (int leftPart = 1; leftPart < size; leftPart++) {
            // 有两种情况
            if (
                    // process s1 s2 为固定变量，
                    // process l1 l2 为s1和s2字符串的开始位置
                    /// process leftPart为长度

                    // s1: 左1  右1
                    // s2: 左2  右2
                    // (左1和左2)  && (右1和右2) 互为旋变串
                    (process(s1, s2, l1, l2, leftPart)   // a1和b1互为旋变串
                            &&
                    process(s1, s2, l1+leftPart, l2+leftPart, size-leftPart) )    // a2a3a4a5和b2b3b4b5互为旋变串

                   ||

                   // s1: 左1  右1
                   // s2: 左2  右2
                   // (左1和右2)  && (右1和左2) 互为旋变串
                    (process(s1, s2, l1, l2+size-leftPart, leftPart)        // a1和b5互为旋变串
                    &&
                    process(s1, s2, l1+leftPart, l2,size-leftPart))     // a2a3a4a5 和 b1b2b3b4互为旋变串
            ) {
                return true;
            }
        }
        return false;
    }

}
