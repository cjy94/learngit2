package com.chenjunyi.practice;

/**
 *    一个数组中只有'G'和'B'，
 *    可以让所有的G都放在左侧，所有的B都放在右侧  或者 让所有的G都放在右侧，所有的B都放在左侧
 *    但是只能在相邻字符之间进行交换操作， 请问至少需要交换几次
 *
 *       BBGGBGGBGBGBBBGG
 * 结果  GGGGGGGGBBBBBBBB
 */
public class MinSwapStep {

    public static void main(String[] args) {
        String str = "GBGG";
        int i = minStep(str);
    }

    public static int minStep(String str) {
        char[] ch = str.toCharArray();
        int step1 = 0;
        int index = 0;

        for (int i =0; i < ch.length; i++) {
            if (ch[i] == 'G') {
                step1 += (i-index);
                index++;
            }
        }

        int step2 = 0;
        index = 0;
        for (int i=0; i < ch.length; i++) {
            if (ch[i] == 'B') {
                step2 += (i-index);
                index++;
            }
        }
        return Math.min(step1, step2);
    }
}
