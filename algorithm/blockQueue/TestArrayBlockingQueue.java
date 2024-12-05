package com.chenjunyi.blockQueue;

import java.util.concurrent.ArrayBlockingQueue;

public class TestArrayBlockingQueue {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
        queue.add("1");
        queue.add("2");
        queue.add("3");
        queue.add("4");
        queue.add("5");
        queue.add("6");
        queue.add("7");
        queue.add("8");
        queue.add("9");
        queue.add("10");

        queue.add("11");
    }
}
