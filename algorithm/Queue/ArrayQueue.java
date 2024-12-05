package com.chenjunyi.Queue;

/**
 * 有3种方式可以实现队列，1、循环数组； 2、动态循环数组； 3、链表
 * 队列的特点是先进先出，后进后出， 元素从队尾加入，从队首删除
 * 对外提供的方法：
 *  isEmpty() 检查队列种是否有元素
 *  deQueue() 将元素从队列中删除
 *  enQueue() 将元素插入队列中
 *  isFull() 队列是否满
 *  getQueueSize() 获取队列中元素的个数
 *
 */

// 基于循环数组实现队列
public class ArrayQueue {

    int[] arr;
    int rear;  // 指向队尾的指针, arr[rear] = value, rear的位置就是新元素插入的位置
    int front; // 指向队首的指针   return arr[front] front位置的元素就是要出队列的元素
    int capacity;

    public ArrayQueue(int size) {
        this.capacity = size;
        arr = new int[size];
        rear = -1;
        front = -1;
    }

    public boolean isEmpty() {
        return front == -1 ? true : false;
    }

    public boolean isFull() {
        return  (rear + 1) % capacity == front ? true : false;
    }

    // 返回队列还有多少位置
    public int getQueueSize() {
        return ((capacity - front + rear + 1) % capacity);
    }

    // rear指向的元素入队列，并且rear指针后移 rear++
    public void enQueue(int value) {
        if (isFull()) {
            throw new RuntimeException("队列满");
        } else {
            rear = (rear + 1) % capacity;
            arr[rear] = value;
            if (front == -1) {
                front = rear;
            }
        }
    }


    // front指向的元素要出队列，并且front之前前移， front--
    public int deQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        int data = arr[front];
        if (front == rear) {
            front = rear -1;
        } else {
            front = (front +1) % capacity;
        }
        return data;         
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(4);
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        System.out.println(queue.getQueueSize());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        queue.enQueue(5);
        System.out.println(queue.deQueue());
        System.out.println(queue.getQueueSize());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        queue.enQueue(6);
    }
    
}
