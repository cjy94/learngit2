package com.chenjunyi.force;

public class IntegerTest {

    public static int process(String s) {
        char[] str = s.toCharArray();
        boolean negative = str[0] == '-' ? true : false;
        boolean positive = str[0] == '+' ? true : false;
        int minr = Integer.MIN_VALUE % 10;
        int minq = Integer.MIN_VALUE / 10;
        int res = 0;
        int index = (negative == true || positive == true) ? 1 : 0;
        for (;index < str.length; index++  ) {
            int cur = '0' - str[index];
            // 需越界的情况
            if (res < minq || (res == minq && cur < minr)) {
                throw new RuntimeException("IndexOutbound!!!!");
            }
            res = res * 10 + cur;

        }
        if (negative == false && res == Integer.MIN_VALUE) {
            throw new RuntimeException("IndexOutbound!!!!");
        }
        return negative ? res : -res;

    }

    public static void main(String[] args) {
        String fn = "_23sr_Lucene50_0.doc";
        fn = "_23sr.fnm";
        //fn = "_4_Lucene50_0.doc";
        String sn = "";
        // segmentName 的第一个位置是'_', 所以下标从1位置开始，知道查找到下一个'_'/'.'则停止
        int index = 1;
        for (; index < fn.length(); index++) {
            char s = fn.charAt(index);
            if (s != '_' && s != '.') {
                sn += s;

            } else {
                break;
            }
        }
        System.out.println(sn);
        System.out.println(Long.parseLong(sn, Character.MAX_RADIX));

    }
}
