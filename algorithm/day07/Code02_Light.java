package com.chenjunyi.day07;


import java.util.HashSet;

/**
 * 放灯问题
 */

public class Code02_Light {
    // 方法一：贪心
    public static int minLight1(String road) {
        char[] str = road.toCharArray();
        int index = 0;
        int lights = 0;
        while (index < str.length) { // 一旦越界，说明都已经决定好了，返回最少的灯数
            if (str[index] == 'X') {// i位置是X，直接去i++做决定
                index++;
            } else {// i位置是点 分情况讨论
                lights++;
                if (index + 1 == str.length) {// 不需要放灯了
                    break;
                }
                if (str[index + 1] == 'X') {// i+1位置是X，必须在i位置放灯，然后去i+2位置做决定
                    index = index + 2;
                } else {// i+1位置是. 不管i+2位置是X还是. 都在i+1位置放灯，然后去i+3位置做决定
                    index = index + 3;
                }
            }
        }
        return lights;
    }

    // str[index....]位置，自由选择放灯还是不放灯
    // str[0..index-1]位置呢？已经做完决定了，那些放了灯的位置，存在lights里
    // 要求选出能照亮所有.的方案，并且在这些有效的方案中，返回最少需要几个灯
    // 暴力递归， 没看懂
    public static int process2(char[] str, int index, HashSet<Integer> lights) {
        //   "..XX"
        if (index == str.length) {// 结束的时候
            for (int i = 0; i < str.length; i++) {
                if (str[i] != 'X') {// 如果当前位置是点的话
                    if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else {
            // str还没结束
            // i位置无论是'X'还是'.'，都有一个选择，那就是不放灯
            // no 当前i位置没有放灯，返回后续的最好灯数
            int no = process2(str, index + 1, lights);
            int yes = Integer.MAX_VALUE;// yes 只有'.'才会变
            if (str[index] == '.') {
                lights.add(index);
                yes = process2(str, index + 1, lights);
                lights.remove(index);
            }
            return Math.min(no, yes);
        }
    }

    // 方法二：暴力解
    public static int minLight2(String road) {
        if (road == null || road.length() == 0) {
            return 0;
        }
        return process2(road.toCharArray(), 0, new HashSet<>());
    }

    public static String generateRandomString(int len) {
        char[] ans = new char[(int) (Math.random() * (len + 1))];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
//        int len = 20;
//        int testTime = 100000;
       // for (int i = 0; i < testTime; i++) {
           // String test = generateRandomString(len);
            String test = ".X";
            //int ans1 = minLight1(test);
            int ans2 = minLight2(test);
            System.out.println(ans2);
//            if (ans1 != ans2) {
//                System.out.println("oops!");
//            }
      //  }
        System.out.println("finish!");
    }
}
