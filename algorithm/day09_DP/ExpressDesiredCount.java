package com.chenjunyi.day09_DP;

import java.util.HashMap;

/**
 * 给定一个只由0、1、&、|、^ 五种字符组成的的字符串express，再给定一个desired，返回express 能有多少中组合方式
 * <p>
 * 范围内的尝试模型
 * 能组成的标准：
 * 1、长度至少为7， 并且长度一定要是奇数
 * 2、数字只能出现再偶数位置上
 * 3、二进制运算符只能出现在奇数位置上
 */
public class ExpressDesiredCount {
    private static int count(String express, boolean desired) {
        if (express == null || express.length() == 0) {
            return 0;
        }
        char[] ch = express.toCharArray();
        // 验证表达式是否达到了标准
        if (!isValid(ch)) {
            return 0;
        }
        HashMap<String, HashMap<Boolean,Integer>> cache = new HashMap<>();
        return process(ch, desired, 0, ch.length - 1, cache);
    }

    private static int process(char[] ch, boolean desired, int l, int r, HashMap<String, HashMap<Boolean,Integer>> cache) {
        if (cache.containsKey(String.valueOf(l)+"_"+String.valueOf(r))) {
            cache.get(String.valueOf(l) + "_" + String.valueOf(r)).get(desired);
        }
        // 如果只有一个字符
        if (l == r) {
            char c = ch[l];
            if (desired) {
                return c == '1' ? 1 : 0;
            } else {
                return c == '0' ? 1 : 0;
            }
        }
        int res = 0;
        if (desired) {
            // l是数字的位置，l+1是运算符的位置
            for (int i = l + 1; i < r; i += 2) {
                switch (ch[i]) {
                    case '|':
                        res += process(ch, true, l, i-1, cache) * process(ch, true, i+1, r, cache);
                        res += process(ch, false, l, i-1, cache) * process(ch, true, i+1, r, cache);
                        res += process(ch, true, l, i-1, cache) * process(ch, false, i+1, r, cache);
                        break;
                    case '^':
                        res += process(ch, true, l, i-1, cache) * process(ch, false, i+1, r, cache);
                        res += process(ch, false, l, i-1, cache) * process(ch, true, i+1, r, cache);
                        break;
                    case '&':
                        res += process(ch, true, l, i-1, cache) * process(ch, true, i+1, r, cache);
                        break;
                    default:
                        break;
                }
            }
        } else {
            for (int i = l + 1; i < r; i += 2) {
                switch (ch[i]) {
                    case '|':
                        res += process(ch, false, l, i-1, cache) * process(ch, false, i+1, r, cache);
                        break;
                    case '^':
                        res += process(ch, true, l, i-1, cache) * process(ch, true, i+1, r, cache);
                        res += process(ch, false, l, i-1, cache) * process(ch, false, i+1, r, cache);
                        break;
                    case '&':
                        res += process(ch, false, l, i-1, cache) * process(ch, true, i+1, r, cache);
                        res += process(ch, true, l, i-1, cache) * process(ch, false, i+1, r, cache);
                        res += process(ch, false, l, i-1, cache) * process(ch, false, i+1, r, cache);
                        break;
                    default:
                        break;
                }
            }

        }
        HashMap map = new HashMap();
        map.put(desired, res);
        cache.put(String.valueOf(l)+"_"+String.valueOf(r), map);
        return res;
    }

    private static boolean isValid(char[] ch) {
        if ((ch.length & 1) == 0) {
            return false;
        }

        // 数字必须再偶数位置上
        for (int i = 0; i < ch.length; i += 2) {
            if (!Character.isDigit(ch[i])) {
                return false;
            }
        }

        // 检查操作符是否符合标准
        for (int i = 1; i < ch.length; i += 2) {
            if (ch[i] != '|' && ch[i] != '&' && ch[i] != '^') {
                return false;
            }
        }
        return true;
    }


    public static class Info {
        int t;
        int f;

        public Info(int t, int f) {
            this.t = t;
            this.f = f;
        }
    }

    // 布尔表达式expr，经过组合后最终结果等于flag的方法数
    public static int f(String expr, boolean flag) {
        int n = expr.length();
        Info[][] dp = new Info[n][n];
        Info info = g(expr.toCharArray(), 0, n-1, dp);
        return flag == true ? info.t : info.f;

    }

    public static Info g(char[] s, int l, int r, Info[][] dp) {
        if (dp[l][r] != null) {
            return dp[l][r];
        }
        // 如果只有一个字符
        int t = 0;
        int f = 0;
        if (l == r) {
            t = s[l] == '1' ? 1 : 0;
            f = s[l] == '0' ? 1 : 0;
        }  else {

            // 不止一个字符
            for (int split = l + 1; split < r; split += 2) {
                Info left = g(s, l, split - 1, dp);
                Info right = g(s, split + 1, r, dp);
                int a = left.t;
                int b = left.f;
                int c = right.t;
                int d = right.f;

                switch (s[split]) {
                    case '|':
                        t += a * c + a * d + b * c;
                        f += b * d;
                        break;
                    case '^':
                        t += a * d + b * c;
                        f += a * c + b * d;
                        break;
                    case '&':
                        t += a * c;
                        f += a * d + b * c + b * d;
                        break;
                }
            }
        }
        dp[l][r] = new Info(t,f);
        return dp[l][r];
    }




    public static void main(String[] args) {
        System.out.println(count("1^0|1&1|1", true));
        System.out.println(f("1^0|1&1|1", true));

    }
}
