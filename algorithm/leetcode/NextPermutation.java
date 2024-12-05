package com.chenjunyi.leetcode;


import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class NextPermutation {

    // 打印出一个数组的所有排列情况
    public static void allPermutation(int[] arr) {
        if (arr== null || arr.length == 0) {
            return;
        }

        process(arr, 0);
    }

    private static void process(int[] arr, int i) {
        if (i == arr.length) {
            System.out.println(Arrays.toString(arr));
            return;
        }

        for (int j=i; j<arr.length; j++) {
            swap(arr, i, j);
            process(arr, i+1);
            swap(arr, i, j);

        }
    }

    private static void swap(int[] str, int i, int j) {
        int temp = str[i];
        str[i] = str[j];
        str[j] = temp;

    }

    // 数组的下一个排列，即按照字典序排序
    // 比如： [1,2,3] --> [1,2,3] [1,3,2] [2,1,3] [2,3,1] [3,1,2] [3,2,1]
    //   123 -> 132
    //   132 -> 213
    //   213 -> 231
    //   231 -> 312
    //   312 -> 321
    //   321 -> 123
    // 也就是 将每一种排列结果按照从小到达进行排序，排在一个数字后面的就是他的下一个全排列
    // 123 132 213 231 213 321  可以将每一种全排列的结果放到数组中，将数组进行从小到大排序，但是题目要求的是原为排序，即空间复杂度是 O(1)
    public static void nextPermutation(int[] nums) {
        if(nums == null || nums.length <= 1) return;

        int i = nums.length-2;
        // 从后往前遍历，后面的字符如果比前面的字符大， 则交换
        while(i >=0 && nums[i]>=nums[i+1])
            i--;

        if(i>=0){
            int j = nums.length-1;
            while(nums[j] <= nums[i]){
                j--;
            }
            swap(nums, i, j);
        }
        // 完全逆序，需要反转的情况
        reverse(nums, i+1, nums.length-1);
    }

    // 如果从后往前找到的数组都是升序的，那么直接从数组头部到数组尾部进行逆序反转
    public static void nextPermutation1(int[] nums)  {
        // 从后往前找打第一个降序的索引位置
        int index = nums.length-2;
        while (index >= 0 && nums[index] > nums[index+1]) {
            index--;
        }
        if (index+1 == 0) {
            // 直接逆序
            reverse(nums, 0, nums.length-1);
            return;
        }
        // 从index后面开始找比arr[index]大的最小值
        int j = nums.length-1;
        for (; j >= index-1; j-- ) {
            if (nums[j] > nums[index])
                break;

        }

        swap(nums, index, j);
        // 保证index后面的数组是升序排序的
        reverse(nums, index+1, nums.length-1);


    }

    public static void reverse(int[] arr, int i, int j){
        while(i < j)
            swap(arr, i++, j--);
    }

    public static List<List<Integer>> permute(int[] nums) {

        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> list = new ArrayList();
        processAll(nums, list, 0);
        return list;
    }

    private static void processAll(int[] nums, List<List<Integer>> list, int i) {
        if (i == nums.length) {
            ArrayList<Integer> l = new ArrayList<>();
            for (Integer num : nums) {
                l.add(num);
            }
            list.add(l);
            return;
        }

        for (int j = i; j < nums.length; j++) {
            swap(nums, i, j);
            processAll(nums, list, i+1);
            swap(nums, j, i);
        }
    }

    public static void main(String[] args) {

        int[] arr = {1,2,3};
        int[] arr1 = {1, 3, 2};
        int[] arr2 = {2,1,3};
        int[] arr3 = {2,3,1};          //[1,2,3] [1,3,2] [2,1,3] [2,3,1] [3,1,2] [3,2,1]
        int[] arr4 = {3,1,2};
        int[] arr5 = {3,2,1};
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(arr3));
        System.out.println(Arrays.toString(arr4));
        System.out.println(Arrays.toString(arr5));
        System.out.println("=================");
        nextPermutation1(arr);
        nextPermutation1(arr1);
        nextPermutation1(arr2);
        nextPermutation1(arr3);
        nextPermutation1(arr4);
        nextPermutation1(arr5);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(arr3));
        System.out.println(Arrays.toString(arr4));
        System.out.println(Arrays.toString(arr5));

    }
}
