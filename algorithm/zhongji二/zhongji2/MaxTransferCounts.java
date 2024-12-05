package com.chenjunyi.zhongji二.zhongji2;

public class  MaxTransferCounts {
    public static int maxCounts(int n) {
        char[] s = String.valueOf(n).toCharArray();
        return process(s, 0);
    }

    private static int process(char[] s, int i) {
        if (i == s.length) {
            return 1;
        }
        if (s[i] == '0') {
            return 0;
        }
        if (s[i] == '1') {
            int p1 = process(s, i+1);
            int p2Next = 0;
            if (i + 1 < s.length) {
                p2Next = process(s, i+2);
            }
            return p1 + p2Next;
        }

        if (s[i] == '2') {
            int p1 = process(s, i+1);
            int p2Next = 0;
            if (i + 1 < s.length && s[i+1] - '0' >= 0 && s[i+1] - '0'<= 6) {
                p2Next = process(s, i+2);
            }
            return p1 + p2Next;

        }
        return process(s, i+1);
    }

    private static int dpWays(int n) {
        char[] s = String.valueOf(n).toCharArray();
        if (s == null || s.length == 0) {
            return 0;
        }
        int[] dp = new int[s.length + 1];
        dp[s.length] = 1;
        for (int i = s.length-1; i>= 0; i--) {
            if (s[i] == '0') {
                dp[i] = 0;
            }else if (s[i] == '1') {
                dp[i] = dp[i+1];
                if (i + 1 < s.length) {
                    dp[i] += dp[i+2];
                }

            }else if (s[i] == '2') {
                dp[i] = dp[ i+1];
                if (i + 1 < s.length && s[i+1] - '0' >= 0 && s[i+1] - '0'<= 6) {
                    dp[i] += dp[i+2];
                }


            } else {
                dp[i] = dp[i+1];
            }

        }
        return dp[0];
        
        


    }




    public static void main(String[] args) {
        System.out.println(maxCounts(12258));
        System.out.println(dpWays(12258));
    }
}
