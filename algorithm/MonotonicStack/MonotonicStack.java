package com.chenjunyi.MonotonicStack;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

/**
 * 单调栈问题
 *  解决某个元素arr[i] 左侧离我最近的比我小的值， 右侧理我最近的比我小的
 */
public class MonotonicStack {

    public static int[][] getLeftRightNearestLessPosition(int[] arr) {
        int n = arr.length;
        int[][] res = new int[n][2];   // 记录每个元素的左侧比它近的最小的值； 右侧比它近的最小的值
        Stack<List<Integer>> stack = new Stack<List<Integer>>();
        // 遍历每一个元素
        for (int i =0; i < n;i++) {
            // 如果栈不是空，arr[i]小于栈顶元素， 那么将栈顶元素依次弹出栈
            while (!stack.isEmpty() && arr[i] < arr[stack.peek().get(0)]) {
                // 栈顶元素弹出，并且形成该元素的[L...R]
                List<Integer> list = stack.pop();
                int r = i;
                // 栈顶元素
                List<Integer> temp = stack.peek();
                int l = stack.isEmpty() ? -1 : temp.get(temp.size()-1);
                for (Integer index : list) {
                    res[index][0] = l;
                    res[index][1] = r;
                }

            }

            // 如果不比栈顶元素小
            if (!stack.isEmpty() && arr[i] == arr[stack.peek().get(0)]) {
                stack.peek().add(i);
            } else {
                List<Integer> list = new ArrayList<Integer>();
                list.add(i);
                stack.push(list);
            }
        }

        // 如果栈不是空，那么依次弹出栈顶元素
        while (!stack.isEmpty()) {
            List<Integer> list = stack.pop();
            int r = -1;
            List<Integer> temp = stack.isEmpty() ? null : stack.peek();
            int l = stack.isEmpty() ? -1 : temp.get(temp.size()-1);
            for (Integer index : list) {
                res[index][0] = l;
                res[index][1] = r;
            }
        }
        return res;
    }

    //  求数组的前缀和
    public static int[] getSum(int[] arr) {
        int[] res = new int[arr.length];
        int sum =arr[0];
        res[0] = sum;
        for (int i =1; i < arr.length; i++) {
            sum  = sum + arr[i];
            res[i] =  sum;

        }
        return res;
    }

    //给定正数数组arr，求任意子数组最小值与子数组累加和的乘积最大值是多少
    public static int maxMultiplyOfMinAndSubSum(int[] arr){
        // 获取前缀额和数组
        int[] pre = getSum(arr);
        int max = Integer.MIN_VALUE;
        // 获取每个元素左侧最近的最小的值，右侧最近的最小的值
        int[][] pos = getLeftRightNearestLessPosition(arr);
        for (int i =0; i < arr.length; i++)  {
            int l = pos[i][0] == -1 ? 0 : pos[i][0];              // 左边界
            int r = pos[i][1] == -1 ? arr.length-1 : pos[i][1];   // 右边界
            // 求前缀和
            int sum = 0;
            // 如果左边界是-1
            if (pos[i][0] == -1) {
                sum  = pos[i][1] != -1 ? pre[r-1] : pre[r];
                
            } else {
                sum = pos[i][1] != -1 ? pre[r-1]-pre[l] : pre[r]-pre[l];
            }
            max = Math.max(max, sum * arr[i]);
        }
        return max;
    }

    

    public static void main(String[] args) {
        int[] arr = {1,2,5,2,1};
        //
//        int[][] ans = getLeftRightNearestLessPosition(arr);
//        int[] sum = getSum(arr);
//        System.out.println(Arrays.toString(sum));
        int i = maxMultiplyOfMinAndSubSum(arr);
        System.out.println(i);


    }

}
