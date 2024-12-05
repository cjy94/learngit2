package com.chenjunyi.zhongji二.zhongji1;

import java.util.HashMap;

/**
 *
 *
 * LRU: Least Recently Used   最近最少使用
 * 设计LRU 缓存结构，该结构在构造时确定大小，假设大小为k,并由如下两个功能set(key, value)和get(key)返回key对应的value值
 * 要求set和get方法的时间复杂度为o(1)
 * 当某个key的set或get操作一旦发生，认为这个key已经记录成了最长使用的
 * 当缓存的大小超过k个，移除最不经常使用的记录，
 *
 *
 * jdk 中的LinkedHashMap 也可以实现LRU的功能， 重写removeEldestEntry()就可以
 * 使用链表+hash表   LinkedHashMap
 * 链表: 规定头节点时最长时间未被使用过的节点； 尾节点是最近被使用的
 *
 *
 */
public class Code01_LRU {

    public static class Node<K,V>{
        K key;
        V value;
        Node<K,V> before;
        Node<K,V> after;
        public Node(K key, V value) {
            this.value = value;
            this.key = key;
        }
        
    }


    public static class NodeDoubleLinkedList<K,V> {
        private Node<K,V> head;
        private Node<K,V> tail;

        public NodeDoubleLinkedList() {
            
        }

        // 向双向链表中插入节点， 将节点插入到链表的尾部
        public void addNode(Node<K,V> newNode) {
           if (newNode == null) {
               return;
           }
           if (head == null) {
               head = tail = newNode;
           } else {
               tail.after = newNode;
               newNode.before = tail;
               tail = newNode;
           }
        }


        // 将链表中的节点移动到链表的尾部
        public void moveNodeToTail(Node<K,V> node) {
            if (tail == node) {
               return;
            }

            if (head == node) {
                head = node.after;
                head.before = null;
            } else {
                node.before.after = node.after;
                node.after.before = node.before;
            }
            node.after = null;
            node.before = tail;
            tail.after = node;
            tail = node;
        }

        // 删除头节点
        public Node<K,V> removeHead() {
            if (head == null) {
                return null;
            }
            Node<K,V> res = head;
            if (head == tail) {
                head = tail = null;
            } else {
                head = res.after;
                head.before = null;
                res.after = null;
            }
            return res;
        }
    }

    public static class MyCache<K,V>{
        public HashMap<K, Node<K,V>> map;
        public NodeDoubleLinkedList<K,V> nodeList;
        public int capacity;
        public MyCache(int capacity) {
            if (capacity < 1) {
                throw new RuntimeException("should be more than 0");
            }
            this.capacity = capacity;
            map = new HashMap<K, Node<K,V>>();
            nodeList = new NodeDoubleLinkedList<K,V>();
        }


        // get方法取值
        public V get(K key) {
            if (map.containsKey(key)) {
                Node<K,V> node = map.get(key);
                nodeList.moveNodeToTail(node);
                return node.value;
            }
            return null;
        }

        // set插入或者更新值
        public void set(K key, V value) {
            // 更新操作
            if (map.containsKey(key)) {
                Node<K,V> node = map.get(key);
                node.value = value;
                nodeList.moveNodeToTail(node);
            } else {
                // 插入操作, 有可能需要删除头节点
                Node<K,V> newNode = new Node<>(key, value);
                map.put(key, newNode);
                nodeList.addNode(newNode);
                // 如果超过了capacity, 则移除头节点
                if (map.size() == capacity +1) {
                    removeLeastRecentlyNode();
                }
            }
        }

        private void removeLeastRecentlyNode() {
            Node<K, V> removeNode = nodeList.removeHead();
            map.remove(removeNode.key);
        }


    }
    
}
