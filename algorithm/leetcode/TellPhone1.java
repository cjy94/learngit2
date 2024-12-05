package com.chenjunyi.leetcode;
import java.util.ArrayList;
import java.util.List;
public class TellPhone1 {
    public static List<String> letterCombinations(String digits) {
        if (digits == null | digits.length() == 0) {
            return new ArrayList();
        }
        String[] array = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        int index = 0;
        List<String> output = new ArrayList<>();
        output.add("");
        for (; index < digits.length(); index++) {
            int ch = digits.charAt(index)- '0';
            String phone = array[ch];
            ArrayList<String> tmp = new ArrayList<>();
            for (char p : phone.toCharArray()) {
                for (String candidate: output) {
                    tmp.add(candidate + p);
                }
            }
            //output.clear();
            output = tmp;
        }
     return output;
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations(""));

    }

}
