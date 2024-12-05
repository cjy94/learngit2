package com.chenjunyi.Joseph;

import com.chenjunyi.day10_List.ReversePairList;

import java.util.Arrays;

/**
 * 约瑟夫环问题： 是一个数学的应用问题， 已知n个人(以编号1,2,3...n分别表示)围坐再一张圆桌周围，从编号为k的人开始报数，数到m的那个人出圈；
 *              他的下一个人又从1开始报数，数到m的那个人又出圈； 依次规律重复下去，直到剩余最后一个胜利者
 *
 *
 *  方式一： 数组求解
 *      用数组求解的基本思想是用一个一维数组去标识这n个人的状态，默认为1，也就是都在圈子内，当数到m的人出圈之后，标识置为0(就是出圈了),同时报数器清0，
 *      下一个人要从1开始。 在每次报数之前要判断他是否在圈子内（也就是他的标识是否为1），如果在圈子里面才会继续报数，定义一个记录出圈的人数，当人数到达n-1时，结束
 *
 *
 *  方式二： 循环链表求解约瑟夫环
 *
 *
 */
public class JosephProblem {

    public static class Node{
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    public static class LinkedList {
        Node head;
        int size = 0;
        public Node add(Integer i){
            Node newNode = new Node(i);
            if(size == 0){
                head = newNode;
                head.next = head;
            }else{
                Node target = head;
                while(target.next!=head){
                    target = target.next;
                }
                target.next = newNode;
                newNode.next = head;
            }
            size++;
            return newNode;
        }
    }

    // 数组求解 ： 时间复杂度n^2
     public static int method1(int n, int k) {
         int[] help = new int[n];
         int num = 0;
         Arrays.fill(help, 1);
         int count = 0;
         while (count < n -1) {
             for (int i =0; i < n; i++) {
                 if (help[i] == 1) { // 该位置的人没有淘汰
                     num++;
                     if (num == k) { // 有人要被淘汰了
                         count++;
                         help[i] = 0;
                         num = 0;
                     }
                     if (count == n-1) {
                         break;
                     }
                 }

             }
         }
         for (int i =0; i < n; i++) {
             if (help[i] == 1) {
                 return i+1;
             }
         }
         return 0;
     }

     // 循环链表求解   , 时间复杂度n * m
    public static int method2(int n, int k) {
        Node head = new Node(1);
        Node tmp = head;
        for (int i =2; i <= n; i++) {
            Node node = new Node(i);
            tmp.next = node;
            tmp = node;
        }
        tmp.next = head;

        for (int i =n; i > 1; i--) {
            
            for (int j =1; j < k; j++) {
                tmp = tmp.next;

            }
            System.out.println("杀掉的人： " + tmp.next.value);
            tmp.next = tmp.next.next;
        }
        // 最后以head为头的循环链表中，应该只剩下一个节点
        return tmp.value;

    }


    // 递归公式
    

    public static void main(String[] args) {

        System.out.println(method2(10, 3));
       // System.out.println(method1(10, 3));
    }
}
