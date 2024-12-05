package com.chenjunyi.zhongji二.zhongji3;

/**
 * 补充题目：贿赂怪兽问题
 * 能力数组[7 2 9]
 * 收买数组[3 6 1]
 *
 * 一个小人，初始能力是0，如果想要从0号怪兽通过所有怪兽，最终到达彼岸至少要花费的钱数？
 */
public class HuiLuGuaiShou {

    // 方法1 ： 如果能力值较小，可以使用方法1； 但是能力值很大，则dp表会很大
    public static int minMoney1(int[] powerArr, int[] moneyArr) {
        return process(powerArr, moneyArr, 0, 0);
    }

    /**
     * 从index号怪兽出发，想要通过index号后面的所有怪兽， 在拥有curPower值的能力下，最少花费的钱数是多少？
     */
    private static int process(int[] powerArr, int[] moneyArr, int index, int curPower) {     
        if (index == powerArr.length) {
            return 0;
        }

        if (curPower < powerArr[index]) {  // 必须贿赂
            return moneyArr[index]
                    +
                    process(powerArr, moneyArr, index+1, curPower+powerArr[index]);
        } else {  // 可贿赂可不贿赂
            return Math.min(
                    process(powerArr, moneyArr, index+1, curPower),
                    moneyArr[index]
                    +
                    process(powerArr, moneyArr, index+1, curPower+powerArr[index])

            );
        }
    }

    // 方法2： 如果能力值都较大， 那么不能使用方法1，
    // dp[i][j]: 表示[0..i-1]怪兽已经通过，想要通过i号怪兽，必须严格花费j钱， 能达到的最大能力
    // 如果dp[i][j] < 0, 表示
    // dp[i][j]: 1) 在通过i-1怪兽的时候，已经花费了j钱， dp[i-1][j] >= powerArr[i]
    //           2) 决定花钱贿赂： dp[i-1][j-moneyArr[i]] ！= -1   dp[i-1][j-moneyArr[i]] + powerArr[i]
    public static int minMoney2(int[] powerArr, int[] moneyArr) {
        // 获取m中的最大值
        int sum = moneyArr[0];
        for (int i =1; i < moneyArr.length; i++) {
            sum += moneyArr[i];
        }
        int n = moneyArr.length;
        // dp[i][j]含义：
        // 能经过0~i号怪兽，且花钱为j（严格花j钱）时的武力值最大时多少
        // 如果dp[i][j]==-1， 表示经过0~i的怪兽，花钱为j是无法通过的，
        int[][] dp = new int[n][sum+1];
        // 默认值修改成-1
        for (int i =0; i < n; i++) {
            for (int j = 0; j <= sum; j++) {
                dp[i][j] = -1;
            }
        }
        dp[0][moneyArr[0]] = powerArr[0];
        for (int i =1; i < n; i++) {
            for (int j =1; j<= sum; j++) {
                if (dp[i-1][j] >= powerArr[i]) {
                    dp[i][j] = dp[i-1][j];
                }

                if ( j-moneyArr[i] >= 0 && dp[i-1][j-moneyArr[i]] != -1) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-moneyArr[i]] + powerArr[i]);
                }
            }
        }

        // dp最后一行表示：严格花费1元通过所有怪兽获得的最大能力值； 严格花费2元通过所有怪兽获得的最大能力值；
        // 严格花费3元通过所有怪兽获得的最大能力值； 遍历最后一行的所有格子， 第一个不是-1的格子，就是答案，表示通过所有怪兽，严格花费j元，
        int res = 0;
        // dp最后一行中第一个不是-1的各自所对应的列数据就是最少的钱
        for (int i =0; i<= sum; i++) {
            if (dp[n-1][i] != -1) {
                res = i;
                break;
            }
        }
        return res;

    }

}
