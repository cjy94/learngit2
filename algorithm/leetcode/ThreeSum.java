package com.chenjunyi.leetcode;

import java.util.*;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        // 不能使用数组中的3个元素，使3个元素的和==0， 不能使用重复元素
        Arrays.sort(nums);
        List<List<Integer>> lists = new ArrayList<>();
        Set<List<Integer>> s = new HashSet<>();
        int first = 0;
        int second = 0;
        int thrid = 0;
        for (int i =0;i < nums.length-2; i++) {
            first = nums[i];
            for (int j=i+1; j< nums.length; j++) {
                int index = j+1;
                second = nums[j];
                thrid = 0-first - second;
                while (index < nums.length) {
                    if (nums[index] == thrid) {
                       ArrayList list =  new ArrayList<Integer>();
                       list.add(first);
                       list.add(second);
                       list.add(thrid);
                       s.add(list);
                    }
                    index++;
                }
            }
        }
        lists.addAll(s);
        return lists;
    }


    public List<List<Integer>> threeSum1(int[] nums) {
        // 不能使用数组中的3个元素，使3个元素的和==0， 不能使用重复元素
        Arrays.sort(nums);
        List<List<Integer>> lists = new ArrayList<>();
        Set<List<Integer>> s = new HashSet<>();

        for (int i =0; i < nums.length; i++) {
            int l = i+1;
            int r = nums.length-1;
            while (l < r) {
                if (nums[i] + nums[l] + nums[r] == 0) {
                    ArrayList list =  new ArrayList<Integer>();
                    list.add(nums[i]);
                    list.add(nums[l]);
                    list.add(nums[r]);
                    s.add(list);
                    l++;
                    r--;
                } else if(nums[i] + nums[l] + nums[r] > 0) {
                    r--;
                }   else {
                    l++;
                }
            }

        }
        lists.addAll(s);
        return lists;
    }

    public List<List<Integer>> threeSum2(int[] nums) {
        int target = 0;
        Arrays.sort(nums);
        Set<List<Integer>> s = new HashSet<>();
        List<List<Integer>> output = new ArrayList<>();
        for (int i = 0; i < nums.length; i++){
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    s.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                } else if (sum < target) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        output.addAll(s);
        return output;
    }


    public static int titleToNumber(String columnTitle) {

        if (columnTitle == null || columnTitle.length() == 0) return 0;
        //A:1 B:2 C:3 D:4 E:5 .... 
        int[] nums = new int[26];
        for (int i =0; i < nums.length; i++) {
            nums[i] = i+1;
        }

        char[] str = columnTitle.toUpperCase().toCharArray();
        int len = str.length;
        int ans = nums[str[len-1] - 'A'];
        int index = 1;
        for (int i =len-2; i >= 0; i--, index++) {
            int ch = str[i] - 'A';
            ans += Math.pow(26, index) * nums[ch];

        }
        return ans;

    }

    public static boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();

        while (n !=1 && !set.contains(n)) {
            set.add(n);
            int sum = 0;
            while (n != 0) {
                int digit = n % 10;
                sum += digit * digit;
                n = n / 10;
            }
            n = sum;
        }
        return n == 1 ? true : false;

    }

    public static void main(String[] args) {
        System.out.println(isHappy(2));
        System.out.println(isHappy(19));
        System.out.println(Math.pow(26, 2));
        System.out.println(titleToNumber("AAA"));
        System.out.println(titleToNumber("A"));
        System.out.println(titleToNumber("AB"));
        System.out.println(titleToNumber("ZY"));


    }
}
