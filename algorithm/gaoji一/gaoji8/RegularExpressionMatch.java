package com.chenjunyi.gaoji一.gaoji8;

/**
 * 正则表达式匹配
 *   ?可以独立使用
 *   *不允许独立使用 *需要和前面的字符配合使用
 *
 *
 */
public class RegularExpressionMatch {

    public static boolean isMatch(String str, String expr) {
        char[] s = str.toCharArray();
        char[] e = expr.toCharArray();
        // 字符串的有效性检查
        return isValid(s, e) && process(s, e, 0, 0);

    }

    // 验证字符串是否符合标准
    private static boolean isValid(char[] str, char[] expr) {
        return true;
    }

    // str[si....] 能否被 expr[ei....] 匹配出来
    // 必须保证expr[ei]压中的不是*, 因为*要和前面的字符匹配使用
    private static boolean process(char[] str, char[] expr, int si, int ei) {
        if (ei == expr.length) {
            return si == str.length;
        }

        // 情况一： expr[ei+1] 位置的字符不是*
        if (ei+1 == expr.length || expr[ei] != '*') {
            return si != str.length && (str[si] == expr[ei] || expr[ei] == '.')
                    && process(str, expr, si+1, ei +1);
        }

        // 情况二： expr[ei+1] 位置的字符 *
        while (si != str.length && (str[si] == expr[ei] || expr[ei] == '.')) {
            if (process(str, expr, si, ei+2)) {
                return true;
            }
            si++;

        }
        return process(str, expr, si, ei+2);
    }

    public static void main(String[] args) {

        

    }
}
