package com.chenjunyi.project;

import com.chenjunyi.zhongji二.zhongji1.Code04_MaxSumTopK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

/*
    最小和
    给定两个整数数组 array1、array2，数组元素按升序排列。假设从 array1、array2 中
    分别取出一个元素可构成一对元素，现在需要取出 k 对元素，并对取出的所有元素求和，
    计算和的最小值
    注意：两对元素如果对应于 array1、array2 中的两个下标均相同，则视为同一对元素。

    利用堆结构
 */
public class MinmunSum {
    public static void main(String[] args) {
        int[] arr1 = {1,1,2};
        int[] arr2 = {1,2,3};
        List<List<Integer>> sum = kSmallestPairs(arr1, arr2, 10);
        System.out.println(sum);
    }

    static class Info {
        int arr1Index;   // 来自哪个数组
        int arr2Index;      // 数组中元素的下标
        int sum;        // sum值

        Info(int arr1Index, int arr2Index, int sum) {
            this.arr1Index = arr1Index;
            this.arr2Index = arr2Index;
            this.sum = sum;
        }

    }
    public static class InfoComparator implements Comparator<Info> {
        @Override
        public int compare(Info o1, Info o2) {
            return o1.sum - o2.sum;
        }
    }

    public static List<List<Integer>> kSmallestPairs(int[] arr1, int[] arr2, int k) {
        int len1 = arr1.length;
        int len2 = arr2.length;
        PriorityQueue<Info> queue = new PriorityQueue(new InfoComparator());
        queue.add(new Info(0,0, arr1[0]+arr2[0]));
        List<List<Integer>> ans = new ArrayList<>();
        HashSet<String> set = new HashSet<String>();
        while (!queue.isEmpty() && k > 0) {
            Info cur = queue.poll();
            int arr1Index =  cur.arr1Index;
            int arr2Index =  cur.arr2Index;
            ans.add(Arrays.asList(arr1[arr1Index], arr2[arr2Index]));
            if (arr1Index +1 < len1) {
                String key = String.valueOf(arr1Index+1)+"_"+String.valueOf(arr2Index);
                if (set.add(key))
                    queue.add(new Info(arr1Index+1, arr2Index, arr1[arr1Index+1] + arr2[arr2Index]));
            }
            if (arr2Index+1 < len2) {
                String key = String.valueOf(arr1Index)+"_"+String.valueOf(arr2Index+1);
                if (set.add(key))
                    queue.add(new Info(arr1Index, arr2Index+1, arr1[arr1Index] + arr2[arr2Index+1]));
            }
            k--;
        }
        return ans;
    }
}
