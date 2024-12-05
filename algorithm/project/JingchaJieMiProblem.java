package com.chenjunyi.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/*
 时间格式的正则表达式： (([01][0-9])|([2][0-3]))[0-5][0-9]
 */
public class JingchaJieMiProblem {
    public static void main(String[] args) {
        String result = getResult("20", "12");
        System.out.println(result);
    }

    public static String getResult(String hour, String minute) {
        HashSet<Character> set = new HashSet<>();
        for (char c : hour.toCharArray()) set.add(c);
        for (char c : minute.toCharArray()) set.add(c);
        Character[] arr = set.toArray(new Character[0]);
        ArrayList<String> res = new ArrayList<>();
        func(arr, new StringBuilder(), res);
        // 将时间排序
        int curTime = Integer.parseInt(hour) * 60 + Integer.parseInt(minute); // 转换成分钟
        res.sort(String::compareTo);
        int curIndex = res.indexOf(hour+minute);
        String ans ;
        if (curIndex == res.size()-1)
            ans = res.get(0);
        else
            ans = res.get(curIndex+1);
        //return ans;
        StringBuilder builder = new StringBuilder(ans);
        builder.insert(2,":");
        return builder.toString();
                



    }

    // 枚举str字符数组中，所有字符组合的情况
    public static void func(Character[] str, StringBuilder path, ArrayList<String> list) {
        if(path.length() == 4) {
            if (isValidTime(path.toString())) {
                list.add(path.toString());
            }
            return;

        }
        for (char ch : str) {
            path.append(ch);
            func(str, path, list);
            path.deleteCharAt(path.length()-1); // 恢复现场
        }
    }

    // 24小时制的正则匹配
    // 小时： 00-23
    // 分钟： 00-59
    public static boolean isValidTime(String timeStr) {
        return timeStr.matches("(([01][0-9])|([2][0-3]))[0-5][0-9]");
    }
}
