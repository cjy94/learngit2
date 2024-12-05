package com.chenjunyi.Stack;

/**
 * 使用动态数组实现一个栈
 */
public class MyStack {

    private int[] arr;
    private int top = -1;
    private int size = 0;
    public MyStack() {
        size = 1;
        arr = new int[size];
    }

    public boolean isStackFull() {
        return top == size -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    // push 压入一个数据
    // 如果栈没有满， 直接放到arr[++top]的位置； 如果栈满了，那么先扩容，2倍扩容
    public void push(int data) {
        if(isStackFull()) {
            doubleStack(arr);
        }
        arr[++top] = data;
    }

    // 弹出栈顶的元素
    public int pop() {
        if (isEmpty()) {
            throw new StackOverflowError("stack is empty!");
        }
        int value = arr[top];
        top--;
        return value;
    }

    private void doubleStack(int[] arr) {
        int[] newArray = new int[size * 2];
        System.arraycopy(arr, 0, newArray, 0, size);
        size = size *2;
        arr = newArray;
    }
}
