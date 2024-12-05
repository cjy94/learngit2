package com.chenjunyi.bits;

import com.chenjunyi.IP.InetAddresses;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Stack;
import java.util.TreeMap;

public class Or {
    public static void main(String[] args) {
        int[] arr = {2,5,9,6,9,3,8,9,7,1};
        int dup = findDup(arr);
        System.out.println(dup);
//        Or or = new Or();
//
//        int[] arr = {2,2,2,3,3,4,4,4,4,6,6,6,6,1};
//        System.out.println(Arrays.toString(arr));
//        //or.findOneOddNumber(arr);
//        or.findTwoOddNumber(arr);
        System.out.println(Integer.toBinaryString(514));
        System.out.println(Integer.toBinaryString(1025));


        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("b", 13);
        map.put("c", 12);
        map.put("d", 11);
        map.put("a", 10);
        map.put("e", 14);
        System.out.println(map);

        Map<String, Integer> subMap1 = map.subMap("b", true, "d", true);
        System.out.println(subMap1);

        Map<String, Integer> subMap2 = map.subMap("b", false, "d", true);
        System.out.println(subMap2);

        Map<String, Integer> subMap3 = map.subMap("b", true, "d", false);
        System.out.println(subMap3);

        Map<String, Integer> subMap4 = map.subMap("b", false, "d", false);
        System.out.println(subMap4);

        NavigableMap<String, Integer> stringIntegerNavigableMap = map.descendingMap();
        System.out.println(stringIntegerNavigableMap);

        

    }
    
    public static boolean isRight1(String str) {
        char[] chars = str.toCharArray();
        int count = 0;
        for (int i =0; i < chars.length; i++) {
            if (chars[i] == '(')
                count++;
            else if (chars[i] == ')')
                count--;
        }
        return count == 0 ? true : false;
    }




    /**
     *  问题： 数组中有几种数出现了偶数次，只有一种数出现了奇数次，找到出现奇数次的数
     *  利用异或， 可以找打
     *  根据^的性质：0^N=N; N^N=0,
     *  出现偶数次的数，异或之后一定是0
     *  出现奇数次的数，异或之后一定是那个数
     */
    public void findOneOddNumber(int [] arr) {
        int index = 0;
        for (int number : arr) {
            index ^= number;
        }
        System.out.println(index);

    }

    /**
     *  给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
     *  [1,4,3,3,2]
     *
     *  i 索引位置上要放i+1
     */
    public static int findDup(int[] arr) {
        int res = 0;
        for (int i =0; i < arr.length; i++) {
            while (arr[i] != i+1 && arr[arr[i]-1] != arr[i]) {
                swap(arr, i, arr[i]-1);
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (i+1 != arr[i]) {
                res = arr[i];
                break;
            }
        }
        return res;

    }


    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }






    /**
     * 问题： 数组中有几种数出现了偶数次，有2种数出现了奇数次，找到这两种数
     *
     * @param arr
     */
    public void findTwoOddNumber(int[] arr) {
        int index = 0;
        for (int number : arr) {
            index ^= number;
        }
        // 因为出现奇数次的有两种数，那么index的结果一定不是0，至少有一位位数上是1
        // 找到index最右边的位数是1的位置
        int rightOne = index & (~index + 1);
        int index1 = 0;
        for (int number : arr) {
            if ((rightOne & number) == 1) {
                index1 ^= number;
            }
        }
        System.out.println("one: " + index1 + ", another: " + (index ^ index1));

    }
}
