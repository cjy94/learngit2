package com.chenjunyi.project;

/**
 *  替换字符，匹配字符串
 */
public class StringMatch {

    public static void compute(String[] words, String chars) {
        int matchCounts = 0;
        int cal = 0;
        for (int i =0; i < chars.length(); i++) {
            if (chars.charAt(i) == '?')
                cal++;
        }

        for (String word : words) {
            int curReplace = cal;
            String rep = chars;
            boolean isMatcher = false;
            for (int i =0; i < word.length(); i++) {
                String cur = String.valueOf(word.charAt(i));
                if (rep.contains(cur)) {
                    rep.replaceFirst(cur,"");
                } else {
                    if (curReplace > 0) {
                        curReplace--;
                    } else {
                        isMatcher = false;
                        break;
                    }
                }
            }
            if (isMatcher)
                matchCounts++;
        }
        System.out.println(matchCounts);

    }

}
