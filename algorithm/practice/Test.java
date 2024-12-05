package com.chenjunyi.practice;

import java.util.HashMap;

public class Test {


    public static void main(String[] args) {
        System.out.println(Long.MAX_VALUE);
        int a = 1000000011 +    2000000000;
        System.out.println(a);
        long l = 2000000000L + 1000000011L;
        System.out.println(l);
    }


    // 最长无重复子串问题
    // 子串： 以某个位置结尾情况下
    // 前一个位置的信息和该字符上一次出现的位置取最大值
    public static int maxNoDuplicationSubString(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }

        char[] s = str.toCharArray();
        return process1(s);
    }

    public static int process1(char[] str) {
        // 每个字符上次出现的位置
        HashMap<Character, Integer> map = new HashMap<>();
        int n = str.length;
        int pre = 1;    // 上一个位置
        int ans = 1;
        map.put(str[0], 0);
        for (int i =1; i < n; i++) {
            int p1 = i - (map.containsKey(str[i]) ? map.get(str[i]) : -1);
            int p2 = pre + 1;
            int cur = Math.min(p1, p2);
            ans = Math.max(cur, ans);
            pre = cur;
            map.put(str[i], i);
        }
        return ans;
    }
    

    
    
    static class Info{
        int t;
        int f;
        public Info(int t, int f) {
            this.t = t;
            this.f = f;
        }
    }

    public static int process(String expr, int desired) {
        if (expr.length() / 2 == 0) {
            return 0;
        }
        char[] arr = expr.toCharArray();
        Info[][] dp = new Info[arr.length][arr.length];
        Info result = f(arr, 0, arr.length, dp);
        return desired == 1 ? result.t :result.f;

    }

    // arr[l...r] 表达式为true的方法数和false的方法数
    public static Info f(char[] arr, int l, int r, Info[][] dp) {
        if (dp[l][r] != null)
            return dp[l][r];
        int t = 0;
        int f = 0;

        if (l == r) {
           t = arr[l] == '1' ? 1 : 0;
           f = arr[l] == '0' ? 1 : 0;
        } else {
            // i一定在逻辑运算符位置上
            for (int i = l + 1; l < r; i += 2) {
                Info left = f(arr, l, i-1, dp);
                Info right = f(arr, i+1, r, dp);
                int lt = left.t;
                int lf = left.f;
                int rt = right.t;
                int rf = right.f;
                switch (arr[i]) {
                    case '&':
                        t += lt * rt;
                        f += lf * rt + lf * rf + lt * rf;
                        break;
                    case '^':
                        t += lt * rf + lf * rt;
                        f += lt * rt + lf * rf;
                        break;
                    case '|':
                        t += lt * rt + lt * rf + lf * rt;
                        f += lf * rf;
                        break;
                }
            }
        }
        dp[l][r] = new Info(t,f);
        return dp[l][r];
    }

    public static int dump(int[] arr) {
        int curMax = 0;
        int nextMax = 0;
        int step = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i > curMax) {
                curMax = nextMax;
                step++;
            }
            nextMax = Math.max(nextMax, i + arr[i]);
        }
        return step;
    }


    // 最长递增子序列
    
    

}
