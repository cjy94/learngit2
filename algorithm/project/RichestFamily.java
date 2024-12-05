package com.chenjunyi.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 * 最富裕家庭
 */
public class RichestFamily {

    public static void rich() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int count = scanner.nextInt();
            HashMap<Integer, Integer> money = new HashMap<>();
            for (int i = 0; i<count; i++) {
                money.put(i+1,scanner.nextInt());
            }

            // 构建家庭关系
            HashMap<Integer, List<Integer>> map = new HashMap<>();
            for (int i =0; i < count; i++) {
                int key = scanner.nextInt();
                if (map.containsKey(key)) {
                    List<Integer> list = map.get(key);
                    list.add(scanner.nextInt());
                } else {
                    List<Integer> list = new ArrayList<>();
                    int value = scanner.nextInt();
                    list.add(value);
                    list.add(key);
                    map.put(key, list);
                }
            }

            int max = 0;
            for (List<Integer> members : map.values()) {
                int allMoney = 0;
                for (Integer member : members) {
                    allMoney += money.get(member);
                }
                max = Math.max(max, allMoney);
            }
            System.out.println(max);
        }

    }
}
