package com.chenjunyi.GO.Range;

/**
 *  范围上的尝试模型
 *
 *  纸牌问题
 *  给定一个整形数组arr， 代表数值不同的纸牌拍成一条线。
 *  玩家A和玩家B依次拿走每张纸牌
 *  规定玩家A先拿，玩家B后拿
 *  但是每个玩家每次只能拿走最左或者最右的纸牌
 *  玩家A和玩家B都绝顶聪明，请返回最后获胜者的分数
 */
public class Card {

    public static int win(int[] arr) {
       if (arr == null || arr.length == 0)
           return 0;

       return Math.max(first(arr, 0, arr.length-1), second(arr, 0, arr.length-1));
    }

    // 先手的情况
    private static int first(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }
        return  Math.max(arr[l] + second(arr, l+1, r), arr[r] + second(arr, l, r-1));
    }

    // 后手的情况
    private static int second(int[] arr, int l , int r) {
        if (l == r)
            return 0;
        return Math.min(first(arr, l+1, r), first(arr, l, r-1));
    }
}
