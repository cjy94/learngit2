package com.chenjunyi.day08_DPTest;

import java.util.Arrays;

public class DP_TiezhiProblem {

    // 暴力递归求解过程
    public static int process(String rest, String[] tiezhi) {
        // 判断base case
        if(rest.length()==0) return 0;
        int result = 0;
        // 尝试每一种贴纸的情况
        for (String tz : tiezhi) {
            // 要在rest中减去包含在rest串中当前贴纸中的字符， 剩余nextRest， 用于下次尝试
//            rest-tz -- > nextRest;
//            int cur = process(nextRest, tiezhi);
//            result = Math.min(cur, result);

        }
        return result+1;

    }



    // 将一个字符串数组中每个字符串的字符频率统计，
    //
    public static void transfer(String[] str) {
        // row: 是由多少个字符串
        // col: row[0]中的美女每一个字符数量
        int[][] map = new int[str.length][26];
        for (int i =0; i < str.length; i++) {
            char[] ch = str[i].toCharArray();
            for (int j =0; j < ch.length; j++) {
                map[i][ch[j]-'a']++;

            }

        }
    }

    public static int strStr(String p, String c) {
        // 思路： 使用双指针，
        if (c.length() > p.length()) return -1;

        int start=0;
        int index = 0;

        while (start < p.length() && index < c.length()) {
            if (p.charAt(start) ==c.charAt(index)) {
                index++;
                start++;
            } else {
                start = (start-index+1);
                index = 0;
            }

            if (index==c.length()) {
                return start-c.length();
            }
        }
        return -1;

//        if (c.length() > p.length()) return -1;
//
//        int n = p.length(), m = c.length();
//        int nIndex = 0;
//        for (int i =0; i < p.length(); i++) {
//            if(p.charAt(i)==c.charAt(nIndex)){
//                nIndex++;
//            }
//            else{
//                // start from the next index of previous start index
//                i=i-nIndex;
//                // needle should start from index 0
//                nIndex=0;
//            }
//            if(nIndex==m){
//                // return the first index
//                return i-m+1;
//            }
//        }
//        return -1;

    }

    public static int[] plusOne(int[] digits) {
        int sum = 0;
        int wei = 1;
        for (int i = digits.length-1; i>=0; i--) {
            System.out.println(digits[i]*wei);
            sum = sum + digits[i] * wei;
            wei = wei*10;
        }
        sum = sum+1;

        String str = String.valueOf(sum);
        int[] arr = new int[str.length()];
        wei = str.length();
        int index = wei-1;
        while(wei >= 1) {
            if (wei==1) {
                arr[index] = sum;
            } else {
                arr[index] = sum % 10;
            }
            sum/=10;
            wei--;
            index--;
        }
        return arr;
    }
    

    public static void main(String[] args) {
//        String[] str = {"abchu", "dehudhuhsdsx", "aaadd", "edhuwydhu"};
//        transfer(str);
        int[] arr = {9,8,7,6,5,4,3,2,1,0};
        System.out.println(Arrays.toString(plusOne(arr)));
        System.out.println(9*100000000);

    }
}
