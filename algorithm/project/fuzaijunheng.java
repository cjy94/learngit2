package com.chenjunyi.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  负载均衡
 *  arr: [1 2 3 4 5 6 7 8 9]   jump：4  save:3
 *  start:每次起跳的起始位置+1  lenght:数组的长度
 *
 *
 */
public class fuzaijunheng {
    public static void main(String[] args) {
        String[] s = {"/huawei/computing/no/one",
                "/huawei/computing",
                "/huawei",
                "/huawei/cloud/no/one",
                "/huawei/wireless/no/one"};

        List<Map<String, Integer>> res = processPaths(s);
        int level = 4;
        String search = "computing";
        if (res.size() > level) {
            Map<String, Integer> map = res.get(level);
            if (map.containsKey(search)) {
                System.out.println(map.get(search));
            } else {
                System.out.println(0);
            }

        }

    }

    static List<Map<String, Integer>> processPaths(String[] strs) {
        List<Map<String, Integer>> list = new ArrayList<>();
        for (int i =0; i < strs.length; i++) {
            String[] split = strs[i].split("/");
            int j = 0;
            while (j < split.length) {
                String segment = split[j];
                if (list.size() <= j) {
                    HashMap<String, Integer> map = new HashMap<>();
                    map.put(segment, 1);
                    list.add(map);
                } else {
                    Map<String, Integer> map = list.get(j);
                    if (map.containsKey(segment)) {
                        map.put(segment, map.get(segment) + 1);
                    } else {
                        map.put(segment, 1);
                    }
                }
                j++;
            }
        }
        return list;
    }
}
