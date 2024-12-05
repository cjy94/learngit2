package com.chenjunyi.project;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *  统计感染人数
 */
public class GanRanPerson {
    public static void main(String[] args) {
        System.out.println(count(45));
    }

    public static void compute(int num) {
        int count = count(num);
        for (int start = num+1;;start++) {
            if (count(start) == count) {
                System.out.println(start);
                break;
            }
        }
    }

    // 统计一个数字中二进制表示1的个数
    public static int count(int num) {
        int count = 0;
        while (num != 0) {
            if ((num & 1) == 1)
                count++ ;
            num = num >>> 1;
        }




        return count;
    }

    // N： 人数编号从0开始
    // infecter: 最初感染人编号
    // contactMatrix: 每个人人是否有接触
    private static int getResult(int N , Set<Integer> infecter, int[][] contactMatrix ) {
        int init = infecter.size();
       // Set<Integer> test = new HashSet<>(infecter);
        Queue<Integer> queue = new LinkedList<>(infecter);

        while (!queue.isEmpty()) {
            int person = queue.poll();
            for (int i =0; i < N; i++) {
                if (contactMatrix[person][i] == 1 && !infecter.contains(i)) {
                    infecter.add(i);
                    queue.add(i);
                }
            }
        }
        return infecter.size() - init;



    }
}
