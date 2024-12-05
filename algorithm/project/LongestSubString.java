package com.chenjunyi.project;

/**
 *  给定一个字符串，只包含字母和数字，按要求找出字符串中的最长（连续）子串的长度，
 *  字符串本身是其最长的子串，子串要求：
 *  1、 只包含 1 个字母(a~z, A~Z)，其余必须是数字；
 *  2、 字母可以在子串中的任意位置；
 *
 *  滑动窗口?
 *  numCount=0
 *  1、l=r=0, r指针每向右侧滑动一次，如果进入窗口的字符是数字，numCount++， 如果numCount==1，记录res的值, 如果numCount>1, l指针右移
 *  2、如果进入窗口的不是数字，numCount不变，
 */
public class LongestSubString {

    public static void main(String[] args) {
        String s = "abhbzbzhabhb";
        int count = count1(s);
        int count1 = count1(s);
        System.out.println(count);
        System.out.println(count1);
    }


    // 只包含一个字母，其余全是数字的最长子串
    public static int count1(String str) {
        int n = str.length();
        int l = 0, r = 0;
        int maxLength = -1;
        int letterCount = 0;
        while (r < n) {
            char cur = str.charAt(r);
            if (Character.isLetter(cur)) {
                letterCount++;
            }
            // 如果字母数量大于1
            while (letterCount > 1) {
                if (Character.isLetter(str.charAt(l)))
                    letterCount--;
                l++;
            }

            if (letterCount == 1)
                maxLength = Math.max(maxLength, (r-l+1));
            r++;
        }
        return maxLength;

    }

    // 子串中只包含一个数字，旗鱼全是字母的最长子串
    public static int count(String str) {
        int n = str.length();
        int l = 0, r = 0;
        int res = 0;
        int letterCount = 0;
        while (r < n) {             //abC124ACb
            char cur = str.charAt(r);
            if (Character.isLetter(cur) && letterCount == 0) {  //  cur是字母，并且当前窗口中没有字母
                letterCount++;
                r++;
                res = Math.max(res, (r-l));
            } else if (!Character.isLetter(cur) && letterCount <= 1) {  // cur不是字母，并且当前窗口中已经可以包含字母(数字个数必须是1)，也可以不包含字母
                r++;
                res = Math.max(res, (r-l));
            } else {  // cur不是字母，并且当前窗口中的字母个数大于1 | cur是数字，并且当前窗口中已经包含数字
                while (l < r) {
                    char lstr = str.charAt(l);
                    if (Character.isLetter(lstr)) {
                        letterCount--;
                        if (letterCount <= 1) {
                            l++;
                            break;
                        }
                    }
                    l++;
                }
                if (Character.isLetter(cur))
                    letterCount++;
                r++;
                res = Math.max(res, (r-l));
            }

        }
        return letterCount == 1 ? res : 0;
    }
}
