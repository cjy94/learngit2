package com.chenjunyi.gaoji一.gaoji5;

/**
 * 求蛇可到达的最大长度
 */
public class Snake {

    // 
    public static class Info{
        int yes;
        int no;
        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }

    }

    public static int snake(int[][] martix) {
        int res = Integer.MIN_VALUE;
        // 列举每一个位置的最大长度，从所有位置中再取一个最大值
        // 蛇可以在任何一个位置停， 取得最大长度
        for (int row = 0; row < martix.length; row++) {
            for (int col = 0; col < martix[0].length; col++) {
                Info info = process(martix, row, col);
                res = Math.max(res, Math.max(info.yes, info.no));
            }
        }
        return res;
    }

    // 从最左侧出发，到达martix[row][col]
    // 在这个旅程中
    // no 一次能力也不用，能到达的最大路径和  (如果返回负数，表示无效)
    // yes 用了一次能力后，能到达的最大路径和
    public static Info process (int[][] martix, int row, int col) {
        // 第一列的情况
        if (col == 0) {
            // 在第一列，初次登陆时，可以使用能力，也可以不使用能力
            return new Info(-martix[row][col], martix[row][col]);
        }

        // 普遍位置
        int preNo = Integer.MIN_VALUE;   // 上一个位置不使用能力的最大长度
        int preYes = Integer.MIN_VALUE;  // 上一个位置使用能力的最大长度

        // 从左上角到达 martix[row][col]
        if (row > 0) {
            Info leftUp = process(martix, row-1, col-1);
            if (leftUp.no >= 0) {
                preNo = leftUp.no;
            }
            if (leftUp.yes >= 0) {
                preYes = leftUp.yes;
            }
        }
        // 从左侧位置到达 martix[row][col]
        Info left = process(martix, row, col -1);
        if (left.no >= 0) {
            preNo = Math.max(preNo, left.no);
        }
        if (left.yes >= 0) {
            preYes = Math.max(preYes, left.yes);
        }

        // 从左下角位置到达 martix[row][col]
        if (row < martix.length-1) {
            Info leftDown = process(martix, row+1, col-1);
            if (left.no >= 0) {
                preNo = Math.max(preNo, leftDown.no);
            }
            if (left.yes >= 0) {
                preYes = Math.max(preYes, leftDown.yes);
            }
        }

        // 封装martix[row][col]位置的最大长度
        int yes = Integer.MIN_VALUE;
        int no = Integer.MIN_VALUE;
        // no表示一次能力也不用的选择
        // yes表示用过一次能力，只要用过一次能力就是yes
        // 之前没用过能力，那么当前位置就有两种选择，1、当前位置也不用能力; 2、当前位置用能力
        if (preNo >= 0) {
            no = preNo + martix[row][col];
            yes = preNo + (-martix[row][col]);
        }
        // 之前已用过一次能力，此处只能选择不用能力
        // 虽然此处没有用能力，但是之前已经使用过能力，所以属于yes的值
        if (preYes >= 0) {
            yes = Math.max(yes, preYes + martix[row][col]);
        }
        return new Info(yes, no);
    }


    // 通过添加缓存的方法，进行加速， 不需要每次都把之前的位置计算一遍，会出现一个位置被重复计算多次的情况
    public static int snake1(int[][] martix) {
        int res = Integer.MIN_VALUE;
        Info[][] dp = new Info[martix.length][martix[0].length];
        for (int row = 0; row < martix.length; row++) {
            for (int col = 0; col < martix[0].length; col++) {
                Info info = process1(martix, row, col, dp);
                res = Math.max(res, Math.max(info.yes, info.no));
            }
        }
        return res;
    }

    // 从最左侧出发，到达martix[row][col]
    // 在这个旅程中
    // no 一次能力也不用，能到达的最大路径和  (如果返回负数，表示无效)
    // yes 用了一次能力后，能到达的最大路径和
    public static Info process1 (int[][] martix, int row, int col, Info[][] dp) {

        // 先从缓存中取数据
        if (dp[row][col] != null) {
            return dp[row][col];
        }

        // 第一列的情况
        if (col == 0) {
            // 在第一列，初次登陆时，可以使用能力，也可以不使用能力
            dp[row][col] = new Info(-martix[row][col], martix[row][col]);
            return dp[row][col];
        }

        // 普遍位置
        int preNo = Integer.MIN_VALUE;   // 上一个位置不使用能力的最大长度
        int preYes = Integer.MIN_VALUE;  // 上一个位置使用能力的最大长度

        // 从左上角到达 martix[row][col]
        if (row > 0) {
            Info leftUp = process(martix, row-1, col-1);
            if (leftUp.no >= 0) {
                preNo = leftUp.no;
            }
            if (leftUp.yes >= 0) {
                preYes = leftUp.yes;
            }
        }
        // 从左侧位置到达 martix[row][col]
        Info left = process(martix, row, col -1);
        if (left.no >= 0) {
            preNo = Math.max(preNo, left.no);
        }
        if (left.yes >= 0) {
            preYes = Math.max(preYes, left.yes);
        }

        // 从左下角位置到达 martix[row][col]
        if (row < martix.length-1) {
            Info leftDown = process(martix, row+1, col-1);
            if (left.no >= 0) {
                preNo = Math.max(preNo, leftDown.no);
            }
            if (left.yes >= 0) {
                preYes = Math.max(preYes, leftDown.yes);
            }
        }

        // 封装martix[row][col]位置的最大长度
        int yes = Integer.MIN_VALUE;
        int no = Integer.MIN_VALUE;
        // no表示一次能力也不用的选择
        // yes表示用过一次能力，只要用过一次能力就是yes
        // 之前没用过能力，那么当前位置就有两种选择，1、当前位置也不用能力; 2、当前位置用能力
        if (preNo >= 0) {
            no = preNo + martix[row][col];
            yes = preNo + (-martix[row][col]);
        }
        // 之前已用过一次能力，此处只能选择不用能力
        // 虽然此处没有用能力，但是之前已经使用过能力，所以属于yes的值
        if (preYes >= 0) {
            yes = Math.max(yes, preYes + martix[row][col]);
        }
        dp[row][col] = new Info(yes, no);
        return dp[row][col];
    }


    // 蛇有可能在任意位置取得最大长度， 未必是右侧的位置，因为有可能右侧到达不了
    public static int snakeWays(int[][] matrix) {
        int res = Integer.MIN_VALUE;
        // 枚举每一个位置上的可向右侧行进的最大值
        for (int row =0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                Info info = processWays(matrix, row, col);
                res = Math.max(res, Math.max(info.yes, info.no));
            }

        }
        return res;

    }

    // 蛇从martix[row][col]开始能向右侧做出的最大长度
    // Info中 no表示一次能力也不用，能达到的最大路径和（如果是负数，则表示没有方案）
    //       yes表示用了一次能力，能达到的最大路径和（如果是负数，则表示没有方案）
    private static Info processWays(int[][] matrix, int row, int col) {
        // 默认是从左侧开始登录，往右侧走出最大的距离，所以， 第一列就是base case
        if (col == 0) {
            return new Info(-matrix[row][0], matrix[row][0]);
        }

        // 之前旅程中的yes和no信息
        int preYes = -1;  // 之前的旅程中用了一次能力
        int preNo = -1;   // 之前的旅程中一次能力也没用

        // 从左下角来 ， 在第1、2、3、4...行一定右左上角的位置
        if (row>0) {
            Info leftDown = processWays(matrix, row+1, col-1);
            if (leftDown.yes >= 0) {
                preYes = leftDown.yes;
            }
            if (leftDown.no >= 0) {
                preNo = leftDown.no;
            }
        }

        // 从左侧来, 总是有左侧信息，因为不可能是在第一列上
        Info left = processWays(matrix, row, col-1);
        if (left.no >= 0) {
            preNo = Math.max(preNo, left.no);
        }
        if (left.yes >= 0) {
            preYes = Math.max(preYes, left.yes);
        }
        // 从左上角来 ， 在n-1、n-2、n-3...1行一定有左下角的位置
        if (row < matrix.length) {
            Info leftUp = processWays(matrix, row-1, col-1);
            if (leftUp.no >= 0) {
                preNo = Math.max(preNo, leftUp.no);
            }
            if (leftUp.yes >= 0) {
                preYes = Math.max(preYes, leftUp.yes);
            }
        }


        // 编辑当前节点的yes和no信息
        int yes = -1; // 用了一次能力的最大距离， 可以是之前的旅程中没用能力，本次旅程使用； 也可以是之前的旅程中用了，本次旅程中不用
        int no = -1;  // 一次能力也不用的最大距离， 之前的旅程中一次能力没用，本次节点也不用


        // 如果之前的旅程中，没有使用能力值，并且有效，那么我既可以使用能力，也可以不使用能力
        if (preNo > 0) {
            // 现在用能力，之前没用
            yes = -matrix[row][col] + preNo; // 当前节点使用能力，之前的所有旅程都不能使用能力
            // 之前没用能力，现在也不用能力
            no = matrix[row][col] + preNo;
        }

        // 如果之前的旅程中使用了能力，那么，整体都算是使用过能力了， 我当前只能不使用能力
        if (preYes > 0) {
            // 之前用能力，现在不用能力
            yes = Math.max(yes, preYes+matrix[row][col]);
        }
        return new Info(yes, no);
    }


    public static void main(String[] args) {
        int[][] martix = {
                {1,-4,10},
                {3,-2,-1},
                {2,-1,0},
                {0,5,-1}
        };
        int snake = snake1(martix);
        System.out.println(snake);
    }
}
