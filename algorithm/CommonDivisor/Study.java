package com.chenjunyi.CommonDivisor;

/**
 *  最大公约数 和 同余原理
 *
 *  最大公约数: 辗转相除法       common divisor
 *  
 *  b == 0 ? a : gcd(b, a%b);
 *
 *  最大公约数
 *  能够 2*(1...n) 所以第n个神奇的数字，最右侧范围就是2*n, 左侧范围是2*1  [2*1 ... 2*n]
 *
 * 最小公倍数 = (a / 最大公约数) * b
 *   x/a + x/b - x/(a和b的最小公倍数) : 容斥原理
 *
 *   
 *  同余原理
 *   1、加法、乘法每一步计算完后直接取模， 减法是(a-b+m)%m
 *   2、要确保 计算过程中不溢出，所以一般使用long类型做中间值
 *   3、除法的同余原理，需要特殊处理
 *
 *   k位整数  '+' / '-' 运算时间复杂度是o(k), '*' / '/' 运算时间复杂度是O(k^2)
 *
 *   加法和乘法同余原理：
 *   (a+b)+c+d+(e+f+k)+s     (a*b)*c*d*(e*f*k)*s               
 *   每一步计算都%m 最后的结果和整体计算结果%m的值是一样的
 *
 *  减法的同余原理：
 *   一个数%7结果等于-2 等同于 这个数%7的结果等于5
 *   例如： 72 - 18  结果要%7
 *   (72-18) % 7 = 54 % 7 = 5
 *     ||
 *   72%7 - 18%7 = 2 - 4 = -2(不允许最后的结果是负数)   等同于  (72%7 - 18%7 + 7) %7 = (-2+7)%7 = 5  和最后结果%7是一样的
 *   
 *    (a-b)%m   ====> (a%m-b%m+m)%m
 *
 *
 *    CopyOnWriteArrayList: 使用场景： 读多写少的场景
 *    数据一致性： 只能保证数据最终一致性
 *
 *
 *
 *  
 */

public class Study {
}
