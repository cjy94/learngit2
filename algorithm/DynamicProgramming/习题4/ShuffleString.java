package com.chenjunyi.DynamicProgramming.习题4;

/**
 打乱字符串
  使用下面方法可以打乱字符串s得到字符串t
 步骤1： 如果字符串的长度位1，算法停止；
 步骤2： 如果字符串的长度 > 1，执行下述步骤
    在一个随机下标处将字符串分割成两个非空的字符串
    已知字符串s，则可以将其分成两个字符串x和y， 满足s=x+y
    可以决定是要交换两个子字符串，还是保留这两个字符串的顺序不变
    即s可能s=x+y 或者 s=y+x
    在x和y这两个子字符串上继续从步骤1开始执行此算法
 给你两个长度相等的字符串s1和s2，判断s2是否是s1的扰乱串



 1、不扰乱情况下，两个字符串相等
     s1[0......i][i+1...j]
     s2[0......i][i+1...j]
    其中s1[0......i]和s2[0......i]相等， s1[i+1...j]和s2[i+1...j]相等
 2、扰乱情况下
    s1[0...i][i+1......j]
    s2[0......i][i+1...j]
    其中s1[0...i]和s2[i+1...j]相等， s1[i+1......j]和s2[0......i]相等

 
 
 */
public class ShuffleString {
    public static void main(String[] args) {
        System.out.println(ShuffleString.class.getSimpleName());
    }
}
