package com.chenjunyi.LRU;

import java.util.HashMap;

/**
 * 设置一个LRU缓存结构
 *  双向链表+hash表 LinkedHashMap底层也是HashMap+双向链表(before, after)
 */
public class LRU {
    // 双向链表中的节点信息
    private static class Node<K,V> {
        public K key;
        public V value;
        public Node<K,V> last;
        public Node<K,V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // 定义一个双向链表，链表的头指针指向的是最长时间没有被使用过的节点
    // 链表的尾指针指向的是最新被使用的节点信息
    public static class NodeDoubleLinkedlist<K,V> {
        private Node<K,V> head;
        private Node<K,V> tail;
        public NodeDoubleLinkedlist() {
            this.head = null;
            this.tail = null;
        }

        // 插入节点
        // 如果一个新节点，插入到尾部
        public void addNode(Node<K,V> newNode) {
            if (newNode == null) {
                return;
            }
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                newNode.last = tail;
                tail = newNode;
            }
        }

        // 前提是： 双向链表中一定存在该node， 将该node从链表中断开连接， 放到链表尾部
        public void moveNodeToTail(Node<K,V> node) {
            if (tail == node) {
                return;
            }

            if (head == node) {
                head = node.next;
                head.last = null;
            } else {
                node.last.next = node.next;
                node.next.last = node.last;
            }
            // 放到链表尾部

            node.last = tail;
            node.next = null;
            tail.next = node;
            tail = node;
        }

        public Node<K,V> removeHead() {
            if (head == null) {
                return head; 
            }

            Node<K,V> tmp = head;
            if (head == tail) {
                tail = head = null;
            } else {
                head = tmp.next;
                tmp.next = null;
                head.last = null;
            }
            return tmp;
        }
    }

    public static class MyCache<K,V>{
        private HashMap<K, Node<K,V>> keyNodeMap;
        private NodeDoubleLinkedlist<K,V> nodeList;
        private int capacity;

        public MyCache(int capacity) {
            if (capacity < 1) {
                throw new RuntimeException("必须大于1");
            }
            keyNodeMap = new HashMap<K, Node<K,V>>();
            nodeList = new NodeDoubleLinkedlist<>();
            this.capacity = capacity;
        }

        public V get(K key) {
            if (keyNodeMap.containsKey(key)) {
                Node<K,V> res = keyNodeMap.get(key);
                nodeList.moveNodeToTail(res);
                return res.value;
            }
            return null;
        }

        public void Set(K key, V value) {
            // 更新操作
            if (keyNodeMap.containsKey(key)) {
                Node<K,V> node = keyNodeMap.get(key);
                node.value = value;
                nodeList.moveNodeToTail(node);
            } else {
                // 新增操作
                Node<K,V> newNode = new Node<>(key, value);
                keyNodeMap.put(key, newNode);
                nodeList.addNode(newNode);
                if (keyNodeMap.size() == capacity +1) {
                    // 移除掉最长时间未被使用过的节点
                    removeMostUnusedCache();
                }
            }
        }

        private void removeMostUnusedCache() {
            Node<K, V> removeHead = nodeList.removeHead();
            keyNodeMap.remove(removeHead.key);
        }
    }
}
