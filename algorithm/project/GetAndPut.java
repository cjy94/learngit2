package com.chenjunyi.project;


import java.util.LinkedList;

/**
 *  幼儿园里有一个放倒的圆桶，它是一个线性结构，允许在桶的右边将篮球放入，
 *  可以在桶的左边和右边将篮球取出。每个篮球有单独的编号，老师可以连续放入
 *  一个或多个篮球，小朋友可以在桶左边或右边将篮球取出，当桶里只有一个篮球
 *  的情况下，必须从左边取出
 *
 *  使用队列
 */
public class GetAndPut {
    public static void main(String[] args) {
        int[] put = {1,2,3,4};
        int[] get = {1,2,3,5};
        compute(put, get);
    }

    public static void compute(int[] put, int[] get) {
        StringBuilder str = new StringBuilder();
        LinkedList<Integer> list = new LinkedList() ;
        int pIndex = 0;
        int pLen = put.length;
        int gIndex = 0;
        int gLen = get.length;

        while (pIndex < pLen) {
            while (!list.isEmpty() && (list.getFirst() == get[gIndex] || list.getLast() == get[gIndex])) {
                 String info = list.getLast() == get[gIndex++] ? "R" : "L";
                 if ("R".equals(info))
                     list.pollLast();
                 else
                     list.pollFirst();
                 str.append(info);
            }
            list.addLast(put[pIndex++]);
        }

        // 数据全部放入到队列中后，队列中还有值继续检查
        while (!list.isEmpty() || gIndex < gLen) {
            if (!list.isEmpty()) {
                if (get[gIndex] == list.getLast() || get[gIndex] == list.getFirst()) {
                    String info = list.getFirst() == get[gIndex++] ? "L" : "R";
                    if ("R".equals(info))
                        list.pollLast();
                    else
                        list.pollFirst();
                    str.append(info);
                } else {
                    System.out.println("NO ");
                    return;
                }
            } else {  // 队列中
                System.out.println("No");
                return;

            }
        }
        System.out.println(str.toString());
        
    }
}
