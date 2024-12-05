package com.chenjunyi.GO;

import java.util.Arrays;

/**
 *  一维动态规划
 *
 *  1、斐波那契数列  f(i) = f(i-1) + f(i-2)    时间复杂度2^n, 因为每个点都有2层展开
 *  2、最低票价
 *      旅行的日子将以一个名为days的数组给出
 *      每一项是一个从1到365的整数
 *      火车票有3中不同的销售方式
 *      一张  为期1天的通行证售价为costs[0]美元
 *      一张  为期7天的通行证售价为costs[1]美元
 *      一张  为期30天的通行证售价为costs[3]美元
 *      通行证允许数天无限制的旅行
 *      例如，如果我们在第2天获得一张为期7天的通行证
 *      那么我们可以连续连着旅行7天(第2~第8天)
 *      返回 想要完成在给定列表days中列出的每一天的旅行所需要的最低消费
 *
 *      f(days[], costs[], index)
 *
 *      枚举通行证， 如果选择为期x天的通行证，后序的可能性：  costs[x] + f(days, costs, index+x)
 *
 *  3、解码方法
 *      'A'->1 'B'->2 'C'->3 'D'->4 ... 'Z'->26  给一个数字字符串，计算出所有的解码方式
 *
 *  4、解码方法II
 *      在题目3的基础上，解码消息中可能包含'*'字符
 *      可以表示'1'到'9'的任意数字(不包含'0')
 *      例如：'1*'表示11、12、13、14、15、16、17、18、19
 *      给你一个字符串s，由数字和'*'字符组成，返回解码该字符串的方法数目
 *
 *     'x*': x1 x2 x3 x4 x5 x6 x7 x8 x9      9种
 *     '*': 1 2 3 4 5 6 7 8 9                9种
 *
 *     可能性：
 *      1) s[i] == '0'  无法转换
 *      2) s[i]自己作为单独字符进行转换，
 *          s[i] != '0' && s[i] != '*'  1种  后序讨论
 *          s[i] != '0' && s[i] == '*'  9种情况  后序讨论
 *
 *      3) s[i] 和 i+1 共同转换
 *          s[i] != '*' && s[i+1] != '*'   和题目3一样
 *          s[i] != '*' && s[i+1] == '*'   s[i]数字是1， 9种； s[i]数字是2，6种； s[i] 数字是3~9  0种
 *          s[i] == '*' && s[i+1] != '*'   s[i+1]数字是1-6  2种； s[i+1]数字>6  1种
 *          s[i] == '*' && s[i+1] == '*'   '*'不能转换成'0'   11-19 和 21-26  15种
 *
 *  5、丑数
 *   只包含质因数2、3、5的正整数 ， 给你一个整数n，请返回第n个丑数， 默认1是第一个丑数
 *
 *   尝试的方式
 *
 *
 *  6、最长的有效括号串
 *   子串一定以i位置结尾，能向左侧延伸多远是有效括号串
 *   dp[i]   可能是 dp[i-1]
 *   s[i] == '('   dp[i]=0
 *   s[i-1] == ')'  根据dp[i-1]的长度往前跳到匹配的位置p，如果s[p] == ')' , dp[i]==0; 否则dp[i] = 2 + dp[i-1] + dp[p-1]
 *          
 *    ( ( ) ( ) ) ( ) ) (  ) (  )  (  )  (  (   )  )
 *    0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17  18
 *   i=0 左括号  dp[0]=0
 *   i=1 左括号 dp[1]=0
 *   i=2 右括号 dp[2]=要看dp[2-1]的信息， 2+dp[1]=2
 *   i=3 左括号 dp[3]=0
 *   ...
 *
 *  7、环绕字符串中唯一的子字符串
 *  定义字符串base为一个"abcdefghijklimn...xyz" 无限环绕的字符串，所以base看起来是这样的： "...zabcdefghijklimn...xyzabcdefghijklnm.."
 *  给你 一个字符串s，请统计并返回s中有多少不同子串也在base中出现
 *
 *
 *  8、不同的子序列
 *  给定一个字符串s，计算s中不同非空子序列的个数,  最后要减掉一个空集 
 *  例如:
 *      abc -->  a b c ab ac bc abc
 *      aba -->  a b ab ba aba aa
 *
 *
 */

public class DynamicProgramming {
    // 4 解码方式II
    public static int numDecoding2(String s) {
        if(s == null || s.length() == 0)
            return 0;

        return numDecoding2(s.toCharArray(), 0);
    }

    public static int numDecoding2(char[] ch, int index) {
        if (index == ch.length)
            return 1;

        if (ch[index] == '0')
            return 0;

        int ans = numDecoding2(ch, index+1) * (ch[index] != '*' ? 1 : 9);
        if (index +1 < ch.length) {
            if (ch[index] != '*') {
                if (ch[index+1] != '*') {
                    if ((ch[index] - '0') * 10 + (ch[index+1] - '0') <= 26)
                        ans += numDecoding2(ch, index+2);
                } else {
                    if (ch[index] == '1') {
                        ans += 9 * numDecoding2(ch, index+2);
                    } else if (ch[index] == '2') {
                        ans += 6 * numDecoding2(ch, index+2);
                    }
                }

            } else {
                if (ch[index+1] != '*') {
                    if (ch[index] <= '6') {
                        ans += 2 * numDecoding2(ch, index+2);
                    } else {
                        ans += numDecoding2(ch, index+2);
                    }

                } else {
                    ans += 15 * numDecoding2(ch, index+2);

                }
            }
        }
        return ans;
    }

    //3 解码方式
    public static int numDecoding(String s) {
        if (s == null || s.length() == 0)
            return 0;
        // 带缓存的方式
        int n = s.length();
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return numDecoding(s.toCharArray(), 0, dp);
    }

    public static int numDecoding(char[] ch, int index, int[] dp) {
        if (dp[index] != -1)
            return dp[index];
        // 如果来到了字符串的末尾，说明是一种有效的解码方式
        if (index == ch.length) {
            return 1;
        }

        int ans = 0;
        if (ch[index] == '0')  // 0不能最为开头，如果当前字符是0那么是一种无效的解码方式
            ans = 0;
        else {
            ans = numDecoding(ch, index+1, dp);
            if (index +1 < ch.length && (ch[index] - '0') * 10 + (ch[index+1]-'0') <= 26) {
                ans += numDecoding(ch, index+2, dp);
            }
        }
        dp[index] = ans;
        return ans;
    }

    // 使用动态规划的方式
    public static int numDecodingDP(String s) {
        if (s == null || s.length() == 0)
            return 0;
        char[] ch = s.toCharArray();
        int n = s.length();
        int[] dp = new int[n+1];
        dp[n] = 1;
        for (int index = n-1; index >= 0; index--) {
            if (ch[index] == '0')  // 0不能最为开头，如果当前字符是0那么是一种无效的解码方式
                dp[index] = 0;
            else {
                dp[index] = dp[index+1];
                if (index +1 < ch.length && (ch[index] - '0') * 10 + (ch[index+1]-'0') <= 26) {
                    dp[index] += dp[index+2];
                }
            }
        }
        return dp[0];
    }



}
