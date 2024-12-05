package com.chenjunyi.Strings;

/**
 *  整数反转
 *
 */
public class ReverseInteger {
    

    public int reverse(int x) {
        int ans = 0;
        while (x!=0){
            int pop = x % 10;
            // 边界条件判断
            if(ans>Integer.MAX_VALUE/10 || (ans==Integer.MAX_VALUE/10 && pop >7)){
                return 0;
            }
            if(ans<Integer.MIN_VALUE/10 || (ans==Integer.MIN_VALUE/10 && pop < -8)){
                return 0;
            }
            ans = ans *10+pop;
            x=x/10;
        }
        return ans;
    }

    public static int reverse1(int x) {
        int ans = 0;
        int max_limit = Integer.MAX_VALUE/10;
        int min_limit = Integer.MIN_VALUE/10;
        int max_mode = Integer.MAX_VALUE % 10;
        int min_mode = Integer.MIN_VALUE % 10;
        while (x != 0) {
            int pop = x % 10;    // 每次通过%10 取出数字的最后一位
            if (ans > max_limit || (ans == max_limit && pop > max_mode)) {
                return 0;
            }
            if (ans < min_limit || (ans == min_limit && pop < min_mode)) {
                return 0;
            }
            ans = ans * 10 + pop;
            x = x / 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(reverse1(1534236469)); // 末尾是7
        System.out.println(Integer.MIN_VALUE);  // 末尾是-8
        System.out.println(-214748364 % 10);
    }

}
