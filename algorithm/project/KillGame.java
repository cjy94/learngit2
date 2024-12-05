package com.chenjunyi.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *  杀人游戏
 *  使用list
 */
public class KillGame {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9};
        int kill = kill(arr, 4, 3);
        System.out.println(kill);
    }

    public static int kill(int[] arr, int jump, int left) {
        int sum = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i =0; i < arr.length; i++) {
            list.add(arr[i]);
            sum += arr[i];
        }
        for (int i = (jump+1) % list.size(); list.size() > left; i = (i+jump) % list.size()) {
            sum -= list.get(i);
            list.remove(i);
        }

        return sum;

        
    }
}
