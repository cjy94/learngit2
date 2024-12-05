package com.chenjunyi.leetcode;

public class IntegerTest {
    public static void main(String[] args) {

        System.out.println(parseInt("23456", 10));
        System.out.println(parseInt("-23456", 10));


    

    }

    /**
     * 将一个字符串解析成数值，
     * 先不考虑
     * @param str
     * @return
     */
    private static int parseInt(String str, int radix) {
        // 先不考虑字符串是否合法问题，暂且认为传入的str是满足要求的
        int len = str.length();
        boolean negative = false;
        int result = 0;

        int i =0;
        if (str.charAt(i) == '-') {
            negative = true;
            i++;
        }

        // INT_MAX = 2147483647; INT_MIN = -2147483648
        int limit = Integer.MIN_VALUE / 10;
        int minr = Integer.MIN_VALUE % 10;
        for (; i < len; i++) {
            // 是数值
           // int ch = '0'- str.charAt(i);
            int digit = 0 - Character.digit(str.charAt(i), radix);
            if(digit <= 0) {
                // limit是负数，小于则越界，
                if (result < limit || (result == limit && digit < minr)) {
                    throw new RuntimeException("not parseInt");
                }
                result = result * 10 + digit;

            }
        }
        if(negative == false && result == Integer.MIN_VALUE) {
            throw new RuntimeException("not parseInt");
        }
        result = negative ? result : -result;
        return  result;

    }

    public static int bitCount(int value) {
        /**
         * 0x55555555  ‭0b01010101010101010101010101010101‬       每两位计算下1的个数
         * 0x33333333  ‭0b00110011001100110011001100110011‬       再每两位计算下1的格式
         * 0x0f0f0f0f  ‭0b00001111000011110000111100001111‬
         * 0x00ff00ff  0b00000000111111110000000011111111
         * 0x0000ffff  ‭0b00000000000000001111111111111111‬
         * 0x3f        ‭0b00111111‬
         */
        // value 保存着每两位统计的1的个数的和
        value = (value & 0x55555555) + ((value >>> 1) & 0x55555555);  // 2
        value = (value & 0x33333333) + ((value >>> 2) & 0x33333333);  // 4
        value = (value & 0x0f0f0f0f) + ((value >>> 4) & 0x0f0f0f0f);  // 8
        value = (value & 0x00ff00ff) + ((value >>> 8) & 0x00ff00ff);  // 16
        value = (value & 0x0000ffff) + ((value >>> 16) & 0x0000ffff);
        return value;
        
    }
}
