package com.chenjunyi.project;

import com.chenjunyi.JVM.StringStudy;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 *  数组去重和排序
 *  输入：1 3 3 3 2 4 4 4 5
 *  输出： 3 4 1 2 5
 *
 */
public class ArraySort {

    public static void main(String[] args) {
//        int[] arr = {1,3,3,3,2,4,4,4,5};
//        compute(arr);
        LinkedHashMap<String, String> linked = new LinkedHashMap<String, String>(16, 0.75f, true);
        linked.put("rerefd","wq");
        linked.put("fr","rew");
        linked.put("bh","jug");
        linked.put("aw","okj");
        linked.put("juhyhy","nhy");
        linked.put("nhgffcv","wq");
        linked.put("sddfs","wq");
        linked.keySet().forEach(System.out::println);

    }

    public static void compute(int[] arr) {
        // LinkedHashMap保证数据的插入顺序和输出顺序是一致的
        // 这样就满足了 相同出现次数按照第一次出现顺序进行先后排序
        LinkedHashMap<Integer, Integer> cntMap = new LinkedHashMap<>();
        for (int a : arr) {
            cntMap.put(a, cntMap.getOrDefault(a, 0) + 1);
        }
        LinkedList<Map.Entry<Integer, Integer>> res = new LinkedList<>(cntMap.entrySet());
        res.sort((o1, o2) -> o2.getValue() - o1.getValue());
        int n = res.size();
        StringBuilder str = new StringBuilder();
        for (int i =0; i < n; i++) {
            Map.Entry<Integer, Integer> entry = res.get(i);
            if (i == n-1) {
                str.append(entry.getKey());
            } else {
                str.append(entry.getKey()+",");
            }
        }
        System.out.println(str.toString());


    }

    // 稳定排序： 插入排序
    public static void insertSort(int[] arr) {
        for (int i =1; i < arr.length; i++) {
            int index = i;
            int temp = arr[index];
            while (index > 0 && temp > arr[index-1]) {
                arr[index] = arr[index-1];
                index--;
            }
            arr[index] = temp;  // 插入要插入的位置
        }
    }
}
