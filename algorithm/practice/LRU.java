package com.chenjunyi.practice;

/**
 *  LRU 内存替换算法
 *
 *  哈希表 和 双向链表
 *  最新最近使用的数据在链表的尾部，不经常使用的放在链表的头部
 *
 *
 *  时间复杂度 O(1)
 *  put(key, value)
 *  update(key, value)
 *  remove()
 *  get(key)
 *  
 */
public class LRU {

    static class Node<K,V> {
        K key;
        V value;
        Node<K,V> next;
        Node<K,V> pre;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // 双向链表
    public static class NodeDoubleLinkedList<K,V> {
        Node<K,V> head;
        Node<K,V> tail;

        public NodeDoubleLinkedList() {
            head = null;
            tail = null;
        }

        public void addNode(Node<K,V> node) {
            if (node == null)
                return;
            if (head == null) {
                head = tail = node;
            } else {
                tail.next = node;
                node.pre = tail;
                tail = node;
            }
        }

         public void moveNodeToTail(Node<K,V> node) {
            if (node == tail)
                return;
            if (node == head) {
                head = node.next;
                head.pre = null;
            } else {
                node.pre.next = node.next;
                node.next.pre = node.pre;
            }

            tail.next = node;
            node.pre = tail;
            node.next = null;
            tail = node;

         }
        public Node<K,V> removeHead() {
            if (head == null)
                return null;

            Node<K,V> cur = head;
            if (head == tail) {
                head = tail = null;
            }  else {
                head = cur.next;
                head.pre = null;
                cur.next = null;
            }
            return cur;
        }


        


    }

}
