package com.chenjunyi.GO;

import java.util.Arrays;

/**
 *  二分答案法
 *  分析单调性，建立f函数  很重要也很常用
 *
 *  1、珂珂吃香蕉问题，
 *
 *  2、分割数组的最大值，给定一个非负整数数组nums和一个整数m，需要将这个数组分成m个非空的连续子数组
 *   设计一个算法使得这m个子数组各自和的最大值最小
 *       (也叫画家问题， 每个画家处理一批油画，求所有画家处理完所有油画的最短时间)
 *
 *   例如： 6 4 5 5  m=2， 也就是分成2个子数组
 *      可能的结果有 [6] [4 5 5]  每个子数组的和是6、14    画家处理完所有油画的时间就是14
 *                  [6 4] [5 5]  每个子数组的和是10、10  画家处理完所有油画的时间就是10， 相比之前的6、14 这个划分更好，画家可以在更短的时间内处理完所有的油画
 *                  [6 4 5] [5]  每个子数组的和是15、5   最大的累加和是15， 不如上面的最大累加和10
 *     每部分累加和的最大值尽量小***************************
 *
 *    使用二分法，子数组累加和有个范围，范围是[min, sum] min是数组中的最小值，sum是整个数组的累加和
 *     l = min, r = sum, mid = (r+l)/2
 *    要求在数组arr上划分子数组，要求每一部分的子数组累加和都不超过mid的值，问可以划分成几个部分
 *
 *    是否有单调性?
 *    如果mid=500, 数组划分的部分是a个
 *    如果mid=600，数组划分的部分是b个， 那么b <= a
 *    如果mid=700，数组划分的部分是c个， 那么c <= b ...  所以具有单调性
 *    累加和变大，返回值会变小或者不变
 *
 *    如果累加和是500的时候，数组划分的部分是a个，并且a<=m 说明累加和是500的时候满足条件，
 *    那么累加和是600或者700等更大的值也会满足条件，所以记录答案500，并且向左侧更小的累加和判断是否仍然能划分出<=m个子数组来
 *
 *    但是如果500的时候划分出来的子数组个数 >m， 那么需要向右侧查找更大的累加和是否能划分出m个子数组，满足累加和的条件
 *
 *    
 *  3、机器人跳跃问题
 *  机器人正在玩一个古老的基于DOS的游戏，游戏中有N+1座建筑，从0到N编号，从左到右排列编号为0的建筑高度为0个单位，编号为i的建筑的高度为H(i)个单位
 *  起初，机器人在编号为0的建筑处，每一步它跳到第k+1个建筑
 *  它将会得到或者时去正比于H(K+1)与E之差的能量
 *  如果H(K+1) > E 那么机器人就失去H(K+1)-E的能量值
 *  否则它将得到E-H(k+1)的能量值
 *  游戏目标是到达第N个建筑，在这个游戏中，能量值不能为负数个单位
 *  现在的问题是机器人以多少能量值开始游戏，才可以保证成功完成游戏
 *
 *   比如初始能量15， [10 30]  那么到达10时，可以获得(15-10)的能量15+5=20能量
 *                            到达30时，失去(30-20)的能量20-10=10能量
 *
 *
 *  4、找到第k小的数对距离
 *   任意两个数的差值右一个范围[0, max-min]数组中的最大值-最小值
 *   int m = (r+l)/2
 *
 *   定义一个函数，求在数组arr中 任意两个数的差值<=m的个数， 有几个数对的差值是<=m的
 *   如果f()返回的结果 < k， 说明差值定小了，向右侧扩大差值；
 *   如果f()函数返回的结果>=k, 可以记录一个答案m, 在左侧范围上继续二分查找
 *
 *   现在的问题是f() 函数如何定义
 *
 *  5、同时运行N台电脑的最长时间
 *   有n台电脑，整数n和一个下标从0开始的整数数组batteries，其中第i个电池可以让一台电脑运行batteries[i]分钟
 *   想使用这些电池让全部n台电脑同时运行
 *   请返回可以让n台电脑同时运行的最长分钟数
 *
 *      电脑运行的分钟数有一个范围[0, sum] batteries数组的累加和， 在这个范围上进行二分计算，
 *      比如 mid是让每台电脑运行的分钟数，经过f(arr, num, mid)计算所有电池中让num台电脑，
 *      每台电脑运行mid分钟，是否可以满足，如果mid分钟可以满足，那么更少的时间也一定是满足的mid-1,mid-2...都是可以的
 *      所以，满足的情况下，记录下一个答案ans=mid， 在右边更多的时间是否还可以满足?
 *           不满足的情况下，则向左边继续二分计算，更少一点的时间这些电池是否可以让num台电脑同时运行呢？
 *
 *      f(arr, num, time): 让num台电脑同时运行time时间，arr个电池是否可以满足，满足返回true；否则返回false
 *        1、如果arr[i]单个电池的电量大于time， 那么就让他负责一台电脑，num-1
 *        2、如果剩下的电池电量不足以负责一台电脑，即arr[i]<time， 那么就将这些小的电池电量进行累加，如果sum >= (time * num), 那么经过各种排列组合，也一定能够满足num台电脑同时运行time时间
 *      以上两种情况都会使f() 返回 true
 *
 *  6、计算等位时间，
 *      给定一个数组arr长度为n,表示n个服务员，每服务一个人的时间
 *      给定一个正数m, 表示m个人等位，如果你是刚来的人，请问需要等多久？
 *      m远大于n,   也就是等位的人数远大于服务员的人数
 *
 *      解法1：使用小根堆   但是m比较大时，时间复杂度会很高
 *      解法2： 服务时间是有范围的
 *
 *  7、刀砍毒杀怪兽问题
 *      怪兽初始血量hp， cuts[]和poison[] 刀砍数组，和毒杀数组，每个回合可以使用一种技能
 *      刀砍直接减少血量
 *      毒杀当前回合不减少血量，但是后面的每一回合都会减少poison[i]血量
 *      至少多少回合可以杀死怪兽
 *
 *   回合数量有个范围[1,hp+1]
 *
 */
