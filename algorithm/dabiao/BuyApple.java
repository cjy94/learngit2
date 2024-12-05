package com.chenjunyi.dabiao;

public class BuyApple {
    // 使用做少的袋子
    // 所以先从8号袋子开始试
    public static int minBags(int n) {
       if (n < 0) return -1;
       int bag6 = -1;
       int bag8 = n / 8;
       int rest = n - 8 * bag8;
       while (bag8 >= 0) {
           int restUse6 = minBagBase6(rest);
           if (restUse6 != -1) {
               bag6 = restUse6;
               break;
           }
           rest = n - 8 * (--bag8);

       }
        return bag6 == -1 ? -1 : bag6+bag8;

    }
    public static int minBagBase6(int rest) {
        return rest % 6 == 0 ? (rest/6) : -1;

    }

    // 使用打表找规律后的代码
    public static int minBaeAwesom(int apple) {
        if((apple & 1) != 0) {
            return -1;
        }

        if (apple < 18) {
            return apple == 0 ? 0 : (apple == 6 || apple == 8) ? 1
                    : (apple == 12 || apple == 14 || apple == 16) ? 2 : -1;

        }
        return (apple-18) / 8 + 3;
    }


    public static String winner1(int n) {
        //  0  1  2  3 4
        // 后  先 后 先 先

        if (n < 5) {
            return (n==0 || n== 2) ? "后手" : "先手";
        }

        // n >= 5时的情况
        int base = 1; // 吃 1份草，
        while (base <= n) {
            if (winner1(n-base).equals("后手")) {
                return "先手";
            }
            if (base > n / 4) {  // 防止 base * 4 之后溢出
                break;
            }
            base *= 4;   // 吃4份草、吃16份草、吃64份草(4的n次幂)......
        }
        return "后手";
    }

    /*
    定义一种数： 可以表示成若干（数量>1）连续正数和的数
    12=3+4+5 是
    5=2+3 是
    2 = 1+1 不是

    暴力求解后，打印1-200的数， 发现只有1，2，4，8，16，32，64，128.... 不满足要求
     */
    public static boolean isSum1(int n) {
        if (n == 0 || n == 1 || n == 2) return false;

        // 依次用1开头累加 1+2 +3 +...
        // 再用2开头 2+3+4+ ...
        for (int i =1; i < n; i++) {
            int sum = i;
           for (int j = i+1; j < n; j++) {
               sum += j;
               if (sum == n) {
                   return true;
               } else if(sum > n) {
                   break;
               }
           }
        }

        return false;
        
    }

    // 2^n 这样的数不满
    public static boolean isSum2(int n) {
        // 2^n 的特征是只有高位时1，其余都是0，
        // 故 n-1 之后，低位都是1
        // 如果满足(n & (n-1) != 0) 说明不是2^n
        // 如果  ((n-1) & n )== 0 是2的某次方
        // 如果 ((n-1) & n ) != 0 不是2的某次方
        if( ((n-1) & n )== 0) {
            return false;
        } else {
            return true;
        }

    }




    public static void main(String[] args) {

        for (int i =1; i <= 200; i++) {
            System.out.println("winner: " + i + ", " + minBags(i));
        }
        //sum(12);

    }
    

}
