package com.chenjunyi.leetcode;
import java.util.*;

public class LongestHuiWenSubstring {

    public String longestPalindrome(String s) {
        // 字符串的长度在[1,1000]之间，可以使用暴力解法 ,先不使用manacher方法
        // 将 1221 构建成 #1#2#2#1#的字符串，然后

        String str = getChuLiString(s);
        int len = str.length();
        int l = 0;
        int r = 0;
        // 开始位置和最大长度
        int maxLength = 1;
        int start = 0;
        int end = 0;
        // s 1221 -----> str #1#2#2#1#
        int[] n = new int[len];
        for (int i=0; i < n.length; i++) {
            n[i]=1;
        }
        for (int i =0; i <len; i++) {
            l = i-1;
            r = i+1;
            while (l>=0 && r < len) {
                if (str.charAt(l--)==str.charAt(r++)) {
                    n[i] += 2;
                    if (n[i] > maxLength) {
                        maxLength = n[i];
                        start = l;
                        end = r;
                    }
                } else {
                    break;
                }
            }
        }

        String str1 = str.substring(start+1, end);
        String str2 = "";
        // 取出n数组中第一个最大的数
        for (int i =1;i < str1.length(); i=i+2) {
            str2 += str1.charAt(i);
            
        }
        return str2;

    }

    private String getChuLiString(String s) {
        String  str = "#";
        for (int i =0; i < s.length(); i++) {
            str = str + s.charAt(i)+"#";

        }
        return str;
    }


    public String longestPalindrome1(String s) {
        // manacher 算法， 用简单版本
        // "aba" --> "#a#b#a#"
        char[] str = processString(s).toCharArray();
        int l, r =0;
        int max = 0;
        int start = 0, end = 0;
        int[] n = new int[str.length];
        for (int i=0; i < n.length; i++) {
            n[i]=1;
        }

        // 检查一个字符的对称的左右两侧的字符是否相等，如果相等则l--, r++ 继续检查
        for (int i =0; i < str.length; i++) {
            l = i-1;
            r= i+1;
            while (l >=0 && r < str.length) {
                if (str[l] == str[r]) {
                    n[i] += 2;
                    if (n[i] > max) {
                        max = n[i];
                        start = l;
                        end = r;

                    }
                    l--;
                    r++;
                } else {
                    break;
                }
            }
        }

        // str 字符串中start和end位置的字符就是最长的回文字符串
        String ans = "";
        for (int i=start+1; i<= end; i+=2) {
            ans += str[i];
        }
        return ans;
    }


    public String processString(String s) {
        StringBuilder sb = new StringBuilder("#");
        for (int i =0; i < s.length(); i++) {
            sb.append(s.charAt(i)).append("#");
        }

        return sb.toString();
    }

    public int myAtoi(String s) {
        // 将前面的空格去掉
        s = s.stripLeading();

        boolean negative = s.charAt(0) == '-' ? true : false;
        boolean positive = s.charAt(0) == '+' ? true : false;
        int index = (negative== true || positive == true) ? 1 : 0;
        int ans = 0;

        while (index < s.length() && Character.isDigit(s.charAt(index))) {
            // 是数字
            int tmp = ((negative ? Integer.MIN_VALUE : Integer.MIN_VALUE + 1) + (s.charAt(index) - '0')) / 10;
            if (tmp > ans) {
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            ans = ans * 10 - (s.charAt(index++) - '0');
        }

        return  negative ? ans : -ans;

    }

    public int reverse(int x) {

        // 利用 % / 进行反转

        int sum = 0;
        while (x != 0) {
            if (Integer.MAX_VALUE / 10 < sum || Integer.MIN_VALUE / 10 > sum) {
                sum = 0;
                break;
            }
            sum = sum * 10;

            sum += (x % 10);
            x = x/ 10;
        }
        return sum;

    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList();
        Set<List<Integer>> s = new HashSet<>();
        for (int i =0; i < nums.length; i++) {
            int first = nums[i];
            int l = i+1;
            int r = nums.length-1;
            while (l < r) {
                int sum = first + nums[l] + nums[r];
                if (sum == 0) {
                    s.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }

            }
        }
        ans.addAll(s);

        return ans;

    }

    public int reverseBits(int n) {
        int m = 0;
        int i = 0;
        while (i < 32) {
            m |= (n >>> (31-i));
            i++;

        }
        return m;

    }



    public static void main(String[] args) {

        
        LongestHuiWenSubstring test = new LongestHuiWenSubstring();
        test.reverseBits(245017234);
        int[] a = {-1,0,0,-1,1,1,2};
        System.out.println(test.threeSum(a));

        //System.out.println(test.myAtoi("-91283472332"));
//        System.out.println(test.myAtoi("2147483648"));
////
////
////        System.out.println(test.myAtoi("+-12"));
//        System.out.println(test.myAtoi("  +12"));
//        System.out.println(test.myAtoi("   -12"));
//        System.out.println(test.myAtoi("   -12"));
//        System.out.println(test.myAtoi("00000-42a1234"));
//        System.out.println(test.myAtoi("4193 with words"));
    }

}
