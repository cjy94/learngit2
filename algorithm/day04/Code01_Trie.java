package com.chenjunyi.day04;

import java.util.HashMap;

/**
 * 前缀树很重要
 */
public class Code01_Trie {
    public static void main(String[] args) {
        Trie1 t = new Trie1();
        t.insert("abc");
        t.insert("abd");
        t.insert("abd");
        t.insert("abd");
        t.insert("bd");
        t.insert("ab");

//        int count = t.search("bd");
//        System.out.println(count);
        t.delete("bd");
        System.out.println(t.search("bd"));


    }

    public static class Node {
        public int end = 0;
        public int pass = 0;
        public Node[] nexts;

        public Node() {
            nexts = new Node[26];
            // 0 a方向的路
            // 1 b方向的路
            // 2 c方向的路
            // 25 z方向的路

            // nexts[i] == null i方向的路不存在
            // nexts[i] != null i方向的路存在

        }
    }

    public static class Trie1 {
        public Node root;

        public Trie1() {
            root = new Node();
        }

        // 从根据点开始建立前缀树
        public void insert(String word) {
            if (word == null) return;
            char[] str = word.toCharArray();
            Node node = root;
            int index = 0;
            for (int i = 0; i < str.length; i++) {
                index = str[i] - 'a';
                // 检查该方向上的路是否存在
                if (node.nexts[index] == null) {
                    node.nexts[index] = new Node();
                }
                node = node.nexts[index];
                node.pass++;
            }
            node.end++;
        }

        /**
         * 将给定的字符串删除，
         * 规则：从root节点开始遍历，没经过一个节点，都将pass-- , 最后退出循环时，将最后一个node.end--
         * 如果pass==, 那么直接root.nexts[index] = null，置为空
         * @param word
         */
        public void delete(String word)  {
            if (search(word) != 0) {
                // 能进入该条件的前提是： 字符串存在，
                char[] str = word.toCharArray();
                Node node = root;
                // 将根节点pass--
                node.pass--;
                int index = 0;
                for (int i =0; i < str.length; i++) {
                    index = str[i]-'a';
                    if (--node.nexts[index].pass == 0) {
                        node.nexts[index] = null;
                        return;
                    }
                    node = node.nexts[index];
                }
                node.end--;
            }

        }

        // 给定一个字符串， 判断该字符串被加入过几次
        // 通过end值确定加入的次数
        public int search(String word) {
            int res = 0;
            if (word == null) return res;
            char[] str = word.toCharArray();
            Node node = root;
            int index = 0;
            while (index < str.length && node.nexts[str[index] - 'a'] != null) {
                node = node.nexts[str[index] - 'a'];
                index++;
            }
            res = node.end;
            return res;
        }

        public int prefixNumber(String word) {
            int res = 0;
            if (word == null) return res;
            char[] str = word.toCharArray();
            int index = 0;
            while (index < str.length && root.nexts[str[index] - 'a'] != null) {
                root = root.nexts[str[index] - 'a'];
                index++;
            }
            res = root.pass;
            return res;
        }


    }

}