public class TwoPoints {
    // 等位时间
    public static long waitTime(int[] arr, int m) {
        int l = 0;
        int r = 0;
        int max = arr[0];
        for (int i =1; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }

        r = max * m;   // 服务时间范围r也可以是min*m, 为什么是min? 即arr[]数组中的最小值
        int ans = 0;
        while (l <= r) {
            int mid = l + ((r-l)>>1);
            if (people(arr, mid) >= m+1) {
                ans = mid;
                r = mid-1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    private static int people(int[] arr, int time) {
        int ans = 0;
        for (int v : arr) {
            ans += ((time/v)+1);
        }
        return ans;
    }



    // 同时运行n台电脑
    public static long compute(int[] batteries, int n) {
        long l = 0;
        long r = batteries[0];
        for (int i =1; i < batteries.length; i++) {
            r += batteries[i];
        }
        // 其实r 是否可以是sum/n? [0,sum] -?-> [0, sum/n]

        long ans = 0;
        while (l <= r) {
            long mid = l + ((r-l)>>1);
            if (allow(batteries, n, mid)) {  // mid时间能满足，向右侧查找更大的时间
                ans = mid;
                l = mid +1;
            } else {
                r = mid -1;
            }
        }
        return  ans;
    }

    // 判断让n台电脑同时运行time时间，batteries[]电池是否能满足
    public static boolean allow(int[] batteries, int n, long time) {
        long sum = 0;
        for (long battery : batteries) {
            if (battery >= time)     // 大电池
                n--;
            else
                sum += battery;      // 小电池
            if (sum >= (long)(time * n))
                return true;
        }
        return false;
    }


    // 数对距离    排序的复杂度：O(n * log(N))  二分的时间复杂度： O(N * log(max-min))
    public static int diff(int[] arr, int k) {
        Arrays.sort(arr);    // n * log(N)
        int l = 0;
        int r = arr[arr.length-1] - arr[0];
        int ans = 0;
        while (l <= r) {       // O(log(max-min))
            int m = l + ((r-l)>>1);
            if (pair(arr, m) < k) {      // O(n)
                l = m + 1;
            } else {
                ans = m;
                r = m - 1;
            }
        }
        return ans;
    }

    // 数组arr中，任意两个数的差值<=diff的个数， 返回
    // [1,2,2,3,3,4,5,8,9]     diff = 3   任意两个数的差值<=3的个数有多少
    private static int pair(int[] arr, int diff) {
        int ans = 0;
        for (int l =0, r = 0; l < arr.length; l++) {   // 窗口 以每一个位置为开头，能向右侧扩充多少 使得arr[r]-arr[l] <= diff
            while (r+1 < arr.length && arr[r+1]-arr[l] <= diff) {
                r++;
            }
            // arr[l...r] 范围上的数值差 是<=diff的
            ans += (r-l);
        }
        return ans;
    }

    // 机器人跳跃问题
    public static int robot(int[] arr) {
        int l = 0;
        int r = arr[0];
        for (int i =1; i < arr.length; i++) {
            r = Math.max(r, arr[i]);
        }

        // max的作用是? 为了防止数据类型溢出的情况， 如果能量在累加过程中已经达到了arr中的高度最大值，那么可以不用在累加了，一定会走到最后的
        // max 参数很重要， 有可能在数组很大，初始能量定义的很大，但是数组中的数值相对较小，这样在每次累加过程中可能会超过INT_MAX或者LONG_MAX, 出现不可预知的错误
        // 所以需要max作为拦截，如果energy在累加过程中>=max了， 那么可以直接返回了，一定会通过数组中的所有数据
        int max = r;
        int ans = -1;
        while (l <= r) {
            int mid = l + ((r-l) >>1);
            if (pass(arr, mid, max)) {
                ans = mid;
                r = mid-1;
            } else {
                l = mid+1;
            }
        }
        return ans;
    }

    // 初始值是init， 在通过arr最后一个元素后， 能够保持init不出现负数
    // 规则是init每到达一个arr[i]， 如果arr[i] < init, init可以加上差值； 如果arr[i] > init, init需要减去差值
    // 要保证在整个过程中init不出现负数，就返回true， 否则返回false
    private static boolean pass(int[] arr, int init, int max) {
        for (int i =1; i < arr.length; i++) {
            if (init < arr[i])
                init -= (arr[i]-init);
            else
                init += (init - arr[i]);

            if (init >= max) {
                return true;
            }
            if (init < 0)
                return false;
        }
        return true;
    }


    // 分割数组  时间复杂度O(log(sum) * n)   空间复杂度O(1)
    public static int minSum(int[] arr, int k) {
        int l = 0;
        int r = arr[0];
        for (int i =1; i < arr.length; i++) {
            r += arr[i];
        }
        long ans = 0;
        while (l <= r) {      // log(sum) * n
            int mid = l + ((r-l)>>1);
            if (process(arr, mid) <= k) { // 达标，记录答案， 左侧二分
                ans = mid;
                r = mid -1;
            } else {
                l = mid +1;
            }
        }
        return (int)ans;
    }

    // 将数组arr，按照每个子数组的最大累加和<=aim进行划分，可以将数组划分成几个部分
    private static int process(int[] arr, int aim) {
        int parts = 1;
        int sum = 0;
        for (int num : arr) {
            if (num > aim)
            return Integer.MAX_VALUE;  // 单个数值已经超过了aim，返回最大值，表示此时的aim 数组无法划分
            if (sum + num <= aim) {
                sum += num;
            } else {
                parts++;
                sum = num;
            }
        }
        return parts;
    }

    // 时间复杂度： (n * log(max))
    public static int speed(int[] arr, int h) {
        int r = arr[0];
        for (int i =1; i < arr.length; i++) {
            r = Math.max(r, arr[i]);
        }
        int l = 1;
        int ans = 0;
        while (l <= r) {
            int mid = l + ((r-l)>>1);
            if (isEnough(arr, mid) <= h) {
                ans = mid;
                r = mid -1;
            } else {
                l = mid+1;
            }
        }
        return ans;
    }

    // 香蕉重量都在arr数组中，速度是mid, 返回吃完所有香蕉耗费的时间
    // 向上取整a/b  ---> (a+b-1)/b
    private static int isEnough(int[] arr, int mid) {
        int ans = 0;
        for (int v : arr) {
            ans += (v+mid-1)/mid;
        }
        return ans;
    }

}
