package com.chenjunyi.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *  连续自然数之和
 */
public class LianXuNumSum {
    public static void main(String[] args) {
        numSum(45);
    }

    public static void numSum(int num) {
        ArrayList<String> list = new ArrayList<>();
        for (int i =1; i <= num; i++) {
            int sum = 0;
            StringBuilder builder = new StringBuilder();
            boolean found = false;
            for (int j = i; j <= num && !found; j++) {
                sum += j;
                builder.append(j).append("+");
                if (sum == num) {
                    found = true;
                    list.add(num + "=" + builder.substring(0, builder.length()-1));
                }
            }
        }
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() != o2.length())
                    return o1.length() - o2.length();
                else
                    return o1.compareTo(o2);
            }
        });
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println("Result: " +list.size());
    }
}
