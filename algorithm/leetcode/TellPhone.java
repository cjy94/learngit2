package com.chenjunyi.leetcode;

import java.util.ArrayList;
import java.util.List;

public class TellPhone {
    public List<String> letterCombinations(String digits) {
        if(digits.isEmpty()){
            return new ArrayList();
        }
        String[] pad = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> output = new ArrayList<>();
        output.add("");
        char[] arrays = digits.toCharArray();
        for(char key : arrays){    // 3^digits.length()
            List<String> temp = new ArrayList<>();
            for(char candidate : pad[key-'0'].toCharArray()){
                for(String c : output){
                    temp.add(c + candidate);
                }
            }
            output.clear();
            output = temp;
        }
        return output;


    }


    public List<String> letterCombinations1(String digits) {
        if(digits.isEmpty()){
            return new ArrayList();
        }
        String[] pad = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> output = new ArrayList<>();
        StringBuilder temp = new StringBuilder();

        process(digits, pad, output, temp, 0);
        return output;
    }

    /**
     * digits和pad 使不变的量，
     * output,保存最终的返回结果，即可以组装成的字符串
     * temp,保存中间结果，
     * i, 遍历digits字符串的索引
     */
    private void process(String digits, String[] pad, List<String> output, StringBuilder temp, int i) {
        // base case
        // 当到达字符串的尾部时，说明已经有了组装结果，将其放入到output集合中
        if (i == digits.length()) {
            output.add(temp.toString());
            return;
        }

        // 取出digits中每个数字代表的字符数组，
        String value = pad[digits.charAt(i)-'0'];

        for (int j =0; j < value.length(); j++) {
            temp.append(value.charAt(j));
            process(digits, pad, output, temp, i+1);
            temp.deleteCharAt(temp.length()-1);
        }
    }


    public static void main(String[] args) {
        TellPhone test = new TellPhone();
        System.out.println(test.letterCombinations1("34").size());
    }
}
