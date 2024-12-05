package com.chenjunyi.force;

/**
 * 从左往右的尝试模型
 *
 * 问题 1:
 * 规定1和A对应、2和B对应、3和C对应....
 * 那么一个数字字符串比如“111”就可以转化为： "AAA" "KA" "AK"
 * 给定一个只有数字字符组成的字符串str，返回右多少种转化结果
 *
 */
public class TryModel1 {

    public static void main(String[] args) {
        int[] w = {3,2,4,7};
        int[] v = {5,6,3,19};
        System.out.println(maxValue1(w, v, 11));
        System.out.println(maxValue(w, v, 11));

    }
    /**
     * 字符串不能以0开头
     * 1开头的字符串后面 如果后面是0, 那必须10； 如果不是0,
     *
     * @param str
     * @return
     */
    public static int strToString(String str) {
        if (str==null || str.length() == 0) {
            return 0;
        }

        return process(str, 0);


    }

    private static int process(String str, int i) {
        if (i == str.length()) {
            return 1;
        }

        char cur = str.charAt(i);
        // "075" 不能进行转化， 方法是0
        if (cur == '0') {
            return 0;
        }

        // "11" 有两种方法 "1"->A 和 "11" --> K
        if (cur == '1') {
            // 两种, i自己， (i+1)自由选择
            int yes = process(str, i+1);
            if (i+1 < str.length()) {
                // i和（i+）组成，（i+2）自由选择
                yes += process(str, i+2);
            }
            return yes;
        }

        // 只有 "20"-"26" 这些可以进行转化
        if (cur == '2') {
            int yes = process(str, i+1);
            if (i+1 < str.length() && 0 <= str.charAt(i+1) - '0' && str.charAt(i+1) -'0' <= 6) {
                yes += process(str, i+2);

            }
            return yes;
        }


        // 3~9 的情况 , 只有一种选择, 自己组成
        return process(str, i+1);

    }

    //==================================================背包问题====================================
    /**
     * 给定两个长度都为N的数组，weights和values, weights[i]和values[i]分别代表i号物品的重量和价值
     * 给定一个bag，表示一个载重bag的待子，装的物品不超过这个给重量，返回能装下最多的价值是多少
     */

    public static int maxValue(int[] weights, int[] values, int bags) {
        if (weights == null || weights.length == 0 || values==null || values.length == 0 || bags<=0) {
            return 0;
        }

        return processBag(weights, values, bags, 0, 0);
    }

    // 0...index-1 做了选择，使得已经达到的重量是alreadyW
    private static int processBag(int[] weights, int[] values, int bags, int index, int alreadyW) {
        // 重量超了
        if (alreadyW > bags) {
            // 返回-1 认为没有方案
            return -1;
        }
        // 重量没超 ， 但是没有或可选了
        if (index == weights.length) {
            return 0;
        }

        // 不选择当前货物放入背包， 后续选择的最大价值
        int p1 = processBag(weights, values, bags, index+1, alreadyW);

        // 选择当前货物放入背包， 后续选择的最大价值
        int p2Next = processBag(weights, values, bags, index+1, alreadyW+weights[index]);
        int p2 = -1;
        if (p2Next != -1) {
            // 后续的最大价值 + 当前货物的价值
            p2 = values[index] + p2Next;
        }
        return Math.max(p1, p2);

    }

    public static int maxValue1(int[] weights, int[] values, int bags) {
        if (weights == null || weights.length == 0 || values==null || values.length == 0 || bags<=0) {
            return 0;
        }

        return processBag1(weights, values, 0, bags);
    }

    //  index 位置开始往后自由选择，还剩的空间rest
    // index.. 获取自由选择，凡是剩余
    private static int processBag1(int[] weights, int[] values, int index, int rest) {
        // 已经没有空间了
        if (rest < 0) {
            return -1;
        }

        // 还有空间但是没有货物了
        if(index == weights.length) {
            return 0;
        }

        // 不选择当前货物的，后续选择的最大价值
        int p1 = processBag1(weights, values, index+1, rest);

        // 不选择当前货物的，后续选择的最大价值
        int p2Next =  processBag1(weights, values, index+1, rest-weights[index]);
        int p2 = -1;
        if (p2Next != -1) {
            // 后续的最大价值 + 当前货物的最大价值
            p2 = p2Next + values[index];
        }
        return Math.max(p1, p2);
    }


}
