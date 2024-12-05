package com.chenjunyi.project;

/**
 * 最多购买宝石数量
 * arr[]每个元素代表对应位置宝石的价格，按照要求购买宝石，在不超过最大价值value的情况下，购买最多的宝石数量是多少
 * 宝石可以购买0个可以购买多个，但是购买多个的宝石必须是连续的
 *
 *  滑动窗口解决
 */
public class BaoShiProblem {


    public static int maxCount(int[] arr, int value) {
        int l = 0, r = 0;
        int n = arr.length;
        int currValue = 0;
        int res = 0;
        outer:
        while (r < n) {
            currValue += arr[r];
            if (currValue <= value) {
                r++;
            } else {    // [l,r)
                res = Math.max(res, r-l);
                while (l < r) {
                    currValue -= arr[l++];
                    if (currValue <= value) {
                        r++;
                        continue outer;
                    }
                }

                // 如果执行到这步，是因为l>=r，而不是currValue <= value， 那么说明arr[l]这个宝石自己就超过了总价值，需要跳过
                l = r+1;
                r++;
                currValue=0;
            }
        }
        // arr[]最后一个元素累加到了curValue变量上，但是还并没有计算res，就出现了r<n 推出了循环，需要在此做一个操作，将最后一个位置的值算进来
        if (currValue <= value)
            res = Math.max(res, r-l);
            
        return res;
    }




}
