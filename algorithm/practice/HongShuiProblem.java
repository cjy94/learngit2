package com.chenjunyi.practice;

import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 *  leetcode1448: 避免洪水泛滥问题
 *
 *  有n个湖泊，所有湖泊一开始都是空的，当第n个湖泊下雨的时候，如果第n个湖泊是空的，那么它就会装满水;
 *  否则这个湖泊会发生洪水，你的目标是避免任意一个湖泊发生宏硕
 *
 *  rains[i] > 0 表示第i天时， rains[i]个湖泊会下雨
 *  rains[i] == 0 表示第i天没有湖泊会下雨，可以选择一个湖泊并抽干这个湖泊的水
 *
 *  请返回一个ans数组，满足：
 *  ans.length == rains.length
 *  如果rains[i] > 0, 那么rains[i] == -1;
 *  如果rains[i] == 0, ans[i] 是你第i天选择抽干的湖泊
 *
 *
 *  rains=[1,2,0,0,2,1]
 *
 *  第0天： 1号湖泊下雨                                 [1]
 *  第1天： 2号湖泊下雨                                 [1,2]
 *  第2天： 没有湖泊下雨，可以抽干某个湖泊， 抽干湖泊2     [1]
 *  第3天： 没有湖泊下雨，可以抽干某个湖泊， 抽干湖泊1     []
 *  第4天： 2号湖泊下雨，如果2号湖泊之前没有抽干，是满的，那么就会发生洪水；是空的，不会发生洪水    [2]
 *  第5天： 1号湖泊下雨，如果1号湖泊之前没有抽干，是满的，那么就会发生洪水                        [1,2]
 *
 *  干活的ans[-1, -1, 2, 1, -1, -1]
 *
 *
 */
public class HongShuiProblem {

    class Node1 implements Comparable<Node1> {

        public int lake;
        public int nextRains;
        public Node1(int lake, int nextRains) {
            this.lake = lake;
            this.nextRains = nextRains;
        }
        @Override
        public int compareTo(Node1 o) {
            return nextRains - o.nextRains;
        }
    }

    class Node {

        public int lake;
        public int nextRains;
        public Node(int lake, int nextRains) {
            this.lake = lake;
            this.nextRains = nextRains;
        }
    }

    class NodeComparator implements Comparator<Node>{

        @Override
        public int compare(Node o1, Node o2) {
            return o1.nextRains - o2.nextRains;
        }
    }

    // rains={1, 2, 0, 2, 3, 0, 1, 0, 3}
    //        0  1  2  3  4  5  6  7  8
    public int[] avoidFlood(int[] rains) {
        // 存储的是，湖泊和哪天下雨的对应关系，比如：
        // 1: {0,6,8,10}   1号湖泊在第0天，第6天，第8天，第10天下雨
        // 2:{1,3}  3:{4,8}
        HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();
        for (int i =0; i < rains.length; i++) {
            if (rains[i] != 0) {
                if (!map.containsKey(rains[i]))
                    map.put(rains[i], new LinkedList<>());
            }
            map.get(rains[i]).addLast(i);
        }

        int N = rains.length;
        int[] ans = new int[N];
        int[] invalid = new int[0];   // 用于无效的返回信息，比如： rains={1,2,3,2} 没有一天不下雨，在最后一天发生了洪水，返回invalid
        // 存储Node信息，并按照Node.nextRains排序的小根堆，即下一次最早下雨的湖泊是哪一个，也就是如果要抽干的话，抽干小根堆堆顶对应的湖泊
        PriorityQueue<Node> queue = new PriorityQueue<>(new NodeComparator());
        // 有雨的湖泊，用于判断是否发生洪水
        HashSet<Integer> set = new HashSet<>();
        for (int i =0; i < rains.length; i++) {
            if (rains[i] != 0) {  // i湖泊要下雨，2个结果：要么洪水（如果之前i号湖泊已经有雨了，发生洪水），要么正常
                if (set.contains(i)) {  // 发生洪水，返回无效结果，invalid
                    return invalid;
                }
                // 没有发生洪水
                set.add(rains[i]);
                map.get(rains[i]).pollFirst();
                if (!map.get(rains[i]).isEmpty()) {
                    queue.add(new Node(rains[i], map.get(rains[i]).peekFirst()));
                }
                ans[i] = -1;
            } else {  // rains[i]==0, 不下雨，干活，选择一个湖泊抽干
                if (queue.isEmpty()) {
                    ans[i] = 1;
                } else {
                    Node node = queue.poll();
                    set.remove(node.lake);
                    ans[i] = node.lake;
                }
            }
        }
        return ans;
    }



    public static void main(String[] args) {


    }

}
