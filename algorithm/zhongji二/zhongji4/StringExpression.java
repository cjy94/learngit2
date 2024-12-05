package com.chenjunyi.zhongji二.zhongji4;

/**
 *  将压缩后的字符串转变成为压缩的字符串
 */
public class StringExpression {

    public static class ReturnData{
        String res;
        int end;
        public ReturnData(String res, int end) {
            this.res = res;
            this.end = end;
        }
    }


    public static String decompress(String str) {
        char[] s = str.toCharArray();
        return process(s, 0).res;
    }

    private static ReturnData process(char[] s, int index) {
        int count = 0;
        StringBuilder res = new StringBuilder();

        while (index < s.length && s[index] != '}') {
            if (s[index] == '{') {
                // 递归调用解析{}中的字符串
                ReturnData data = process(s, index+1);
                index = data.end+1;
                res.append(getString(count,  data.res));
                count = 0;
            }  else {
                // 字符可能是：英文，数字
                if(Character.isDigit(s[index])) {
                    count = count * 10 + (s[index]-'0');
                }
                if (s[index] >= 'a' && s[index] <= 'z') {
                    res.append(String.valueOf(s[index]));
                }
                index++;
            }
        }
        return new ReturnData(res.toString(), index);
    }

    private static String getString(int count, String res) {
        StringBuilder str = new StringBuilder();
        for (int i =0; i < count; i++) {
            str.append(res);

        }
        return str.toString();
    }

    public static void main(String[] args) {
        String str = "aa2{cb7{k}}fc";       //aa cbkkkkkkk cbkkkkkkk fc
        str = "10{2{abc}}";
        String decompress = decompress(str);
        System.out.println(decompress);
    }
}
