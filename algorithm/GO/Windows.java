package com.chenjunyi.GO;

import java.util.Arrays;
import java.util.HashSet;

/**
 *
 *  窗口内最大值最小值更新结构
 *  [l,r) 表示一个窗口， 使用双端队列， 队列中的元素要保证从队列头到队列尾部的数据是从大到小有序的
 *  任何时候双端队列中的最大值，都是队列中的头部元素
 *
 *  l往右侧移动，双端队列中的元素有可能会过期，
 *  r往右移动，往队列中添加元素，需要保证队列中元素的单调性
 *
 *  因为每个元素最多进窗口1次，出窗口1次， 平均时间复杂度O(1)
 *
 *  定义： 数组中累加和与最小值的乘积，假设叫做指标A
 *  给定一个数组，请返回子数组中，指标A的最大值       使用单调栈
 *      求解方式： 遍历每一个位置i，要求必须包含arr[i]， 并且arr[i]最为子数组中的最小值的情况下的形成的子数组最大值
 *
 *      arr={5, 3, 2, 1, 6, 7, 8}          ******每一个位置左边比你小的，右边比你小的位置是你扩充不到的位置******
 *      1、子数组必须包含5，并且5是子数组中的最小值， 形成的子数组中{5} 5*5
 *      2、子数组必须包含3，并且3是子数组中的最小值，形成的子数组{5,3}  8*3
 *      3、子数组必须包含2，并且2是子数组中的最小值，形成的子数组{5,3,2}
 *      4、子数组必须包含1，并且1是子数组中的最小值，形成的子数组{5,3,2,1,6,7,8}
 *      5、子数组必须包含6，并且6是子数组的最小值，形成的最好结果 {6, 7, 8}
 *      6、子数组必须包含7，并且7是子数组的最小值，形成的最好结果 {7, 8}
 *      7、子数组必须包含8，并且8是子数组的最小值，形成的最好结果 {8}
 *  
 *
 *  滑动窗口：  大部分解决子数组和子串的问题     求子数组在每个位置 开头 或者 结尾情况下的答案
 *  题目：
 *
 *  1、累加和大于等于target的最短子数组长度
 *   给定一个含有n个正整数的数组和一个正整数target
 *   找到累加和<=target的长度最小的子数组并返回其长度
 *
 *  2、无重复字符的最长子串       还有一个题目是：去除重复字符，并且保证剩余的字符串字典序最小， 使用单调栈求解， 栈底到栈顶的字符按照字典序从小到大进行压栈
 *
 *    前一个字符延伸的左边界和这个字符上一次出现的位置取最大值   max(a上一次出现的位置+1, 左)
 *    也可以用动态规划求解
 *
 *  3、最小覆盖子串
 *     给定一个字符串s，一个字符串t。 返回s中涵盖t所有字符的最短子串，如果s中不存在涵盖t的所有字符的子串，则返回空字符串""
 *     对字符串t做一个词频统计
 *
 *  4、加油站
 *  在一条环路上有n个加油站，其中第i个加油站有汽油gas[i]升
 *  你有一辆邮箱容积无限的汽车，
 *  从第i个加油站开往第i+1个加油站需要消耗汽油cost[i]升
 *  你从其中一个加油站出 发，开始时邮箱为空
 *  给定两个整数数组gas和cost，如果可以按顺序绕环路行驶一周，则返回出发时加油站编号，否则返回-1
 *
 *  数组转换一下， 储能值或者油量剩余数组， gas[i] - cost[i]： [3,-5,10,-2]
 *
 *  5、替换子串得到平衡字符串
 *  右一个q w e r四种字符，且长度为n的字符串，n一定为4的整数倍，假如在该字符串中，这4个字符都恰好出现了n/4次，那么它就是一个平衡字符串
 *  给你一个这样的字符串s，请通过替换一个子串的方式，使原字符串s变成一个平衡字符串
 *  子串可以替换成由q w e r四种字符组成的任何样子
 *  请返回待替换子串的最小可能长度，如果原字符串本身是一个平衡字符串，则返回0
 *
 *  5.1、先统计字符串s中词频情况， 比如字符串总长度是40,
 *      q    4个
 *      w    12个
 *      e    14个
 *      r    10个
 *
 *    其中2个w是多余的，4个e是多余的，需要被替换掉，那么也就转换成了要在字符串s中找到包含wweeee的最短子串问题了， 转换成了题目3 最小覆盖子串问题
 *  
 *
 *  6、k个不同正数的子数组
 *  给定一个正整数数组nums和一个正数k，返回nums中好子数组的数目
 *  如果nums的某个子数组中不同正数的个数恰好是k，那么称nums的这个连续不一定不同的子数组为好子数组
 *  比如nums:[1,2,1,2,3] k=2
 *  好子数组： {1,2} {2,1} {1,2} {2,3} {1,2,1} {2,1,2} {1,2,1,2} 7个
 *
 *  严格等于k不太好求， 不同数的种类<=k 比较好求
 *
 *  7、至少有K个重复字符的最长子串, 字符串只由小写字母组成        难
 *  给你一个字符串s和一个整数k，请找出s中的最长子串
 *  要求该子串中的每一个字符出现次数都不少于k，请返回这一子串的长度。如果不存在，则返回0
 *
 *   达标子串，每种字符出现的次数至少是k
 *   1) 子串必须包含1种字符， >=k，最长多长?
 *   2) 子串必须包含2种字符， >=k，最长多长?
 *   3) 子串必须包含3种字符， >=k，最长多长?
 *   ...
 *   26) 子串必须包含26种字符， >=k，最长多长?
 *
 *   
 *
 */

