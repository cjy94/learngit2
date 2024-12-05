package com.chenjunyi.practice;

/**
 * 一个子序列的消除规则如下：
 * 1) 在某一个子序列中，如果'1'的左边右'0'， 那么这两个字符'01'可以消除
 * 2) 在某一个子序列中，如果'3'的左边右'2'， 那么这两个字符'23'可以消除
 * 3) 当这个子序列的某个部分消除后，认为其他字符会自动贴在一起，可以继续寻找消除的机会
 * 比如：某个子序列"0231", 先消除"23", 剩下的字符贴在一些"01"， 继续消除就没有字符了
 * 如果某个子序列通过最优良的方式，可以都消除掉，那么这样的子序列叫做"全消子序列"
 * 返回全消子序列的最大长度
 * 字符串长度<=200
 */
public class Disappear {

    public static int disappear(String str) {
        char[] arr = str.toCharArray();
        return process(arr, 0, arr.length-1);

    }

    // 动态规划的方案
    // 在arr[l...r] 范围内，查找能消掉的最大范围
    public static int process(char[] arr, int l, int r) {
        if (l >= r) {
            return 0;
        }
        if (l == r-1) {
            return arr[l] == '0' && arr[r] == '1' || arr[l] == '2' || arr[r] == '3' ? 2 : 0;
        }
        // l...r 有若干个字符
        // 如果arr[l]=='1' 或者 arr[l] == '3', 那么不能使用l位置的字符
        int p1 = process(arr, l+1, r);
        if (arr[l] == '1' || arr[l] == '3') {
            return p1;
        }

        int p2 = Integer.MIN_VALUE;
        char find = arr[l] == '0' ? '1' : '3';
        for (int i = l+1; i <= r; i++) {
            if (arr[i] == find) {
                p2 = Math.max(p2, process(arr, l+1, i-1) + 2  + process(arr,i+1, r));
            }
        }
        return Math.max(p1, p2);
    }
}
