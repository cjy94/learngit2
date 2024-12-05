package com.chenjunyi.project;

/**
 * 字符'l' 'o' 'x'字符出现的次数恰好都是偶数的最长子串
 * 使用动态规划
 * dp[i]: 0...i-1 位置已经满足次数是偶数次并且最长子串的要求，求str[i]位置的最长子串长度
 *
 * 1、如果 str[i]的加入不满足l o x次数是偶数次，那么dp[i]=dp[i-1]
 * 2、如果str[i]的加入满足l o x次数是偶数，那么
 *  2.1 没出环形， r-l+1
 *  2.2 出环了，
 *
 *  s = aabbccdd
 *  枚举每一个索引位置开始的满足要求的最长子串
 *  a a b b c c d d     从target+1... len - ()
 *  target=0
 *  target=1
 *  target=2 ... target= len-1
 *
 */
public class LOXCount {

    public static void main(String[] args) {
        String s = "aabbccddij";
        int result = 0;
        for (int i =0; i < s.length(); i++) {
            result = Math.max(result, maxSubString(s, i));
        }
        System.out.println(result);
    }

    public static boolean isValid(int l, int o, int x) {
        return l % 2 == 0 && o % 2 == 0 && x % 2 == 0;
    }

    public static int maxSubString(String s, int target) {
        int[] dp = new int[s.length()];
        int lCount = 0;
        int oCount = 0;
        int xCount = 0;
        char ch = s.charAt(0);
        if (ch == 'l')
            lCount++;
        else if (ch == 'o')
            oCount++;
        else if(ch == 'x')
            xCount++;
        dp[0] = isValid(lCount, oCount, xCount) ? 1 : 0;
        int i = 1;  // 填写dp的索引
        int start = target == s.length() - 1 ? 0 : target + 1;
        int result = 0;
        while (start != target) {
            ch = s.charAt(start);
            if (ch == 'l')
                lCount++;
            else if (ch == 'o')
                oCount++;
            else if(ch == 'x')
                xCount++;
            if (isValid(lCount, oCount, xCount)) {
                if (start >= target)
                    dp[i] = start-target+1;
                else
                    dp[i] = s.length() - (target-start+1);

            } else {
                dp[i] = dp[i-1];
            }
            if (start + 1 == s.length())
                start = 0;
            else
                start++;

            result = Math.max(result, dp[i]);
        }
        return result;
        
    }
}
