package com.chenjunyi.force;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class JobTest {
    public static class Job{
        int money;
        int hard;
        public Job(int money, int hard) {
            this.hard = hard;
            this.money = money;
        }
    }


    public static class JobComparator implements Comparator<Job> {
        @Override
        public int compare(Job o1, Job o2) {
            // 对象按照难度由小到达， 难度相同， 
            return o1.hard != o2.hard ? (o1.hard - o2.hard) : (o2.money-o1.money);
        }
    }


    public static int[] getMoney(Job[] job, int[] ability) {
        Arrays.sort(job, new JobComparator());
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(job[0].hard, job[0].money);
        Job pre = job[0];
        // 保证有序表中的数据 是按照随着难度的增加，钱数也是递增的这么一个顺序
        for (int i =1; i < job.length; i++) {
            Job j = job[i];
            if (j.hard != pre.hard && j.money > pre.money) {
                pre = j;
                map.put(j.hard, j.money);
            }
        }
        int[] ans = new int[ability.length];
        for (int i =0; i < ability.length; i++) {
            Integer key = map.floorKey(ability[i]);
            ans[i] = key != null ? map.get(key) : 0;
        }
        return ans;
    }
}
