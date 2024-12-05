package com.chenjunyi.zhongji二.zhongji3;

import com.chenjunyi.UnionSet.UnionFind;

import java.util.HashMap;
import java.util.Stack;

/**
 * 并查集的使用
 * 给定一个不包含1的正数数组arr，假设其中任意两个数为a和b，如果a和b的最大公约数比1大， 那么认为a和b之间有路相连；
 * 如果a和b的最大公约数是1， 认为a和b之间没有路相连
 * 那么arr中所有数组就可以组成一张图
 *
 *
 * 并查集的3个主要方法：
 * union(): 将多个集合合并
 * isSameSet(): 判断两个对象是否属于同一个集合
 * findHead(): 查找某个元素的父节点
 */
public class UnionSet {

    public static int largestComponentSize(int[] arr) {
        UnionFindSet1 set = new UnionFindSet1(arr);
        for (int i =0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (gcd(arr[i], arr[j]) != 1) {
                    set.union(arr[i], arr[j]);
                }
            }
        }
        return set.maxSize();
    }


    // 求两个数的最大公约数
    // 欧几里得的辗转相除法
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static class UnionFindSet1{
        // key： value的父节点
        public HashMap<Integer, Integer> fatherMap;

        // key: 集合中的代表点， value: 该集合中的元素个数
        public HashMap<Integer, Integer> sizeMap;

        public UnionFindSet1(int[] arr) {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (int value : arr) {
                fatherMap.put(value, value);
                sizeMap.put(value, 1);
            }
        }

        public int findHead(int element) {
            Stack<Integer> path = new Stack<>();
            while (element != fatherMap.get(element)) {
                path.push(element);
                element = fatherMap.get(element);
            }

            while (!path.isEmpty()) {
                fatherMap.put(path.pop(), element);
            }
            return element;
        }

        public int size() {
            return sizeMap.size();
        }

        public int maxSize() {
           int ans = 0;
           for (int size : sizeMap.values()) {
               ans = Math.max(ans, size);
           }
           return ans;
        }

//        public boolean isSameSet(int a, int b) {
//
//        }

        public void union(int a, int b) {
           int aF = findHead(a);
           int bF = findHead(b);
           if (aF != bF) {
               int aSize = sizeMap.get(aF);
               int bSize = sizeMap.get(bF);
               int big = aSize > bSize ? aSize : bSize;
               int small = big == aSize ? bSize : aSize;
               fatherMap.put(small, big);
               sizeMap.put(big, aSize+bSize);
               sizeMap.remove(small);
           }
        }



        



    }
    


}
