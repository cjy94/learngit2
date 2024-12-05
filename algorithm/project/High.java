package com.chenjunyi.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * 身高查排序
 */
public class High {
    public static void main(String[] args) {
        int[] arr = {95,96,97,98,99,101,102,103,104,105};
        compute(100, arr);
    }

    // h：小明身高
    // arr, 其他小朋友身高
    public static void compute(int h, int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int v : arr) {
            map.put(v, Math.abs(h - v));
        }

        // 将 Map 转换为 List
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList(map.entrySet());

        // 按值降序排序
        entryList.sort((entry1, entry2) -> {
            if (entry1.getValue() != entry2.getValue()) {
                return entry1.getValue() - entry2.getValue();
            } else {
                return entry1.getKey() - entry2.getKey();
            }
        });
        for (int i =0; i < entryList.size(); i++) {
            Map.Entry<Integer, Integer> integerIntegerEntry = entryList.get(i);
            System.out.print(integerIntegerEntry.getKey()+" ");
        }
        System.out.println();


    }
}
