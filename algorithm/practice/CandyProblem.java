package com.chenjunyi.practice;


import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * 小朋友糖问题
 * 1) 每个孩子至少一颗糖
 * 2) [i]比[i-1]多或者比[i+1]多，要多分一块糖，
 *       [1,3,3,2,1,4,2,2,5,2,1]
 * left  [1,2,1,1,1,2,1,1,2,1,1]
 * right [1,1,3,2,1,2,1,1,3,2,1]
 * max   [1,2,3,2,1,2,1,1,3,2,1]
 *
 *
 * 
 *
 *
 *
 * 
 *
 * 
 */
public class CandyProblem {

    // 编辑距离
    // 让字符串str1变成str2的代价，最小是多少？ 操作：删除、替换、添加的代价
    /*
    * 1、dp[i][j-1] + add
    * 2、dp[i-1][j] + delete
    * 3.1、str1[i-1] == str2[j-1]   dp[i-1][j-1]
    * 3.2、str1[i-1] != str2[j-1]   dp[i-1][j-1] + replace
    * 四种代价取最小值
    * */


    static class Info{
        int value;
        int index;
        public Info(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
    // 公式字符串结算结果
    // 字符串格式是正确的
    // 字符串： 3*5+2*(4/2+45)+(8-2+9/3)
    // 利用递归压栈的方式
    public static Info total(char[] ch, int index) {
        LinkedList<String> queue = new LinkedList<>();
        int cur = 0;
        while (index < ch.length && ch[index] != ')') {
            char s = ch[index];
            // s是数字
            if (s >= '0' && s <= '9') {
                cur = cur * 10 + s - '0';
            } else if(s != '(') {  // s是运算符
                // 将数值压入栈
                addNum(queue, cur);
                // 将s压入栈
                queue.addLast(String.valueOf(s));
                cur = 0;
            }  else {  // s是'('
                Info info = total(ch, index + 1);
                cur = info.value;
                index = info.index + 1;
            }
        }

        // 要么到字符串末尾了，要么遇到')'了，组要将队列中数据进行结算
        addNum(queue, cur);
        return new Info(getNum(queue), index);
    }

    // 将value压入到queue队列中
    private static void addNum(LinkedList<String> queue, int value) {
        if (!queue.isEmpty()) {
            String top = queue.pollLast();
            if (top.equals("*") || top.equals("/")) {
                int cur = Integer.valueOf(queue.pollLast());
                value = top.equals("*") ? (cur * value) : (cur / value);
            }  else {
                queue.addLast(top);
            }
        }
        queue.addLast(String.valueOf(value));
    }

    // 计算队列中queue的数值，队列中的运算符只有"+" / "-"
    private static int getNum(LinkedList<String> queue) {
        boolean add = false;
        int ans = 0;
        int num = 0;
        while (!queue.isEmpty()) {
            String expr = queue.pollLast();
            if (expr.equals("+")) {
                add = true;
            } else if (expr.equals("-")) {
                add = false;
            }  else {
               num = Integer.valueOf(expr);
               ans += add ? num : (-num);
            }
        }
        return ans;
    }


     // 随意选择两根支柱所能容纳的最大水量
     // 定义两个左右指针，谁小结算谁


    /* 无效括号串变有效括号串
     * ()((())))
     * 一种可能性    (((())))
     * 一种可能性    ()((()))
     * 递归代码， 难*****
     */
    public static void flag(String str) {

    }

    // 最长递增子序列问题
    // {3,2,1,2,3,0,4,6,2,7}  时间复杂度是o(n^2)
    public static int largestIncreasingSubArray1(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        dp[0] = 1;
        int max = 1;
        int ans = 1;
        for (int i =1; i < n; i++) {
            for (int j =0; j <i; j++) {
                if (arr[i] > arr[j]) {
                    max= Math.max(dp[j] + 1, max);
                }
                dp[i] = max;
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    // {3,2,1,2,3,0,4,6,2,7}
    // 优化1的方法  时间复杂度是o(n * logN)
    public static int largestIncreasingSubArray2(int[] arr) {
        int n = arr.length;
        int[] end = new int[n];  // end[i]表示目前所有长度为i+1的递增子序列中最小结尾是什么
        end[0] = arr[0];
        int l = 0;
        int r = 0;
        int m = 0;
        int max = 1;
        int right = 0;  // end数组实际使用的长度
        for (int i=1; i < n; i++) {
            l = 0;  // end数组的l指针
            r = right;  // end数组的r指针
            while (l <= r) {
                m = (l + r) / 2;
                if (arr[i] <= end[m]) {
                    r = m-1;
                } else {
                    l = m+1;
                }
            }
            right = Math.max(right, l);
            end[l] = arr[i];
            max = Math.max(max, l+1);
        }
        return max;
    }


    /**
     * 定义step num
     * 比如680   680 + 68 + 6 = 754， 680的step num较754
     * 给定一个整数num， 判断它是不是某个数的step num
     *
     * 思路： 二分
     */

    public static boolean isStepNum(int num) {
        int l = 1;
        int r = num;
        int m = 0;
        int step = 0;
        while (l <= r) {
            m = (l+r)/2;
            step = stepNum(m);
            if (step == num)
                return true;
            else if (step < num) {
                l = m+1;
            } else {
                r = m-1;
            }
        }
        return false;
    }


    public static int stepNum(int num) {
        int ans = 0;
        while (num != 0) {
            ans += num;
            num /= 10;
        }
        return ans;
    }


    // 跳跃问题

    public static int step(int[] arr) {
        int step = 0;
        int cur = 0;
        int next = 0;
        for (int i =0; i <arr.length; i++) {
            if (cur < i) {
                step++;
                cur = next;
            }
            next = Math.max(next, i + arr[i]);
        }
        return step;
    }


    /**
     * 洗衣机问题
     *   arr[]每一个位置代表一个洗衣机， 希望每个洗衣机中的衣服同样多
     *   每个洗衣机中的衣服，只能向右侧洗衣机中扔1件，或者向左侧洗衣机中扔1件 (每一轮)
     *   但是一轮中，可以多个洗衣机同时扔衣服
     *   问：希望每个洗衣机中的衣服相同多，最多扔多少件衣服
     *
     * 情况1：一个正数， 一个负数的情况
     *   Math.max(|left|, |right|)
     *
     * 情况2： 两个都是负数的情况
     *  |left| + |right|
     *
     * 情况3： 两个都是正数的情况
     *  Math.max(left, right)
     */

    public static int findMinMoves(int[] arr) {
        if (arr == null || arr.length == 0)
            return 0;

        int sum = 0;
        for (int value : arr)
            sum += value;
        int size = arr.length;
        if (sum % size != 0)
            return -1;
        int avg = sum / size;
        int ans = 0;
        int leftSum = 0;
        for (int i =0; i < size; i++) {
            int leftRest = leftSum - i * avg;                                  // 左侧需要的衣服数量
            int rightRest = (sum - leftSum - arr[i]) - (size -i -1) * avg;     // 右侧需要的衣服数量
            if (leftRest < 0 && rightRest < 0)
                ans = Math.max(ans, Math.abs(leftRest) + Math.abs(rightRest));
            else
                ans = Math.max(ans, Math.max(Math.abs(leftRest), Math.abs(rightRest)));

            leftSum += arr[i];
        }
        return ans;
    }

    /**
     * 约瑟夫环问题
     *  有一个环，每次数到m就杀人， 求最终存活的节点在原始环中编号是多少？
     *  函数： y = x % i
     *
     *  左加右减， 上加下减
     *
     *  编号 = (数 - 1) % i + 1;
     * 前编号 = (后编号 -1 + s) % i +1;
     *
     */

    /**
     * 给定一个每一行有序、每一列有序 整体可能无序的二位数组
     * 在给定一个正数k，返回二维数组中，最小的第k个数
     *
     * 在二维数组中， 可以球的小于等于某个数右多少个?
     * 
     */


    /**
     *  给定两个字符串S和 T
     *  返回S的所有子序列中有多少子序列的字面值等于T
     *
     *  动态规划dp[i][j] s[0...i]随意组成子序列， 有多少个子序列的字面值等于T[0..j]
     *
     *  S[i]字符参与子序列      S[i-1] == T[j-1]     dp[i-1][j-1]
     *  S[i]字符不参与子序列  dp[i-1][j]
     *
     *  dp[i][j] = dp[i-1][j] + (S[i-1] == T[j-1] ? dp[i-1][j-1] : 0)
     *
     *  
     *
     *  dp[i][j]就是答案
     */

    public static int dp(String S, String T) {
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        int N = s.length;
        int M = t.length;
        int[][] dp = new int[N][M];
        for (int i =1; i < N; i++) {
            dp[i][0] = (s[i] == t[0] ? (dp[i-1][0] + 1) : dp[i-1][0]);
        }

        for (int i =1; i < N; i++) {
            for (int j = 1; j <= Math.min(i,M-1); j++) {
                dp[i][j] = dp[i-1][j] + (s[i-1] == t[j-1] ? dp[i-1][j-1] : 0);
            }
        }
        return dp[N-1][M-1];
    }



    /**
     * 返回字符串中有多少个不同字面值的子序列
     * aabb
     *
     * 第一个a：""，{a}
     * 第二个a: {a} {a,a} , ""                                                              其中{a}重复
     * 第一个b: {a,b} {a,a,b} {b} ,  {a} {a,a} ""
     * 第二个b: {a,b,b} {a,a,a,b,b} {b,b} {a,b} {b} ,  {a,b} {a,a,b} {b} {a} {a,a} ""       其中{a,b}重复
     *   
     */

    public static int subSequence(String str) {
        HashMap<Character, Integer> map = new HashMap<>(); // 存储，上次以某个字符结尾的子序列个数
        char[] s = str.toCharArray();
        int all = 1;
        for (int i= 0; i < s.length; i++) {
            int newAdd = all;
            int curAll = all + newAdd - (map.containsKey(s[i]) ? map.get(s[i]) : 0);
            map.put(s[i], newAdd);
            all = curAll;
        }
        return all;
    }

    /**
     * 联通两块岛， 最短的桥
     *
     *  难点： 将二维数组中的元素转换成一维数组
     *  [1 2 4
     *   2 3 5]      -->  [1 2 4 2 3 5]
     *
     *  下标变换  [i][j]二维数组的下标计算成 对应一维数组的下标 (i * 列数) + j
     *  [0][0] --> 0*3+0=0
     *  [0][1] --> 0*3+1=1
     *  [0][2] --> 0*3+2=2
     *  [1][0] --> 1*3+0=3
     *  [1][1] --> 1*3+1=4
     *  [1][2] --> 1*3+2=5
     * 
     */

    public static int shortBridge(int[][] matrix) {
        int N = matrix.length;      // N行
        int M = matrix[0].length;   // M列
        int all = N * M;            // 二维数组中所有元素的个数
        int curs[] = new int[all];
        int nexts[] = new int[all];
        int[][] records = new int[2][all];
        int island = 0;
        for (int i =0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == 1) {  // 当前位置是岛，开始向外发散
                    int queueSize = infect(matrix, i, j, N, M, curs, 0, records[island]);
                    int V = 1;
                    while (queueSize != 0) {
                        V++;
                        queueSize = bfs(N, M, all, V, curs, queueSize, nexts, records[island]);
                        int[] temp = curs;
                        curs = nexts;
                        nexts = temp;
                    }
                    island++;
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i =0; i < all; i++) {
            ans = Math.min(records[0][i]+records[1][i], ans);
        }
        return ans;
    }

    // bfs 宽度优先遍历

    /**
     *   将一维坐标转换成原始二维数组坐标
     *
     * 6行5列
     *   [11 12 13 14 15
     *    16 17 18 19 20
     *    21 22 23 24 25
     *    26 27 28 29 30
     *    31 32 33 34 35
     *    36 37 38 39 40]
     *
     *    第一列坐标：
     *    (0,0)  -> 0*5+0=0
     *    (1,0)  -> 1*5+0=5
     *    (2,0)  -> 2*5+0=10
     *    (3,0)  -> 3*5+0=15
     *    (4,0)  -> 4*5+0=20
     *    (5,0)  -> 5*5+0=25
     *    是不是第一列的坐标： 坐标 % 列数 == 0
     *
     *    最后一列的坐标：
     *    (0,4)  -> 4
     *    (1,4)  -> 9
     *    (2,4)  -> 14
     *    (3,4)  -> 19
     *    (4,4)  -> 24
     *    (5,4)  -> 29
     *    是不是最后一列的坐标： 坐标 % (列数) == 列数-1
     *
     *    第一行的坐标：
     *    (0,0)  -> 0
     *    (0,1)  -> 1
     *    (0,2)  -> 2
     *    (0,3)  -> 3
     *    (0,4)  -> 4
     *    是不是第一行的最表： 坐标 / 列数  == 0
     *
     *    最后一行的坐标：
     *    (4,0)  -> 20
     *    (4,1)  -> 21
     *    (4,2)  -> 22
     *    (4,3)  -> 23
     *    (4,4)  -> 24
     *    是不是最后一行的最表： 坐标 / 列数  == 列数-1
     *
     *
     *    一维坐标a, 处于中间位置，不在矩阵的4边
     *    左侧： a-1    右侧： a+1
     *    上侧： a-列数  下侧： a+列数
     *
     *   有n行m列， 总数据大小是all， 在第V层， 遍历curs中的一维坐标，curs有效数据大小是queueSize， 将curs数据计算后写入岛nexts数组中
     */
    public static int bfs(int N, int M, int all, int V, int[] curs, int queueSize, int[] nexts, int[] records) {
        int nexti = 0;
        for (int i =0; i < queueSize; i++) {    // a/M 就是行号
            int up = (curs[i] / M == 0) ? -1 : curs[i] - M;
            int down = (curs[i] / M == M-1) ? -1 : curs[i] + M;
            int left = (curs[i] % M == 0) ? -1 : curs[i] -1;
            int right = (curs[i] % M == M -1) ? -1 : curs[i] + 1;
            if (up != -1 && records[up] == 0) {
                nexts[nexti++] = up;
                records[up] = V;
            }
            if (down != -1 && records[down] == 0) {
                nexts[nexti++] = down;
                records[down] = V;
            }
            if (left != -1 && records[left] == 0) {
                nexts[nexti++] = left;
                records[left] = V;
            }
            if (right != -1 && records[right] == 0) {
                nexts[nexti++] = right;
                records[right] = V;
            }
        }
        return nexti;
    }
    

    /**
     *  当前来到m[i][j], 总行数是N， 总列数是M
     *  m[i][j]感染出去，把每一个1的坐标， 放入岛int[] curs队列!
     *  1 坐标(a,b) -> curs[index++] (a*M+b)
     *  1 坐标(c,d) -> curs[index++] (c*M+d)
     *  二维坐标转变成一维坐标  (a,b) -> record[a*M+b] =1
     */
    public static int infect(int[][] m, int i, int j, int N, int M, int[] curs, int index, int[] record) {
        if (i < 0 || i > N || j < 0 || j > M || m[i][j] != 1) {
            return index;
        }

        m[i][j] = 2;// 遍历值为1的岛， 将遍历过的1修改成2，防止重复计算
        int one = i * M + j;
        record[one] = 1;
        curs[index++] = one;
        // 上下左右4个方向， 遍历岛
        index = infect(m, i, j-1, N, M, curs, index, record);
        index = infect(m, i, j+1, N, M, curs, index, record);
        index = infect(m, i-1, j, N, M, curs, index, record);
        index = infect(m, i+1, j, N, M, curs, index, record);
        return index;
    }


    /**
     * 最小包含区间问题
     * [1,7,30
     * 21,54
     * 3,67]
     *
     * 这样一个矩阵中，每行必须包含一个元素，求改矩阵的最小包含区间
     * 
     *  TreeSet/TreeMap 有序表
     */

    public static int smallestRange(int[][] matrix) {
        return 0;
    }


    /**
     * 超级水王问题，
     *
     * 给定一个正数K， 返回所有出现次数>N/K的数
     * 一次删两个不同值的数，
     * 1) 没有数剩下来，无水王数
     * 2) 有数剩下来， 遍历一遍数组的元素，计算这个数出现的次数，是否大于N/K
     */


    /**
     * 相邻的k个数合成一个数 要求数组中所有数合并成K个数的最小代价
     *
     * 问题：
     * 1、如何判断这个数组中的数能最终合成k个数？
     * 2、动态规划求解，最终要合成K个数，可能的情况 arr[0] 和 arr[1...N]
     *   arr[0..0] 1份  arr[1...N] k-1份
     *   arr[0..1] 1份 arr[2...N]  k-1份
     *   arr[0..2] 1份 arr[3...N]  k-1份
     *   arr[0..3] 1份 arr[4...N]  k-1份 ...
     *
     *   (length -1) % (K-1) == 0 就可以合成K个数
     */


    /**
     * 最小包含子串
     *
     * str1="XXXXacbdcXXXX"   str2="bcca"
     * str1中包含str2的最小子串是： acbdc
     *
     */

    public static int minLength(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() < s2.length()) {
            return Integer.MAX_VALUE;
        }

        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] map = new int[256];
        for (int i =0; i < str2.length; i++) {
            map[str2[i]]++;
        }
        int all = str2.length;

        int L = 0;
        int R = 0;
        int minLength = 0;
        while (R < str1.length) {
            map[str1[R]]--;
            if (map[str1[R]] >= 0) {      // 有效的还款 all--； 无效还款all不变
                all--;
            }
            if (all == 0) {    // 全部还款结束，开始计算最小包含
                while (map[str1[L]] < 0) {     // 缩
                    map[str1[L++]]++;
                }
                minLength = Math.min(minLength, (R-L+1));
                all++;
                map[str1[L++]]++;
            }
            R++;
        }
        return minLength;
    }


    /**
     *  一个字符串每种字符只保留一个， 字典序最小的结果
     */
    public static String removeDuplicateLetters(String str) {
        if (str == null || str.length() == 0)
            return str;

        int[] map = new int[256];
        for (int i =0; i < str.length(); i++) {
            map[str.charAt(i)]++;
        }

        int minASCIndex = 0;
        for (int i =0; i < str.length(); i++) {
            minASCIndex = str.charAt(i) > str.charAt(minASCIndex) ? minASCIndex : i;       // 记录str字符串中遍历到的最小的字符的索引位置
            if (--map[str.charAt(i)] == 0) {
                break;
            }
        }

        return String.valueOf(str.charAt(minASCIndex)) + removeDuplicateLetters(str.substring(minASCIndex+1).replaceAll(String.valueOf(str.charAt(minASCIndex)), "") );

    }

    static class Node1 implements Comparator<Node1> {
        int value;
        int arrIndex;
        int ownIndex;

        public Node1(int v, int a, int o) {
            value = v;
            arrIndex = a;
            ownIndex = o;
        }

        @Override
        public int compare(Node1 o1, Node1 o2) {
            return o1.value != o2.value ? (o1.value - o2.value) : (o1.toString().compareTo(o2.toString()));
        }
    }

    /**
     *   三个有序数组，选三个数彼此相减的绝对值相加怎么最小
     *   |X-Y| + |Y-Z| + |Z-X| 尽量小   --->  2(X-Z)
     *
     *   (x-z) 尽量小， 选择一个最小的范围，并且这个范围中包含三个数组中的数
     *   最窄区间问题    有序表
     */
    public static int minRange(int[][] martix) {
        int N = martix.length;
        TreeSet<Node1> set = new TreeSet<>();
        for (int i = 0; i < N; i++) {
            set.add(new Node1(martix[i][0], i, 0));
        }
        int min = 0;
        while (set.size() == N) {
            min = Math.min(min, (set.last().value - set.first().value));
            Node1 first = set.pollFirst();
            int arrIndex = first.arrIndex;
            int ownIndex = first.ownIndex;
            if (ownIndex < martix[arrIndex].length -1) {
                set.add(new Node1(martix[arrIndex][ownIndex+1], arrIndex, ownIndex+1));
            }
        }
        return min;
    }





    /**
     * 鹅厂文化衫
     *  向上取整
     *  a/b --> (a+b-1)/b
     */
    public static int minPeople(int[] arr) {
        if (arr == null || arr.length == 0)
            return 0;

        Arrays.sort(arr);
        int pre = arr[0];
        int ans = 0;
        int count = 1;
        for (int i =1; i < arr.length; i++) {
            if (arr[i] == pre) {
                count++;

            } else {
                ans += ((count+pre) / (pre+1)) * (pre+1);
                pre = arr[i];
                count = 1;
            }
        }
        return ans + ((count+pre) / (pre+1)) * (pre+1);
    }

    /**
     *   有效的括号 '{'、  '['、  '('
     *
     *   遇到左括号就压栈，遇到右括号就将栈顶的元素进行匹配，匹配成功弹出栈，随后栈空匹配成功；否则匹配失败
     */

    /**
     *   1、有效的数独
     *   判断在同一行，同一列，同一个桶中，一个数只出现一次 [1-9]
     *
     *   每个桶中9个元素 (3*3)
     *   桶的计算： 3*(i/3) + (j/3)
     *
     *   2、解数独
     *   
     *
     */

    /**
     *  实现pow()  pow(x,n) 计算x^n
     *
     *  求 10^75
     *  75的二进制： 1001011
     */

    public static int pow(int x, int n) {
        int ans = 1;
        int a = x;
        while (n != 0) {
            if ((n & 1) != 0) {
                ans *= a;
            }
            n >>= 1;
            a *= a;
        }
        return ans;
    }

    /**
     *  开根号 sqrt(x)
     *  二分计算： 1-X 取中间值， 2/x 计算(2/x)^2 和x的关系
     */

    public static int sqrt(int x) {  // 对x开根号
        int L = 1;
        int R = x;
        int ans = 1;
        while (L <= R) {
            int m = (L+R) / 2;
            if (m * m < x) {
                ans = m;
                L = m+1;
            } else {
                R = m-1;
            }
        }
        return ans;
    }

    /**
     *  累乘积
     *  每个位置求两个值min和max
     *
     *   preMin preMax
     */

    /**
     *  阶乘后的零
     *
     *  有多少5的因子
     *
     */

    /**
     *  颠倒二进制位
     *   16    8    4    2    1
     *
     * 素数：除了1和自己之外，没有其他因子, 计算一个非负数n的质数个数
     * 对于这个数n，可以确定的是1-n中有一半的偶数是可以排除的，所以素数最多也就是n/2个质数
     *
     * 然后从3开始排除偶数的个数，3*3     3*5     3*7     3*9    3*11    3*13......
     *                         5*5     5*7     5*9     5*11   5*13    5*15......
     *     2、4、6、8、10... 可以排除，因为n/2已经将其偶数排除了， 3*2   3*4   3*6   3*8   3*10...   5*2    5*4   5*6    5*8.... 也可以排除
     *
     */

    public static int primeCount(int n) {
        if (n == 1 || n == 2) {
            return 0;
        }

        int count = n/2;
        boolean[] flag = new boolean[n];
        // 3 5 7 9 判断， 2 4 6... 偶数排除
        for (int i =3; i * i < n; i += 2) {
            if(flag[i]) {
                continue;
            }
            for (int j = i*i; j < n; j += 2 * i) {
                if (!flag[j]) {
                    count--;
                    flag[j] = true;
                }
            }
        }
        return count;
    }

    /**
     * 出卷子的方法数
     * arr[7 3 5 3] 第一套试卷难度7， 第二套试卷难度3, 第三套试卷难度5 第四套试卷难度3...
     * 要求： 出一套卷子，任何相邻的试题，前一套试题难度不能超过后面难度加 m，满足这样要求的出卷子方法有哪些
     *
     * 从左往右的尝试模型，
     * 检查已经满足要求的数组中，有多少数是满足 x >= (num - m)
     *
     * 将数组进行排序，该操作很重要 ， 将数组从小到大排好序后，任意[0...i]这一组都是满足要求的出卷子方式，在新来一个数字，可以直接将该数字放置数组最后，就是一套满足要求的试卷；
     * 或者将该数放置在(x-m)前面也是满足要求的试卷
     */

    // 全排列方法
    public static int ways1(int[] arr, int m) {
        return process10(arr, 0, m);
    }

    public static int process10(int[] arr, int index, int m) {
        if (index == arr.length) {  // 下标到了数组最后，验证这一组数据是否满足出卷子的要求
            for (int i =0; i < arr.length; i++) {
                if (arr[i-1] > arr[i] + m)
                    return 0;
            }
            return 1;
        }
        int ans = 0;
        for (int i = index; i < arr.length; i++) {
            swap(arr, i, index);
            ans += process10(arr, index+1, m);
            swap(arr, i, index);
        }
        return ans;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * 打家劫舍问题
     * 在不能取相邻数的情况下，子序列的最大累加和问题
     *
     * 从左往右的尝试模型，来到[i]位置，该位置要或者不要取决于前一个位置，如果前一个位置保留，那么i位置不要，index+1继续判断； 如果i位置要，前面的sum+[i]，继续index+i判断
     * 
     */

    public static int sum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }

        if (arr.length == 2) {
            return Math.max(arr[0], arr[1]);
        }

        int dp[] = new int[arr.length];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);
        for (int i =2; i < arr.length; i++) {
            int p1 = arr[i];
            int p2 = dp[i-1];
            int p3 = dp[i-2] + arr[i];
            dp[i] = Math.max(p1, Math.max(p2, p3));
        }
        return dp[arr.length -1];
    }


