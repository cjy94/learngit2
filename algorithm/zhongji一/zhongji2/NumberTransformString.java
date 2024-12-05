package com.chenjunyi.zhongji一.zhongji2;

/*
将数字转换成字符串

1对应a, 2对应b，... 26对应z， 例如12258 转成"abbeh" "aveh" "abyh"
 */
public class NumberTransformString {

    // 从左往右的尝试模型
    public static int transform(String str) {
        char[] s = str.toCharArray();
        return process(s, 0);
    }

    private static int process(char[] s, int i) {
        // 终止位置了，
        if (i == s.length) {
            return 1;
        }

        if (s[i] == '0') {
            return 0;
        }

        if (s[i] == '1') {
            int p1 = process(s, i+1);
            if (i+1 < s.length ) {
                p1 += process(s, i+2);
            }
            return p1;
        }

        if (s[i] == '2') {
            int p1 = process(s, i+1);
            if (i+1 < s.length && (s[i+1] - '0' >= 0 && s[i+1] - '0' <= 6) ) {
                p1 += process(s, i+2);
            }
            return p1;
        }

        // 其他数字的情况，只有一种
        return process(s, i+1);

    }

    public static void main(String[] args) {
        System.out.println(transform("12258"));
    }
}
