package com.chenjunyi.GO.FromLeftToRight;

/**
 *  从左往右的尝试模型
 *  给定
 */
public class BackPack {

    public static int maxValue(int[] weights, int[] values, int bag) {

        return process(weights, values, bag, 0, 0);
    }

    // 不变的值是weights、values、bag   每个货物的重量， 每个货物的价值，总背包的zhongl
    // 表示的是index...往后的货物随意选择，在不超过bag的情况下，能取得货物的最大价值，
    //      其中0..index-1之间的获取取得的货物重量是alreadyW
    private static int process(int[] weights, int[] values, int bag, int index, int alreadyW) {
        // index... 往后选择的货物已经超过的总重量，
        // 返回-1, 表示这次选择无意义
        if (alreadyW > bag) {
            return -1;
        }

        // 背包还有重量， 但是没有货物可选择了
        // 返回0 表示的是，index... 没有货物可选择了，那么重量也就是0，队医0...index-1之间的重量不影响
        // 返回的是index...往后的价值
        if (index == weights.length) {
            return 0;
        }

        int p1 = process(weights, values, bag, index+1, alreadyW);
        // 选择index货物
        // 要了index货物之后， 后面的货物产生的最大价值
        int p2Next = process(weights, values, bag, index+1, alreadyW-weights[index]);
        int p2 = -1;
        if (p2Next != -1) { // 如果后面货物产生的最大价值不是-1，   当前货物的价值values[i] 加上后序选择的最大价值
            p2 = values[index] + p2Next;
        }

        return Math.max(p1, p2);
    }


}
