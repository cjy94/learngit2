package com.chenjunyi.day07_GreedyTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;



/**
 * 背包问题：
 * 总重量150, 由7样物品，
 * 重量分别是 wi=[35,30,60,50,40,10,25]，价值分别是 pi=[10,40,30,50,35,40,30]，
 * 现在从这 7 个物品中选择一个或多个装入背包，要求在物品总重量不超过 C 的前提下，所装入的物品总价值最高。
 * <p>
 * 1、暴力递归： 将所有可能的情况全部列出来，从中选择pi最大的
 */
public class Greedy_BeiBaoTest {

    public static final int[] WI = {35,30,60,50,40,10,25};
    public static final int[] PI = {10,40,30,50,35,40,30};


    public static void main(String[] args) {
//        int simple = simple(150);
//        int process = process(WI, PI, 150, 0, 0);
//        System.out.println("res1: " + simple + ", res2: " + process);
       //int[] arr = {0,0,1,1,1,2,2,3,3,4};
       int[] arr = {1,1,1,1,2,2,2,3,4,5,5,6};
       int k = removeDuplicates(arr);
        System.out.println(k + "===== "+Arrays.toString(arr));



    }

    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0|| nums.length==1) return 0;
        int index = 1;
        int cur = 0;
        while (cur < nums.length-1) {
            if (nums[cur]!=nums[cur+1]) {

                nums[index]=nums[cur+1];
                index++;
            }
            cur++;
        }
        return index;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static boolean isValid(String s) {
        // 思路： 使用栈
        // 将左括号'(','{','['压入栈，当遇到'),'}',']'， 就取出栈顶元素，并比较是否是一堆括号，如果一致就弹出
        // 结束条件：1、字符串遍历结束 2、或者中间某个结果不满足也退出

        Stack<Character> stack = new Stack<>();
        char[] ch = s.toCharArray();
        int index = 0;
        int len = s.length();
        while (index < len) {
            if (ch[index]=='(' || ch[index]=='{' || ch[index]=='[') {
                stack.push(ch[index]);
            }
            if (ch[index]==')' || ch[index]=='}' || ch[index]==']') {
                // 取栈顶元素比较
                if(stack.isEmpty()==false) {
                    Character c = stack.peek();
                    if (c=='(' && ch[index]==')' || c=='[' && ch[index]==']' || c=='{' && ch[index]=='}') {
                        stack.pop();
                    } else{
                        return false;
                    }
                } else{
                    return false;
                }
            }
            index++;
        }
        return true;

    }

    public static int romanToInt1(String s) {
        int ans = 0, num = 0;
        for (int i = s.length()-1; i >= 0; i--) {
            switch(s.charAt(i)) {
                case 'I': num = 1; break;
                case 'V': num = 5; break;
                case 'X': num = 10; break;
                case 'L': num = 50; break;
                case 'C': num = 100; break;
                case 'D': num = 500; break;
                case 'M': num = 1000; break;
            }
            if (4 * num < ans) ans -= num;
            else ans += num;
        }
        return ans;
    }


    public static int romanToInt(String s) {
        // MCMXCIV
        // 规则 IV=4 IX=9 XL=40 XC=90 CD=400 CM=900
        //  M 1000 CM 900  XC 90 IV4

        // 思路： 遍历字符串中的每个字符
        //        1）当前字符串如果后面还有字符，并且和后面的字符是规则中的字符，那么两个一起做一个数值参与运算
        //        2）当前字符后面没有字符，或者和后面的字符不满足规则，那么自己单独参与运算
        HashMap<String, Integer> map = new HashMap();
        map.put("IV",4);
        map.put("IX",9);
        map.put("XL",40);
        map.put("XC",90);
        map.put("CD",400);
        map.put("CM",900);
        map.put("I",1);
        map.put("V",5);
        map.put("X",10);
        map.put("L",50);
        map.put("C",100);
        map.put("D",500);
        map.put("M",1000);


        int len = s.length();
        int index = 0;
        int total = 0;

        // String s = "MCMXCIV";
        while (index < len) {
            if (index+1 < len) {
                final String sub = s.substring(index,index+2);
                if ("IV".equals(sub) ||
                        "IX".equals(sub) ||
                        "XL".equals(sub) ||
                        "XC".equals(sub) ||
                        "CD".equals(sub) ||
                        "CM".equals(sub)) {

                    total += map.get(sub);
                    index = index+2;

                } else {
                    total += map.get(s.substring(index, index+1));
                    index++;

                }

            } else {
                total += map.get(s.substring(index, index+1));
                index++;
            }
        }
        return total;



    }


    /**
     *
     * @param weight  每个物品的重量
     * @param value   每个物品的价值
     * @param bag     背包能盛下的总重量
     * @param alreadyW 背包已经装下的重量
     * @param index     遍历到的物品索引
     * @return         在盛下的物品重量不超过bag的情况下，返回剩下物品的最大价值
     *
     * 
     */
    public static int process(int[] weight, int[] value, int bag, int alreadyW, int index) {
        if (alreadyW > bag) {
            return -1;
        }
        if (index == weight.length) {
            return 0;
        }

        // 可能性一：不将自己添加进背包的最大价值, 在没有要自己的情况下，后续的最大价值
        int p1 = process(weight, value, bag, alreadyW, index+1);

        // 可能性二：将自己添加进背包的最大价值， 在要自己的情况下，后续的最大值
        int p2next = process(weight,value, bag, alreadyW+weight[index], index+1);

        int p2 = -1;
        // 如果后面的过程不是无效方案
        if (p2next != -1) {
            // 可能性二的价值 = 当前货的最大价值 + 后续货的最大价值
            p2 = value[index] + p2next;
        }
        // 要当前货的价值p1, 不要当前货的价值p2,
        // 两者取最大，即为最大价值
        return Math.max(p1, p2);
        
    }

    // 方法一： 对数器， 全组合版本
    // 要求 max<= weight   WI = {35,30,60,50,40,10,25};
    public static int simple(int weight) {
        int max = 0;

        /**
         * 比如： weigth{4,2,3,5}  price{1,4,3,2}  weight=10
         * 那么全组合的情况就是
         * 选择一个物品： 4 , 2, 3, 5
         * 选择2个物品： (4,2) (4,3) (4,5) (2,3) (2,5) (3,5)
         * 选择3个物品： (4,2,3) (4,2,5) (4,3,5) (2,3,5)
         * 选择4个物品： (4,2,3,5)
         * 以上所有情况的price种取最大值
         */
        for (int num = 1; num < 1 << WI.length; num++) {
            int allW = 0;
            int allV = 0;
            int[] index = new int[WI.length];
            for (int i=0; i < WI.length; i++) {

                if ((1<<i & num) != 0) {
                    index[i] =1;
                    allV += PI[i];
                    allW += WI[i];
                }
            }

            //debug
//            StringBuilder str1 = new StringBuilder("[");
//            StringBuilder str2 = new StringBuilder("[");
//            for (int x =0; x < index.length; x++) {
//                if (index[x]!=0) {
//                    str1.append(WI[x]).append(",");
//                    str2.append(PI[x]).append(",");
//                }
//
//            }
//            str1.append("]");
//            str2.append("]");
//            System.out.println(str1 + ",       " + str2);
            //debug

            // 在不超过背包总重量的前提下，计算价格
            if (allW <= weight) {
                max = Math.max(max, allV);
            }
        }
        return  max;

    }


}
