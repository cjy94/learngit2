package com.chenjunyi;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class Main {

    public static void main(String[] args) {
        Main test = new Main();
        System.out.printf("1 + 2 = %d\n", test.add(1,2));

        // test toUpperCase
        String greeting = "hello, world!";
        System.out.printf("Upper case of \"%s\" is \"%s\"\n",
                greeting, test.toUpperCase(greeting));


        System.out.println(System.getProperty("java.home"));
    }

    public int add(int num1, int num2) {
        return num1 + num2;
    }

    public String toUpperCase(String str) {
        return str.toUpperCase();
    }


    
}
