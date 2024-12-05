package com.chenjunyi.leetcode;

public class MyAtoi {
    /**
     * 类似c++的atio操作，将字符串转换成数值
     * 如果是严格的数字，则直接转成数值 "123" --> 123    "-123"->-123
     * 如果出现空格，则只忽略前面的所有空格，即没有碰到字符前的所有空格需要忽略
     * 只要出现了非空格的字符，如果是非数字和‘+/-’的直接退出
     * 如果前面的字符排除空格后，出现了‘+/-’则代表数值正负， +/- 只允许出现一次
     * @param s
     * @return
     */
    public int myAtoi(String s) {

        double sum = 0;
        s = s.stripLeading();
        int index = 0;
        boolean positive = s.charAt(index) == '+';
        boolean negative = s.charAt(index) == '-';
       if (negative == true || positive== true) index++;
       while (index < s.length() && Character.isDigit(s.charAt(index))) {
           sum = sum + s.charAt(index)-'0';
           sum *= 10;
           index++;
       }
        sum = negative == true ? -sum/10 : sum/10;
        if (sum > Integer.MAX_VALUE ) {
            return Integer.MAX_VALUE;
        }
        if (sum < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int)sum;

    }

    public static void main(String[] args) {
        MyAtoi test = new MyAtoi();

        System.out.println("   ji ji ji  ".stripLeading());
        System.out.println(test.myAtoi("3.14159"));
        System.out.println(test.myAtoi("+-12"));
        System.out.println(test.myAtoi("  +12"));
        System.out.println(test.myAtoi("  -12"));
        System.out.println(test.myAtoi("42"));
        System.out.println(test.myAtoi("00000-42a1234"));
        System.out.println(test.myAtoi("4193 with words"));
        System.out.println(test.myAtoi("   +0 123"));
        System.out.println(test.myAtoi("20000000000000000000"));
        System.out.println(test.myAtoi("-91283472332"));


    }
}
