package com.chenjunyi.GO.FromLeftToRight;


import java.util.ArrayList;
import java.util.HashMap;

/**
 *  贴纸问题   难******************
 *  给定一个字符串str，给定一个字符串类型的数组arr
 *  arr里的每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来
 *  返回需要至少多少张贴纸可以完成这个任务
 *  例子：str="babac", arr={"ba", "c", "abcd"}
 *  至少需要两张贴纸"ba"和"abcd", 因为使用这两张贴纸，把每一个字符单独剪开，包含2个a，2个b，1个c，可以拼出str, 返回2
 *
 *  尝试的方案是： 选择那一张贴纸作为第一个
 *                      babac
 *                 ba/    |c   \abcd
 *                bac    baba   ba
 *            ba/ |c \abcd  ...
 *
 *  设计递归的原则：尽量减少可变参数的数量
 * 
 *
 *
 */
public class StickerProblem {

    public static int sticker(String str, String[] arr) {
        int n = arr.length;
        int[][] map = new int[n][26]; // 用于记录每一张贴纸中的字符信息
        for (int i =0; i < n; i++) {
            for (char ch : arr[i].toCharArray()) {
                map[i][ch-'a']++;     // 0位置表示a, 1位置表示b，2位置表示c ...
            }
        }
        HashMap<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        return process(str, map, dp);
    }

    // 要处理的字符串s
    // 贴纸信息map
    // 任意使用map中的贴纸，能拼出s的最少贴纸数量
    private static int process(String s, int[][] map, HashMap<String, Integer> dp) {
        if (dp.containsKey(s))
            return dp.get(s);
        // 将s变成词频信息
        int[] tmap = new int[26];
        for (char ch : s.toCharArray()) {
            tmap[ch-'a']++;
        }
        int n = map.length;  // 贴纸的数量
        int ans = Integer.MAX_VALUE; // 返回结果

        // 用map贴纸信息 解决tmap指定字符串
        // dp --> tmap
        for (int i = 0; i <n; i++) { // 尝试每一张贴纸
            int[] sticker = map[i]; // 第i张贴纸的信息
            char[] ch = s.toCharArray();
            ArrayList<Integer> lists = new ArrayList<>();
            for (int x = 0; x < sticker.length; x++) {
                if (sticker[x] > 0) {
                    lists.add(sticker[x]);
                }
            }
            boolean flag = false;
            for (char st : ch) {
                if (lists.contains(st-'a'))
                    flag = true;
            }
            // 以上一大段代码主要是在检查当前贴纸是否能处理s这个字符，如果不能则选下一张贴纸消耗掉s，比如贴纸是"abc" 但是当前字符串是"xyz"， 这种贴纸应该过滤掉


            if (flag == false)
                continue;

            // map 贴纸中肯定包含s中的至少一种字符
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                if (tmap[j] > 0) {
                    for (int y = 0; y <Math.max(0, tmap[j] - map[i][j]); y++) {
                        sb.append((char)(j + 'a'));
                    }
                }
            }
            String rest = sb.toString();      // i号贴纸 帮忙搞定的字符都去掉了，还剩下的字符串被后序贴纸搞定
            int p1 = process(rest, map, dp);
            if (p1 != -1) {     // 如果返回值是-1， 说明这个贴纸怎么也搞不定s字符串
                ans = Math.min(ans, 1 + p1);
            }
        }
        dp.put(s, ans == Integer.MAX_VALUE ? -1 : ans);
        return dp.get(s);
    }
}
