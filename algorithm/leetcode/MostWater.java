package com.chenjunyi.leetcode;

public class MostWater {
    public int maxArea(int[] height) {
      int l = 0;
      int r = height.length-1;
      int max = 0;
      while (l < r) {
          max = Math.max(max,  (r-l) * Math.min(height[l], height[r]));
          if (height[l] > height[r]) r--;
          else l++;
      }
      return max;



    }



    public static void main(String[] args) {
        int[] h = {1,8,100,2,100,4,8,3,7};
        MostWater test = new MostWater();
        System.out.println(test.maxArea(h));
    }




}
