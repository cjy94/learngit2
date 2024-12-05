package com.chenjunyi.zhongji二.zhongji5;

import java.util.HashMap;

public class ReceiveAndPrintMessage {
    public static class Node{
        String info;
        Node next;
        public Node(String info) {
            this.info = info;
        }

    }

    public static class MessageBox{
        HashMap<Integer, Node> headMap;
        HashMap<Integer, Node> tailMap;
        private int waitPoint;

        public MessageBox()  {
            headMap = new HashMap<>();
            tailMap = new HashMap<>();
            waitPoint = 1;
        }

        public void receive(int num, String info) {
            if (num < 1) {
                return;
            }
            Node cur = new Node(info);
            headMap.put(num, cur);
            headMap.put(num, cur);
            if (headMap.containsKey(num+1)) {
                Node next = headMap.get(num+1);
                cur.next = next;
                headMap.remove(num+1);
                tailMap.remove(num);
            }
            if (tailMap.containsKey(num-1)) {
                Node pre = tailMap.get(num-1);
                pre.next = cur;
                tailMap.remove(num-1);
                headMap.remove(num);
            }
            if (num == waitPoint) {
                print();
            }
        }

        private void print() {
            Node cur = headMap.get(waitPoint);
            headMap.remove(waitPoint);
            while (cur != null) {
                System.out.println(cur.info+" ");
                cur = cur.next;
                waitPoint++;
            }
            tailMap.remove(waitPoint-1);
        }
    }
}
