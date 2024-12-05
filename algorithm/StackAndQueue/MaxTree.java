package com.chenjunyi.StackAndQueue;

import java.util.HashMap;
import java.util.Stack;

public class MaxTree {
    public static class Node{
        int value;
        Node left;
        Node right;
        Node(int value) {
            this.value = value;
        }
    }

    public Node getMaxTree(int[] arr) {
        Node[] nArrr = new Node[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nArrr[i] = new Node(arr[i]);
        }
        Stack<Node> stack = new Stack<>();
        HashMap<Node, Node> lBigMap = new HashMap<>();
        HashMap<Node, Node> rBigMap = new HashMap<>();
        for (int i =0; i!=nArrr.length; i++) {
            Node curNode = nArrr[i];
            while ((!stack.isEmpty()) && stack.peek().value < curNode.value) {
                popStackSetMap(stack, lBigMap);
            }
            stack.push(curNode);
        }
        while (!stack.isEmpty()) {
            popStackSetMap(stack, lBigMap);

        }

        // rBigMap
        for (int i =nArrr.length-1; i!=-1; i--) {
            Node curNode = nArrr[i];
            while ((!stack.isEmpty()) && stack.peek().value < curNode.value) {
                popStackSetMap(stack, rBigMap);
            }
            stack.push(curNode);
        }
        while (!stack.isEmpty()) {
            popStackSetMap(stack, rBigMap);

        }

        Node head = null;
        for (int i =0; i != nArrr.length; i++) {
            Node cur = nArrr[i];
            Node left = lBigMap.get(cur);
            Node right = rBigMap.get(cur);
            if (left == null && right==null) {
                head = cur;
            } else if(left==null) {
                if (right.left == null) {
                    right.left = cur;
                } else {
                    right.right = cur;
                }
            } else if (right == null) {
                if (left.left == null) {
                    left.left = cur;
                } else {
                    left.right = cur;
                }
            } else {
                Node parent = left.value < right.value ? left : right;
                if (parent.left == null) {
                    parent.left = cur;
                } else {
                    parent.right = cur;
                }
            }
        }
        return head;

    }

    private void popStackSetMap(Stack<Node> stack, HashMap<Node, Node> map) {
        Node cur = stack.pop();
        if (stack.isEmpty()) {
            map.put(cur, null);
        } else {
            map.put(cur, stack.peek());
        }
    }

    public static void main(String[] args) {
        int[] arr = {3,4,5,1,2};

        MaxTree test = new MaxTree();
        test.getMaxTree(arr);

    }
}
