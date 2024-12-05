package com.chenjunyi.force;

import org.openjdk.jol.info.ClassLayout;

/**
 *
 * 4、寻找业务限制的尝试模型
 *
 *
 * 给定一个数组，代表每个人喝完咖啡准备刷杯子的时间
 * 只有一台咖啡机，一次只能洗一个杯子，时间耗费a, 洗完才能洗下一个杯子，
 * 每个咖啡杯也可以自己挥发干净，时间耗费b, 咖啡杯可以并行挥发
 * 返回让所有咖啡杯变干净的最早完成时间
 */
public class TryModel4 {
    public static int coffe(int[] arr, int a, int b) {
        // 如果挥发的时间比洗杯子的时间还短
        if (a >= b) {
            int sum = 0;
            for (int i =0; i< arr.length; i++) {
                sum += arr[i] + b;

            }
            return sum;
        }

        return process(arr, a, b, 0, 0);


    }

    // arr[] 有一组咖啡杯需要洗，a为咖啡机洗杯子的时间， b为挥发干净的时间，
    // 处理[0..i-1]个杯子耗时time的时间
    private static int process(int[] arr, int a, int b, int i, int time) {
        // 没有杯子可洗了
        if (i == arr.length) {
            return time;
        }

        // 当前i 一杯咖啡杯用咖啡机洗完结束的时间点
        int wash = Math.max(time, arr[i]) + a;
        
        // 在当前杯子用咖啡机洗完的时间wash情况下，后续杯子干净的最早时间
        int next1 = process(arr, a, b, i+1, wash);
        int p1 = Math.max(wash, next1);

        //   当前咖啡杯自然挥发的时间
        int dry = arr[i] + b;
        // 在当前杯子用自然挥发 time 情况下，后续杯子干净的时间
        int next2 = process(arr, a, b, i+1, time);
        int p2 = Math.max(dry, next2);

        return Math.min(p1, p2);
    }

    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        System.out.println(o.hashCode() +"   " +  Integer.toBinaryString(o.hashCode()));
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());

        }
    }
}
