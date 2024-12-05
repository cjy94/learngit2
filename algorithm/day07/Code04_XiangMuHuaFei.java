package com.chenjunyi.day07;

import java.io.IOException;
import java.util.Scanner;

/**
 * 一个项目包含花费和利润两项，有初始资金w, k最多可做的项目，在利润最大的前提下，能最多做多少个项目？
 * 思路：
 *  1、创建一个根据花费建立的小根堆 和一个根据利润建立的大根堆
 *  2、根据初始资金w解锁小根堆中的项目，放入到利润的大根堆中，取大根堆的堆顶元素，将堆顶元素中的利润和初始资金求和，再继续解锁小根堆中的项目
 *  直到小根堆中的项目全部被取完 或者 无法再取（大根堆的堆顶元素和小根堆的项目资金小）
 */
public class Code04_XiangMuHuaFei {

    public static class BlockState{


        private int[] offsets = new int[0];
        private int[] numStoredFields = new int[0];

        // the start pointer at which you can read the compressed documents


        private final byte[] spare = new byte[0];

        void reset(int size) {
            int[] back = new int[size];
            byte[] back1 = new byte[size];
            System.arraycopy(back, 0, offsets, 0, size);
            System.arraycopy(back, 0, numStoredFields, 0, size);
            System.arraycopy(back1, 0, spare, 0, size);
            
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.nextLine());
        BlockState state = new BlockState();
        state.reset(100*1024*1024);
        

    }
}
