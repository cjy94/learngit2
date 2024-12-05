package com.chenjunyi.linkedList;

import java.util.List;
import java.util.ArrayList;

public class TestList {
    public static void main(String[] args) {
        List<String> arr = new ArrayList(2);
        
        arr.add("2");
        arr.add("3");
        arr.add("4");
        System.out.println(arr);
        System.out.println(arr.size());
    }
}
