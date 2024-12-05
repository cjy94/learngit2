package com.chenjunyi.force;

import java.util.List;
import java.util.ArrayList;

/**
 * 从左往右的尝试模型
 * <p>
 * 问题 1:
 * 规定1和A对应、2和B对应、3和C对应....
 * 那么一个数字字符串比如“111”就可以转化为： "AAA" "KA" "AK"
 * 给定一个只有数字字符组成的字符串str，返回右多少种转化结果
 * <p>
 * <p>
 * 问题2：背包问题
 * 给定两个长度都为N的数组，weights和values, weights[i]和values[i]分别代表i号物品的重量和价值
 * 给定一个bag，表示一个载重bag的待子，装的物品不超过这个给重量，返回能装下最多的价值是多少
 * <p>
 * 问题3：生成括号问题
 * 比如： n=2 --->  "()()" "(())"     n=3 --> "()()()" "(())()" "()(())" "(()())" "((()))"
 */
public class FromLeftToRightTryModel {
    //================================字符串转化问题=================================
    public static int transform(String str) {
        if (str == null || str.length() == 0)
            return 0;

        return process1(str, 0);

    }

    // [0....i-1]的字符已经组成了字符串， i后面组成字符的方法有多少种
    private static int process1(String str, int i) {
        if (i == str.length()) {
            return 1;
        }

        char ch = str.charAt(i);
        // 以0开头的情况 , 不能组成任何字符串
        if (ch == '0') {
            return 0;
        }
        // 以“1”开头的情况
        if (ch == '1') {
            // 自己组成一个字符  1-> a
            int res = process1(str, i + 1);
            // 10-> 11-> 12-> 13-> 14-> 15->...
            if (i + 1 < str.length()) {
                res += process1(str, i + 2);
            }
            return res;
        }
        // 以“2”开头的情况
        if (ch == '2') {
            // 2 自己组成一个字符串，"b"
            int res = process1(str, i + 1);
            int next = str.charAt(i + 1) - '0';
            // 2和后面的字符组成一个字符串
            // 20 -->  21->  22->   23->    24->x    25->y  26->z

            if (i + 1 < str.length() && next >= 0 && next <= 6) {
                res += process1(str, i + 2);

            }
            return res;
        }
        // 以3-9开头的情况 , 只能自己组成一个字符串3->c
        return process1(str, i + 1);

    }


    private static int dpWayString(String s) {
        if (s == null || s.length() == 0)
            return 0;
        char[] str = s.toCharArray();
        int n = str.length;
        int[] dp = new int[n+1];
        // dp 生成
        dp[n] = 1;
        for (int i = n-1; i >= 0; i--) {
            if (str[i] == '0') {
                dp[i] = 0;
            } else if (str[i] == '1') {
                // 自己组成一个字符  1-> a
                dp[i] = dp[i + 1];
                // 10-> 11-> 12-> 13-> 14-> 15->...
                if (i + 1 < str.length) {
                    dp[i] += dp[ i + 2];
                }
            }else  if (str[i] == '2') {
                // 2 自己组成一个字符串，"b"
                dp[i] = dp[i + 1];
                // 2和后面的字符组成一个字符串
                // 20 -->  21->  22->   23->    24->x    25->y  26->z
                if (i + 1 < str.length && (str[i + 1] - '0' >= 0 && str[i + 1] - '0' <= 6)) {
                    dp[i] += dp[ i + 2];
                }
            } else {
                // 以3-9开头的情况 , 只能自己组成一个字符串3->c
                dp[i] = dp[i + 1];
            }
        }
        return dp[0];
    }

    //=====================================背包问题====================================
    public static int maxValue(int[] w, int[] v, int bags) {
        if (w == null || w.length == 0 || v == null || v.length == 0 || bags <= 0) {
            return 0;
        }

        return processBags(w, v, bags, 0, 0);
    }


    // [0..index-1]个获取已经做出了选择，并且背包已经达到了alreadyW的重量，
    // index... 后面的货物继续做出选择的最大价值是多少
    private static int processBags(int[] w, int[] v, int bags, int index, int alreadyW) {
        // 如果已经没有空间了
        if (alreadyW > bags) {
            return -1;
        }
        // 如果有空间，但是没有货物了
        if (index == w.length) {
            return 0;
        }

        // 不选择将当前货物装入背包中，后续货物选择的最大价值
        int p1 = processBags(w, v, bags, index + 1, alreadyW);
        // 选择当前货物 装入背包中，后续货物选择的最大价值
        int p2Next = processBags(w, v, bags, index + 1, alreadyW + w[index]);
        int p2 = -1;
        if (p2Next != -1) {
            // 当前货物的价值+ 后续选择的最大价值
            p2 = v[index] + p2Next;
        }
        return Math.max(p1, p2);
    }


    // 背包的第二种尝试模型, 剩余空间
    public static int maxValue1(int[] w, int[] v, int bags) {
        if (w == null || w.length == 0 || v == null || v.length == 0 || bags <= 0) {
            return 0;
        }

        return processBags1(w, v, 0, bags);
    }

    // 在还有rest剩余空间的前提下，选择index... 货物产生的最大价值
    private static int processBags1(int[] w, int[] v, int index, int rest) {
        // 剩余空间==0
        if (rest <= 0) {
            return -1;
        }
        // 有空间，但是没有货物可选
        if (index == w.length) {
            return 0;
        }

        int p1 = processBags1(w, v, index + 1, rest);
        int p2Next = processBags1(w, v, index + 1, rest - w[index]);
        int p2 = -1;
        if (p2Next != -1) {
            p2 = p2Next + v[index];

        }
        return Math.max(p1, p2);
    }

    private static int dpWay(int[] w, int[] v, int bags) {
        int n = w.length;
        int[][] dp = new int[n + 1][bags + 1];
        // dp[n][0]-dp[n][bags]一行的数据，每个位置的元素都是0
        for (int index = n - 1; index >= 0; index--) {
            // 从倒数第二行开始填值
            for (int rest = 1; rest <= bags; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - w[index] > 0) {
                    dp[index][rest] = Math.max(dp[index][rest], dp[index + 1][rest - w[index]] + v[index]);
                }
            }
        }
        return dp[0][bags];
    }

    //=========================================== 生成括号问题===========================================
    public static List<String> generateParenthesis(int n) {
        if (n == 0) return new ArrayList<>();
        String sub = "";
        ArrayList<String> list = new ArrayList<>();
        processParenthes(n, 0, 0, list, sub);
        return list;


    }

    private static void processParenthes(int n, int l, int r, ArrayList<String> list, String sub) {
        // 如果n=1, 那么 sub="()", 索引 sub.length() < 2 * n
        // base case
        if (sub.length() == 2 * n) {
            list.add(sub);
            return;
        }

        // 规定 left<n 加'('
        // 为什么???
        if (l < n) {
            processParenthes(n, l + 1, r, list, sub + "(");
        }
        // right<left 加")"
        if (r < l) {
            processParenthes(n, l, r + 1, list, sub + ")");
        }
    }


    public static void main(String[] args) {
//        int[] w = {3,5,7,2,1};
//        int[] v = {4,7,3,2,4};
//        int bags = 10;
//        System.out.println(maxValue(w, v, bags));
//        System.out.println(maxValue1(w, v, bags));
//        System.out.println(dpWay(w, v, bags));
//        System.out.println(generateParenthesis(3));
        System.out.println(dpWayString("11111"));
        System.out.println(transform("11111"));

    }
}
