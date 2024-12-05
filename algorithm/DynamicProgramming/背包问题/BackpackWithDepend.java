package com.chenjunyi.DynamicProgramming.背包问题;

/**
 *  有依赖的背包问题
 *   物品分为两大类：主件和附件
 *   主件购买没有限制，钱够就可以；附件购买有限制，该附件所归属的主件先购买，才能购买这个附件
 *   例如： 若想买打印机或者扫描仪这样的附件，必须先购买电脑这个主件。以下是一些主件以及附件的展示：
 *   电脑：打印机、扫描仪       | 书柜：图书            | 书桌：台灯、文具            | 工作椅：无附件
 *   每个主件最多有2个附件，并且附件不会再有附件，主件购买后，怎么去选择归属附件完全随意，钱够就可以
 *   所有的物品编号再1~m之间，每个物品有三个信息：价格v，重要度p，归属q
 *   价格 * 重要度就是收益，归属是该商品是依附于那个编号的主件
 *   比如一件商品信息为[300,2,6] 花费300， 收益600 该商品是6号主件商品的附件
 *   再比如一件商品信息为[100,4,0] 花费100， 收益400 该商品自身是主件
 *   在给定的m件商品信息，给定总价钱n, 返回在不违反购买规则的情况下最大的收益
 *
 *
 *  输入
 *  number  1  2  3  4  5  6
 *       v  3  2  2  5  3  2        主商品：   2     4     6
 *       p  4  1  3  2  1  7        附件商品： 3    1、5   无
 *       q  4  0  2  0  4  0
 *
 *   在该问题中，只需要对主件商品进行0-1展开讨论，
 *      2号商品不要 --> 4号商品、 2号商品要 --> 4号商品、  2号商品要+3号商品 --> 4号商品
 *      4号商品不要 --> 6号商品、 4号商品要 --> 6号商品、  4号商品要+1号商品 --> 6号商品、  4号商品要+5号商品  --> 6号商品、    4号商品+1和5号商品 --> 6号商品
 *      ....
 *      对每一个主件商品进行情况展开，取最大值
 *
 *      dp[i][j]：在前i件商品中，只关心主商品，在花费不超过j的情况下，获得的最大收益
 *
 */
public class BackpackWithDepend {

    final static int MAX = 10000;
    static int[] cost  = new int[MAX];       //花费数组
    static int[] value = new int[MAX];     // 收益数组
    static boolean[] primary = new boolean[MAX];   // 主件数组，如果是主件true， 否则false
    static int[] fans = new int[MAX];   // 主件商品才会填写，表示主件商品有多少个附件
    static int[][] follows = new int[MAX][2];  // 主件商品才会填写，表示主件商品对应的附件编号



    // m[0] 价格
    // m[1] 重要度
    // m[2] 归属
    // m 有3列
    public static int maxProfit(int[][] m, int n) {
        int len = m.length;   // 所有商品的个数
        int ans = 0;
        for (int i =0; i < len; i++) {
            cost[i] = m[i][0];
            value[i] = m[i][0] * m[i][1];
            primary[i] = m[i][2] == 0;
            if (m[i][2] != 0) {   // 附件商品
                follows[m[i][2]][fans[m[i][2]]++] = i;
            }
        }
        ans += compute(n);
        return ans;
    }

    private static int compute(int n) {
        int count = cost.length;
        int[][] dp = new int[count+1][n+1];
        int p =0;
        for (int i =0;i < count; i++) {
            if (primary[i]) {   // 只对主件商品进行0-1展开
                for (int j = 0; j <= n; j++) {
                    // 不要i商品
                    dp[i][j] =  dp[p][j];
                    // 要该商品
                    if (j - cost[i] >= 0)
                        dp[i][j] = Math.max(dp[i][j], dp[p][j - cost[i]] + value[i]);
                    // 取出附件商品
                    int fan1 = fans[i] >= 1 ? follows[i][0] : -1;
                    int fan2 = fans[i] >= 2 ? follows[i][1] : -1;
                    // 只要fan1
                    if (fan1 != -1 && j - cost[i] - cost[fan1] >= 0)
                        dp[i][j] = Math.max(dp[i][j], dp[p][j - cost[i] - cost[fan1]] + value[i] + value[fan1]);

                    // 只要fan2
                    if (fan2 != -1 && j - cost[i] - cost[fan2] >= 0)
                        dp[i][j] = Math.max(dp[i][j], dp[p][j - cost[i] - cost[fan2]] + value[i] + value[fan2]);

                    // fan1 和fan2都要
                    if (fan1 != -1 && fan2 != -1 && j - cost[i] - cost[fan1] - cost[fan2] >= 0)
                        dp[i][j] = Math.max(dp[i][j], dp[p][j - cost[i] - cost[fan1] - cost[fan2]] + value[i] + value[fan1] + value[fan2]);
                }
                p = i;
            }
        }
        
        return dp[p][n];
    }


}