public class Windows {

    // 6、k个不同正数的子数组
    public static int subArrayWithDistinct(int[] arr, int k) {
        return numsOfMostKinds(arr, k) -  numsOfMostKinds(arr, k-1);
    }

    private static int numsOfMostKinds(int[] arr, int k) {
        int n = arr.length;
        int[] cnts = new int[n];
        int ans = 0;
        for (int l =0, r =0, collect = 0; r < n; r++) {
            // r 位置的字符进窗口，可能会增加字符种类
            if (++cnts[arr[r]] == 1)   // 是一个新字符
                collect++;
            while (collect > k) {
                if (--cnts[arr[l++]] == 0) {   // 窗口吐出字符
                    collect--;
                }
            }
            ans += r-l+1;
        }
        return ans;
    }

    // 5、平衡子串
    public static int balanceSubstring(String str) {
        int n = str.length();
        int[] s = new int[n];  // 因为str中只包含4中字符，故将其转换成数字数组比如str="qqrrewwqq" --> s=[003321100]
        int[] cnts = new int[4];  // 每一种字符做词频统计
        //
        // Q 0
        // W 1
        // E 2
        // R 3
        for (int i = 0;i < n; i++) {
            char c = str.charAt(i);
            s[i] = (c == 'W' ? 1 : (c == 'E' ? 2 : (c == 'R' ? 3 : 0)));
            cnts[s[i]]++;
        }

        // 统计欠债信息
        int debt = 0;
        for (int i =0; i < 4; i++) {
            if (cnts[i] - n/4 < 0) {   // 不欠债的字符
                cnts[i] = 0;
            } else {         // 大于n/4的字符
                cnts[i] = n/4-cnts[i];
                debt -= cnts[i];
            }
        }

        if (debt == 0)
            return 0;

        // 其中debt cnts s 转换成了题目3
        int ans = Integer.MAX_VALUE;
        for (int l =0, r =0; r < n; r++) {
            if (cnts[s[r]]++ < 0)
                debt--;
            if (debt == 0) {
                while (cnts[s[l]] > 0) {
                    cnts[s[l++]]--;
                }
                ans = Math.min(ans, (r-l+1));
            }
        }
        return ans;
    }


    // 4、加油站
    public static int gasStation(int[] gas, int cost[]) {
        int n = gas.length;
        // 是个环路，本来下标是[0..n-1] 但是扩充到了[0..2n-1]
        for (int l =0, r = 0, curSum = 0; l < n; l = r+1, r = l) {   // l=r+1 利用了一点贪心
            while (curSum + gas[r%n]-cost[r%n] >= 0) {
                // r位置即将右扩，窗口会变大
                if (r-l+1 == n)   // 此时检查是否已经转了一圈
                    return l;
                // r位置进入窗口，累加和加上r位置的余量
                curSum += gas[r%n]-cost[r%n];
                r++;
            }
        }
        return -1;
    }


    // 3、最小覆盖子串
    public static String minWindow(String str, String target) {
        if (target.length() > str.length())
            return "";
        char[] s = str.toCharArray();
        char[] t = target.toCharArray();
        int[] collects = new int[256];
        for (char v : t) {
            collects[v]--;
        }
        int len = Integer.MAX_VALUE;   // 最短字符串的长度
        int start = 0;  // 最短字符串的其实位置
        for (int l =0, r = 0, debt = t.length; r < s.length; r++) {
            if (collects[s[r]]++ < 0)   // 一次有效的还款
                debt--;

            if (debt == 0) {  // target中的所有字符都在s中某一处开始凑齐了
                while (collects[s[l]] > 0) {   // 尝试满足要求的字符串是否可以更短
                    collects[s[l++]]--;
                }
                if (r-l+1 < len) {
                    len = r-l+1;
                    start = l;
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : str.substring(start, start + len);    // substring(startIndex, endIndex)
    }

    // 2、无重复字符的最长字串
    public static int NoRepeatSubString(String s) {
        // 每一种字符上次出现的位置
        int[] last = new int[256];
        char[] str = s.toCharArray();
        Arrays.fill(last, -1);
        int ans = 0;
        for (int l = 0, r = 0; r < str.length; r++) {
            l = Math.max(last[str[r]]+1, l);
            ans = Math.max(ans, (r-l+1));
            last[str[r]] = r;
        }
        return ans;
    }

    // 1、累加和>=target的最短子数组长度
    public static int shortestSubArray(int[] arr, int target) {
        int sum = 0;
        //int l = 0, r = 0;
        int ans = Integer.MAX_VALUE;
        for (int l = 0, r = 0; r < arr.length; r++) {   // 以每一个位置i为结尾情况下的，能向左侧延伸的最短长度是多少
            sum += arr[r];
            while (sum - arr[l] >= target) {  // 求最短距离，试着左侧能不能缩回来一点
                sum -= arr[l++];
            }
            if (sum >= target) {
                ans = Math.min(ans, (r-l+1));
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
