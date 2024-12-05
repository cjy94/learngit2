package com.chenjunyi.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *  众数 和 中位数
 *
 */
public class ZhongShuAndZhongWeiNum {

    public static void compute(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int v : arr) {
            if (map.get(v) == null) {
                map.put(v, 1);
            }  else {
                int oldCount = map.get(v);
                map.put(v, oldCount+1);
            }
        }
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        ArrayList<Map.Entry<Integer, Integer>> list = new ArrayList(entries);
        list.sort((o1, o2) -> {
            return o2.getValue() - o1.getValue();
        });


        int maxCount = list.get(0).getKey();
        ArrayList<Integer> l = new ArrayList();
        for (Map.Entry<Integer, Integer> m : list) {
            if (m.getValue() == maxCount) {
                for (int i =0; i < maxCount; i++) {
                    l.add(m.getKey());
                }
            }
        }

        l.sort((o1,o2) -> {
            return o1 - o2;
        });

        if((l.size() % 2) == 0)  // 偶数个
        {
            System.out.println(( l.get(l.size() / 2) + l.get(l.size() /2 -1)) /2);
            return;
        }    else {
            System.out.println(l.get(l.size() / 2));
            return;
        }



    }
}
