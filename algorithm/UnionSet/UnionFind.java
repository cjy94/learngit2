package com.chenjunyi.UnionSet;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 *并查集： 1964发明 - 1989年才证明完毕
 *
 *
 *  该结构的每个方法调用的时间复杂度都是O(1)
 *
 *
 */
public class UnionFind {
    public static class Element<V> {
        V value;
        Element(V v) {
            this.value = v;
        }
    }

    public static class UnionFindSet<V> {
        public HashMap<V, Element<V>> elementMap;
        public HashMap<Element<V>, Element<V>> fatherMap;
        public HashMap<Element<V>, Integer> sizeMap;


        // 初始化的时候，每个元素就是独立的一个集合
        public UnionFindSet(List<V> list) {
            //
            elementMap = new HashMap<>();
            // key 是当前节点， value是代表节点
            fatherMap = new HashMap<>();
            // 该map表示每一个代表节点下的节点个数，所以只有代表节点才会出现在该map中
            sizeMap = new HashMap<>();

            // 初始化并查集中的 3个map
            for (V value : list) {
                Element<V> element = new Element<>(value);
                elementMap.put(value, element);
                fatherMap.put(element, element);
                sizeMap.put(element, 1);
            }
        }

        // value A 和 value B 是否在同一个集合中
        public boolean isSameSet(V a, V b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                return findHead(elementMap.get(a)) == findHead(elementMap.get(b));
            }
            return false;
        }

        // a 和 b 都是代表节点
        // 所以fatherMap中一定存在节点 a和 b
        public void union(V a, V b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                Element<V> ah = findHead(elementMap.get(a));
                Element<V> bh = findHead(elementMap.get(b));
                // 不是一个集合，进行合并
                if (ah != bh) {
                    Element<V> big = sizeMap.get(ah) > sizeMap.get(bh) ? ah : bh;
                    Element<V> small = ah == big ? bh : ah;
                    // small的父节点是big
                    fatherMap.put(small, big);
                    // 将代表节点big 放入到sizeMap中
                    sizeMap.put(big, sizeMap.get(ah) + sizeMap.get(bh));
                    sizeMap.remove(small);
                }
            }
        }
        // 给定一个ele节点，找到ele的代表节点
        // 一直在fatherMap中找ele的父节点，直到ele.father=ele停止
        //  调用次数越多， 后面每次调用的平均时间复杂度越低
        private Element<V> findHead(Element<V> ele) {
            Stack<Element<V>> path = new Stack<>();
            while (ele != fatherMap.get(ele)) {
                path.push(ele);
                ele = fatherMap.get(ele);
            }

            while (!path.isEmpty()) {
                fatherMap.put(path.pop(), ele);
            }
            return ele;
        }
    }
}
