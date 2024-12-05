package com.chenjunyi.force;

/***
 * 给定一个只有0、1、&、|、^ 无重字符串组成的字符串express，再给定一个boolean  desire，返回express能有多少种组合方式，可以达到desire的结果
 * 举例：
 * express="1^0|0|1" desire=false， 有2种方式
 *
 *
 * 明确：
 *  二位运算符一定出现在索引值是奇数的位置上，数值（0，1）一定出现在偶数的位置上，在这样的前提条件下进行递归操作
 */
public class RangeTryModel2 {

    public static int method(String str, boolean desire) {
        if (isValid(str) == false) {
            return 0;
        }


        char[] s = str.toCharArray();
        return process(s, desire, 0, s.length-1);
    }

    // 在[l...r] 范围上，位操作可以组合成desire的方法数
    // 潜台词: l 和 r位置一定不会压中逻辑运算符
    private static int process(char[] s, boolean desire, int l, int r) {
        // 只有一个字符
        if (l == r) {
            if (s[l] == '1') {
                return desire ? 1 : 0;
            } else {
                return desire ? 0 : 1;
            }
        }
        // 多个字符的情况
        int ans = 0;
        if (desire) {
            for (int i = l+1; i < r; i += 2) {
                switch (s[i]) {
                    case '&':
                        //  逻辑运算符是 & 那么左右两边的组合必须都是true，
                        ans += process(s, true, l, i-1) * process(s, true, i+1, r);
                        break;
                    case  '|':
                        ans += process(s, true, l, i-1) * process(s, false, i+1, r);
                        ans += process(s, false, l, i-1) * process(s, true, i+1, r);
                        ans += process(s, true, l, i-1) * process(s, true, i+1, r);
                        break;
                    case '^':
                        ans += process(s, true, l, i-1) * process(s, false, i+1, r);
                        ans += process(s, false, l, i-1) * process(s, true, i+1, r);
                        break;
                    default:
                        break;
                }
            }
        } else {
            for (int i = l+1; i < r; i += 2) {
                switch (s[i]) {
                    case '&':
                        //  逻辑运算符是 & 那么左右两边的组合必须都是true，
                        ans += process(s, false, l, i-1) * process(s, false, i+1, r);
                        ans += process(s, true, l, i-1) * process(s, false, i+1, r);
                        ans += process(s, false, l, i-1) * process(s, true, i+1, r);
                        break;
                    case  '|':
                        ans += process(s, false, l, i-1) * process(s, false, i+1, r);
                        break;
                    case '^':
                        ans += process(s, true, l, i-1) * process(s, true, i+1, r);
                        ans += process(s, false, l, i-1) * process(s, false, i+1, r);
                        break;
                    default:
                        break;
                }
            }
        }
        return ans;
    }

    private static int dpWays(String str, boolean desire) {
        char[] s = str.toCharArray();
        int n = s.length;
        // true 表
        int[][] tMap = new int[n][n];
        // false 表
        int[][] fMap = new int[n][n];
        // 填对角线
        for (int i =0; i < n ; i++) {
            tMap[i][i] = s[i] == '1' ? 1 : 0;
            fMap[i][i] = s[i] == '0' ? 1 : 0;
        }

        // 从下往上，从左往右填满dp表
        for (int row = n - 3; row >= 0; row -= 2) {
            for (int col = row + 2; col < n; col += 2) {

                // i 代表运算符的索引下标
                for (int i = row+1; i < col; i += 2) {
                    switch (s[i]) {
                        case '&':
                            //  逻辑运算符是 & 那么左右两边的组合必须都是true，
                            tMap[row][col] += tMap[row][i-1] * tMap[i+1][col];
                            break;
                        case  '|':
                            tMap[row][col] += tMap[row][i-1] * fMap[i+1][col];
                            tMap[row][col] += fMap[row][i-1] * tMap[i+1][col];
                            tMap[row][col] += tMap[row][i-1] * tMap[i+1] [col];
                            break;
                        case '^':
                            tMap[row][col] += fMap[row][i-1] * tMap[i+1][col];
                            tMap[row][col] += tMap[row][i-1] * fMap[i+1][col];
                            break;
                        default:
                            break;
                    }

                    switch (s[i]) {
                        case '&':
                            //  逻辑运算符是 & 那么左右两边的组合必须都是true，
                            fMap[row][col] += tMap[row][i-1] * fMap[i+1][col];
                            fMap[row][col] += fMap[row][i-1] * tMap[i+1][col];
                            fMap[row][col] += fMap[row][i-1] * fMap[i+1][col];
                            break;
                        case  '|':
                            fMap[row][col] += fMap[row][i-1] * fMap[i+1][col];
                            break;
                        case '^':
                            fMap[row][col] += fMap[row][i-1] * fMap[i+1][col];
                            fMap[row][col] += tMap[row][i-1] * tMap[i+1][col];
                            break;
                        default:
                            break;
                    }
                }

            }

        }
        return desire ? tMap[0][n-1] : fMap[0][n-1];

    }

    // 检查传入的字符串是否可以进行位运算操作
    private static boolean isValid(String str) {
        // 并且字符串的长度必须是奇数才是满足要求的字符串
        if (str == null || str.length() == 0 || (str.length() & 1) == 0) {
            return false;
        }

        // 长度满足要求的前提下，进行字符校验
        for (int i =0; i < str.length(); i += 2) {
            if (str.charAt(i) != '1' && str.charAt(i) != '0') {
                return false;
            }
        }

        for (int i =1; i< str.length(); i += 2) {
            if (str.charAt(i) != '&' && str.charAt(i) != '|' && str.charAt(i) != '^') {
                return false;
            }
        }
        return true;

    }

    public static void main(String[] args) {
        System.out.println(dpWays("1&0^1", true));
        System.out.println(method("1&0^1", true));

    }

    
}
