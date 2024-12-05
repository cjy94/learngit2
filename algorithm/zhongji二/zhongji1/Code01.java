package com.chenjunyi.zhongji二.zhongji1;


import java.util.Arrays;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;


/**
 * 1、给定一个有序数组arr, 代表数轴上从左到右有n个点arr[0] arr[1]... arr[n-1] 给定一个正数L，
 * 代表一根长度为L的绳子， 求绳子最多能覆盖其中的几个点？
 *
 *  解法1：可以将绳子的最右位置压中数轴上的某个点，往左延伸，看能压中的点数有多少？
 *  O(N*logN)
 *
 *  解法2：滑动窗口      , 维持一个窗口，窗口的大小不能大于绳子的长度
 *  O(N)
 *  
 *  
 *
 */
public class Code01 {

    // 绳子问题
    public static int process1(int[] arr, int n) {
        if (arr==null || arr.length == 0) return 0;
        return 0;

    }


    // 苹果装袋子问题
    public static int process2(int n) {
        // 如果是奇数，则直接返回-1
        if((n & 1) != 0) {
            return -1;
        }
        if (n < 0) return -1;
        // 最先用8号袋子 算一下用几个待子
        int bags8 = n / 8;
        int bag6 = -1;
        // 用8号袋子装 剩下几个苹果
        int rest = n - bags8 * 8;

        // 如果，剩余少于24个则不用试了， 24? 最小公倍数？？？
        while (bags8 >= 0 && rest < 24) {
            // 剩下的苹果用6号袋子装，需要几个袋子
            int restBag6 = minBagBase6(rest);
            // 如果剩下的苹果能正好用6号袋子装，那么返回袋子数量，否则返回-1, 表示剩下的苹果用6号袋子装不下
            if (restBag6 != -1) {
                bag6 = restBag6;
                break;
            }

            // 6号袋子搞定不了，则调整8号袋子的数量
            bags8--;
            rest = n - 8 * bags8;
        }
        return bag6 == -1 ? -1 : (bags8 + bag6);
        

    }


    // 吃草问题， 先手和后手 每次只能吃1\4\16\64份草
    public static String winner(int n) {
        //  0  1  2  3 4
        // 后  先 后 先 先
        if (n < 5) {
            return (n==0 || n== 2) ? "后手" : "先手";
        }
        
        // base 代表先吃1份草
        int base = 1;
        while (base <= n) {
            // 子过程
            if (winner(n-base).equals("后手")) {
                return "先手";
            }

            // 防止base*4 溢出
            if (base > n / 4) {
                break;
            }

            // 每次吃草是4的倍数
            base *= 4 ;
        }
        return "后手";

    }

    // 如果剩余的苹果正好被6整除，则返回袋子数量，否则返回-1
    public static int minBagBase6(int rest) {
        return rest % 6 == 0 ? (rest/6) : -1;

    }


    // "RGGRRGR" 要求左侧全是G 右侧全是R  , 需要更改最少几个字符可以达到标准
    // 预处理数组
    public static int minPaints(String s) {
        char[] str = s.toCharArray();
        int len = s.length();
        int min = Integer.MAX_VALUE;
        // 左侧全是G， 右侧全是R
        // 枚举左侧的部分大小为 l, 右侧大小 n-l
        for (int l =0;l < len; l++) {
            int count = 0;
            if (l == 0) {
                // 统计arr[0...n-1] 一共有多少个G
                for (int i=l; i < len; i++) {
                    if (str[i] == 'G') {
                        count++;
                    }
                }
            }else if(l == len-1) {
                // 统计arr[0...n-1] 一共有多少个R
                for (int i =0; i <= l; i++) {
                    if (str[i] == 'R') {
                        count++;
                    }
                }

            } else {
                // 普遍状态， 统计[0..l] 一共有多少个G, [l+1 ... r] 一共有多少个r
                for (int i = l-1; i >= 0; i--) {
                    // 左侧统计R的数量
                    if (str[i] == 'R') {
                        count++;
                    }
                }
                // 右边统计G的数量
                for (int r = l; r < len; r++) {
                    if(str[r] == 'G') {
                        count++;
                    }

                }


            }
            min = Math.min(count, min);
        }
        return min;
    }


    // 预处理： 对数组进行提前处理
    // 添加一个预处理数组，数组中保存着，从左往右 G的数量，从右往左G的数量
    public static int minPaints1(String s) {
        char[] str = s.toCharArray();
        int n = str.length;
        int[] l = new int[n];
        int[] r = new int[n];
        for (int i =0; i < n; i++) {
            if (str[i] == 'G') {
                if (i-1>=0) {
                    l[i] += Math.max(l[i-1], 1);
                } else {
                    l[i]++;
                }
            }
        }

        for (int i =n-1; i >= 0; i--) {
            if (str[i] == 'G') {
                if (i+1<n) {
                    l[i] += Math.max(l[i+1], 1);
                } else {
                    l[i]++;
                }
            }

        }
        return 0;
    }

    public static int  parenthesis(String s) {
        char[] ch = s.toCharArray();
        int count = 0;
        for (int i =0; i < ch.length; i++) {
            if (ch[i] == '(')  {
                count++;
            } else if (ch[i] == ')') {
                count--;
            }

        }
        return Math.abs(count);
    }

    public static int  parenthesis1(String s) {
        int t = 0;
        int ans = 0;
        for (int i =0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                t++;
            }       else {
                if (t==0) {
                    ans++;
                }         else {
                    t--;
                }
            }
        }
        return t + ans;
    }

    // 滑动窗口
    public static void process3(int[] arr, int k) {
        Arrays.sort(arr);
        int l = 0;
        int r = 1;
        HashSet<String> set = new HashSet<>();
        while (r < arr.length) {
            if(arr[r] - arr[l] == k) {
                set.add(arr[l]+","+arr[r]);
                r++;

            } else if (arr[r] - arr[l] < k) {
                r++;
            }   else {
                l++;
            }
        }
        System.out.println(set);
    }

    public static List<List<Integer>> allPair(int[] arr, int k) {
        HashSet<Integer> set = new HashSet<>();
        for (int i =0; i < arr.length; i++) {
            set.add(arr[i]);
        }

        List<List<Integer>>  res = new ArrayList<>();
        for (Integer cur : set) {
            if (set.contains(cur + k)) {
                res.add(Arrays.asList(cur, cur + k));
            }
        }
        return res;

    }




    public static void main(String[] args) {
        int[] arr = {0,0,2,3,4,1,2,3,7,6,4,9,8};
        process3(arr, 2);
        System.out.println(allPair(arr, 2));
//        for (int i =1; i < 100; i++) {
//            System.out.println("apple: " + i + ", "+process2(i));
//        }
    }


}
