package com.chenjunyi.zhongji二.zhongji1;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 通过LinkedHashMap实现LRU
 */
public class MyLRU {
    public static class LRUCache<K,V> extends LinkedHashMap<K,V> {
        private static final int MAX_NODE_NUM = 2<<4;

        private int limit;
        public LRUCache() {
            this(MAX_NODE_NUM);
        }

        public LRUCache(int limit) {
            super(limit, 0.75f, true);
            this.limit = limit;

        }

        public V putValue(K key, V value) {
            return put(key, value);
        }

        public V getValue(K key) {
            return get(key);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > limit;
        }
    }

    public static void main(String[] args) {
        LRUCache<String, Integer> cache = new LRUCache<>(3);
        cache.putValue("cbh",23);
        cache.putValue("a",24);
        cache.putValue("ui",25);
        System.out.println(cache);

        cache.getValue("cbh");
        System.out.println(cache);

        cache.putValue("io", 67);
        System.out.println(cache);

    }
}
