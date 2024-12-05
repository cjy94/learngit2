package com.chenjunyi.SortedMap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * 有序表， 底层使用的红黑树
 *
 * 不允许插入的key为null
 *  // 天花板，上限
 *  E ceilingKey(K key) 返回大于等于key的最小值，如果不存在，则返回null
 *  boolean containsKey(K key) 是否包含指定的key
 *  boolean containsValue(Object value) 是否包含指定的value
 *  descendingMap() 返回逆序视图
 *  // 地板，下限
 *  E floorKey(K key) 返回小于等于key的最大值，如果不存在，则返回null
 *
 *
 *
 *
 *
 */
public class TestTreeMap {

    static class Node{
        int value;
        Node left;
        Node right;
        Node parent;
        public Node(int value) {
            this.value = value;
        }

    }

    /**
     *  左旋和右旋， 主要的流程就是修改3个节点之间的指向关系
     *   leftRotate
     *
     *        X                         X
     *       /                         /
     *      p                         pr
     *     / \            左旋        / \
     *    pl  pr         ------>    p   rr
     *        / \                  / \
     *       rl  rr               pl rl
     *
     *  其中变化的节点指向有：
     *      1、 pr-rl   --->  p-pl
     *      2、 X-p     --->  X-pr
     *      3、 p-pr    --->  pr-p
     *
     */

    public static void leftRotate(Node p) {
        // 1、处理pr-rl  p-pl的关系
        Node pr = p.right;
        p.right = pr.left;
        if (pr.left != null) {
            pr.left.parent = p;
        }

        // 2、处理X-p  X-pr的关系
        pr.parent = p.parent;
        if (p.parent == null) {
            // 根节点设置成pr
        } else if(p == p.parent.left) {
            p.parent.left = pr;
        } else {
            p.parent.right = pr;
        }

        // 3、处理p-pr  pr-p的关系
        pr.left = p;
        p.parent = pr;
    }

    // 右旋， 和左旋完全相反的操作，将left换成right 将right换成left
    public static void rightRotate(Node p) {
        // 1、处理pr-rl  p-pl的关系
        Node pr = p.left;
        p.left = pr.right;
        if (pr.right != null) {
            pr.right.parent = p;
        }

        // 2、处理X-p  X-pr的关系
        pr.parent = p.parent;
        if (p.parent == null) {
            // 根节点设置成pr
        } else if(p == p.parent.left) {
            p.parent.left = pr;
        } else {
            p.parent.right = pr;
        }

        // 3、处理p-pr  pr-p的关系
        pr.right = p;
        p.parent = pr;
    }



    public static void main(String[] args) {
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("b",2);
        treeMap.put("ac",4);
        treeMap.put("f",12);
        treeMap.put("hy",6);
        treeMap.put("n",5);
        treeMap.put("njh",8);
        System.out.println("treeMap: " + treeMap);    // 组织的数据是按照插入顺序

        // lowerKey <key的最大值
        System.out.println(treeMap.floorKey("d"));  // <=5 的最大值，4
        // ceilingKey  >= key的最小值
        // higherKey   > key的最小值
        System.out.println(treeMap.ceilingKey("a")); // >=5 的最小是 6
//
//        NavigableMap<Integer, Integer> integerIntegerNavigableMap = map.descendingMap();
//        System.out.println(integerIntegerNavigableMap);
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("b",2);
        linkedHashMap.put("ac",4);
        linkedHashMap.put("f",12);
        linkedHashMap.put("hy",6);
        linkedHashMap.put("n",5);
        linkedHashMap.put("njh",8);
        System.out.println("linkedHashMap: " + linkedHashMap);    // 组织的数据是按照插入顺序
       // linkedHashMap.put("hy", 7);
       // System.out.println(linkedHashMap);

        HashMap<String, Integer> map = new HashMap<>();
        map.put("b",2);
        map.put("ac",4);
        map.put("f",12);
        map.put("hy",6);
        map.put("n",5);
        map.put("njh",8);
        System.out.println("hashMap: " + map);   // 组织的数据是按照散列表的形式
//        map.put("hy", 7);
//        System.out.println(map);


        // 第3个参数 accessorder表示： 是否维护一个节点的访问顺序，默认是false。如果传入true, 就会维护，将最近使用的节点放置到链表的尾部
//        LinkedHashMap<String, Integer> order = new LinkedHashMap<String, Integer>(16, 0.75f, true);
//        order.put("b",2);
//        order.put("ac",4);
//        order.put("f",12);
//        order.put("hy",6);
//        order.put("n",5);
//        order.put("njh",8);
//        System.out.println(order);
//        order.put("hy",7);
//        System.out.println(order.get("b"));
//        System.out.println(order);





    }
}
