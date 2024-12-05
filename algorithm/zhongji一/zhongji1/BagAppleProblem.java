package com.chenjunyi.zhongji一.zhongji1;

/**
 *  思路： 想尽量少的使用袋子，那么应该尽量多的使用8个袋子，
 */
public class BagAppleProblem {


    // 既然想使用最少的袋子，那么应该尽量多的使用装8个苹果的袋子才可以
    public static int minBag(int n) {
        int bag6 = -1;
        int bag8 = n/8;  // 优先使用8个袋子装
        int rest = n - bag8 * 8;
        while (bag8 >= 0) {
            int restUse6 = minBag6(rest);
            if (restUse6 != -1) {
                bag6 = restUse6;
                break;
            }
            rest = n - 8 *(--bag8);
        }
        return bag6 == -1 ? -1 : bag6 + bag8;
    }

    public static int minBag6(int n) {
        return n % 6 == 0 ? (n / 6) : -1;
    }

    public static void main(String[] args) {
        for (int i =1 ; i <= 100; i++) {
            System.out.println(i +": " +minBag(i));
        }
    }
}
