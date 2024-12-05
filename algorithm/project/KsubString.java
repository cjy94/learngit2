package com.chenjunyi.project;

import java.util.HashMap;

/**
 *  给定 M(0<M<=30)个字符（a-z），从中取出任意字符（每个字符只能用一次）拼接成长
 *  度为 N(0<N<=5)的字符串，要求相同的字符不能相邻，计算出给定的字符列表能拼接出多
 *  少种满足条件的字符串，输入非法或者无法拼接出满足条件的字符串则返回 0。
 */
public class KsubString {
    public static void main(String[] args) {
        String s = "abc";
        int count = count(s, 1);
        System.out.println(count);

    }
    public static int count(String str, int k) {
        char[] s = str.toCharArray();
        int ans = 0;
        HashMap<Character, Integer> map = new HashMap<>();  // 记录每个字符上次出现的位置
        for (int i =0; i < s.length; i++) {
            map.clear();
            map.put(s[i], i);
            int count = 1;
            for (int j = i+1; j < s.length; j++) {
                if (map.containsKey(s[j])) {
                    int preIndex = map.get(s[j]);
                    if (preIndex + 1 != j) {
                        count++;
                    }
                }  else {
                    count++;
                }
                
                if (count == k) {
                    ans++;
                    break;
                }

            }
        }
        return ans;
    }
}
