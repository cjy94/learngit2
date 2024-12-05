package com.chenjunyi.Stack;

import java.util.Stack;

/**
 * 删除所有相邻的重复元素
 * 如： [1,5,6,8,8,8,0,1,1,0,6,5] -> [1]
 *
 * 当战中元素与当前数据不相等时，入栈，如果栈顶元素和当前元素一致，则跳过当前元素，直到找到与栈顶元素不相等的数字
 */
public class RemoveAdjacentDuplicates {

    public static Stack<Integer> remove(int[] arr) {
        Stack<Integer> s = new Stack<>();
        int index = 0;
        while(index < arr.length) {
            // 如果栈不是空,那么先弹出栈顶元素
            if (!s.isEmpty() && s.peek() == arr[index]) {
                int temp = s.pop();
                // 如果栈顶元素和当前元素一样，跳过当前元素
                while (index < arr.length && temp == arr[index] ) {
                    index++;
                }

            } else {
                // 栈时空的
                // 栈顶元素和当前元素不一样，
                s.push(arr[index]);
                index++;

            }
        }
        return s;
    }

    public static void main(String[] args) {
        int[] arr = {1,5,6,8,8,8,0,1,1,0,6,5};
        int[] arr1 = {1,9,6,8,8,8,0,1,1,0,6,5};
        Stack<Integer> s = remove(arr1);
        System.out.println(s);

    }
}
