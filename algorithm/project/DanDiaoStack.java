package com.chenjunyi.project;

import java.util.Arrays;
import java.util.Stack;

/**
 *  在学校中，N 个小朋友站成一队， 第 i 个小朋友的身高为 height[i]，
 * 第 i 个小朋友可以看到的第一个比自己身高更高的小朋友 j，那么 j 是 i 的好朋友(要求 j >
 * i)。
 * 请重新生成一个列表，对应位置的输出是每个小朋友的好朋友位置，如果没有看到好朋友，
 * 请在该位置用 0 代替。
 *
 *  解决： 单调栈
 *  构建一个栈底到栈顶是从大到小的 单调栈
 *  当栈为空或者栈顶元素比我当前值大，当前元素入栈； 否则栈顶弹出，结算栈顶元素信息，直到栈顶元素大于当前元素，当前元素入栈
 */
public class DanDiaoStack {
    public static void main(String[] args) {
        int[] arr = {123,124,125,121,119,122,126,123};
        System.out.println(Arrays.toString(friend(arr)));
    }

    public static int[] friend(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[arr.length];
        for (int i =arr.length-1; i >=0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            
            if (stack.isEmpty())
                res[i] = 0;
            else
                res[i] = stack.peek();
            stack.push(i);
        }
        return res;

    }
}
