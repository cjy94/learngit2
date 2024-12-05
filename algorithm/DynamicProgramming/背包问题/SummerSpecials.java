package com.chenjunyi.DynamicProgramming.背包问题;

import java.awt.*;
import java.util.ArrayList;

/**
 *  夏季特惠
 *  某公司游戏平台的夏季特惠开始了， 你决定入手一些游戏
 *  现在你一共有X元的预算，平台所有的n个游戏均有折扣
 *  标号为i的游戏原价a_i, 现价只要b_i
 *  也就是说该游戏可以优惠a_i - b_i， 并且你购买该游戏能获得快乐值w_i
 *  由于优惠的存在，你可能做出一些冲动消费导致最终买游戏的总费用超出预算
 *  只要满足: 获得的总优惠金额不低于超过预算的总金额， 心理上就不会觉着吃亏
 *  现在你希望在心理上不觉着吃亏的前提下，获得尽可能多的快乐值
 *
 *  就是自由选择游戏，每一款游戏的优惠
 *  (a_i - b_i) * 购买游戏个数 >=  (b_i * 购买游戏个数 - X)   成立的条件下，w_i的最大值是多少
 *
 *  每一款游戏的 总优惠价格 >= 总超过价格  就可以购买
 *
 *  比如：
 *            0 1 2
 *      原价 [3 2 11]
 *      现价 [1 2 6]         全部购买后优惠的价格： 2+5=7元
 *
 *      编号0： 优惠价格：2元   购买这个商品花费：1元
 *      编号1： 优惠价格：0元   购买这个商品花费：2元
 *      编号2： 优惠价格：5元   购买这个商品花费：6元
 *
 *
 */
public class SummerSpecials {

    /*
        x: 总金额
        pre：所有商品的原价
        cur：所有商品的现价
        happy：购买每个商品的快乐值
        返回在随意购买商品，在心里不吃亏情况下的最大快乐值
     */
    public static int maxHappy(int[] pre, int[] cur, int[] happy, int x) {
        int ans = 0;
        int n = pre.length;   // 商品数量
        int[] costs = new int[n];
        int[] happys = new int[n];
        int index = 0;
        for (int i =0; i < n; i++) {
            if (pre[i] - cur[i] >= cur[i]) {
                x += cur[i];
                ans += happy[i];
            } else {   // 待定的商品，需要考虑是否购买的商品
                costs[index] = -(pre[i] - cur[i] - cur[i]);
                happys[index++] = happy[i];
            }
        }

        // costs[], happy[], x之间的背包问题
        ans += compute(costs, happys, index, x);
        return ans;
    }

    // 在costs[0...i]中任意选择货物，在总重量不超过x的情况下，获得的最大价值
    private static int compute(int[] costs, int[] happy, int index, int x) {
        int[][] dp = new int[index+1][x+1];
        for (int i =1; i <= index; i++) {
            for (int j =0; j <= x; j++) {
                dp[i][j] = dp[i-1][j];
                if (j - costs[i] >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-costs[i]] + happy[i]);

                }
            }
        }
        return dp[index][x];
    }

    // 空间压缩下的0-1背包问题
    private static int compute1(int[] costs, int[] happy, int index, int x) {
        int[] dp = new int[x+1];
        for (int i =1; i <= index; i++) {     // 遍历每一个货物
            for (int j = x; j >= costs[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j-costs[i]] + happy[i]);
            }
        }
        return dp[x];

    }


    public static void main(String[] args) {
        SummerSpecials test = new SummerSpecials();
        long start = System.currentTimeMillis();
        //test.method(100000);    //907
        test.method2(100000);  //9
        // 字符串拼接使用StringBuilder
        // StringBuilder 改进的空间 , 创建StringBuilder时，可以传入一个length， 防止底层的数组进行扩容

        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start));

    }

    public void method(int count) {
        String str = "";
        for (int i =0; i < count; i++) {
            str += 'a';
        }
    }

    public void method2(int count) {
        StringBuilder str = new StringBuilder(count);
        for (int i =0; i < count; i++) {
            str.append("a");
        }
    }

    public void method3(int count) {
        String s1 = "ab";
        String ab = new String("ab");
    }



    public static void print(long num)  {
        StringBuilder str = new StringBuilder("");
        for (int i =63; i >= 0; i--) {
            str.append((num & (1L << i)) == 0 ? "0" : "1");
        }
        System.out.println(str);

    }

    public static void test() {
        // 1、
        int i1 = 10;
        i1++;       // i1=11
        System.out.println("i1:" + i1);

        int i2 = 10;
        ++i2;  // i2=11
        System.out.println("i2:" + i2);

        // 2、
        int i3 = 10;
        int i4 = i3++;    // i3=11, i4=10
        System.out.println("i3:" + i3 +", i4: "+i4);

        int i5 = 10;
        int i6 = ++i5;   // i5=11, i6=11
        System.out.println("i5:" + i5 +", i6: "+i6);

        // 3、
        int i7 = 10;
        i7 = i7++;    // i7=10
        System.out.println("i7:" + i7);

        int i8 = 10;
        i8 = ++i8;  // i8=11
        System.out.println("i8:" + i8);

        // 4
        int i9 = 10;
        int i10 = i9++ + ++i9;   // i9=12, i0=22
        System.out.println("i9:" + i9 +", i10: "+i10);

    }
}
