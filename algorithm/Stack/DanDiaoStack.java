package com.chenjunyi.Stack;

import java.util.Arrays;
import java.util.Stack;
import java.util.ArrayList;
import java.util.List;

/**
 * 单调栈 相关问题
 * 1、给一个数组，返回一个大小相同的数组。返回的数组的第i个位置的值应当是，
 * 对于原数组中的第i个元素，至少往右走多少步，才能遇到一个比自己大的元素（如果之后没有比自己大的元素，或者已经是最后一个元素，则在返回数组的对应位置放上-1）。
 * 简单的例子：
 *
 * input: 5,3,1,2,4
 *
 * return: -1 3 1 1 -1
 *
 *
 * 2、直方图中最大的矩形面积问题
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 *
 *
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 *
 * For example,
 * Given heights = [2,1,5,6,2,3],
 * return 10.
 *
 *
 * 3、数组中累加和与最小值的乘积， 假设叫做指标A， 给定一个数组，请返回子数组中，指标A最大的值
 * [5,3,2,1,6,7,8,4]
 * 保证arr[i]是当前子数组中的最小值，那么就是利用单调栈找出arr[i]左边比他小的最近的索引信息，和右边比他小的最近的索引信息，
 * left[i]和right[i]就是arr[i]不能到达的边界，在这个范围内的元素都比arr[i]大，索引arr[i]一定是这个范围内形成的子数组中的最小值，求一个累加和即可
 */
public class DanDiaoStack {

    // 针对问题1， 维护一个单调栈(栈中元素从栈底到栈顶由大倒小)的顺序，当前元素大于栈顶元素时，栈顶元素弹出，并开始处理信息；
    // 直到栈顶元素不比当前元素小，将当前元素压入栈中
    public static int[] problem1(int[] arr) {
        int[] res = new int[arr.length];
        // 栈中存放索引信息
        //Stack<Integer> stack = new Stack<>();
        Stack<Integer> stack = new Stack<>();
        for (int i =0; i <arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                int curIndex = stack.pop();
                res[curIndex] = i - curIndex;
            }
            stack.push(i);
        }

        // 栈不为空
        while (!stack.isEmpty()) {
            res[stack.pop()] = -1;
        }
        return res;


    }


    // 针对问题2， 维护一个从小到大的单调栈结果(栈底到栈顶， 从小到大)
    // 找到每一个矩形的左右边界，即左边比我大的边界，右边比我大的边界，两个边界就是矩形的宽度，
    public static int problem2(int[] arr) {
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];
        Arrays.fill(right, arr.length);
        Stack<Integer> s = new Stack<>();
        int res = 0;
        for (int i =0; i < arr.length; i++) {
            while (!s.isEmpty() && arr[s.peek()] >= arr[i]) {
                right[s.peek()] = i;
                s.pop();
            }
            left[i] = (s.isEmpty() ? -1 : s.peek());
            s.push(i);

        }

        for (int i =0; i < arr.length; i++) {
            res = Math.max(res, (right[i]-left[i]-1) * arr[i]);
            
        }
        return res;

    }


    public static int sumAndMinValueMulti(int[] arr) {
        // 定义两个数组lef和right
        // left中的信息是左边比arr[i]小的最近的元素的索引位置
        // right中的信息是右边比arr[i]小的最近的元素的索引信息
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];
        Arrays.fill(right, arr.length);
        Stack<Integer> s = new Stack<>();
        for (int i =0; i < arr.length; i++) {
            while (!s.isEmpty() && arr[s.peek()] >= arr[i]) {
                int curIndex = s.pop();
                right[curIndex] = i;

            }
            left[i] = s.isEmpty() ? -1 : s.peek();
            s.push(i);
        }

        int res = 0;
        for (int i =0; i < arr.length; i++) {
            // sum(l, r) 中的
            res = Math.max(arr[i] * sum(arr, left[i], right[i]), res);

        }
        return res;
    }

    private static int sum(int[] arr, int l, int r) {
        int sum = 0;
        for (int i =l+1; i < r; i++) {
            sum += arr[i];

        }
        return sum;

    }



    public static void main(String[] args) {
        int[] arr = {5,3,1,1,2,4};
        System.out.println(Arrays.toString(problem1(arr)));
        int[] arr2 = {2,1,5,6,2,3, 1, 4};
        System.out.println(problem2(arr2));
        int[] arr3 = {3,5,6,7,1,2,4};
        sumAndMinValueMulti(arr3);
    }
}
