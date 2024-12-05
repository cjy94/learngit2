package com.chenjunyi.Stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * 计算跨度， 也就是找出左侧离我最近的比我大元素的位置与我的距离
 *
 * 两种方式：
 * 方式1：每到一个位置，就把他前面的元素都遍历一遍找到比当前元素大的元素的位置
 * 时间复杂度O(n^2)
 *
 * 方式2：利用单调栈的性质，栈顶到栈底的元素是从小到大的顺序
 */
public class LeftBiggerElementIndex {

    public static int[] method1(int[] arr) {
        int[] res = new int[arr.length];
        for (int i =0; i < arr.length; i++) {
            int temp = 1;
            int j = i-1;
            while (j >= 0 && arr[j] <= arr[j+1]) {
                temp++;
                j--;
            }
            res[i] = temp;
        }
        return res;
    }

    // 利用单调栈，栈顶到栈顶元素从小到大
    // 时间复杂度O(n)
    public static int[] method2(int[] arr) {
        int[] res = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        int temp = 0;
        for (int i =0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                temp = -1;
            } else {
                temp = stack.peek();
            }
            res[i] = i-temp;
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {6,3,4,5,2};
        System.out.println(Arrays.toString(method1(arr)));
        System.out.println(Arrays.toString(method2(arr)));
    }
}
