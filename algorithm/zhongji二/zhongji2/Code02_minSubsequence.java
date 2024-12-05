package com.chenjunyi.zhongji二.zhongji2;

import java.util.Iterator;
import java.util.concurrent.*;

/**
 * 给定一个无需数组arr, 如果只能对一个子数组进行排序，但是想让数组整体都有序，求需要排序的最短子数组长度
 *
 * 例如： arr=[1, 5, 3, 4, 2, 6] 返回4， 因为只有[5, 3, 4, 2]需要排序
 */
public class Code02_minSubsequence {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        // set底层使用的就是list ， 但是不允许有重复值
        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<>();
        list.add("11");
        list.add("12");
        list.add("13");
        Iterator<String> iterator = list.iterator();
        list.add("14");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


    }

}
