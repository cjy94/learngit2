package com.chenjunyi.GO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 *  处理输入输出
 *  acm风格
 *      a、规定数量(BufferedReader、StreamTokenizer、PrinterWriter)，其他语言有对等的写法
 *      b、按行读(BufferReader, PrintWriter),
 *      c、不要使用Scanner和System.out IO效率慢
 *  不推荐：临时空间
 *  推荐： 全局静态空间
 *
 */
public class SystemInSystemOut {

    public static void main(String[] args) {
        String str = "0xAA";
        String replace = str.replace("0x", "");
        test1(replace);
    }

    public static void test1(String s) {
        int ans = 0;
        int bits = 0;
        char[] ch = s.toCharArray();
        for (int i = ch.length-1; i>=0; i--) {
            if (ch[i] >= '0' && ch[i] <= '9') {
                ans += ((ch[i]-'0') * Math.pow(16, bits++));
            } else if (ch[i] >= 'A' && ch[i] <= 'F') {
                ans += ((ch[i]-'A'+10) * Math.pow(16, bits++));
            }
        }
        System.out.println(ans);
    }


    public static void test(String s) {
        int ans = Integer.parseInt(s, 16);
        System.out.println(ans);
    }

    public static void heapSort(int[] arr) {
        int n = arr.length;
        for (int i =0; i < n; i++) {
            heapInsert(arr, i);
        }

        while (n > 0) {
            swap(arr, 0, --n);
            heapify(arr, 0, n);
        }
    }
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index-1)/2]) {
            swap(arr, index, (index-1)/2);
            index = (index -1) / 2;
        }
    }

    public static void heapify(int[] arr, int index, int limit) {
        int left = index * 2 + 1;
        while (left < limit) {
            int largest = (left+1 < limit) && (arr[left] < arr[left+1]) ? left+1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index)
                break;
            swap(arr, largest, index);
            index = largest;
            left = index * 2 +1;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }



    






























    static int[] beer = new int[100];

//    public static void main(String[] args) {
//        Scanner sr = new Scanner(System.in);
//        for (int i = 0; ; i++) {
//            beer[i] = sr.nextInt();
//            if (beer[i] == 0) break;
//        }
//
//        for (int j = 0; j < beer.length; j++) {
//            if (beer[j] == 0) break;
//            text(beer[j]);
//        }
//
//    }

    private static void text(int n) {
        int drink = 0;
        while (true) {
            if (n == 2) {
                drink++;
                break;
            }
            if (n == 0 || n == 1) break;
            int over = n % 3;
            drink += (n-over)/3;
            n = over + (n-over)/3;
        }
        System.out.println(drink);
    }

}
