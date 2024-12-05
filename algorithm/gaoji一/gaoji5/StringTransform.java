package com.chenjunyi.gaoji一.gaoji5;



/**
 * ABCD
 * 字符串对应
 * A B C D AA AB AC AD BB BC BD CC CD DD AAA .. DDD
 */
public class StringTransform {
    // 给定一个数字，转换成字符串
    // 按照chs中的转换规则
    public static String getString(char[] chs, int n) {
        int cur = 1;
        int base = chs.length; // K
        int len = 0;
        // 计算最终字符串的长度
        // ...26^4  26^3  26^2  26^1  26^0
        // 该方法主要计算出n 需要26^n   n是多长
        while (n >= cur) {        // 2321
            //  26^2 26^1 26^0
            // cur=1, 26, 26*26(676), 26*26*26(17576)
            // len =1 n = 2321-1(2320)    cur=26
            // len =2 n = 2320-26(2294)   cur=26*26(676)
            // len =3 n = 2294-676(1618)  cur=26*26*26(17576)   (n < cur 退出while)
            len++;
            n -= cur;
            cur *= base;
        }
        char[] res = new char[len];
        int index = 0;
        int nCur = 0;
        do {
            // cur = 26*26*26/26 = 676   nCur=2321/676=3  res[0]= C  n=293
            // cur = 676/26 = 26         nCur=293/26=11   res[1]= K  n= 7
            // cur = 26/16 = 1           nCur=7/1=7       res[2]= G  n=0
            cur /= base;       // 从高位开始计算
            nCur = n / cur;
            res[index++] = getKthCharChs(chs, nCur);
            n %= cur;
        } while (index != res.length);
        return String.valueOf(res);
    }

    private static char getKthCharChs(char[] chs, int k) {
        if (k < 0 || k > chs.length) {
            return 0;
        }
        return chs[k];
    }

    // CGH
    public static int getNum(char[] chs, String s) {
        int res = 0;
        int base = chs.length; 
        int cur = 1;
        for (int i =s.length()-1; i>=0; i--) {
            res += getCharNum(chs, s.charAt(i)) * cur;
            cur *= base;

        }
        return res;
    }

    private static int getCharNum(char[] chs, char s) {
        for (int i =0; i < chs.length; i++) {
            if (s == chs[i]) {
                return i+1;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        char[] chs = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N',
                        'O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        String str = getString(chs, 2321);
        System.out.println(getNum(chs, str));
        System.out.println(str);


    }
}