    /**
     *  查询名人
     *  给一个know(a, b) 一个布尔类型函数，表示a是否认识b，认识返回true; 不认识返回false
     *  规则： 明星不认识任何人，任何人都认识明星， 满足这样要求的就是明星，要求在数组中找到这个明星，如果存在的话; 如果不存在返回-1
     */

    /**
     *  完全平方数
     *
     *  一个数最少能拆成几个平方数的和
     *
     *  暴力方法，从2*2开始枚举判断 2*2   3*3   4*4   5*5 ...
     *
     */

    public static int numSquares(int n) {
        int ans = n;
        int num = 2;
        while (num * num < n) {
            int a = n / (num * num), b = n % (num * num);
            ans = Math.min(ans, numSquares(b) + a);
            num++;
        }
        return ans;
    }

    /**
     *  把01字符串切分成多个部分，要求每一个部分的0和1比例一样， 同时要求尽可能多的划分
     *  比如： 0101010
     *  1) 01 01 01 01
     *  2) 0101 0101
     *  最好的答案是1),  比例是1：1 , 并且划分了4部分
     *
     */

    public static int[] split(int[] arr) {
        HashMap<Integer, HashMap<Integer, Integer>> pre = new HashMap<>();
        int[] ans = new int[arr.length];    // 记录[0..i]每一个位置能等比例切分的数量
        int zero = 0;
        int one = 0;
        for (int i =0; i < arr.length; i++) {
            if (arr[i] == 0)
                zero++;
            else
                one++;
            if (zero == 0 || one == 0) {
                ans[i] = i+1;
            } else {
                int gcd = gcd(zero, one);
                int a = zero / gcd;
                int b = one / gcd;
                if (!pre.containsKey(a)) {
                    pre.put(a, new HashMap<>());
                }

                if (!pre.get(a).containsKey(b)) {
                    pre.get(a).put(b, 1);
                } else {
                    pre.get(a).put(b, pre.get(a).get(b) + 1);
                }
                ans[i] = pre.get(a).get(b);
            }
        }
        return ans;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    /**
     * 给定一个字符串str，和一个正数k
     * 返回长度为k的所有子序列中，字典序最大的子序列
     *
     * 单调栈问题
     * 1) 栈中字符数量 >= k
     * 2) 栈中字符数量 + arr[i...n]长度 < k
     */

    public static String maxSubSequence(String str, int k) {
        char[] s = str.toCharArray();
        char[] stack = new char[s.length];
        int size = 0;

        // 单调栈： 从栈底到栈顶元素一次递增
        for (int i =0; i < s.length; i++) {
            // 如果栈中有元素
            // 并且 栈顶元素小于 s[i]
            // 并且 栈中元素个数 + s数组中剩余的字符数量 > k   才可以弹出元素
            while (size > 0 && stack[size-1] < s[i] && (size + s.length -i) > k) {   // 将栈中元素弹出的条件
                size--;
            }
            if (size + s.length - i == k) {
                return String.valueOf(stack, 0, size) + str.substring(i);
            }

            stack[size++] = s[i];
        }
        return String.valueOf(stack, 0, k);
    }


    /**
     * 删除S中的字符
     *   给定一个只由0和1组成的字符串，假设下标从1开始，规定i位置的字符价值V[i]计算方式如下：
     *   1) i == 1, V[i] = 1;
     *   2) i > 1, S[i] != S[i-1], V[i] = 1
     *   2) i > 1, S[i] == S[i-1], V[i] = V[i-1] + 1
     *   可以随意删除字符，返回S的最大价值
     *
     *   动态规划： 从左往右的尝试模型
     */

    // s由0和1组成的字符串
    // lastNum: 上一次保留的字符： 0 或者 1
    // baseValue: 上一次保留的字符，所携带的价值
    public static int maxValue(int[] arr, int index, int lastNum, int baseValue) {
        if (index == arr.length)
            return 0;

        int curValue = arr[index] == lastNum ? (baseValue+1) : 1;
        // 保留当前字符，后续产生的最大价值
        int next1 = maxValue(arr, index+1, arr[index], curValue);
        // 不保留当前字符，后续产生的最大价值
        int next2 = maxValue(arr, index+1, lastNum, baseValue);
        return Math.max(next1 + curValue, next2);
    }

    /**
     * 全消子序列的最大长度
     *  一个子序列的消除规则：
     *  1) 在某一个子序列中，如果'1'的左边是'0'， 那么这两个字符'01'可以消除
     *  2) 在某一个子序列中，如果'3'的左边是'2'， 那么这两个字符'23'可以消除
     *  在一个由0 1 2 3四种字符组成的字符串中，可以生成很多子序列，返回全消子序列的最大长度
     *
     *  动态规划： 从左往右的尝试模型 
     */

    /**
     * 一个无序数组，所有数字都不一样，并且值都在[0...n-1]范围上
     * 返回让这个无序数组变成有序数组的最小交换次数
     *
     * 数组长度是8， 那么数组中值在0...7之间     数组和下标的关系是arr[i] = i
     *
     * 下标循环怼
     *
     * 数组元素离散化
     *
     */

    // 前提： arr[]中的元素范围在[0...n-1]之间，并且每个数字都只出现1次
    public static int minSwapCount(int[] arr) {
        int count = 0;
        int index = 0;
        while (index < arr.length) {
            if (index != arr[index]) {
                swap(arr, arr[index], index);
                count++;
            } else {
                index++;
            }
        }
        return count;
    }

    public static int minSwap2(int[] arr) {
        int ans = 0;
        for (int i =0; i < arr.length; i++) {
            while (arr[i] != i) {
                swap(arr, i, arr[i]);
                ans++;
            }
        }
        return ans;
    }

    /**
     * 下一个排列
     *  比如： ...476321 的下一个全排列， ...612347
     *
     *  找到第一个降序的索引位置 4的索引位置index, 4后面的排列已经是最大的排列76321
     *  找到第一个大于4的位置，和4进行交换，...674321， 将6后面的排列进行逆序 ，就是下一个全排列
     *
     */
    public static void nextPermutation(int[] nums) {
        int n = nums.length;
        int firstLess = -1;
        for (int i = n-2; i >= 0; i--) {  // 找到第一个降序的位置
            if (nums[i] < nums[i+1]) {
                 firstLess = i;
                 break;
            }
        }

        // firstLess是这组全排列中第一次降序的索引位置

        if (firstLess == -1) {
            reverse(nums, 0, n-1);
        }  else {
            // 从[firstLess...N]找到大于nums[firstLess]并且离nums[firstLess]最近的位置
            int firstMore = -1;
            for (int i = n - 1; i > firstLess; i--) {
                if (nums[i] > nums[firstLess]) {
                    firstMore = i;
                    break;
                }
            }
            swap(nums, firstLess, firstMore);
            reverse(nums, firstLess + 1, n-1);
        }
    }

    private static void reverse(int[] nums, int L , int R) {
        while (L < R) {
            swap(nums, L, R);        
            L++;
            R--;
        }
    }

    /**
     * 执行X和Y操作让累加降到0以下的最小代价
     *
     * X: 变成0
     * Y: 变成负数
     *
     * 暴力方式：process(int index, int sum)
     */
    public static int minCost(int[] arr, int x, int y) {
        int sum = arr[0];
        for (int i =1; i < arr.length; i++) {
            sum += arr[i];
        }
        return process11(arr, 0, sum, x, y);
    }

    // 暴力方法： nums[index...] 自由选择： 不执行任何操作； 执行x操作； 执行Y操作， 使sum降至0以下的最小代价
    public static int process11(int[] arr, int index, int sum, int x, int y) {
        if (sum <= 0) {
            return 0;
        }
        if (index == arr.length) {   // 已经到达数组末尾，但是sum也没有降至0以下
            return Integer.MAX_VALUE;
        }

        // 不采用任何方法
        int p1 = process11(arr, index+1, sum, x, y);
        // 采用x方法
        int p2Next = process11(arr, index + 1, sum - arr[index], x, y);
        int p2 = Integer.MAX_VALUE;
        if (p2Next != Integer.MAX_VALUE)
            p2 = p2Next + x;
        // 采用y方法
        int p3Next = process11(arr, index + 1, sum - 2 * arr[index], x, y);
        int p3 = Integer.MAX_VALUE;
        if (p3Next != Integer.MAX_VALUE)
            p3 = p3Next + y;

        return Math.min(p1, Math.min(p2, p3));
    }

    //
    public static int minCost2(int[] arr, int x, int y) {
        Arrays.sort(arr);
        int n = arr.length;
        for (int l =0, r = n-1; l <= r; l++, r--) {
            swap(arr, l, r);
        }
        if (y <= x) {
            int sum = arr[0];
            int count  = 0;
            for (int i =1; i < n; i++) {
                sum += arr[i];
            }
            for (int i = 0; i < n && sum > 0; i++) {
                sum -= (arr[i] << 1);
                count += y;
            }
            return count;
            
        } else {
            int[] suffixSum = new int[n];
            suffixSum[n-1] = arr[n-1];
            for (int i =n-2; i >= 0; i--) {
                suffixSum[i] = arr[i] + suffixSum[i+1];
            }
            int benefit = 0;
            int left = mostLeft(suffixSum, 0, benefit); // 在suffixSum[0...N]位置上查找小于等于benefit的最左位置
            int count = left * x;
            for (int i =0; i < n; i++) {
                benefit +=  arr[i];
                left = mostLeft(suffixSum, i+1, benefit);
                count = Math.min(count, (i+1) * y + (left - i -1)*x);
            }
            return count;
        }
    }

    private static int mostLeft(int[] arr, int L, int v) {
        int R = arr.length-1;
        int m = 0;
        int ans = arr.length;
        while (L <= R) {
            m = (R+L)/2;
            if (arr[m] <= v) {
               ans = m;
               R = m-1;
            }  else
                L = m+1;
        }
        return ans;
    }

    /**
     *  两个窗口问题
     *  
     */

    public static int subArrayWithDistinct(int[] arr, int k) {
        int equalKind = 0;
        int lessKind = 0;
        int equalLeft = 0;
        int lessLeft = 0;
        int n = arr.length;
        int[] equalCounts = new int[n];
        int[] lessCounts = new int[n];
         int ans = 0;
        for (int r = 0;  r < n; r++) {
            if (equalCounts[arr[r]] == 0) {
                equalKind++;
            }
            if (lessCounts[arr[r]] == 0) {
                lessKind++;
            }
            equalCounts[arr[r]]++;
            lessCounts[arr[r]]++;

            while (equalKind > k) {
                if (equalCounts[arr[equalLeft]] == 1) {
                    equalKind--;
                }
                equalCounts[arr[equalLeft++]]--;

            }

            while (lessKind > k-1) {
               if (lessCounts[arr[lessLeft]] == 1) {
                   lessKind--;
               }
               lessCounts[arr[lessLeft++]]--;
            }
            ans += (lessLeft - equalLeft);
        }
        return ans;
    }

    /**
     *  魔法积木问题   
     */

    /**
     *  arr={5, 3, 1, 4}
     *
     *  全部数字对：(5,3) (5,1) (5,4) (3,1) (3,4) (1,4)
     *  绝对值(排序): 1 1 2 2 3 4
     *  给定一个数组arr, 在给一个数字k，返回arr中所有数字对差值的绝对值，第k小的是多少
     *
     *  arr排序 {1,3,4,5}      数字对差值在[0-4]之间
     *
     *  差值绝对值第k小， 二分??
     *
     */


    /**
     *  等差数列划分
     *  数组arr[], 能够成为等差数列的子序列有多少个? 等差数列的长度大于2
     *  以每一个数结尾情况下，和前面的数做差值，将每一个差值记录到map表中，
     */

    /**
     * 灯泡问题
     *
     */


    /**
     *  洪水问题
     *  有n个湖泊，所有湖泊一开始时空的，当第n个湖泊下雨的时候，如果第n个湖泊时空的，那么他装满水，否则这个湖泊会发生洪水，目标时避免任意一个湖泊发生洪水
     *  rains[i]数组，rains[i] > 0 表示第i天，第rains[i]个湖泊会下雨； rains[i]表示第i天没有湖泊会下雨，可以选择抽干一个湖泊的水
     *  返回一个数组ans， 满足ans[i]是第i天选择抽干的湖泊，如果有多个可行解，请返回他们中的任意一个，如果没有办法阻止洪水，请返回一个空的数组
     *
     *  LinkedList：  单链表， 4: {1,3,5,11} 4好湖泊在第1、3、5、11天下雨
     *
     */



    
    
    


    




    



    
    
    


    
    
    

    // 字符串交错组成
    // 字符串s1 和 字符串s2 能否交错组成出字符串s3
    public static boolean can(String s1, String s2, String s3) {
        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();
        char[] ch3 = s3.toCharArray();
        int n = ch1.length;
        int m = ch2.length;
        boolean[][] dp = new boolean[n+1][m+1];
        dp[0][0] = true;
        for (int i =1; i < n; i++) {
            if(ch1[i-1] != ch3[i-1])    // str1取前i长度的字符串如果和str3前i长度的字符串相同就可以交错组成
                break;
            dp[i][0] = true;
        }

        for (int j =1; j < m; j++) {
            if (ch2[j-1] != ch3[j-1])    // str2取前j长度的字符串如果和str3前j长度的字符串相同就可以交错组成
                break;
            dp[0][j] = true;
        }

        for (int i =1; i < n; i++) {
            for (int j =1; j < m; j++) {
                if(ch1[i-1] == ch3[i+j-1] && dp[i-1][j] ||
                    ch2[j-1] == ch3[i+j-1] && dp[i][j-1]) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[n][m];
    }

    static class Node{
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    //  二叉树中求有多少棵相等子树
    // 二叉树的递归套路
    // 左树的相等子树个数 + 右树的相等子树个数 + (如果以X为头的树自己本身也是相等子树+1， 否则+0)
    // 时间复杂度： o(n * logN)
    public static int number(Node x) {
        if (x==null) {
            return 0;
        }
        // 左树相等子树数量
        int left = number(x.left);
        // 右树相等子树数量
        int right = number(x.right);

        // 如果x的左树和x的右树相等+1， 否则+0
        return left + right + (same(x.left, x.right) ? 1 : 0);
    }

    // 判断left为头的树 和 right为头的树 结构书否相同
    public static boolean same(Node left, Node right) {
        if (left == null ^ right == null)    // left != null && right == null 返回false; left == null && right != null 返回false
            return false;
        if (left == null && right == null)
            return true;

        // left != null && right != null的情况
        // value 值要相同
        return left.value == right.value && same(left.left, right.left) && same(right.right, left.right);
    }

    // 大于等于n的最小的2某次方
    public static int near2Power(int value) {
        if (value <= 0)
            return 1;
        value--;
        value |= (value >>> 1);
        value |= (value >>> 2);
        value |= (value >>> 4);
        value |= (value >>> 8);
        value |= (value >>> 16);
        return value+1;

    }


    public static void main(String[] args) {
//        System.out.println(tableSizeFor(36));
//        System.out.println(near2Power(36));
//        System.out.println(reverseBits1(35));
//        System.out.println(reverseBits2(35));
      //  System.out.println(countOne(20));

        System.out.println(1/Integer.MIN_VALUE);
        int[] arr = {2,2,3};
        int i = minPeople(arr);
        System.out.println(i);

    }


    public static int reverseBits1(int n) {
        n = ((n & 0xffff0000) >>> 16) | ((n & 0x0000ffff) << 16);
        n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc)  >>> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xaaaaaaaa)  >>> 1) | ((n & 0x55555555) << 1);
        return n;
    }

    public static int reverseBits2(int n) {
        n = (n >>> 16) | (n << 16);
        n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc)  >>> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xaaaaaaaa)  >>> 1) | ((n & 0x55555555) << 1);
        return n;
    }

    // 返回一个数二进制中有几个1
    public static int countOne(int n) {
        int count= 0;
        while (n != 0) {
            n ^= (n & (-n));
            count++;
        }
        return count;
    }

    static final int MAXIMUM_CAPACITY = 1 << 30;
    static final int tableSizeFor(int cap) {
        int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

}
