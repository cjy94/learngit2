package com.chenjunyi.zhongji一.zhongji6;

/**
 * dp: 范围尝试模型
 * 给定一个字符串， 添加最少的字符使字符串整体都是回文字符串
 *
 */
public class StringMinCharacterPalindrome {

    // 通过dp表计算添加的最少字符串
    // dp含义：str[L...R]范围的字符串，最少添加几个字符可以形成回文字符串
    public static int minCharacters(String str) {
        int n = str.length();
        int[][] dp = new int[n][n];
        // dp的下半部分是无效的，因为str[L..R] L不应该超过R的范围

        // 第一条对角线 dp[0][0] dp[1][1] dp[2][2] dp[3][3]...
        // 因为只有一个字符，所以不需要添加字符就是回文

        // 填写第二条对角线  dp[0][1] dp[1][2] dp[2][3]....
        // str[0]和str[1]最少添加几个字符可以形成回文字符串，如果str[i]==str[i+1]不需要添加，否则添加1个字符即可，或者str[i]添加到字符串尾部；或者str[i+1]添加到字符串头部
        for (int i =0; i < n-1; i++) {
            dp[i][i+1] = str.charAt(i) == str.charAt(i+1) ? 0 : 1;
        }


        // 普遍位置
        // 依次填写dp[6][8] , dp[5][7] dp[5][8], dp[4][6] dp[4][7] dp[4][8]...
        for (int l = n-2; l >=0; l--) {
            for (int r = l+2; r < n; r++) {
                // str[l...r]范围上，至少添加几个字符，可以形成回文字符串
                // 情况1： str[l...r-1]是回文，str[r]添加到str[l..r]的首部， dp[l][r-1]+1
                // 情况2： str[l+1...r]是回文，str[l]添加到str[l..r]的位部， dp[l+1][r]+1
                // 情况3： str[l] == str[r]， 那么str[l+1...r-1]是回文
                dp[l][r] = Math.min(dp[l][r-1], dp[l+1][r]) + 1;
                if (str.charAt(l) == str.charAt(r))  {
                    dp[l][r] = Math.min(dp[l][r], dp[l+1][r-1]);

                }
            }
        }

        return dp[0][n-1];


    }

    public static void main(String[] args) {
        System.out.println(minCharacters("a122b321c"));
    }
}
